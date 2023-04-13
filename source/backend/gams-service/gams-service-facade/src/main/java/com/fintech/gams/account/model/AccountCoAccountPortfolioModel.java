package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountCoAccountPortfolio;
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
 * @date 2019-08-02 11:01
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "公司账户组合信息 + 字典信息")
public class AccountCoAccountPortfolioModel {

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
    public AccountCoAccountPortfolio convertToEntity(){
        AccountCoAccountPortfolioModel.ModelConvert modelConvert = new AccountCoAccountPortfolioModel.ModelConvert();
        AccountCoAccountPortfolio entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountCoAccountPortfolioModel convertToModel(AccountCoAccountPortfolio entity){
        AccountCoAccountPortfolioModel.ModelConvert modelConvert = new AccountCoAccountPortfolioModel.ModelConvert();
        AccountCoAccountPortfolioModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountCoAccountPortfolioModel, AccountCoAccountPortfolio> {
        @Override
        protected AccountCoAccountPortfolio doForward(AccountCoAccountPortfolioModel model) {
            AccountCoAccountPortfolio entity = new AccountCoAccountPortfolio();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountCoAccountPortfolioModel doBackward(AccountCoAccountPortfolio entity) {
            AccountCoAccountPortfolioModel model = new AccountCoAccountPortfolioModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }

}
