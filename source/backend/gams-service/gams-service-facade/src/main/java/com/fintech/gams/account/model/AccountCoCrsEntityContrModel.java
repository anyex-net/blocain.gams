package com.fintech.gams.account.model;

import com.fintech.gams.account.entity.AccountCoCrsEntityContr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author yann
 * @date 2019-12-25 13:12
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "公司CrsCp")
public class AccountCoCrsEntityContrModel {

//    @ApiModelProperty(value = "crs控权人信息")
//    private AccountCoCrsEntityContr accountCoCrsEntityContr;

    @ApiModelProperty(value = "crs控权人信息")
    private List<AccountCoCrsControlModel> accountCoCrsControls;

//    @ApiModelProperty(value = "地址列表")
//    private List<AccountCoAddressModel> accountCoAddressModels;

    @ApiModelProperty(value = "公司CRS控权人自我证明控权人编号")
    private List<AccountCoCrsControlNumModel> accountCoCrsControlNums;
}
