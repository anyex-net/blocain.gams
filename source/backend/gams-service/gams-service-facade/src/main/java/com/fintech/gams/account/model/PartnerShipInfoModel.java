package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/7/17 21:58
 * @Description
 */
@Data
@ApiModel(description = "合伙企业开户第一页信息")
public class PartnerShipInfoModel {

    @NotNull(message = "合伙企业机构信息")
    @ApiModelProperty(value = "合伙企业机构信息", required = true)
    private AccountParInstitutional accountParInstitutional;

    @NotNull(message = "合伙企业地址信息")
    @ApiModelProperty(value = "合伙企业地址信息", required = true)
    private List<AccountParAddress> accountParAddressList;

    @NotNull(message = "合伙企业联系信息")
    @ApiModelProperty(value = "合伙企业联系信息", required = true)
    private List<AccountParContact> accountParContactList;

    @NotNull(message = "合伙企业税务居住地")
    @ApiModelProperty(value = "合伙企业税务居住地", required = true)
    private List<AccountParTaxes> accountParTaxesList;

    @NotNull(message = "合伙企业财富来源")
    @ApiModelProperty(value = "合伙企业财富来源", required = true)
    private List<AccountParWealthSource> accountParWealthSourceList;

    @NotNull(message = "合伙企业账户组合信息")
    @ApiModelProperty(value = "合伙企业账户组合信息", required = true)
    private AccountParAccountPortfolio accountParAccountPortfolio;



}
