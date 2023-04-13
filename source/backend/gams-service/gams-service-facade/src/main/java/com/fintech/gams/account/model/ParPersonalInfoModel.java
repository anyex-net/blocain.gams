package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yann
 * @date 2019-07-22 14:11
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "合伙企业账户个人详细信息")
public class ParPersonalInfoModel {

    @NotNull(message = "合伙企业个人信息")
    @ApiModelProperty(value = "合伙企业个人信息", required = true)
    private AccountParPersonalModel accountParPersonal;

    @NotNull(message = "合伙企业地址信息")
    @ApiModelProperty(value = "合伙企业个人地址信息", required = true)
    private List<AccountParAddressModel> accountParAddressList;

    @NotNull(message = "合伙企业联系信息")
    @ApiModelProperty(value = "合伙企业个人联系信息", required = true)
    private List<AccountParContactModel> accountParContactList;

    @NotNull(message = "合伙企业身份信息")
    @ApiModelProperty(value = "合伙企业个人身份信息", required = true)
    private List<AccountParIdentityModel> accountParIdentityList;

    @NotNull(message = "合伙企业税务居住地")
    @ApiModelProperty(value = "合伙企业个人税务居住地", required = true)
    private List<AccountParTaxesModel> accountParTaxesList;


}
