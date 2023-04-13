package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountCoCrsControlNum;
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
 * @date 2019-08-14 9:59
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "公司CRS控权人自我证明控权人编号 + 字典")
public class AccountCoCrsControlNumModel {
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**实体编号*/
    @ApiModelProperty(value = "实体编号")
    private String entityNumber;

    /**实体类别（法人,信托,除信托以外的法律安排，见字典数据控权人类型）*/
    @NotNull(message = "实体类别（法人,信托,除信托以外的法律安排，见字典数据控权人类型）不可为空")
    @ApiModelProperty(value = "实体类别（法人,信托,除信托以外的法律安排，见字典数据控权人类型）", required = true)
    private Dictionary entityCategory;

    /**控权人类别（见字典数据控权人子节点）*/
    @NotNull(message = "控权人类别（见字典数据控权人子节点）不可为空")
    @ApiModelProperty(value = "控权人类别（见字典数据控权人子节点）", required = true)
    private List<Dictionary> controlCategory;

    /**第二部分,控权人的实体账户持有人的名称(公司名)*/
    @NotNull(message = "第二部分,控权人的实体账户持有人的名称(公司名)不可为空")
    @ApiModelProperty(value = "第二部分,控权人的实体账户持有人的名称(公司名)", required = true)
    private String entityAccountName;


    /**
     * 复制 model 到 entity
     * @return
     */
    public AccountCoCrsControlNum convertToEntity(){
        AccountCoCrsControlNumModel.ModelConvert modelConvert = new AccountCoCrsControlNumModel.ModelConvert();
        AccountCoCrsControlNum entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountCoCrsControlNumModel convertToModel(AccountCoCrsControlNum entity){
        AccountCoCrsControlNumModel.ModelConvert modelConvert = new AccountCoCrsControlNumModel.ModelConvert();
        AccountCoCrsControlNumModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountCoCrsControlNumModel, AccountCoCrsControlNum> {
        @Override
        protected AccountCoCrsControlNum doForward(AccountCoCrsControlNumModel model) {
            AccountCoCrsControlNum entity = new AccountCoCrsControlNum();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountCoCrsControlNumModel doBackward(AccountCoCrsControlNum entity) {
            AccountCoCrsControlNumModel model = new AccountCoCrsControlNumModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
