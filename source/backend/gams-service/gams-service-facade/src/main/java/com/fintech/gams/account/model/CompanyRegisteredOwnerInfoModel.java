package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/6/26 00:48
 * @Description
 */
@Data
@ApiModel(description = "公司开户第八页信息")
public class CompanyRegisteredOwnerInfoModel {

    @ApiModelProperty(value = "公司个人信息")
    private AccountCoPersonal accountCoPersonal;

    @ApiModelProperty(value = "公司地址信息")
    private List<AccountCoAddress> accountCoAddressList;

    @ApiModelProperty(value = "公司联系信息")
    private List<AccountCoContact> accountCoContactList;

    @ApiModelProperty(value = "公司身份信息")
    private AccountCoIdentity accountCoIdentity;

    @ApiModelProperty(value = "公司税务居住地", required = true)
    private List<AccountCoTaxes> accountCoTaxesList;

}
