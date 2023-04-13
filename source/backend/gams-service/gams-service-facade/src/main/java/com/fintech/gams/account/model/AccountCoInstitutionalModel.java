package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountCoInstitutional;
import com.fintech.gams.common.entity.Dictionary;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yann
 * @date 2019-08-19 10:18
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "公司机构 + 字典")
public class AccountCoInstitutionalModel {
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**机构名称*/
    @NotNull(message = "机构名称不可为空")
    @ApiModelProperty(value = "机构名称", required = true)
    private String institutionalName;

    /**业务描述*/
    @ApiModelProperty(value = "业务描述")
    private String bsinessDescription;

    /**商业名称*/
    @ApiModelProperty(value = "商业名称")
    private String businessName;

    /**网址*/
    @NotNull(message = "网址不可为空")
    @ApiModelProperty(value = "网址", required = true)
    private String websiteUrl;

    /**机构性质(见数据字典机构性质)*/
    @NotNull(message = "机构性质(见数据字典机构性质)不可为空")
    @ApiModelProperty(value = "机构性质(见数据字典机构性质)", required = true)
    private List<Dictionary> istitutionalNature;

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
    public AccountCoInstitutional convertToEntity(){
        AccountCoInstitutionalModel.ModelConvert modelConvert = new AccountCoInstitutionalModel.ModelConvert();
        AccountCoInstitutional entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountCoInstitutionalModel convertToModel(AccountCoInstitutional entity){
        AccountCoInstitutionalModel.ModelConvert modelConvert = new AccountCoInstitutionalModel.ModelConvert();
        AccountCoInstitutionalModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountCoInstitutionalModel, AccountCoInstitutional> {
        @Override
        protected AccountCoInstitutional doForward(AccountCoInstitutionalModel model) {
            AccountCoInstitutional entity = new AccountCoInstitutional();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountCoInstitutionalModel doBackward(AccountCoInstitutional entity) {
            AccountCoInstitutionalModel model = new AccountCoInstitutionalModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }

}
