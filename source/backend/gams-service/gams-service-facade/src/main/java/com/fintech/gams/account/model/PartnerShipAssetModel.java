package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/7/18 11:40
 * @Description 合伙企业开户第七页信息
 */
@Data
@ApiModel(description = "合伙企业开户第七页信息")
public class PartnerShipAssetModel {

    @NotNull(message = "合伙企业资产信息")
    @ApiModelProperty(value = "合伙企业资产信息", required = true)
    AccountParAsset accountParAsset;

    @NotNull(message = "合伙企业投资目标信息")
    @ApiModelProperty(value = "合伙企业投资目标信息", required = true)
    AccountParInvestmentTarget accountParInvestmentTarget;

    @NotNull(message = "合伙企业交易经验信息")
    @ApiModelProperty(value = "合伙企业交易经验信息", required = true)
    List<AccountParTradeExperience> accountParTradeExperienceList;
}
