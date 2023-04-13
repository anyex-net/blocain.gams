package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountParTaxClassification;
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
 * @date 2019-08-08 13:49
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "合伙企业税收分类 + 字典")
public class AccountParTaxClassificationModel {
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**税收类型(见数据字典税收类型)*/
    @NotNull(message = "税收类型(见数据字典税收类型)不可为空")
    @ApiModelProperty(value = "税收类型(见数据字典税收类型)", required = true)
    private Dictionary taxType;

    /**是否 非企业的情况下*/
    @ApiModelProperty(value = "是否 非企业的情况下")
    private Boolean isPreferentia;

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
    public AccountParTaxClassification convertToEntity(){
        AccountParTaxClassificationModel.ModelConvert modelConvert = new AccountParTaxClassificationModel.ModelConvert();
        AccountParTaxClassification entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountParTaxClassificationModel convertToModel(AccountParTaxClassification entity){
        AccountParTaxClassificationModel.ModelConvert modelConvert = new AccountParTaxClassificationModel.ModelConvert();
        AccountParTaxClassificationModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountParTaxClassificationModel, AccountParTaxClassification> {
        @Override
        protected AccountParTaxClassification doForward(AccountParTaxClassificationModel model) {
            AccountParTaxClassification entity = new AccountParTaxClassification();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountParTaxClassificationModel doBackward(AccountParTaxClassification entity) {
            AccountParTaxClassificationModel model = new AccountParTaxClassificationModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }

}
