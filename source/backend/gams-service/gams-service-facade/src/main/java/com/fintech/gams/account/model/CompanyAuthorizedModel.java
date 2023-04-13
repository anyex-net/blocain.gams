package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/6/18 16:22
 * @Description 公司开户第二页信息
 */
@Data
@ApiModel(description = "公司开户第二页信息")
public class CompanyAuthorizedModel {

    @NotNull(message = "公司个人信息")
    @ApiModelProperty(value = "公司个人信息", required = true)
    private AccountCoPersonal accountCoPersonal;

    @NotNull(message = "公司地址信息")
    @ApiModelProperty(value = "公司地址信息", required = true)
    private List<AccountCoAddress> accountCoAddressList;

    @NotNull(message = "公司联系信息")
    @ApiModelProperty(value = "公司联系信息", required = true)
    private List<AccountCoContact> accountCoContactList;

    @NotNull(message = "公司身份信息")
    @ApiModelProperty(value = "公司身份信息", required = true)
    private AccountCoIdentity accountCoIdentity;

    @NotNull(message = "公司税务居住地")
    @ApiModelProperty(value = "公司税务居住地", required = true)
    private List<AccountCoTaxes> accountCoTaxesList;

    @NotNull(message = "公司安全问题")
    @ApiModelProperty(value = "公司安全问题", required = true)
    private List<AccountCoSafeQuestion> accountCoSafeQuestionList;
}
