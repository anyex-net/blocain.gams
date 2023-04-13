package com.fintech.gams.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/6/26 00:44
 * @Description
 */
@Data
@ApiModel(description = "公司开户第八页")
public class CompanyRegisteredOwnerModel {

    @ApiModelProperty(value = "公司开户第八页信息")
    private List<CompanyRegisteredOwnerInfoModel> companyRegisteredOwnerInfoModelList;

}
