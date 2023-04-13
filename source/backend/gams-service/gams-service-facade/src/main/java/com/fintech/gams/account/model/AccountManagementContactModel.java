package com.fintech.gams.account.model;

import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.entity.Region;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yann
 * @date 2019-09-18 16:07
 * @Description
 */
@Data
@ApiModel(description = "账户管理联系信息")
public class AccountManagementContactModel {

    /**国家(见数据字典国家类型)*/
    @NotNull(message = "国家(见数据字典国家类型)不可为空")
    @ApiModelProperty(value = "国家(见数据字典国家类型)", required = true)
    private Region country;

    /**号码类别 手机，电话，传真，家庭，商务(见字典电话号码类型)*/
    @NotNull(message = "号码类别 手机，电话，传真，家庭，商务(见字典电话号码类型)不可为空")
    @ApiModelProperty(value = "号码类别 手机，电话，传真，家庭，商务(见字典电话号码类型)", required = true)
    private Dictionary contactNumberType;

    /**联系号码*/
    @NotNull(message = "联系号码不可为空")
    @ApiModelProperty(value = "联系号码", required = true)
    private String contactNumber;
}
