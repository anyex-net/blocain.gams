package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountCoInvestmentTarget;
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
 * @date 2019-08-06 14:14
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "公司投资目标信息 + 字典")
public class AccountCoInvestmentTargetModel {
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**投资目标(见数据字典投资目标类型)*/
    @ApiModelProperty(value = "投资目标(见数据字典投资目标类型)")
    private List<Dictionary> investmentTarget;


    /**
     * 复制 model 到 entity
     * @return
     */
    public AccountCoInvestmentTarget convertToEntity(){
        AccountCoInvestmentTargetModel.ModelConvert modelConvert = new AccountCoInvestmentTargetModel.ModelConvert();
        AccountCoInvestmentTarget entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountCoInvestmentTargetModel convertToModel(AccountCoInvestmentTarget entity){
        AccountCoInvestmentTargetModel.ModelConvert modelConvert = new AccountCoInvestmentTargetModel.ModelConvert();
        AccountCoInvestmentTargetModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountCoInvestmentTargetModel, AccountCoInvestmentTarget> {
        @Override
        protected AccountCoInvestmentTarget doForward(AccountCoInvestmentTargetModel model) {
            AccountCoInvestmentTarget entity = new AccountCoInvestmentTarget();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountCoInvestmentTargetModel doBackward(AccountCoInvestmentTarget entity) {
            AccountCoInvestmentTargetModel model = new AccountCoInvestmentTargetModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
