package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.AccountParAddress;
import com.fintech.gams.account.entity.AccountParCrsControl;
import com.fintech.gams.account.entity.AccountParCrsControlNum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/7/24 10:42
 * @Description  合伙企业开户第十三页信息
 */
@Data
@ApiModel(description = "合伙企业开户第十三页")
public class PartnerShipCrsControlModel {

    @NotNull(message = "合伙企业CRS控权人自我证明表格")
    @ApiModelProperty(value = "合伙企业CRS控权人自我证明表格", required = true)
    private AccountParCrsControl accountParCrsControl;

    @NotNull(message = "合伙企业CRS控权人自我证明控权人编号")
    @ApiModelProperty(value = "合伙企业CRS控权人自我证明控权人编号", required = true)
    private List<AccountParCrsControlNum> accountParCrsControlNumList;

    @NotNull(message = "地址信息")
    @ApiModelProperty(value = "地址信息", required = true)
    private List<AccountParAddress> accountParAddressList;
}
