package com.fintech.gams.account.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author yann
 * @date 2019-09-11 17:04
 * @Description
 */
@Getter
@Setter
@ApiModel(description = "公司受益所有人")
public class AccountCoBeneficiaryOwnerModel implements Serializable {

    List<CompanyPersonalInfoModel> beneficiaryOwnerList;
    List<CompanyPersonalInfoModel> operationPrincipalList;
}
