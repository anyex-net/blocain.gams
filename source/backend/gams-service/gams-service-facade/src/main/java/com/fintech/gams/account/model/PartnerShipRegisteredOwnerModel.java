package com.fintech.gams.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/7/18 13:58
 * @Description
 */
@Data
@ApiModel(description = "合伙企业开户第八页")
public class PartnerShipRegisteredOwnerModel {

    @ApiModelProperty(value = "合伙企业开户第八页信息")
    private List<PartnerShipRegisteredOwnerInfoModel> partnerShipRegisteredOwnerInfoModelList;

}
