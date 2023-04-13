package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountPerContact;
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
 * @date 2019-08-02 10:18
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "个人账户联系方式 + 字典表信息")
public class AccountPerContactModel {


    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**联系号码*/
    @NotNull(message = "联系号码不可为空")
    @ApiModelProperty(value = "联系号码", required = true)
    private String contactNumber;

    /**号码类别 手机，电话，传真，家庭，商务(见数据字典电话号码类型)*/
    @NotNull(message = "号码类别 手机，电话，传真，家庭，商务(见数据字典电话号码类型)不可为空")
    @ApiModelProperty(value = "号码类别 手机，电话，传真，家庭，商务(见数据字典电话号码类型)", required = true)
    private Dictionary contactNumberType;

    /**国家(见数据字典国家类型)*/
    @NotNull(message = "国家(见数据字典国家类型)不可为空")
    @ApiModelProperty(value = "国家(见数据字典国家类型)", required = true)
    private Region country;

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
    public AccountPerContact convertToEntity(){
        AccountPerContactModel.ModelConvert modelConvert = new AccountPerContactModel.ModelConvert();
        AccountPerContact entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountPerContactModel convertToModel(AccountPerContact entity){
        AccountPerContactModel.ModelConvert modelConvert = new AccountPerContactModel.ModelConvert();
        AccountPerContactModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountPerContactModel, AccountPerContact> {
        @Override
        protected AccountPerContact doForward(AccountPerContactModel model) {
            AccountPerContact entity = new AccountPerContact();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountPerContactModel doBackward(AccountPerContact entity) {
            AccountPerContactModel model = new AccountPerContactModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
