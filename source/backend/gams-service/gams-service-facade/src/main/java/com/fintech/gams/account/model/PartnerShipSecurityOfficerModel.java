package com.fintech.gams.account.model;


import com.fintech.gams.account.entity.AccountParSecurityOfficer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/7/18 15:14
 * @Description
 */
@Data
@ApiModel(description = "合伙企业开户第十页")
public class PartnerShipSecurityOfficerModel {

    @ApiModelProperty(value = "合伙企业安全官")
    private List<AccountParSecurityOfficer> accountParSecurityOfficerList;

    @ApiModelProperty(value = "账户ID")
    private Long accountId;
}
