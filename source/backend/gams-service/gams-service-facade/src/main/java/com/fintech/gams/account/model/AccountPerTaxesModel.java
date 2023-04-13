package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountPerTaxes;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.entity.Region;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * @author yann
 * @date 2019-08-08 11:39
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "个人税务居住地 + 字典")
public class AccountPerTaxesModel {

    /**
     * 账户ID
     */
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**
     * 税务居住国家(见数据字典数据国家类型)
     */
    @ApiModelProperty(value = "税务居住国家(见数据字典数据国家类型)")
    private Region taxResidenceCountry;

    /**
     * 税务居住地证件号码
     */
    @ApiModelProperty(value = "税务居住地证件号码")
    private String taxResidenceIdCardNumber;

    /**
     * 无纳税识别号的理由选择
     */
    @ApiModelProperty(value = "无纳税识别号的理由选择")
    private Dictionary noTaxIdentificationNumber;

    /**
     * 不同的注册号码
     */
    @ApiModelProperty(value = "不同的注册号码")
    private String differentRegistrationNumber;

    /**
     * 复制 model 到 entity
     * @return
     */
    public AccountPerTaxes convertToEntity(){
        AccountPerTaxesModel.ModelConvert modelConvert = new AccountPerTaxesModel.ModelConvert();
        AccountPerTaxes entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountPerTaxesModel convertToModel(AccountPerTaxes entity){
        AccountPerTaxesModel.ModelConvert modelConvert = new AccountPerTaxesModel.ModelConvert();
        AccountPerTaxesModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountPerTaxesModel, AccountPerTaxes> {
        @Override
        protected AccountPerTaxes doForward(AccountPerTaxesModel model) {
            AccountPerTaxes entity = new AccountPerTaxes();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountPerTaxesModel doBackward(AccountPerTaxes entity) {
            AccountPerTaxesModel model = new AccountPerTaxesModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}