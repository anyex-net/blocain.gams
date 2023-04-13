package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.AccountPerAsset;
import com.fintech.gams.account.entity.AccountPerInvestTarget;
import com.fintech.gams.account.entity.AccountPerRegulatory;
import com.fintech.gams.account.entity.AccountPerTradeExp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yann
 * @date 2019-06-18 10:46
 * @Description
 */
@Data
@ApiModel(description = "个人开户第二页数据保存/更新")
public class AccountPersonalSaveTwoModel {


    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人资产信息", required = true)
    private AccountPerAsset accountPerAsset;

    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人投资目标", required = true)
    private AccountPerInvestTarget  accountPerInvestTargets;

    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人交易经验", required = true)
    private  List<AccountPerTradeExp>  accountPerTradeExpList;

    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人交易经验", required = true)
    private  AccountPerRegulatory  accountPerRegulatories;
}
