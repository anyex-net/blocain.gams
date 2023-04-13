package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.entity.Region;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author yann
 * @date 2019-09-18 15:59
 * @Description
 */
@Data
@ApiModel(description = "账户管理基本信息")
public class AccountManagementBasicInfoModel implements Serializable {

    //国家
    @ApiModelProperty(value = "国家", required = true)
    Region country;

    //邮箱
    @ApiModelProperty(value = "邮箱", required = true)
    String email;

    //客户名
    @ApiModelProperty(value = "客户名", required = true)
    String customerName;
    //Url
    @ApiModelProperty(value = "网址", required = true)
    String url;

    @ApiModelProperty(value = "联系信息", required = true)
    List<AccountManagementContactModel> contactList;

    @ApiModelProperty(value = "财富来源", required = true)
    List<AccountManagementWealthSourceModel> wealthSourceList;

    @ApiModelProperty(value = "地址", required = true)
    List<AccountManagementAddressModel> addressList;
    /**生日  个人*/
    @ApiModelProperty(value = "生日")
    private String birthday;

    /**先生，女士，博士，夫人(见数据字典数据称呼类型)
     * 个人
     */
    @ApiModelProperty(value = "先生，女士，博士，夫人(见数据字典数据称呼类型)", required = true)
    @Size(max = 32)
    private Dictionary appellation;

    /**性别(见数据字典数据性别类型)
     * 个人
     */
    @ApiModelProperty(value = "性别(见数据字典数据性别类型)", required = true)
    @Size(max = 32)
    private Dictionary gender;
}
