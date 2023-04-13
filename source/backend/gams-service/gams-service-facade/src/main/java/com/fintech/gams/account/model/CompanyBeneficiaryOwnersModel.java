package com.fintech.gams.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/6/26 15:40
 * @Description
 */
@Data
@ApiModel(description = "公司开户第九页")
public class CompanyBeneficiaryOwnersModel {

    @ApiModelProperty(value = "公司开户第九页信息")
    private List<CompanyBeneficiaryOwnersInfoModel> companyBeneficiaryOwnersInfoModelList;

    @ApiModelProperty(value = "状态")
    private String status;
}
