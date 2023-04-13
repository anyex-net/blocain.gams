package com.fintech.gams.utils;

import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.Map;

/**
 * pdf处理工具类
 */
@Component
@Slf4j
public class PdfUtils {
    private PdfUtils() {
    }
    @Autowired
    FreeMarkerConfigurer freeMarkerConfigurer;

    private final String imagePath = "static/image/";
    private final String fontPath = "static/font/";


    /**
     * 按模板和参数生成html字符串,再转换为flying-saucer识别的Document
     *
     * @param templateName freemarker模板名称
     * @param variables    freemarker模板参数
     * @return Document
     */
    private  Document generateDoc(String templateName, Map<String, Object> variables) {
        Template tp;
        try {
            tp = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
        StringWriter stringWriter = new StringWriter();
        try(BufferedWriter writer = new BufferedWriter(stringWriter)) {
            try {
                tp.process(variables, writer);
                writer.flush();
            } catch (TemplateException e) {
                log.error("模板不存在或者路径错误", e);
            } catch (IOException e) {
                log.error("IO异常", e);
            }
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //设置 UTF-8 编码，防止jar包下因为编码报错
            return builder.parse(new ByteArrayInputStream(stringWriter.toString().getBytes("UTF-8")));
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 核心: 根据freemarker模板生成pdf文档
     *
     * @param templateName freemarker模板名称
     * @param out          输出流
     * @param data     freemarker模板参数
     * @param fontPath 字体文件路径
     * @throws Exception 模板无法找到、模板语法错误、IO异常
     */
    private  void generateAll(String templateName, OutputStream out, Map<String,Object> data,String fontPath) throws Exception {
        if (CollectionUtils.isEmpty(data)) {
            log.warn("警告:freemarker模板参数为空!");
            return;
        }
        ITextRenderer renderer = new ITextRenderer();
        Document doc = generateDoc(templateName,data);
        renderer.setDocument(doc, null);
        //设置字符集(例如宋体),此处必须与模板中的<body style="font-family: SimSun">一致,区分大小写,不能写成汉字"宋体"
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //展现和输出pdf
        renderer.layout();
        renderer.createPDF(out, false);
        //完成pdf写入
        renderer.finishPDF();
    }

    /**
     * pdf下载
     *
     * @param templateName freemarker模板名称(带后缀.ftl)
     * @param data     模板参数集
     * @param response     HttpServletResponse
     * @param fileName     下载文件名称(带文件扩展名后缀)
     * @param imageName     图片名(带文件扩展名后缀)
     * @param fontName 字体文件名(不带后缀)
     * @param fontNameSuffix 字体后缀名(例如 .ttf)
     */
    public  void download(String templateName, Map<String,Object> data, HttpServletResponse response, String fileName,String imageName,String fontName,String fontNameSuffix) {
        InputStream stream = getInputStream(imagePath+imageName);
        data.put("fileType", "image/jpeg");
        data.put("file64Str",fileToBase64Str(stream));
        // 设置编码、文件ContentType类型、文件头、下载文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" +
                    new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        try (ServletOutputStream out = response.getOutputStream()) {
            generateAll(templateName, out, data,getPath(fontPath+fontName+fontNameSuffix,fontName,fontNameSuffix));
            out.flush();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * pdf预览
     *
     * @param templateName freemarker模板名称(带后缀.ftl)
     * @param map     模板参数集
     * @param response     HttpServletResponse
     * @param imageName     图片名
     * @param fontName 字体文件名(不带后缀)
     * @param fontNameSuffix 字体后缀名(例如 .ttf)
     */
    public  void preview(String templateName,Map<String,Object> map, HttpServletResponse response,String imageName,String fontName,String fontNameSuffix) {
        InputStream stream = getInputStream(imagePath+imageName);
        map.put("fileType", "image/jpeg");
        map.put("file64Str",fileToBase64Str(stream));
        try (ServletOutputStream out = response.getOutputStream()) {
            generateAll(templateName, out, map,getPath(fontPath+fontName+fontNameSuffix,fontName,fontNameSuffix));
            out.flush();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * File to 64bit Str
     *
     * @param inputStream
     * @return
     */
    public  String fileToBase64Str(InputStream inputStream) {

        byte[] data = null;

        if (inputStream != null) {
            try {
                data = new byte[inputStream.available()];
                inputStream.read(data);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return Base64.encodeBase64String(data);
        }
        return "";
    }

    /**
     * 获取resources内文件流
     * @param filePath 文件路径
     * @return
     */
    private  InputStream getInputStream(String filePath) {
        return PdfUtils.class.getClassLoader().getResourceAsStream(filePath);
    }

    /**
     * 获取resources内文件路径
     * 根据原文件路径获取文件流，生成新的临时文件
     * @param filePath 原文件路径
     * @param name 文件名(不带后缀)
     * @param suffix 后缀名(例如 .jpg)
     * @return
     */
    private  String getPath(String filePath,String name,String suffix) {
        try
        {
            InputStream inputStream = getInputStream(filePath);
            File tempFile = File.createTempFile(name, suffix);
            FileUtils.copyInputStreamToFile(inputStream, tempFile);
            return tempFile.getPath();
        }
        catch (IOException e)
        {
            log.error(e.getLocalizedMessage());
        }
        return "";
    }
}
