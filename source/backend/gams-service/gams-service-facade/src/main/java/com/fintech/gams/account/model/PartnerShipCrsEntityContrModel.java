package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.AccountParCrsEntity;
import com.fintech.gams.account.entity.AccountParCrsEntityContr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/12/22 19:36
 * @Description  合伙企业开户第十二页信息
 */
@Data
@ApiModel(description = "合伙企业开户第十二页")
public class PartnerShipCrsEntityContrModel {

    @NotNull(message = "合伙企业CRS实体自我证明表格")
    @ApiModelProperty(value = "合伙企业CRS实体自我证明表格", required = true)
    private AccountParCrsEntity accountParCrsEntity;

    @ApiModelProperty(value = "合伙企业crs实体（CRS-E）控权人信息", required = true)
    private List<AccountParCrsEntityContr> accountParCrsEntityContrList;
}
