package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.entity.Region;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yann
 * @date 2019-09-19 16:17
 * @Description
 */
@Data
@ApiModel(description = "税务居住地")
public class AccountManagementTaxesModel implements Serializable {

    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**公司个人信息ID*/
    @ApiModelProperty(value = "公司个人信息ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long coPersonalId;

    /**税务居住国家(见数据字典国家类型)*/
    @ApiModelProperty(value = "税务居住国家(见数据字典国家类型)")
    private Region taxResidenceCountry;

    /**税务居住地证件号码*/
    @ApiModelProperty(value = "税务居住地证件号码")
    private String taxResidenceIdCardNumber;

    /**无纳税识别号的理由选择*/
    @ApiModelProperty(value = "无纳税识别号的理由选择")
    private Dictionary noTaxIdentificationNumber;

    /**不同的注册号码*/
    @ApiModelProperty(value = "不同的注册号码")
    private String differentRegistrationNumber;

}
