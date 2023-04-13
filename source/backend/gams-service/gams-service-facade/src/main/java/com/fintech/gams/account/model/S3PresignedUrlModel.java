package com.fintech.gams.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yann
 * @date 2019-09-26 9:27
 * @Description
 */
@Data
@ApiModel(description = "S3预签名路径")
public class S3PresignedUrlModel implements Serializable {

    @ApiModelProperty(value = "预签名路径", required = true)
    private String presignedUrl;

    @ApiModelProperty(value = "url", required = true)
    private String url;

}
