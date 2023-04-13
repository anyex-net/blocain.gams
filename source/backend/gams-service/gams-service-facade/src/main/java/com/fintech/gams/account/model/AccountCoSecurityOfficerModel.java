package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountCoSecurityOfficer;
import com.fintech.gams.common.entity.Dictionary;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author yann
 * @date 2019-08-08 13:25
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "公司安全官 + 字典数据")
public class AccountCoSecurityOfficerModel {

    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**先生，女士，博士，夫人(见数据字典称呼类型)*/
    @NotNull(message = "先生，女士，博士，夫人(见数据字典称呼类型)不可为空")
    @ApiModelProperty(value = "先生，女士，博士，夫人(见数据字典称呼类型)", required = true)
    private Dictionary appellation;

    /**姓*/
    @NotNull(message = "姓不可为空")
    @ApiModelProperty(value = "姓", required = true)
    private String lastName;

    /**中间名*/
    @ApiModelProperty(value = "中间名")
    private String middleName;

    /**名*/
    @NotNull(message = "名不可为空")
    @ApiModelProperty(value = "名", required = true)
    private String firstName;

    /**后缀*/
    @ApiModelProperty(value = "后缀")
    private String suffix;

    /**电子邮件*/
    @Email
    @NotNull(message = "电子邮件不可为空")
    @ApiModelProperty(value = "电子邮件", required = true)
    private String email;

    /**实体关系*/
    @ApiModelProperty(value = "实体关系")
    private String etityRelationship;

    /**授权人关系*/
    @ApiModelProperty(value = "授权人关系")
    private String authorizedRelationship;

    /**用户名(第八页需要)*/
    @ApiModelProperty(value = "用户名(第八页需要)")
    private String userName;

    /**密码(第八页)*/
    @ApiModelProperty(value = "密码(第八页)")
    private String password;

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
    public AccountCoSecurityOfficer convertToEntity(){
        AccountCoSecurityOfficerModel.ModelConvert modelConvert = new AccountCoSecurityOfficerModel.ModelConvert();
        AccountCoSecurityOfficer entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountCoSecurityOfficerModel convertToModel(AccountCoSecurityOfficer entity){
        AccountCoSecurityOfficerModel.ModelConvert modelConvert = new AccountCoSecurityOfficerModel.ModelConvert();
        AccountCoSecurityOfficerModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountCoSecurityOfficerModel, AccountCoSecurityOfficer> {
        @Override
        protected AccountCoSecurityOfficer doForward(AccountCoSecurityOfficerModel model) {
            AccountCoSecurityOfficer entity = new AccountCoSecurityOfficer();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountCoSecurityOfficerModel doBackward(AccountCoSecurityOfficer entity) {
            AccountCoSecurityOfficerModel model = new AccountCoSecurityOfficerModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }

}
