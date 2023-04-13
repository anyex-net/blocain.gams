package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.AccountCoAsset;
import com.fintech.gams.account.entity.AccountCoInvestmentTarget;
import com.fintech.gams.account.entity.AccountCoTradeExperience;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/6/19 9:56
 * @Description 公司开户第七页信息
 */
@Data
@ApiModel(description = "公司开户第七页信息")
public class CompanyAssetModel {

    @NotNull(message = "公司资产信息")
    @ApiModelProperty(value = "公司资产信息", required = true)
    AccountCoAsset accountCoAsset;

    @NotNull(message = "公司投资目标信息")
    @ApiModelProperty(value = "公司投资目标信息", required = true)
    AccountCoInvestmentTarget accountCoInvestmentTarget;

    @NotNull(message = "公司交易经验信息")
    @ApiModelProperty(value = "公司交易经验信息", required = true)
    List<AccountCoTradeExperience> accountCoTradeExperiencesList;
}
