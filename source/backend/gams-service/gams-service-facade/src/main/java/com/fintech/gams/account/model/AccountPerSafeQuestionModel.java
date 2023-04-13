package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountPerSafeQuestion;
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
 * @date 2019-08-02 11:28
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "个人安全问题 + 字典信息")
public class AccountPerSafeQuestionModel {

    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**安全问题(见数据字典数据安全问题类型)*/
    @NotNull(message = "安全问题(见数据字典数据安全问题类型)不可为空")
    @ApiModelProperty(value = "安全问题(见数据字典数据安全问题类型)", required = true)
    private Dictionary safeQuestion;

    /**答案*/
    @NotNull(message = "答案不可为空")
    @ApiModelProperty(value = "答案", required = true)
    private String answer;

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
    public AccountPerSafeQuestion convertToEntity(){
        AccountPerSafeQuestionModel.ModelConvert modelConvert = new AccountPerSafeQuestionModel.ModelConvert();
        AccountPerSafeQuestion entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountPerSafeQuestionModel convertToModel(AccountPerSafeQuestion entity){
        AccountPerSafeQuestionModel.ModelConvert modelConvert = new AccountPerSafeQuestionModel.ModelConvert();
        AccountPerSafeQuestionModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountPerSafeQuestionModel, AccountPerSafeQuestion> {
        @Override
        protected AccountPerSafeQuestion doForward(AccountPerSafeQuestionModel model) {
            AccountPerSafeQuestion entity = new AccountPerSafeQuestion();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountPerSafeQuestionModel doBackward(AccountPerSafeQuestion entity) {
            AccountPerSafeQuestionModel model = new AccountPerSafeQuestionModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }

}
