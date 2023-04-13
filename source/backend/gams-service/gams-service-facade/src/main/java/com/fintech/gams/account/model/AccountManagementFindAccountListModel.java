package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yann
 * @date 2019-09-17 10:47
 * @Description
 */
@Data
@ApiModel(description = "账户管理列表查询参数")
public class AccountManagementFindAccountListModel implements Serializable {

    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

//    账户名
    @ApiModelProperty(value = "账户名", required = true)
    String accountName;

//    客户名
    @ApiModelProperty(value = "客户名", required = true)
    String customerName;

//    手机
    @ApiModelProperty(value = "手机", required = true)
    String mobile;

//    国家
    @ApiModelProperty(value = "国家", required = true)
    String country;

//    账户状态
    @ApiModelProperty(value = "账户状态", required = true)
    Integer status;

//    账户类型
    @ApiModelProperty(value = "账户类型", required = true)
    String accountType;

//    账户创建起始时间
    @ApiModelProperty(value = "账户创建起始时间", required = true)
    Long accountCreateDateStart;

//    账户创建结束时间
    @ApiModelProperty(value = "账户创建结束时间", required = true)
    Long accountCreateDateEnd;

//    关联的IB账户
    @ApiModelProperty(value = "关联的IB账户", required = true)
    String ibAccountCode;

    @ApiModelProperty(value = "字典表语言", required = true)
    String lang;

}
