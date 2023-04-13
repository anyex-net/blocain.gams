package com.fintech.gams.account.model;


import com.fintech.gams.account.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 重置密码对象
 * <p>File: AccountPersonalSaveOneModel.java </p>
 * <p>Title: AccountPersonalSaveOneModel </p>
 * <p>Description: AccountPersonalSaveOneModel </p>
 * <p>Copyright: Copyright (c) 2019-06-13</p>
 * <p>Company: mof</p>
 *
 * @author yangnan
 * @version 1.0
 */

@Data
@ApiModel(description = "个人开户第一页数据保存/更新")
public class AccountPersonalSaveOneModel {

    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人信息", required = true)
    private AccountPerInfo accountPerInfo;

    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人地址", required = true)
    private List<AccountPerAddress> accountPerAddressList;

    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人联系方式", required = true)
    private List<AccountPerContact>  accountPerContactList;

    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人身份信息", required = true)
    private  AccountPerIdentity   accountPerIdentity;

    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人税务居住地", required = true)
    private List<AccountPerTaxes>  accountPerTaxesList;

    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人财富来源", required = true)
    private List<AccountPerWealthSource>  accountPerWealthSourceList;

    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人安全问题", required = true)
    private List<AccountPerSafeQuestion>  accountPerSafeQuestionList;

    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人雇佣(就业)信息", required = true)
    private  AccountPerEmployment  accountPerEmployment;

    @NotNull(message = "验证对象不可为空")
    @ApiModelProperty(value = "个人雇佣(就业)信息", required = true)
    private  AccountPerPortfolio  accountPerPortfolio;

}
