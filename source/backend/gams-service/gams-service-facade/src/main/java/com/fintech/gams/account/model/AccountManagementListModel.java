package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yann
 * @date 2019-09-17 13:55
 * @Description
 */
@Data
@ApiModel(description = "账户管理列表")
public class AccountManagementListModel implements Serializable {
    //账户名
    @ApiModelProperty(value = "账户名", required = true)
    String accountName;

    @ApiModelProperty(value = "账号编号")
    private java.lang.Long    unid;

    //账户类型
    @ApiModelProperty(value = "账户类型", required = true)
    String accounType;

    //客户名
    @ApiModelProperty(value = "客户名", required = true)
    String customerName;

    //国家
    @ApiModelProperty(value = "国家", required = true)
    String country;

    //手机
    @ApiModelProperty(value = "手机", required = true)
    String mobile;

    //邮箱
    @ApiModelProperty(value = "邮箱", required = true)
    String email;

    //账户投资类型描述
    @ApiModelProperty(value = "账户投资类型描述", required = true)
    String accountPortfolioTypeDest;

    //账户状态
    @ApiModelProperty(value = "账户状态", required = true)
    Integer status;

    //关联的IB账户
    @ApiModelProperty(value = "关联的IB账户", required = true)
    String ibAccountCode;

    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    @ApiModelProperty(value = "创建时间", required = true)
    private Long createDate;

    @ApiModelProperty(value = "基础货币(见数据字典数据基础货币)", required = true)
    private String baseCurrency;

    /**区号*/
    @ApiModelProperty(value = "区号")
    private java.lang.String  region;
}
