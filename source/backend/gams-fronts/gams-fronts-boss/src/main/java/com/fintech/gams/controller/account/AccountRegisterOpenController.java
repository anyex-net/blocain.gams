package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.entity.AccountCoAccountPortfolio;
import com.fintech.gams.account.entity.AccountParAccountPortfolio;
import com.fintech.gams.account.entity.AccountPerPortfolio;
import com.fintech.gams.account.model.AccountModel;
import com.fintech.gams.account.service.AccountCoAccountPortfolioService;
import com.fintech.gams.account.service.AccountParAccountPortfolioService;
import com.fintech.gams.account.service.AccountPerPortfolioService;
import com.fintech.gams.account.service.AccountService;
import com.fintech.gams.bean.*;
import com.fintech.gams.common.entity.Region;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * AccountRegisterOpenController
 * <p>File: AccountRegisterOpenController.java </p>
 * <p>Title: AccountRegisterOpenController </p>
 * <p>Description: AccountRegisterOpenController </p>
 * <p>Copyright: Copyright (c) 2019-05-29</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.ACCOUNT)
@Api(description = "帐户开户管理")
public class AccountRegisterOpenController extends GenericController
{
    @Autowired(required = false)
    private AccountService  accountService;

    @Autowired(required = false)
    private RegionService regionService;

    @Autowired(required = false)
    private DictionaryService dictionaryService;

    @Autowired(required = false)
    private AccountPerPortfolioService accountPerPortfolioService;

    @Autowired(required = false)
    private AccountCoAccountPortfolioService accountCoAccountPortfolioService;

    @Autowired(required = false)
    private AccountParAccountPortfolioService accountParAccountPortfolioService;

    @Autowired(required = false)
    private AmazonS3 amazonS3;

    @PostMapping(value = "/registerOpen/approvedRegister")
    @RequiresPermissions("account:registerOpen:operator")
    @ApiOperation(value = "根据ID更新注册开户状态为通过审核", httpMethod = "POST")
    public JsonMessage updateRegisterStatus(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountService.approvedRegister(id));
    }

    @PostMapping(value = "/registerOpen/findAccountList")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据账户名和开户状态取帐户列表", httpMethod = "POST")
    public JsonMessage findAccountList(Pagination pagin,String accountName,Integer registerStatus,String lang) throws BusinessException
    {
        if(registerStatus == null){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountInfo(accountService.findAccountList(pagin,accountName,registerStatus),lang));
    }

    @GetMapping(value = "/registerOpen/getPresignedUrl")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "获取开户文件预签名URL", httpMethod = "GET")
    public JsonMessage generatePresignedUrl(String url) throws BusinessException
    {
        if (StringUtils.isBlank(url)) url = "";
        String amazonaws=".amazonaws.com/";
        String s3=".s3-";
        String http="https://";
        String bucketName = url.substring(0, url.indexOf(s3)).substring(http.length());
        String fileName =url.substring( url.indexOf(amazonaws)+amazonaws.length());
        if(fileName.substring(0,11).endsWith("AccountFile")==false){
            return getJsonMessage(CommonEnums.SUCCESS,url);
        }
        return getJsonMessage(CommonEnums.SUCCESS,amazonS3.generatePresignedUrl(bucketName,fileName));
    }

    /**
     * 获取账户字典信息
     * @param page
     * @return
     */
    private PaginateResult<AccountModel> getAccountInfo( PaginateResult<Account> page,String lang){
        List<Account> accounts=page.getList();
        List<AccountModel> accountModels = new ArrayList<>();
        for(Account account:accounts){
            AccountModel accountModel = new AccountModel()
                    .convertToModel(account)
                    .setAccountType(dictionaryService.findDictionary("accountType",account.getAccountType(),lang));
            //账户国家
            if(account.getCountry() != null){
                Region region = new Region();
                region.setsCode(account.getCountry());
                List<Region> regions = regionService.findList(region);
                if(regions.isEmpty()==false){
                    accountModel.setCountry(regionService.findList(region).get(0));
                }
            }
            //根据accountId查询 个人账户投资组合
            if(account.getAccountType().endsWith("personal")){
                AccountPerPortfolio accountPerPortfolio = new AccountPerPortfolio();
                accountPerPortfolio.setAccountId(account.getId());
                List<AccountPerPortfolio> accountPerPortfolios = accountPerPortfolioService.findList(accountPerPortfolio);
                if(accountPerPortfolios.isEmpty()==false){
                    accountModel.setAccountPortfolioType(dictionaryService.findDictionary("accountPortfolioType",accountPerPortfolios.get(0).getAccountPortfolioType(),lang));
                }
            }
            //根据accountId查询 公司账户投资组合
            if(account.getAccountType().endsWith("limitedLiablity")){
                AccountCoAccountPortfolio accountCoAccountPortfolio = new AccountCoAccountPortfolio();
                accountCoAccountPortfolio.setAccountId(account.getId());
                List<AccountCoAccountPortfolio> accountCoAccountPortfolios = accountCoAccountPortfolioService.findList(accountCoAccountPortfolio);
                if(accountCoAccountPortfolios.isEmpty()==false){
                    accountModel.setAccountPortfolioType(dictionaryService.findDictionary("accountPortfolioType",accountCoAccountPortfolios.get(0).getAccountPortfolioType(),lang));
                }
            }
            //根据accountId查询 企业账户投资组合
            if(account.getAccountType().endsWith("partnerShip")){
                AccountParAccountPortfolio accountParAccountPortfolio = new AccountParAccountPortfolio();
                accountParAccountPortfolio.setAccountId(account.getId());
                List<AccountParAccountPortfolio> accountParAccountPortfolios = accountParAccountPortfolioService.findList(accountParAccountPortfolio);
                if(accountParAccountPortfolios.isEmpty()==false){
                    accountModel.setAccountPortfolioType(dictionaryService.findDictionary("accountPortfolioType",accountParAccountPortfolios.get(0).getAccountPortfolioType(),lang));
                }
            }
            accountModels.add(accountModel);
        }
        return  new PaginateResult<>(page.getPage(), accountModels);
    }

    /**
     * 验证字典的语言类型
     * @param lang
     * @return
     */
    private String dictLangParameterVerification(String lang){
        return StringUtils.isBlank(lang) ? "zh_CN" : lang;
    }

}
