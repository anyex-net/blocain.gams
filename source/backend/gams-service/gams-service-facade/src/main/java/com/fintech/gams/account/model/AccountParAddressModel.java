package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountParAddress;
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
 * @date 2019-08-02 13:49
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "合伙企业地址信息 + 字典信息")
public class AccountParAddressModel {

    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**合伙企业个人信息ID*/
    @NotNull(message = "合伙企业个人信息ID不可为空")
    @ApiModelProperty(value = "合伙企业个人信息ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parPersonalId;

    /**国家(见数据字典国家类型)*/
    @NotNull(message = "国家(见数据字典国家类型)不可为空")
    @ApiModelProperty(value = "国家(见数据字典国家类型)", required = true)
    private Region country;

    /**地址*/
    @NotNull(message = "地址不可为空")
    @ApiModelProperty(value = "地址", required = true)
    private String address;

    /**城市*/
    @NotNull(message = "城市不可为空")
    @ApiModelProperty(value = "城市", required = true)
    private String city;

    /**省*/
    @NotNull(message = "省不可为空")
    @ApiModelProperty(value = "省", required = true)
    private String rovince;

    /**邮编*/
    @NotNull(message = "邮编不可为空")
    @ApiModelProperty(value = "邮编", required = true)
    private String zipCode;

    /**居住，邮寄,公司(见数据字典地址类型)*/
    @NotNull(message = "居住，邮寄,公司(见数据字典地址类型)不可为空")
    @ApiModelProperty(value = "居住，邮寄,公司(见数据字典地址类型)", required = true)
    private String addressType;

    /**其他国家(是否和上方选的国家不同)(见数据字典国家类型)*/
    @ApiModelProperty(value = "其他国家(是否和上方选的国家不同)(见数据字典国家类型)")
    private Region otherCountry;

    /**其他出生国家(是否和上方选的国家不同)(见数据字典国家类型)*/
    @ApiModelProperty(value = "其他出生国家(是否和上方选的国家不同)(见数据字典国家类型)")
    private Region otherBirthCountry;

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
    public AccountParAddress convertToEntity(){
        AccountParAddressModel.ModelConvert modelConvert = new AccountParAddressModel.ModelConvert();
        AccountParAddress entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountParAddressModel convertToModel(AccountParAddress entity){
        AccountParAddressModel.ModelConvert modelConvert = new AccountParAddressModel.ModelConvert();
        AccountParAddressModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountParAddressModel, AccountParAddress> {
        @Override
        protected AccountParAddress doForward(AccountParAddressModel model) {
            AccountParAddress entity = new AccountParAddress();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountParAddressModel doBackward(AccountParAddress entity) {
            AccountParAddressModel model = new AccountParAddressModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
