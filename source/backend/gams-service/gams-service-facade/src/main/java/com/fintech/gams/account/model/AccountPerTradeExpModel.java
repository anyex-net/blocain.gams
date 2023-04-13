package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.account.entity.AccountPerTradeExp;
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
 * @date 2019-08-02 14:15
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "个人交易经验信息 + 字典信息")
public class AccountPerTradeExpModel {

    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**交易经验(见数据字典数据交易经验)*/
    @ApiModelProperty(value = "交易经验(见数据字典数据交易经验)")
    private Dictionary tradingExperience;

    /**年交易数(见数据字典数据每年交易笔数)*/
    @ApiModelProperty(value = "年交易数(见数据字典数据每年交易笔数)")
    private Dictionary yearTransactionsPerNumber;

    /**总交易数*/
    @ApiModelProperty(value = "总交易数")
    private String transactionsTotalNumber;

    /**知识水平(见数据字典数据知识水平)*/
    @ApiModelProperty(value = "知识水平(见数据字典数据知识水平)")
    private Dictionary knowledgeLevel;

    /**交易地点*/
    @ApiModelProperty(value = "交易地点")
    private List<AccountCommonTradeLocation> tradingLocation;

    /**交易类型(见数据字典数据交易类型)*/
    @ApiModelProperty(value = "交易类型(见数据字典数据交易类型)")
    private Dictionary tansactionType;

    /**受交易权限*/
    @ApiModelProperty(value = "受交易权限")
    private String transactionAuthority;

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
    public AccountPerTradeExp convertToEntity(){
        AccountPerTradeExpModel.ModelConvert modelConvert = new AccountPerTradeExpModel.ModelConvert();
        AccountPerTradeExp entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountPerTradeExpModel convertToModel(AccountPerTradeExp entity){
        AccountPerTradeExpModel.ModelConvert modelConvert = new AccountPerTradeExpModel.ModelConvert();
        AccountPerTradeExpModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountPerTradeExpModel, AccountPerTradeExp> {
        @Override
        protected AccountPerTradeExp doForward(AccountPerTradeExpModel model) {
            AccountPerTradeExp entity = new AccountPerTradeExp();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountPerTradeExpModel doBackward(AccountPerTradeExp entity) {
            AccountPerTradeExpModel model = new AccountPerTradeExpModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
