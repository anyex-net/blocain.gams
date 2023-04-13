package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.common.entity.Dictionary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author yann
 * @date 2019-09-19 16:49
 * @Description
 */
@Data
@ApiModel(description = "账户管理地址")
public class AccountManagementTradeExperienceModel  implements Serializable {
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**交易经验*/
    @ApiModelProperty(value = "交易经验")
    private Dictionary tradeExperience;

    /**年交易数*/
    @ApiModelProperty(value = "年交易数")
    private Dictionary yearTransactionsPerNumber;

    /**总交易数*/
    @ApiModelProperty(value = "总交易数")
    private String transactionsTotalNumber;

    /**知识水平(见数据字典知识水平)*/
    @ApiModelProperty(value = "知识水平(见数据字典知识水平)")
    private Dictionary knowledgeLevel;

    /**交易地点*/
    @ApiModelProperty(value = "交易地点")
    private List<AccountCommonTradeLocation> tradingLocation;

    /**交易类型(见数据字典交易类型)*/
    @ApiModelProperty(value = "交易类型(见数据字典交易类型)")
    private Dictionary tansactionType;

    /**受交易权限*/
    @ApiModelProperty(value = "受交易权限")
    private Boolean transactionAuthority;

}
