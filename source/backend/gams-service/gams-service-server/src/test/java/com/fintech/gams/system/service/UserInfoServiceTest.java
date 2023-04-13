package com.fintech.gams.system.service;

import com.fintech.gams.BaseServiceImplTest;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.system.entity.UserInfo;
import com.fintech.gams.utils.EncryptUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * UserInfoServiceTest
 * <p>File: UserInfoServiceTest.java </p>
 * <p>Title: UserInfoServiceTest </p>
 * <p>Description: UserInfoServiceTest </p>
 * <p>Copyright: Copyright (c) 2019-05-30</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class UserInfoServiceTest extends BaseServiceImplTest
{
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 集合查询Userinfo测试
     * @throws BusinessException
     */
    @Test
    public void findListUserInfo() throws BusinessException
    {
        UserInfo userInfoSelect = new UserInfo();
        userInfoSelect.setUserName("admin");
        userInfoSelect.setPassword("38fd398f2e5f3ad8a5cbbb510c82dd1c09e13d04839ead2ee298efa0");
        //userInfoSelect.setGender(TRUE);
        //userInfoSelect.setActive(TRUE);

        List<UserInfo> listUserinfos = userInfoService.findList(userInfoSelect);
        for(int i=0; i< listUserinfos.size(); i++)
        {
            System.out.println("userInfo:" + listUserinfos.get(i).toString() );
        }
    }

    /**
     * 新增插入Userinfo测试
     * @throws BusinessException
     */
    @Test
    public void insertUserInfo() throws BusinessException
    {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("test");
        userInfo.setTrueName("测试员");
        userInfo.setPassword("123456");
        userInfo.setGender(0);
        userInfo.setActive(TRUE);
        userInfoService.save(userInfo);
    }

    /**
     * 更新修改Userinfo测试
     * @throws BusinessException
     */
    @Test
    public void updateUserInfo() throws BusinessException
    {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(200000000000L);
        userInfo.setUserName("admin");
        userInfo.setTrueName("管理员");
        userInfo.setPassword("123456");
        userInfo.setGender(0);
        userInfo.setActive(TRUE);
        userInfoService.save(userInfo);
    }

    /**
     * 逻辑删除Userinfo测试
     * @throws BusinessException
     */
    @Test
    public void deleteUserInfo() throws BusinessException
    {
        UserInfo userInfo = userInfoService.selectByPrimaryKey(228790950124589059l);
        if(null != userInfo)
        {
            System.out.println("userInfo:" + userInfo.toString() );

            userInfoService.delete(userInfo.getId());
        }
    }

    /**
     * 物理删除Userinfo测试
     * @throws BusinessException
     */
    @Test
    public void removeUserInfo() throws BusinessException
    {
        UserInfo userInfo = userInfoService.selectByPrimaryKey(228790950124589056l);
        if(null != userInfo)
        {
            System.out.println("userInfo:" + userInfo.toString() );

            userInfoService.remove(userInfo.getId());
        }
    }
}
