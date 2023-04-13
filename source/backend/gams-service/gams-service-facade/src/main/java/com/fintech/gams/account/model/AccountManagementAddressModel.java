package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.common.entity.Region;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yann
 * @date 2019-09-19 13:07
 * @Description
 */
@Data
@ApiModel(description = "账户管理地址")
public class AccountManagementAddressModel implements Serializable {

    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**国家(见数据字典国家类型)*/
    @NotNull(message = "国家(见数据字典国家类型)不可为空")
    @ApiModelProperty(value = "国家(见数据字典国家类型)", required = true)
    private Region country;

    /**地址*/
    @NotNull(message = "地址不可为空")
    @ApiModelProperty(value = "地址", required = true)
    private String address;

    /**城市*/
    @NotNull(message = "城市不可为空")
    @ApiModelProperty(value = "城市", required = true)
    private String city;

    /**省*/
    @NotNull(message = "省不可为空")
    @ApiModelProperty(value = "省", required = true)
    private String rovince;

    /**邮编*/
    @ApiModelProperty(value = "邮编")
    private String zipCode;

    /**居住，邮寄,公司(见数据字典地址类型)*/
    @NotNull(message = "居住，邮寄,公司(见数据字典地址类型)不可为空")
    @ApiModelProperty(value = "居住，邮寄,公司(见数据字典地址类型)", required = true)
    private String addressType;

    /**其他国家(是否和上方选的国家不同)(见数据字典国家类型)*/
    @ApiModelProperty(value = "其他国家(是否和上方选的国家不同)(见数据字典国家类型)")
    private Region otherCountry;

    /**其他出生国家(是否和上方选的国家不同)(见数据字典国家类型)*/
    @ApiModelProperty(value = "其他出生国家(是否和上方选的国家不同)(见数据字典国家类型)")
    private Region otherBirthCountry;
}
