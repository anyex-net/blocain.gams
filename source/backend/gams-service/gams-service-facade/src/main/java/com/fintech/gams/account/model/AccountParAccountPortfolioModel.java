package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountParAccountPortfolio;
import com.fintech.gams.common.entity.Dictionary;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * @author yann
 * @date 2019-08-02 15:30
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "合伙企业账户组合信息 + 字典信息")
public class AccountParAccountPortfolioModel {
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**账户投资组合类型(见数据字典账户投资组合类型)*/
    @NotNull(message = "账户投资组合类型(见数据字典账户投资组合类型)不可为空")
    @ApiModelProperty(value = "账户投资组合类型(见数据字典账户投资组合类型)", required = true)
    private Dictionary accountPortfolioType;

    /**基础货币(见数据字典基础货币)*/
    @NotNull(message = "基础货币(见数据字典基础货币)不可为空")
    @ApiModelProperty(value = "基础货币(见数据字典基础货币)", required = true)
    private Dictionary baseCurrency;

    /**创建人*/
    @NotNull(message = "创建人不可为空")
    @ApiModelProperty(value = "创建人", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;

    /**创建时间*/
    @NotNull(message = "创建时间不可为空")
    @ApiModelProperty(value = "创建时间", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdDate;

    /**更新人*/
    @ApiModelProperty(value = "更新人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatedBy;

    /**更新时间*/
    @ApiModelProperty(value = "更新时间")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatedDate;


    /**
     * 复制 model 到 entity
     * @return
     */
    public AccountParAccountPortfolio convertToEntity(){
        AccountParAccountPortfolioModel.ModelConvert modelConvert = new AccountParAccountPortfolioModel.ModelConvert();
        AccountParAccountPortfolio entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountParAccountPortfolioModel convertToModel(AccountParAccountPortfolio entity){
        AccountParAccountPortfolioModel.ModelConvert modelConvert = new AccountParAccountPortfolioModel.ModelConvert();
        AccountParAccountPortfolioModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountParAccountPortfolioModel, AccountParAccountPortfolio> {
        @Override
        protected AccountParAccountPortfolio doForward(AccountParAccountPortfolioModel model) {
            AccountParAccountPortfolio entity = new AccountParAccountPortfolio();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountParAccountPortfolioModel doBackward(AccountParAccountPortfolio entity) {
            AccountParAccountPortfolioModel model = new AccountParAccountPortfolioModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
