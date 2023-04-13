package com.fintech.gams.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yann
 * @date 2019-09-19 16:12
 * @Description
 */
@Data
@ApiModel(description = "账户管理更多信息")
public class AccountManagementMoreInfoModel implements Serializable {

    //税务居住地
    @ApiModelProperty(value = "税务居住地", required = true)
    List<AccountManagementTaxesModel> taxesList;

    //个人信息
    @ApiModelProperty(value = "个人信息", required = true)
    AccountManagementPersonalModel personal;

    //资产
    @ApiModelProperty(value = "资产", required = true)
    List<AccountManagementAssetModel>   assetList;

    //交易经验
    @ApiModelProperty(value = "交易经验", required = true)
    List<AccountManagementTradeExperienceModel> tradeExperienceList;

    //身份
    @ApiModelProperty(value = "身份", required = true)
    List<AccountManagementIdentityModel> identityList;

    @ApiModelProperty(value = "投资目标", required = true)
    List<AccountManagementInvestmentTargetModel> investmentTargetList;

    @ApiModelProperty(value = "地址", required = true)
    List<AccountManagementAddressModel> addressList;

    @ApiModelProperty(value = "联系信息", required = true)
    List<AccountManagementContactModel> contactList;

}
