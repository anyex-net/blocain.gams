package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountParTaxes;
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
 * @date 2019-08-02 13:51
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "合伙企业税务居住地 + 字典信息")
public class AccountParTaxesModel {

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

    /**税务居住国家(见数据字典国家类型)*/
    @ApiModelProperty(value = "税务居住国家(见数据字典国家类型)")
    private Region taxResidenceCountry;

    /**税务居住地证件号码*/
    @ApiModelProperty(value = "税务居住地证件号码")
    private String taxResidenceIdCardNumber;

    /**无纳税识别号的理由选择*/
    @ApiModelProperty(value = "无纳税识别号的理由选择")
    private Dictionary noTaxIdentificationNumber;

    /**不同的注册号码*/
    @ApiModelProperty(value = "不同的注册号码")
    private String differentRegistrationNumber;

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
    public AccountParTaxes convertToEntity(){
        AccountParTaxesModel.ModelConvert modelConvert = new AccountParTaxesModel.ModelConvert();
        AccountParTaxes entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountParTaxesModel convertToModel(AccountParTaxes entity){
        AccountParTaxesModel.ModelConvert modelConvert = new AccountParTaxesModel.ModelConvert();
        AccountParTaxesModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountParTaxesModel, AccountParTaxes> {
        @Override
        protected AccountParTaxes doForward(AccountParTaxesModel model) {
            AccountParTaxes entity = new AccountParTaxes();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountParTaxesModel doBackward(AccountParTaxes entity) {
            AccountParTaxesModel model = new AccountParTaxesModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }

}
