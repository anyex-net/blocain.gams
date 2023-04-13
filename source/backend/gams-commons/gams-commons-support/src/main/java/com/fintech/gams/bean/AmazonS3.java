package com.fintech.gams.bean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.*;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.fintech.gams.amazon.*;
import com.fintech.gams.config.Amazon;
import com.fintech.gams.config.Bucket;
import com.fintech.gams.config.GlobalProperies;
import com.fintech.gams.consts.CharsetConst;
import com.fintech.gams.utils.DateUtils;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * 亚马逊文件存储
 * <p>File: AmazonS3.java </p>
 * <p>Title: AmazonS3 </p>
 * <p>Description: AmazonS3 </p>
 * <p>Copyright: Copyright (c) 2019-05-29</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class AmazonS3
{
    @Autowired
    private GlobalProperies                          properies;
    
    public static com.amazonaws.services.s3.AmazonS3 amazonS3;
    
    private static CredentialsProvider               credsProvider;
    
    /**
     * 获取文件上传策略
     *
     * @param dir  上传文件的前缀
     * @param host 上传地址
     * @return
     */
    public Map<String, Object> getPostPolicy(String dir, String host)
    {
        Amazon amazon = properies.getAmazon();
        Map<String, Object> map = Maps.newLinkedHashMap();
        long expireEndTime = System.currentTimeMillis() + 60 * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
        policyConds.addConditionItem(MatchMode.StartWith, "success_action_status", "");
        policyConds.addConditionItem(MatchMode.StartWith, "callback", "");
        policyConds.addConditionItem(MatchMode.StartWith, "name", "");
        policyConds.addConditionItem("bucket", amazon.getBucket().getTempName());
        policyConds.addConditionItem("acl", "public-read");
        String postPolicy = generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = Base64.toBase64String(binaryData);
        String postSignature = calculatePostSignature(postPolicy);
        map.put("dir", dir);
        map.put("host", host);
        map.put("acl", "public-read");
        map.put("expire", expireEndTime);
        map.put("accessid", amazon.getAccessKey());
        map.put("policy", encodedPolicy);
        map.put("signature", postSignature);
        return map;
    }

    public String generatePostPolicy(Date expiration, PolicyConditions conds)
    {
        String formatedExpiration = DateUtils.formatIso8601Date(expiration);
        String jsonizedExpiration = String.format("\"expiration\":\"%s\"", formatedExpiration);
        String jsonizedConds = conds.jsonize();
        StringBuilder postPolicy = new StringBuilder();
        postPolicy.append(String.format("{%s,%s}", jsonizedExpiration, jsonizedConds));
        return postPolicy.toString();
    }
    
    public String calculatePostSignature(String postPolicy)
    {
        try
        {
            if (null == credsProvider)
            {
                Amazon amazon = properies.getAmazon();
                credsProvider = new DefaultCredentialProvider(amazon.getAccessKey(), amazon.getSecretKey());
            }
            byte[] binaryData = postPolicy.getBytes(CharsetConst.CHARSET_UT);
            String encPolicy = Base64.toBase64String(binaryData);
            return ServiceSignature.create().computeSignature(credsProvider.getCredentials().getSecretAccessKey(), encPolicy);
        }
        catch (UnsupportedEncodingException ex)
        {
            log.error("Unsupported charset: " + ex.getMessage());
        }
        return null;
    }
    
    /**
     * 本地上传文件
     *
     * @param path     路径
     * @param fileName 文件名
     */
    public void upload(String path, String fileName)
    {
        try
        {
            Amazon amazon = properies.getAmazon();
            Bucket bucket = amazon.getBucket();
            getAmazonS3().putObject(new PutObjectRequest(bucket.getTempName(), fileName, new File(path)).withCannedAcl(CannedAccessControlList.PublicRead));
        }
        catch (Throwable e)
        {
            log.error("文件上传失败：{} ", e.getLocalizedMessage());
        }
    }
    
    /**
     * 创建虚拟文件夹
     *
     * @param bucketName
     * @param folderName
     */
    public boolean createFolder(String bucketName, String folderName)
    {
        boolean flag = true;
        try
        {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(0);
            InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, emptyContent, metadata);
            getAmazonS3().putObject(putObjectRequest);
        }
        catch (RuntimeException e)
        {
            flag = false;
            log.error("转移文件失败：{} ", e.getLocalizedMessage());
        }
        return flag;
    }
    
    /**
     * 转移临时空间中的文件到正式空间
     * <p>
     * 将真实有性的文件转移到正式空间，转移完毕之后自动清理临时文件。
     * 另：临时空间可做定期清理以减少资料开销
     * </p>
     *
     * @param fileName 文件名
     * @return {@link Boolean}
     */
    public boolean transferObject(String fileName)
    {
        boolean flag = true;
        try
        {
            Amazon amazon = properies.getAmazon();
            Bucket bucket = amazon.getBucket();
            getAmazonS3().copyObject(new CopyObjectRequest(bucket.getTempName(), fileName, bucket.getReleaseName(), fileName)
                    .withCannedAccessControlList(CannedAccessControlList.PublicRead));
            getAmazonS3().deleteObject(bucket.getTempName(), fileName);
        }
        catch (RuntimeException e)
        {
            flag = false;
            log.error("转移文件失败：{} ", e.getLocalizedMessage());
        }
        return flag;
    }

    /**
     * 转移临时空间中的文件到正式空间
     * <p>
     * 将真实有性的文件转移到正式空间，转移完毕之后自动清理临时文件。
     * 另：临时空间可做定期清理以减少资料开销
     * </p>
     *
     * @param sourcePath 资源路径
     * @param destinationPath 目标路径
     * @return {@link Boolean}
     */
    public boolean transferObject(String sourcePath,String destinationPath)
    {
        boolean flag = true;
        try {
            Amazon amazon = properies.getAmazon();
            Bucket bucket = amazon.getBucket();
            // Copy the object into a new object in the same bucket.
            CopyObjectRequest copyObjRequest = new CopyObjectRequest(bucket.getTempName(), sourcePath, bucket.getReleaseName(), destinationPath);
            getAmazonS3().copyObject(copyObjRequest);
            getAmazonS3().deleteObject(new DeleteObjectRequest(bucket.getTempName(), sourcePath));
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            flag = false;
            log.error("转移文件失败：{} ", e.getLocalizedMessage());
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            flag = false;
            log.error("转移文件失败：{} ", e.getLocalizedMessage());
        }
        return flag;
    }

    /**
     * 移动目标文件夹信息到目标容器
     *
     * @param objects
     * @param destinationBucket
     * @return
     */
    public Boolean moveForder(ObjectListing objects, String destinationBucket)
    {
        String bucketName = objects.getBucketName();
        do
        {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries())
            {
                getAmazonS3().copyObject(bucketName, objectSummary.getKey(), destinationBucket, objectSummary.getKey());
                deleteObject(bucketName, objectSummary.getKey());
            }
            objects = getAmazonS3().listNextBatchOfObjects(objects);
        }
        while (objects.isTruncated());
        return true;
    }
    
    /**
     * 删除文件夹内容（必须先遍历删除文件夹内的内容）
     *
     * @param objects
     * @return
     */
    public Boolean deleteForder(ObjectListing objects)
    {
        String bucketName = objects.getBucketName();
        do
        {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries())
            {
                deleteObject(bucketName, objectSummary.getKey());
            }
            objects = getAmazonS3().listNextBatchOfObjects(objects);
        }
        while (objects.isTruncated());
        return true;
    }
    
    /**
     * 获取某个文件（前缀路径）下的所有信息
     *
     * @param bucketName
     * @param prefix
     * @param isDelimiter
     * @return
     */
    public ObjectListing getBacketObjects(String bucketName, String prefix, Boolean isDelimiter)
    {
        if (bucketName == null || bucketName.isEmpty())
        { return null; }
        ListObjectsRequest objectsRequest = new ListObjectsRequest().withBucketName(bucketName);
        if (prefix != null && !prefix.isEmpty())
        {
            objectsRequest = objectsRequest.withPrefix(prefix);
        }
        if (isDelimiter)
        {
            objectsRequest = objectsRequest.withDelimiter("/");
        }
        ObjectListing objects = getAmazonS3().listObjects(objectsRequest);
        return objects;
    }
    
    /**
     * 删除临时空间中的文件
     *
     * @param fileName 文件名
     * @return {@link Boolean}
     */
    public boolean deleteObject(String fileName)
    {
        Amazon amazon = properies.getAmazon();
        Bucket bucket = amazon.getBucket();
        return deleteObject(bucket.getTempName(), fileName);
    }
    
    /**
     * 删除文件
     *
     * @param bucketName 存储桶
     * @param fileName   文件名
     * @return {@link Boolean}
     */
    public boolean deleteObject(String bucketName, String fileName)
    {
        boolean flag = true;
        try
        {
            getAmazonS3().deleteObject(bucketName, fileName);
        }
        catch (RuntimeException e)
        {
            flag = false;
            log.error("删除文件失败：{} ", e.getLocalizedMessage());
        }
        return flag;
    }
    
    /**
     * 删除临时空间中的文件
     *
     * @param bucketName 存储桶
     * @return {@link Boolean}
     */
    public boolean deleteBucket(String bucketName)
    {
        boolean flag = true;
        try
        {
            getAmazonS3().deleteBucket(bucketName);
        }
        catch (RuntimeException e)
        {
            flag = false;
            log.error("删除存储桶失败：{} ", e.getLocalizedMessage());
        }
        return flag;
    }

    /**
     * 生成获取文件预签名URL
     * @param bucketName
     * @param fileName
     * @return
     */
    public String generatePresignedUrl(String bucketName,String fileName)
    {
        String url="";
        try {
            // Set the presigned URL to expire after one hour.
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60;
            expiration.setTime(expTimeMillis);
            // Generate the presigned URL.
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucketName, fileName)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(expiration);
            URL S3Url = getAmazonS3().generatePresignedUrl(generatePresignedUrlRequest);
            url = S3Url.toString();
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            log.error("The call was transmitted successfully, but Amazon S3 couldn't process it, so it returned an error response：{} ", e.getLocalizedMessage());
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            log.error("Amazon S3 couldn't be contacted for a response, or the client couldn't parse the response from Amazon S3：{} ", e.getLocalizedMessage());
        }
        return url;
    }

    /**
     * 上传文件预签名
     * @param 
     * @param dir
     * @param fileName
     * @return
     */
    public String generateUploadObjectPresignedUrl (String bucketName,String dir,String fileName){
        String url="";
        String objectKey = dir +"/"+ fileName;
        try {
            // Set the pre-signed URL to expire after one hour.
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60;
            expiration.setTime(expTimeMillis);

            // Generate the pre-signed URL.
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
                    .withMethod(HttpMethod.PUT)
                    .withExpiration(expiration);
            URL S3Url = getAmazonS3().generatePresignedUrl(generatePresignedUrlRequest);
            url = S3Url.toString();
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            log.error("The call was transmitted successfully, but Amazon S3 couldn't process it, so it returned an error response.", e.getLocalizedMessage());
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            log.error("Amazon S3 couldn't be contacted for a response, or the client couldn't parse the response from Amazon S3.", e.getLocalizedMessage());
        }
        return url;
    }
    /**
     * 获取亚马逊文件上传服务
     *
     * @return
     */
    public com.amazonaws.services.s3.AmazonS3 getAmazonS3()
    {
        if (null == amazonS3)
        {// 初始化
            Amazon amazon = properies.getAmazon();
            ClientConfiguration clientConfig = new ClientConfiguration();
            clientConfig.setProtocol(Protocol.HTTPS);
            com.amazonaws.regions.Region region = Region.getRegion(Regions.AP_NORTHEAST_1);
            AWSCredentials credentials = new BasicAWSCredentials(amazon.getAccessKey(), amazon.getSecretKey());
            amazonS3 = AmazonS3ClientBuilder.standard()//
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))// 凭证
                    .withClientConfiguration(clientConfig)// 连接配置
                    .withRegion(region.getName()).build(); // 区域配置
        }
        return amazonS3;
    }
}
