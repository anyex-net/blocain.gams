package com.fintech.gams.utils;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.model.TreeModel;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.system.entity.Resources;
import com.fintech.gams.system.entity.RoleInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>File：OnLineUserUtils.java </p>
 * <p>Title: 在线用户工具类 </p>
 * <p>Description: OnLineUserUtils </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 20:50</p>
 * <p>Company: GAMS</p>
 *
 * @author playguy
 * @version 1.0
 */
@Slf4j
public class OnLineUserUtils
{
    private OnLineUserUtils()
    {
    }
    
    /**
     * 获取当前登录者对象
     */
    public static UserPrincipal getPrincipal()
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            Object object = subject.getPrincipal();
            if (null != object)
            { return (UserPrincipal) object; }
        }
        catch (UnavailableSecurityManagerException e)
        {
            log.error(e.getLocalizedMessage());
        }
        catch (NullPointerException e)
        {
            log.error(e.getLocalizedMessage());
        }
        catch (InvalidSessionException e)
        {
            log.error(e.getLocalizedMessage());
        }
        return null;
    }
    
    /**
     * 取用户对应的厂店信息
     *
     * @return
     */
    public static Long getId()
    {
        UserPrincipal principal = getPrincipal();
        return null != principal ? principal.getId() : null;
    }
    
    /**
     * 获取授权主要对象
     */
    public static Subject getSubject()
    {
        return SecurityUtils.getSubject();
    }
    
    /**
     * 取用户所拥有的机构Ids
     * @return
     */
    public static List<Long> getUserData()
    {
        UserPrincipal principal = getPrincipal();
        return principal.getOrgIds();
    }
    
    /**
     * 取当前用户的菜单
     *
     * @return
     * @throws BusinessException
     */
    public static List<TreeModel> getUserResources() throws BusinessException
    {
        TreeSet<TreeModel> zTreeModels = getAllData();
        List<TreeModel> treeList = Lists.newArrayList();
        for (TreeModel parent : zTreeModels)
        {
            if (null == parent.getPid() || 0L == parent.getPid())
            {
                treeList.add(parent);
            }
            for (TreeModel child : zTreeModels)
            {
                if (parent.getId().equals(child.getPid()))
                {
                    if (parent.getChildren() == null)
                    {
                        parent.setChildren(Lists.newArrayList(child));
                    }
                    else
                    {
                        parent.getChildren().add(child);
                    }
                }
            }
        }
        return treeList;
    }
    
    /**
     * 取去重后的所有数据
     *
     * @return {@link TreeSet <  TreeModel  >}
     * @throws BusinessException
     */
    protected static TreeSet<TreeModel> getAllData() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        { throw new BusinessException("用户未登陆"); }
        TreeSet<TreeModel> treeModels = Sets.newTreeSet(Comparator.comparingLong(TreeModel::getSort).thenComparingLong(TreeModel::getId));
        TreeModel menu;
        for (RoleInfo role : principal.getRoles())
        {
            for (Resources res : role.getResources())
            {
                if (res.getResType())
                {
                    continue;// 跳过权限
                }
                menu = new TreeModel();
                menu.setId(res.getId());
                menu.setPid(res.getParentId());
                menu.setText(res.getResName());
                menu.setIconCls(res.getResIcon());
                menu.setAttributes(res.getResUrl());
                menu.setSort(null == res.getSortNum() ? 99 : res.getSortNum());
                treeModels.add(menu);
            }
        }
        return treeModels;
    }
}
