package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.AccountCoSecurityOfficer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/6/26 16:39
 * @Description
 */
@Data
@ApiModel(description = "公司开户第十页")
public class CompanySecurityOfficerModel {

    @ApiModelProperty(value = "公司安全官")
    private List<AccountCoSecurityOfficer> accountCoSecurityOfficerList;

}
