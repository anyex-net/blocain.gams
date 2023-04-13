package com.fintech.gams.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author yann
 * @date 2019-09-18 16:14
 * @Description
 */
@Data
@ApiModel(description = "账户管理财富来源")
public class AccountManagementWealthSourceModel implements Serializable {
    /**资金来源类型(见数据字典财富来源)*/
    @ApiModelProperty(value = "资金来源类型(见数据字典财富来源)")
    @Size(max = 256)
    private String incomeType;

    /**是否注资账户*/
    @ApiModelProperty(value = "是否注资账户")
    private Boolean isCapitalInjection;

    /**资金百分比*/
    @ApiModelProperty(value = "资金百分比")
    @DecimalMax("100")
    private java.math.BigDecimal percentageOfFunds;

    /**描述*/
    @ApiModelProperty(value = "描述")
    @Size(max = 128)
    private String sourceDesc;
}
