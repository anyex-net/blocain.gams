package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountCoContact;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.entity.Region;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yann
 * @date 2019-08-02 11:38
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "公司联系信息 + 字典信息")
public class AccountCoContactModel {

    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**公司个人信息ID*/
    @ApiModelProperty(value = "公司个人信息ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long coPersonalId;

    /**国家(见数据字典国家类型)*/
    @NotNull(message = "国家(见数据字典国家类型)不可为空")
    @ApiModelProperty(value = "国家(见数据字典国家类型)", required = true)
    private Region country;

    /**号码类别 手机，电话，传真，家庭，商务(见字典电话号码类型)*/
    @NotNull(message = "号码类别 手机，电话，传真，家庭，商务(见字典电话号码类型)不可为空")
    @ApiModelProperty(value = "号码类别 手机，电话，传真，家庭，商务(见字典电话号码类型)", required = true)
    private Dictionary contactNumberType;

    /**联系号码*/
    @NotNull(message = "联系号码不可为空")
    @ApiModelProperty(value = "联系号码", required = true)
    private String contactNumber;


    /**成立国家（见区域表。法人股东需要）*/
    @ApiModelProperty(value = "成立国家（见区域表。法人股东需要）")
    @Size(max = 32)
    private Region foundingCountry;

    /**实体是否在监管交易所公开上市交易（法人股东需要）*/
    @ApiModelProperty(value = "实体是否在监管交易所公开上市交易（法人股东需要）")
    private Boolean status;

    /**交易所（法人股东需要）*/
    @ApiModelProperty(value = "交易所（法人股东需要）")
    @Size(max = 128)
    private String exchange;

    /**代码（法人股东需要）*/
    @ApiModelProperty(value = "代码（法人股东需要）")
    @Size(max = 128)
    private String code;

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
    public AccountCoContact convertToEntity(){
        AccountCoContactModel.ModelConvert modelConvert = new AccountCoContactModel.ModelConvert();
        AccountCoContact entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountCoContactModel convertToModel(AccountCoContact entity){
        AccountCoContactModel.ModelConvert modelConvert = new AccountCoContactModel.ModelConvert();
        AccountCoContactModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private  class ModelConvert extends Converter<AccountCoContactModel, AccountCoContact> {
        @Override
        protected AccountCoContact doForward(AccountCoContactModel model) {
            AccountCoContact entity = new AccountCoContact();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountCoContactModel doBackward(AccountCoContact entity) {
            AccountCoContactModel model = new AccountCoContactModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
