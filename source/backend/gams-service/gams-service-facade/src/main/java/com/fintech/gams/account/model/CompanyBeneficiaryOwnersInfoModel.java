package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/6/26 15:41
 * @Description
 */
@Data
@ApiModel(description = "公司开户第九页信息")
public class CompanyBeneficiaryOwnersInfoModel {

    @NotNull(message = "公司个人信息")
    @ApiModelProperty(value = "公司个人信息")
    private AccountCoPersonal accountCoPersonal;

    @NotNull(message = "公司地址信息")
    @ApiModelProperty(value = "公司地址信息")
    private List<AccountCoAddress> accountCoAddressList;

    @ApiModelProperty(value = "公司身份信息")
    private AccountCoIdentity accountCoIdentity;

    @NotNull(message = "公司税务居住地")
    @ApiModelProperty(value = "公司税务居住地", required = true)
    private List<AccountCoTaxes> accountCoTaxesList;
}
