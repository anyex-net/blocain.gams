package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.account.entity.AccountPerTradeExp;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author yann
 * @date 2019-07-04 14:47
 * @Description
 */
@Data
@ApiModel(description = "根据accountId取个人交易经验信息")
public class PerTradeExpModel {
    private List<AccountPerTradeExp> accountPerTradeExpList;
    private List<AccountCommonTradeLocation> accountCommonTradeLocations;
}
