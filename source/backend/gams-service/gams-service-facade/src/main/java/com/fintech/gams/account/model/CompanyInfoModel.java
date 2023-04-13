package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/6/15 16:09
 * @Description  公司开户第一页信息
 */
@Data
@ApiModel(description = "公司开户第一页信息")
public class CompanyInfoModel {

    @NotNull(message = "公司机构信息")
    @ApiModelProperty(value = "公司机构信息", required = true)
    private AccountCoInstitutional accountCoInstitutional;

    @NotNull(message = "公司地址信息")
    @ApiModelProperty(value = "公司地址信息", required = true)
    private List<AccountCoAddress> accountCoAddressList;

    @NotNull(message = "公司联系信息")
    @ApiModelProperty(value = "公司联系信息", required = true)
    private List<AccountCoContact> accountCoContactList;

    @NotNull(message = "公司税务居住地")
    @ApiModelProperty(value = "公司税务居住地", required = true)
    private List<AccountCoTaxes> accountCoTaxesList;

    @NotNull(message = "公司财富来源")
    @ApiModelProperty(value = "公司财富来源", required = true)
    private List<AccountCoWealthSource> accountCoWealthSourceList;

    @NotNull(message = "公司账户组合信息")
    @ApiModelProperty(value = "公司账户组合信息", required = true)
    private AccountCoAccountPortfolio accountCoAccountPortfolio;


}
