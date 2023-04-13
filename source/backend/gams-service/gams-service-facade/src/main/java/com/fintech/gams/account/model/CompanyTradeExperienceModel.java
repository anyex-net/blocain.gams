package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.AccountCoTradeExperience;
import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/7/4 11:35
 * @Description
 */
@Data
@ApiModel(description = "公司交易经验和交易地点名")
public class CompanyTradeExperienceModel {

    @ApiModelProperty(value = "公司交易经验")
    private List<AccountCoTradeExperience> accountCoTradeExperienceList;

    @ApiModelProperty(value = "共同的交易地点类型")
    private List<AccountCommonTradeLocation> accountCommonTradeLocationList;
}
