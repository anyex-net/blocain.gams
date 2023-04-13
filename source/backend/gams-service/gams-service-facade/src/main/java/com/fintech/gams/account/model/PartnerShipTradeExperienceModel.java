package com.fintech.gams.account.model;


import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.account.entity.AccountParTradeExperience;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/7/18 13:34
 * @Description
 */
@Data
@ApiModel(description = "合伙企业交易经验和交易地点名")
public class PartnerShipTradeExperienceModel {

    @ApiModelProperty(value = "合伙企业交易经验")
    private List<AccountParTradeExperience> accountParTradeExperienceList;

    @ApiModelProperty(value = "共同的交易地点类型")
    private List<AccountCommonTradeLocation> accountCommonTradeLocationList;

}
