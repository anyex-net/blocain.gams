package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountPerIdentity;
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
 * @date 2019-08-02 10:35
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "个人账户身份信息 + 字典信息 ")
public class AccountPerIdentityModel {


    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**签发国家*/
    @ApiModelProperty(value = "签发国家")
    private Region issuingCountry;

    /**身份证类型(见数据字典数据证件类型)*/
    @ApiModelProperty(value = "身份证类型(见数据字典数据证件类型)")
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
    public AccountPerIdentity convertToEntity(){
        AccountPerIdentityModel.ModelConvert modelConvert = new AccountPerIdentityModel.ModelConvert();
        AccountPerIdentity entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountPerIdentityModel convertToModel(AccountPerIdentity entity){
        AccountPerIdentityModel.ModelConvert modelConvert = new AccountPerIdentityModel.ModelConvert();
        AccountPerIdentityModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountPerIdentityModel, AccountPerIdentity> {
        @Override
        protected AccountPerIdentity doForward(AccountPerIdentityModel model) {
            AccountPerIdentity entity = new AccountPerIdentity();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountPerIdentityModel doBackward(AccountPerIdentity entity) {
            AccountPerIdentityModel model = new AccountPerIdentityModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }


}
