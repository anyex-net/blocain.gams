package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.AccountCoAddress;
import com.fintech.gams.account.entity.AccountCoCrsControl;
import com.fintech.gams.account.entity.AccountCoCrsControlNum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/7/23 15:55
 * @Description  公司开户第十三页信息
 */
@Data
@ApiModel(description = "公司开户第十三页")
public class CompanyCrsControlModel {

    @NotNull(message = "公司CRS控权人自我证明表格")
    @ApiModelProperty(value = "公司CRS控权人自我证明表格", required = true)
    private  AccountCoCrsControl accountCoCrsControl;

    @NotNull(message = "公司CRS控权人自我证明控权人编号")
    @ApiModelProperty(value = "公司CRS控权人自我证明控权人编号", required = true)
    private List<AccountCoCrsControlNum> accountCoCrsControlNumList;

    @NotNull(message = "地址信息")
    @ApiModelProperty(value = "地址信息", required = true)
    private List<AccountCoAddress> accountCoAddressList;
}
