package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/7/18 9:30
 * @Description 合伙企业开户第二页信息
 */
@Data
@ApiModel(description = "合伙企业开户第二页信息")
public class PartnerShipAuthorizedModel {

    @NotNull(message = "合伙企业个人信息")
    @ApiModelProperty(value = "合伙企业个人信息", required = true)
    private AccountParPersonal accountParPersonal;

    @NotNull(message = "合伙企业地址信息")
    @ApiModelProperty(value = "合伙企业地址信息", required = true)
    private List<AccountParAddress> accountParAddressList;

    @NotNull(message = "合伙企业联系信息")
    @ApiModelProperty(value = "合伙企业联系信息", required = true)
    private List<AccountParContact> accountParContactList;

    @NotNull(message = "合伙企业身份信息")
    @ApiModelProperty(value = "合伙企业身份信息", required = true)
    private AccountParIdentity accountParIdentity;

    @NotNull(message = "合伙企业税务居住地")
    @ApiModelProperty(value = "合伙企业税务居住地", required = true)
    private List<AccountParTaxes> accountParTaxesList;

    @NotNull(message = "合伙企业安全问题")
    @ApiModelProperty(value = "合伙企业安全问题", required = true)
    private List<AccountParSafeQuestion> accountParSafeQuestionList;
}
