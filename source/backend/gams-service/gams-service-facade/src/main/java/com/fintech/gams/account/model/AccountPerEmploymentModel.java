package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountPerEmployment;
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
 * @date 2019-08-02 10:10
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "个人账户雇佣信息 + 字典信息 ")
public class AccountPerEmploymentModel {
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**就业状态(见数据字典数据雇佣类型)*/
    @NotNull(message = "就业状态(见数据字典数据雇佣类型)不可为空")
    @ApiModelProperty(value = "就业状态(见数据字典数据雇佣类型)", required = true)
    private Dictionary employmentStatus;

    /**公司名*/
    @ApiModelProperty(value = "公司名")
    private String companyName;

    /**商业性质*/
    @ApiModelProperty(value = "商业性质")
    private Dictionary commercialNature;

    /**职位*/
    @ApiModelProperty(value = "职位")
    private Dictionary position;

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

    /**雇佣地址*/
    private AccountPerAddressModel accountPerAddress;


    /**
     * 复制 model 到 entity
     * @return
     */
    public AccountPerEmployment convertToEntity(){
        AccountPerEmploymentModel.ModelConvert modelConvert = new AccountPerEmploymentModel.ModelConvert();
        AccountPerEmployment entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountPerEmploymentModel convertToModel(AccountPerEmployment entity){
        AccountPerEmploymentModel.ModelConvert modelConvert = new AccountPerEmploymentModel.ModelConvert();
        AccountPerEmploymentModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountPerEmploymentModel, AccountPerEmployment> {
        @Override
        protected AccountPerEmployment doForward(AccountPerEmploymentModel model) {
            AccountPerEmployment entity = new AccountPerEmployment();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountPerEmploymentModel doBackward(AccountPerEmployment entity) {
            AccountPerEmploymentModel model = new AccountPerEmploymentModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }

}
