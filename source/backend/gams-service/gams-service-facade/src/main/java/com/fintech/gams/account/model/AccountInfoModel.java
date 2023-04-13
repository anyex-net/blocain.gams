package com.fintech.gams.account.model;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class AccountInfoModel {

    /**账号编号*/
    private java.lang.Long unid;

    /**账户名*/
    private java.lang.String accountName;

    /**账户类型(数据字典)*/
    private java.lang.String accountType;

    /**邮箱*/
    @Email
    private java.lang.String email;

    /**国家代码*/
    private java.lang.String  region;

    /**手机号*/
    private java.lang.String mobile;

    /**安全验证策略*/
    private java.lang.Integer securityPolicy;

    /**个人账户显示【姓+名】，有限公司/合伙企业显示【公司名称】**/
    private String name;

    /**IB对应FamilyCode*/
    private String ibAccountCode;

    /**本位币*/
    private String baseCurrency;

    /**账户投资组合类型（见字典数据账户投资组合类型）*/
    private String accountPortfolioType;

    /**国家(见数据字典国家类型)*/
    @Size(max = 32)
    private String country;

    /**城市*/
    @Size(max = 128)
    private String city;

    /**省*/
    @Size(max = 128)
    private String rovince;
}
