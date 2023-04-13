package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountCoWealthSource;
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
 * @date 2019-08-08 13:36
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "公司财富来源 + 字典")
public class AccountCoWealthSourceModel {

    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**资金来源类型(见数据字典财富来源)*/
    @ApiModelProperty(value = "资金来源类型(见数据字典财富来源)")
    private Dictionary incomeType;

    /**是否注资账户*/
    @ApiModelProperty(value = "是否注资账户")
    private Boolean isCapitalInjection;

    /**资金百分比*/
    @ApiModelProperty(value = "资金百分比")
    private java.math.BigDecimal percentageOfFunds;

    /**描述*/
    @ApiModelProperty(value = "描述")
    private String sourceDesc;

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
    public AccountCoWealthSource convertToEntity(){
        AccountCoWealthSourceModel.ModelConvert modelConvert = new AccountCoWealthSourceModel.ModelConvert();
        AccountCoWealthSource entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountCoWealthSourceModel convertToModel(AccountCoWealthSource entity){
        AccountCoWealthSourceModel.ModelConvert modelConvert = new AccountCoWealthSourceModel.ModelConvert();
        AccountCoWealthSourceModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountCoWealthSourceModel, AccountCoWealthSource> {
        @Override
        protected AccountCoWealthSource doForward(AccountCoWealthSourceModel model) {
            AccountCoWealthSource entity = new AccountCoWealthSource();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountCoWealthSourceModel doBackward(AccountCoWealthSource entity) {
            AccountCoWealthSourceModel model = new AccountCoWealthSourceModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
