package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountPerRegulatory;
import com.fintech.gams.common.entity.Dictionary;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author yann
 * @date 2019-09-20 11:13
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "个人账户规管信息 + 字典信息")
public class AccountPerRegulatoryModel implements Serializable {
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**雇主*/
    @ApiModelProperty(value = "雇主")
    @Size(max = 1024)
    private String employer;

    /**账户持有人关系*/
    @ApiModelProperty(value = "账户持有人关系")
    @Size(max = 32)
    private Dictionary relationshipAccountHolders;

    /**与公司有关联的家庭成员*/
    @ApiModelProperty(value = "与公司有关联的家庭成员")
    @Size(max = 32)
    private String familyMembersCompany;

    /**账户持有人公司股票代码*/
    @ApiModelProperty(value = "账户持有人公司股票代码")
    @Size(max = 300)
    private String accountHolderCompanyStock;

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
    public AccountPerRegulatory convertToEntity(){
        AccountPerRegulatoryModel.ModelConvert modelConvert = new AccountPerRegulatoryModel.ModelConvert();
        AccountPerRegulatory entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountPerRegulatoryModel convertToModel(AccountPerRegulatory entity){
        AccountPerRegulatoryModel.ModelConvert modelConvert = new AccountPerRegulatoryModel.ModelConvert();
        AccountPerRegulatoryModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountPerRegulatoryModel, AccountPerRegulatory> {
        @Override
        protected AccountPerRegulatory doForward(AccountPerRegulatoryModel model) {
            AccountPerRegulatory entity = new AccountPerRegulatory();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountPerRegulatoryModel doBackward(AccountPerRegulatory entity) {
            AccountPerRegulatoryModel model = new AccountPerRegulatoryModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }

}
