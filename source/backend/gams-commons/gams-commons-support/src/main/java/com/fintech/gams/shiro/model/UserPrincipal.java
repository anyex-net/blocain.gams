package com.fintech.gams.shiro.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fintech.gams.system.entity.RoleInfo;

/**
 * <p>File：UserPrincipal.java </p>
 * <p>Title: UserPrincipal </p>
 * <p>Description: UserPrincipal </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 20:41</p>
 * <p>Company: GAMS</p>
 *
 * @author playguy
 * @version 1.0
 */
public class UserPrincipal implements Serializable
{
    private static final long serialVersionUID = 191434150385861991L;
    
    /**
     * 主键编号
     */
    private Long              id;
    
    /**
     * 用户编号
     */
    private Long              unid;
    
    /**
     * 国家地区
     */
    private String            country;

    /**
     * 国家代码
     * 86 - 中国
     */
    private String            region;
    
    /**
     * 语言标识
     */
    private String            lang;
    
    /**
     * 帐号
     */
    private String            userName;
    
    /**
     * 昵称
     */
    private String            trueName;
    
    /**
     * google验证器私钥
     */
    private String            authKey;
    
    /**
     * 手机号码
     */
    private String            userMobile;
    
    /**
     * 电子邮箱
     */
    private String            userMail;
    
    /**
     * accessToken
     */
    private String            accessToken;
    
    /**
     * 角色和权限
     **/
    @JsonIgnore
    List<RoleInfo>            roles;
    
    /**
     * 机构ID集
     */
    @JsonIgnore
    List<Long>                orgIds;
    
    /**
     * DEFAULT CONSTRUCTOR
     *
     * @param id
     */
    public UserPrincipal(Long id)
    {
        this.id = id;
    }
    
    public UserPrincipal(Long id, String userName, String nickName, List<RoleInfo> roles, List<Long> orgIds)
    {
        this.id = id;
        this.userName = userName;
        this.trueName = nickName;
        this.roles = roles;
        this.orgIds = orgIds;
    }
    
    public UserPrincipal(Long id, Long unid, String userName, String nickName, String userMobile, String userMail, String lang, String country,String region, String authKey)
    {
        this.id = id;
        this.unid = unid;
        this.userName = userName;
        this.trueName = nickName;
        this.userMobile = userMobile;
        this.userMail = userMail;
        this.lang = lang;
        this.country = country;
        this.region = region;
        this.authKey = authKey;
    }
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public Long getUnid()
    {
        return unid;
    }
    
    public void setUnid(Long unid)
    {
        this.unid = unid;
    }
    
    public String getLang()
    {
        return lang;
    }
    
    public void setLang(String lang)
    {
        this.lang = lang;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getTrueName()
    {
        return trueName;
    }
    
    public void setTrueName(String nickName)
    {
        this.trueName = trueName;
    }
    
    public String getUserMobile()
    {
        return userMobile;
    }
    
    public void setUserMobile(String userMobile)
    {
        this.userMobile = userMobile;
    }
    
    public void setAuthKey(String authKey)
    {
        this.authKey = authKey;
    }
    
    public String getAuthKey()
    {
        return authKey;
    }
    
    public String getCountry()
    {
        return country;
    }
    
    public void setCountry(String country)
    {
        this.country = country;
    }
    
    public String getUserMail()
    {
        return userMail;
    }
    
    public void setUserMail(String userMail)
    {
        this.userMail = userMail;
    }
    
    public String getAccessToken()
    {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }
    
    public List<RoleInfo> getRoles()
    {
        return roles;
    }
    
    public void setRoles(List<RoleInfo> roles)
    {
        this.roles = roles;
    }
    
    public List<Long> getOrgIds()
    {
        return orgIds;
    }
    
    public void setOrgIds(List<Long> orgIds)
    {
        this.orgIds = orgIds;
    }
}
