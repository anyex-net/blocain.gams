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
 * @date 2019-09-19 16:51
 * @Description
 */
@Data
@ApiModel(description = "账户管理身份信息")
public class AccountManagementIdentityModel  implements Serializable {

    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**签发国家*/
    @ApiModelProperty(value = "签发国家")
    private Region issuingCountry;

    /**身份证类型(见数据字典数据证件类型)*/
    @ApiModelProperty(value = "身份证类型(见数据字典数据证件类型)")
    private Dictionary idCardType;

    /**证件号码*/
    @ApiModelProperty(value = "证件号码")
    private String idNumber;

}
