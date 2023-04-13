package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/7/18 14:00
 * @Description
 */
@Data
@ApiModel(description = "合伙企业开户第八页信息")
public class PartnerShipRegisteredOwnerInfoModel {

    @ApiModelProperty(value = "合伙企业个人信息")
    private AccountParPersonal accountParPersonal;

    @ApiModelProperty(value = "合伙企业地址信息")
    private List<AccountParAddress> accountParAddressList;

    @ApiModelProperty(value = "合伙企业联系信息")
    private List<AccountParContact> accountParContactList;

    @ApiModelProperty(value = "合伙企业身份信息")
    private AccountParIdentity accountParIdentity;

    @ApiModelProperty(value = "合伙企业税务居住地")
    private List<AccountParTaxes> accountParTaxesList;
}
