package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.Account;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.entity.Region;
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
 * @date 2019-08-02 16:45
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "账户表 + 字典")
public class AccountModel {

    /**
     * 主键编号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long   id;

    /**账号编号*/
    //@NotNull(message = "账号编号不可为空")
    //@ApiModelProperty(value = "账号编号", required = true)
    @ApiModelProperty(value = "账号编号")
    private java.lang.Long unid;

    /**账户类型(数据字典)*/
    @NotNull(message = "账户类型(数据字典)不可为空")
    @ApiModelProperty(value = "账户类型(数据字典)", required = true)
    private Dictionary accountType;

    /**账户名*/
    @NotNull(message = "账户名不可为空")
    @ApiModelProperty(value = "账户名", required = true)
    private java.lang.String accountName;

    /**帐户昵称*/
    @ApiModelProperty(value = "帐户昵称")
    private java.lang.String nickName;

    /**邮箱*/
    @Email
    @NotNull(message = "邮箱不可为空")
    @ApiModelProperty(value = "邮箱", required = true)
    private java.lang.String email;

    /**手机号*/
    @ApiModelProperty(value = "手机号")
    private java.lang.String mobile;

    /**注册所在地或所在IP*/
    @ApiModelProperty(value = "注册所在地或所在IP")
    private java.lang.String location;

    /**国家地区(区域表)*/
    @NotNull(message = "国家地区(区域表)不可为空")
    @ApiModelProperty(value = "国家地区(区域表)", required = true)
    private Region country;

    /**合法居住国家地区(区域表)*/
    @ApiModelProperty(value = "合法居住国家地区(区域表)")
    private java.lang.String nationality;

    /**语言标识*/
    @ApiModelProperty(value = "语言标识")
    private java.lang.String lang;


    /**账户状态(0正常、1冻结、2注销)*/
    //@NotNull(message = "账户状态(0正常、1冻结、2注销)不可为空")
    //@ApiModelProperty(value = "账户状态(0正常、1冻结、2注销)", required = true)
    @ApiModelProperty(value = "账户状态(0正常、1冻结、2注销)")
    private java.lang.Integer status;


    /**注册开户状态(0 表示开户ok 未审核, 1 表示开户OK 并且审核通过,注册过程 写负数(页数))*/
    @ApiModelProperty(value = "注册开户状态(0 表示开户ok 未审核, 1 表示开户OK 并且审核通过,注册过程 写负数(页数))")
    private java.lang.Integer registerStatus;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;

    /**账户投资组合类型（见字典数据账户投资组合类型）*/
    @NotNull(message = "账户投资组合类型（见字典数据账户投资组合类型）不可为空")
    @ApiModelProperty(value = "账户投资组合类型（见字典数据账户投资组合类型）", required = true)
    private Dictionary accountPortfolioType;

    /**国家代码*/
    @ApiModelProperty(value = "国家代码")
    private java.lang.String  region;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private java.lang.Long createDate;

    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private java.lang.Long updateDate;

    /**
     * 复制 model 到 entity
     * @return
     */
    public Account convertToEntity(){
        AccountModel.ModelConvert modelConvert = new AccountModel.ModelConvert();
        Account entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountModel convertToModel(Account entity){
        AccountModel.ModelConvert modelConvert = new AccountModel.ModelConvert();
        AccountModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountModel, Account> {
        @Override
        protected Account doForward(AccountModel model) {
            Account entity = new Account();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountModel doBackward(Account entity) {
            AccountModel model = new AccountModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
