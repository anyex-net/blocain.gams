package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author yann
 * @date 2019-09-19 16:32
 * @Description
 */
@Data
@ApiModel(description = "账户管理资产")
public class AccountManagementAssetModel implements Serializable {
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**年度净收入（见字典数据年度净收入）*/
    @NotNull(message = "年度净收入（见字典数据年度净收入)不可为空")
    @ApiModelProperty(value = "年度净收入（见字典数据年度净收入）", required = true)
    @Size(max = 32)
    private String anualNetIncome;

    /**资产净值（见字典数据资产净值）*/
    @NotNull(message = "资产净值（见字典数据资产净值）不可为空")
    @ApiModelProperty(value = "资产净值（见字典数据资产净值）", required = true)
    @Size(max = 32)
    private String netAssetValue;

    /**流动资产净值（见字典数据流动资产净值）*/
    @NotNull(message = "流动资产净值（见字典数据资产净值）不可为空")
    @ApiModelProperty(value = "流动资产净值（见字典数据资产净值）", required = true)
    @Size(max = 32)
    private String netCurrentAssets;
}
