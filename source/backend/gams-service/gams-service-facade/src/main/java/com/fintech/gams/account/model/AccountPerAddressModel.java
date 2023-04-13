package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountPerAddress;
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
 * @date 2019-07-31 13:52
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "个人账户地址信息 + 字典信息 ")
public class AccountPerAddressModel {


    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

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
    @ApiModelProperty(value = "邮编")
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
    public AccountPerAddress convertToEntity(){
        AccountPerAddressModel.ModelConvert modelConvert = new AccountPerAddressModel.ModelConvert();
        AccountPerAddress entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountPerAddressModel convertToModel(AccountPerAddress entity){
        AccountPerAddressModel.ModelConvert modelConvert = new AccountPerAddressModel.ModelConvert();
        AccountPerAddressModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountPerAddressModel, AccountPerAddress> {
        @Override
        protected AccountPerAddress doForward(AccountPerAddressModel model) {
            AccountPerAddress entity = new AccountPerAddress();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountPerAddressModel doBackward(AccountPerAddress entity) {
            AccountPerAddressModel model = new AccountPerAddressModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }

}
