package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountPerInvestTarget;
import com.fintech.gams.common.entity.Dictionary;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yann
 * @date 2019-08-06 10:13
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "个人投资目标 + 字典")
public class AccountPerInvestTargetModel {

    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**投资目标(见数据字典数据投资目标类型)*/
    @ApiModelProperty(value = "投资目标(见数据字典数据投资目标类型)")
    private List<Dictionary> investmentTarget;

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
    public AccountPerInvestTarget convertToEntity(){
        AccountPerInvestTargetModel.ModelConvert modelConvert = new AccountPerInvestTargetModel.ModelConvert();
        AccountPerInvestTarget entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountPerInvestTargetModel convertToModel(AccountPerInvestTarget entity){
        AccountPerInvestTargetModel.ModelConvert modelConvert = new AccountPerInvestTargetModel.ModelConvert();
        AccountPerInvestTargetModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountPerInvestTargetModel, AccountPerInvestTarget> {
        @Override
        protected AccountPerInvestTarget doForward(AccountPerInvestTargetModel model) {
            AccountPerInvestTarget entity = new AccountPerInvestTarget();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountPerInvestTargetModel doBackward(AccountPerInvestTarget entity) {
            AccountPerInvestTargetModel model = new AccountPerInvestTargetModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }

}
