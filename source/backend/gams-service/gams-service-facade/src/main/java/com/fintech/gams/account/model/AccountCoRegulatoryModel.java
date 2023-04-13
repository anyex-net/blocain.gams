package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountCoRegulatory;
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
 * @date 2019-08-08 15:11
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "公司规管信息 + 字典")
public class AccountCoRegulatoryModel {
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**是否*/
    @NotNull(message = "是否不可为空")
    @ApiModelProperty(value = "是否", required = true)
    private Boolean whether;

    /**信息详情json*/
    @ApiModelProperty(value = "信息详情json")
    private String infoDetails;

    /**类型*/
    @NotNull(message = "类型不可为空")
    @ApiModelProperty(value = "类型", required = true)
    private Dictionary infoType;


    /**
     * 复制 model 到 entity
     * @return
     */
    public AccountCoRegulatory convertToEntity(){
        AccountCoRegulatoryModel.ModelConvert modelConvert = new AccountCoRegulatoryModel.ModelConvert();
        AccountCoRegulatory entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountCoRegulatoryModel convertToModel(AccountCoRegulatory entity){
        AccountCoRegulatoryModel.ModelConvert modelConvert = new AccountCoRegulatoryModel.ModelConvert();
        AccountCoRegulatoryModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountCoRegulatoryModel, AccountCoRegulatory> {
        @Override
        protected AccountCoRegulatory doForward(AccountCoRegulatoryModel model) {
            AccountCoRegulatory entity = new AccountCoRegulatory();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountCoRegulatoryModel doBackward(AccountCoRegulatory entity) {
            AccountCoRegulatoryModel model = new AccountCoRegulatoryModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
