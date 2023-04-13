package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.AccountParCrsEntityContr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author yann
 * @date 2019-12-25 16:31
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "企业CrsCp")
public class AccountParCrsEntityContrModel {


    @ApiModelProperty(value = "crs控权人信息")
    private AccountParCrsEntityContr accountParCrsEntityContr;

    @ApiModelProperty(value = "crs控权人信息")
    private List<AccountParCrsControlModel> accountParCrsControls;

    @ApiModelProperty(value = "地址列表")
    private List<AccountParAddressModel> accountParAddressModels;

    @ApiModelProperty(value = "公司CRS控权人自我证明控权人编号")
    private List<AccountParCrsControlNumModel> accountParCrsControlNums;
}
