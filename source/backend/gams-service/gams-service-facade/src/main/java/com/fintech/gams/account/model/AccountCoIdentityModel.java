package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountCoIdentity;
import com.fintech.gams.common.entity.Dictionary;
import com.google.common.base.Converter;
import com.fintech.gams.common.entity.Region;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;


import javax.validation.constraints.NotNull;

/**
 * @author yann
 * @date 2019-08-02 11:38
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "公司身份信息 + 字典信息")
public class AccountCoIdentityModel {
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**公司个人信息ID*/
    @ApiModelProperty(value = "公司个人信息ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long coPersonalId;

    /**签发国家*/
    @ApiModelProperty(value = "签发国家")
    private Region issuingCountry;

    /**身份证类型(见数据字典证件类型)*/
    @ApiModelProperty(value = "身份证类型(见数据字典证件类型)")
    private Dictionary idCardType;

    /**证件号码*/
    @ApiModelProperty(value = "证件号码")
    private String idNumber;

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
    public AccountCoIdentity convertToEntity(){
        AccountCoIdentityModel.ModelConvert modelConvert = new AccountCoIdentityModel.ModelConvert();
        AccountCoIdentity entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountCoIdentityModel convertToModel(AccountCoIdentity entity){
        AccountCoIdentityModel.ModelConvert modelConvert = new AccountCoIdentityModel.ModelConvert();
        AccountCoIdentityModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountCoIdentityModel, AccountCoIdentity> {
        @Override
        protected AccountCoIdentity doForward(AccountCoIdentityModel model) {
            AccountCoIdentity entity = new AccountCoIdentity();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountCoIdentityModel doBackward(AccountCoIdentity entity) {
            AccountCoIdentityModel model = new AccountCoIdentityModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
    
}
