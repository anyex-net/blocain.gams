package com.fintech.gams.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yann
 * @date 2019-07-19 10:08
 * @Description
 */
@Data
@ApiModel(description = "公司账户个人详细信息 + 字典")
public class CompanyPersonalInfoModel {

    @ApiModelProperty(value = "公司个人信息")
    private AccountCoPersonalModel accountCoPersonal;

    @ApiModelProperty(value = "公司个人地址信息")
    private List<AccountCoAddressModel> accountCoAddressList;

    @ApiModelProperty(value = "公司个人联系信息")
    private List<AccountCoContactModel> accountCoContactList;

    @ApiModelProperty(value = "公司个人身份信息")
    private List<AccountCoIdentityModel> accountCoIdentityList;

    @ApiModelProperty(value = "公司个人税务居住地", required = true)
    private List<AccountCoTaxesModel> accountCoTaxesList;

}
