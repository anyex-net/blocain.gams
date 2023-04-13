package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.common.entity.Dictionary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author yann
 * @date 2019-09-20 16:14
 * @Description
 */
@Data
@ApiModel(description = "账户管理投资目标信息")
public class AccountManagementInvestmentTargetModel  implements Serializable {
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**投资目标(见数据字典投资目标类型)*/
    @ApiModelProperty(value = "投资目标(见数据字典投资目标类型)")
    private List<Dictionary> investmentTarget;
}
