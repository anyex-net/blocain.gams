package com.fintech.gams.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/7/18 14:44
 * @Description
 */
@Data
@ApiModel(description = "合伙企业开户第九页")
public class PartnerShipBeneficiaryOwnersModel {

    @ApiModelProperty(value = "合伙企业开户第九页信息")
    private List<PartnerShipBeneficiaryOwnersInfoModel> partnerShipBeneficiaryOwnersInfoModelList;

    @ApiModelProperty(value = "状态")
    private String status;
}
