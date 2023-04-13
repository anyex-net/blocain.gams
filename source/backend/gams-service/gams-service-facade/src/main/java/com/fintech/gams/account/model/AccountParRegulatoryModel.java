package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountParRegulatory;
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
 * @date 2019-08-08 15:15
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "合伙企业规管信息 + 字典")
public class AccountParRegulatoryModel {

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
    public AccountParRegulatory convertToEntity(){
        AccountParRegulatoryModel.ModelConvert modelConvert = new AccountParRegulatoryModel.ModelConvert();
        AccountParRegulatory entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountParRegulatoryModel convertToModel(AccountParRegulatory entity){
        AccountParRegulatoryModel.ModelConvert modelConvert = new AccountParRegulatoryModel.ModelConvert();
        AccountParRegulatoryModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountParRegulatoryModel, AccountParRegulatory> {
        @Override
        protected AccountParRegulatory doForward(AccountParRegulatoryModel model) {
            AccountParRegulatory entity = new AccountParRegulatory();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountParRegulatoryModel doBackward(AccountParRegulatory entity) {
            AccountParRegulatoryModel model = new AccountParRegulatoryModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
