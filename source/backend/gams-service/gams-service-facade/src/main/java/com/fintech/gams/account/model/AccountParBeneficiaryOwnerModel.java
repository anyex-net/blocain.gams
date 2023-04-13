package com.fintech.gams.account.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author yann
 * @date 2019-09-11 17:16
 * @Description
 */
@Getter
@Setter
@ApiModel(description = "合伙企业受益所有人")
public class AccountParBeneficiaryOwnerModel  implements Serializable {
    List<ParPersonalInfoModel> beneficiaryOwnerList;
    List<ParPersonalInfoModel> operationPrincipalList;
}