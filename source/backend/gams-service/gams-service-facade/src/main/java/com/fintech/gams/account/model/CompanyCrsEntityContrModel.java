package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.AccountCoCrsEntity;
import com.fintech.gams.account.entity.AccountCoCrsEntityContr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/12/19 12:26
 * @Description 公司开户第十二页信息
 */
@Data
@ApiModel(description = "公司开户第十二页")
public class CompanyCrsEntityContrModel {

    @NotNull(message = "公司CRS实体自我证明表格")
    @ApiModelProperty(value = "公司CRS实体自我证明表格", required = true)
    private AccountCoCrsEntity accountCoCrsEntity;

    @ApiModelProperty(value = "公司crs实体（CRS-E）控权人信息")
    private List<AccountCoCrsEntityContr> accountCoCrsEntityContrList;
}
