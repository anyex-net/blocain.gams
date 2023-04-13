package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.UploadFileType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/10/18 18:29
 * @Description
 */
@Data
@ApiModel(description = "文件上传类型结果集")
public class UploadFileTypeModel {

    @ApiModelProperty(value = "文件上传类型结果集")
    private List<UploadFileType> uploadFileTypeList;
}
