package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountParPersonal;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.entity.Region;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author yann
 * @date 2019-08-15 11:41
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "合伙企业个人信息 + 字典")
public class AccountParPersonalModel {


    @ApiModelProperty(value = "ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long Id;

    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**先生，女士，博士，夫人(见数据字典称呼类型)*/
    @NotNull(message = "先生，女士，博士，夫人(见数据字典称呼类型)不可为空")
    @ApiModelProperty(value = "先生，女士，博士，夫人(见数据字典称呼类型)", required = true)
    @Size(max = 32)
    private Dictionary appellation;

    /**姓*/
    @NotNull(message = "姓不可为空")
    @ApiModelProperty(value = "姓", required = true)
    @Size(max = 32)
    private String lastName;

    /**中间名*/
    @ApiModelProperty(value = "中间名")
    @Size(max = 32)
    private String middleName;

    /**名*/
    @NotNull(message = "名不可为空")
    @ApiModelProperty(value = "名", required = true)
    @Size(max = 32)
    private String firstName;

    /**后缀*/
    @ApiModelProperty(value = "后缀")
    @Size(max = 32)
    private String suffix;

    /**职位*/
    @ApiModelProperty(value = "职位")
    @Size(max = 32)
    private String position;

    /**生日*/
    @ApiModelProperty(value = "生日")
    private String birthday;

    /**电子邮件*/
    @Email
    @ApiModelProperty(value = "电子邮件")
    @Size(max = 32)
    private String email;

    /**所有权百分比*/
    @DecimalMax("100")
    @ApiModelProperty(value = "所有权百分比")
    private java.math.BigDecimal prcentageOfOwnership;

    /**国籍(见数据字典国家类型)*/
    @NotNull(message = "国籍(见数据字典国家类型)不可为空")
    @ApiModelProperty(value = "国籍(见数据字典国家类型)", required = true)
    @Size(max = 32)
    private Region nationality;

    /**出生国家(见数据字典国家类型)*/
    @NotNull(message = "出生国家(见数据字典国家类型)不可为空")
    @ApiModelProperty(value = "出生国家(见数据字典国家类型)", required = true)
    @Size(max = 32)
    private Region birthCountry;

    /**个人类型(见数据字典个人类型)*/
    @NotNull(message = "个人类型(见数据字典个人类型)不可为空")
    @ApiModelProperty(value = "个人类型(见数据字典个人类型)", required = true)
    @Size(max = 32)
    private Dictionary personalType;

    /**是否(经营负责人是有10%或25%所有权的人士吗?)*/
    @ApiModelProperty(value = "是否(经营负责人是有10%或25%所有权的人士吗?)")
    private Boolean isBusinessManager;

    /**是否美国纳税人*/
    @NotNull(message = "是否美国纳税人不可为空")
    @ApiModelProperty(value = "是否美国纳税人", required = true)
    private Boolean isAmericanTaxpayer;

    /**用户名(第八页需要)*/
    @ApiModelProperty(value = "用户名(第八页需要)")
    @Size(min = 4, max = 16)
    private String userName;

    /**密码(第八页)*/
    @ApiModelProperty(value = "密码(第八页)")
    @Size(min = 8, max = 16)
    private String password;

    /**实体名称（法人股东需要）*/
    @ApiModelProperty(value = "实体名称（法人股东需要）")
    @Size(max = 128)
    private String entityName;

    /**在公司持有哪些头寸（被授权人）*/
    @ApiModelProperty(value = "在公司持有哪些头寸（被授权人）")
    @Size(max = 128)
    private List<Dictionary> companyPosition;

    /**个人类型第二种身份(见数据字典个人类型，第九页引用需要)*/
    @ApiModelProperty(value = "个人类型第二种身份(见数据字典个人类型，第九页引用需要)")
    @Size(max = 64)
    private Dictionary personalTypeTwo;

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
    public AccountParPersonal convertToEntity(){
        AccountParPersonalModel.ModelConvert modelConvert = new AccountParPersonalModel.ModelConvert();
        AccountParPersonal entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountParPersonalModel convertToModel(AccountParPersonal entity){
        AccountParPersonalModel.ModelConvert modelConvert = new AccountParPersonalModel.ModelConvert();
        AccountParPersonalModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountParPersonalModel, AccountParPersonal> {
        @Override
        protected AccountParPersonal doForward(AccountParPersonalModel model) {
            AccountParPersonal entity = new AccountParPersonal();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountParPersonalModel doBackward(AccountParPersonal entity) {
            AccountParPersonalModel model = new AccountParPersonalModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
