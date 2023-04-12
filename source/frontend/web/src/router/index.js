import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

/* Layout */
import Layout from '@/layout';

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true },
  {
    path: '/404',
    name: 'Page404',
    component: () => import('@/views/404'),
    hidden: true
  },
  // registered account
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/account/register/index'),
    redirect: '/register/openType',
    meta: { title: '开立账户' },
    children: [
      {
        path: 'openType',
        name: 'OpenType',
        component: () => import('@/views/account/register/signIn/openType'),
        meta: { title: '注册类型' }
      },
      {
        path: 'signIn',
        name: 'SignIn',
        component: () => import('@/views/account/register/signIn/signIn'),
        meta: { title: '注册' }
      },
      {
        path: 'activate',
        name: 'Activate',
        component: () => import('@/views/account/register/signIn/activate'),
        meta: { title: '邮箱激活' }
      }
    ]
  },

  // to open an account
  {
    path: '/openAccount',
    name: 'OpenAccount',
    component: () => import('@/views/account/openAccount/index'),
    redirect: '/openAccount/person1',
    meta: { title: '开立账户' },
    children: [
      {
        path: 'person1',
        name: 'Person1',
        component: () => import('@/views/account/openAccount/apply/person/person1'),
        meta: { title: '个人申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'person2',
        name: 'Person2',
        component: () => import('@/views/account/openAccount/apply/person/person2'),
        meta: { title: '个人申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'person3',
        name: 'Person3',
        component: () => import('@/views/account/openAccount/apply/person/person3'),
        meta: { title: '个人申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'person4',
        name: 'Person4',
        component: () => import('@/views/account/openAccount/apply/person/person4'),
        meta: { title: '个人申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'person5',
        name: 'Person5',
        component: () => import('@/views/account/openAccount/apply/person/person5'),
        meta: { title: '个人申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'person6',
        name: 'Person6',
        component: () => import('@/views/account/openAccount/apply/person/person6'),
        meta: { title: '个人申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'person7',
        name: 'Person7',
        component: () => import('@/views/account/openAccount/apply/person/person7'),
        meta: { title: '个人申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'person8',
        name: 'Person8',
        component: () => import('@/views/account/openAccount/apply/person/person8'),
        meta: { title: '个人申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'person9',
        name: 'Person9',
        component: () => import('@/views/account/openAccount/apply/person/person9'),
        meta: { title: '个人申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'person10',
        name: 'Person10',
        component: () => import('@/views/account/openAccount/apply/person/person10'),
        meta: { title: '个人申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company1',
        name: 'Company1',
        component: () => import('@/views/account/openAccount/apply/company/company1'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company2',
        name: 'Company2',
        component: () => import('@/views/account/openAccount/apply/company/company2'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company3',
        name: 'Company3',
        component: () => import('@/views/account/openAccount/apply/company/company3'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company4',
        name: 'Company4',
        component: () => import('@/views/account/openAccount/apply/company/company4'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      // {
      //   path:'company5',
      //   name: 'Company5',
      //   component: () => import('@/views/account/openAccount/apply/company/company5'),
      //   meta: { title: '有限公司申请' }
      // },
      // {
      //   path:'company6',
      //   name: 'Company6',
      //   component: () => import('@/views/account/openAccount/apply/company/company6'),
      //   meta: { title: '有限公司申请' }
      // },

      {
        path: 'company7',
        name: 'Company7',
        component: () => import('@/views/account/openAccount/apply/company/company7'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company8',
        name: 'Company8',
        component: () => import('@/views/account/openAccount/apply/company/company8'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company9',
        name: 'Company9',
        component: () => import('@/views/account/openAccount/apply/company/company9'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company10',
        name: 'Company10',
        component: () => import('@/views/account/openAccount/apply/company/company10'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company11a',
        name: 'Company11a',
        component: () => import('@/views/account/openAccount/apply/company/company11a'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company11b',
        name: 'Company11b',
        component: () => import('@/views/account/openAccount/apply/company/company11b'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company12',
        name: 'Company12',
        component: () => import('@/views/account/openAccount/apply/company/company12'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company13',
        name: 'Company13',
        component: () => import('@/views/account/openAccount/apply/company/company13'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company14',
        name: 'Company14',
        component: () => import('@/views/account/openAccount/apply/company/company14'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company15',
        name: 'Company15',
        component: () => import('@/views/account/openAccount/apply/company/company15'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company16',
        name: 'Company16',
        component: () => import('@/views/account/openAccount/apply/company/company16'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company17',
        name: 'Company17',
        component: () => import('@/views/account/openAccount/apply/company/company17'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company18',
        name: 'Company18',
        component: () => import('@/views/account/openAccount/apply/company/company18'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'company19',
        name: 'Company19',
        component: () => import('@/views/account/openAccount/apply/company/company19'),
        meta: { title: '有限公司申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership1',
        name: 'Partnership1',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership1'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership2',
        name: 'Partnership2',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership2'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership3',
        name: 'Partnership3',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership3'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership4',
        name: 'Partnership4',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership4'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership5',
        name: 'Partnership5',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership5'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership6',
        name: 'Partnership6',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership6'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership7',
        name: 'Partnership7',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership7'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership8',
        name: 'Partnership8',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership8'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership9',
        name: 'Partnership9',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership9'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership10',
        name: 'Partnership10',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership10'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership11a',
        name: 'Partnership11a',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership11a'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership11b',
        name: 'Partnership11b',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership11b'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership12',
        name: 'Partnership12',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership12'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership13',
        name: 'Partnership13',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership13'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership14',
        name: 'Partnership14',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership14'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership15',
        name: 'Partnership15',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership15'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership16',
        name: 'Partnership16',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership16'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership17',
        name: 'Partnership17',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership17'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership18',
        name: 'Partnership18',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership18'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      },
      {
        path: 'partnership19',
        name: 'Partnership19',
        component: () => import('@/views/account/openAccount/apply/partnership/partnership19'),
        meta: { title: '合伙企业申请', requiresAuth: true, routerPermissions: true }
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/account/login'),
    meta: { title: '登录' }
  }, {
    path: '/deny/:message',
    props: true,
    name: 'Deny',
    component: () => import('@/views/account/login/deny'),
    meta: { title: '授权失败' }
  },
  {
    path: '/',
    component: Layout,
    redirect: '/index',
    children: [{
      path: 'index',
      name: 'Index',
      component: () => import('@/views/index'),
      meta: { title: '首页' }
    }, {
      path: 'about',
      name: 'About',
      redirect: '/about/introduction',
      component: () => import('@/views/common/about'),
      children: [{
        path: 'introduction',
        name: 'Introduction',
        component: () => import('@/views/common/about/module/introduction'),
        meta: { title: '公司简介' }
      }, {
        path: 'reportList',
        name: 'ReportList',
        component: () => import('@/views/common/about/module/reportList'),
        meta: { title: '媒体报道' }
      }, {
        path: 'reportDetail',
        name: 'ReportDetail',
        component: () => import('@/views/common/about/module/reportDetail'),
        meta: { title: '报道详情' }
      }, {
        path: 'development',
        name: 'Development',
        component: () => import('@/views/common/about/module/development'),
        meta: { title: '发展历程' }
      }, {
        path: 'contactUs',
        name: 'ContactUs',
        component: () => import('@/views/common/about/module/contactUs'),
        meta: { title: '联系我们' }
      }]
    // }, {
    //   path: 'express',
    //   name: 'Express',
    //   redirect: '/express/list',
    //   component: () => import('@/views/common/express'),
    //   children: [{
    //     path: 'list',
    //     name: 'List',
    //     component: () => import('@/views/common/express/module/list'),
    //     meta: { title: '市场快讯' },
    //     children: [{
    //       path: 'recommendChild',
    //       name: 'RecommendChild',
    //       component: () => import('@/views/common/express/module/navItems/commonChild/recommendChild'),
    //       meta: { title: '新闻详情' }
    //     }]
    //   }, {
    //     path: 'detail/:expressId',
    //     props: true,
    //     name: 'Detail',
    //     component: () => import('@/views/common/express/module/detail'),
    //     meta: { title: '快讯详情' }
    //   }]
    }, {
      path: 'fee',
      name: 'Fee',
      redirect: '/fee/northAmericanMarket',
      component: () => import('@/views/common/fee/index'),
      children: [{
        path: 'northAmericanMarket',
        name: 'NorthAmericanMarket',
        component: () => import('@/views/common/fee/module/NorthAmericanMarket'),
        meta: { title: '北美市场' }
      }, {
        path: 'asianMarket',
        name: 'AsianMarket',
        component: () => import('@/views/common/fee/module/AsianMarket'),
        meta: { title: '亚洲市场' }
      }, {
        path: 'europeanMarket',
        name: 'EuropeanMarket',
        component: () => import('@/views/common/fee/module/EuropeanMarket'),
        meta: { title: '欧洲市场' }
      }],
      meta: { title: '费用' }
    }, {
      path: 'download',
      name: 'Download',
      component: () => import('@/views/common/download/index'),
      meta: { title: '下载' }
    }, {
      path: 'product',
      name: 'Product',
      redirect: '/product/stock',
      component: () => import('@/views/common/product'),
      children: [{
        path: 'stock',
        name: 'Stock',
        component: () => import('@/views/common/product/module/stock'),
        meta: { title: '股票行情' }
      }, {
        path: 'fund',
        name: 'Fund',
        component: () => import('@/views/common/product/module/fund'),
        meta: { title: '基金行情' }
      }, {
        path: 'foreignEx',
        name: 'ForeignEx',
        component: () => import('@/views/common/product/module/foreignEx'),
        meta: { title: '外汇行情' }
      }, {
        path: 'option',
        name: 'Option',
        component: () => import('@/views/common/product/module/option'),
        meta: { title: '期权行情' }
      }, {
        path: 'futures',
        name: 'Futures',
        component: () => import('@/views/common/product/module/futures'),
        meta: { title: '期货行情' }
      }, {
        path: 'bond',
        name: 'Bond',
        component: () => import('@/views/common/product/module/bond'),
        meta: { title: '债券行情' }
      }]
    }, {
      path: 'transaction',
      name: 'Transaction',
      component: () => import('@/views/common/content/transaction'),
      meta: { title: '美股智能化交易' }
    }, {
      path: 'cornucopia',
      name: 'Cornucopia',
      component: () => import('@/views/common/content/cornucopia'),
      meta: { title: '国瑞理财"聚宝盆"' }
    }, {
      path: 'foreignExchange',
      name: 'ForeignExchange',
      component: () => import('@/views/common/content/foreignExchange'),
      meta: { title: '外汇服务' }
    }, {
      path: 'insuranceServices',
      name: 'InsuranceServices',
      component: () => import('@/views/common/content/insuranceServices'),
      meta: { title: '保险服务' }
    }, {
      path: 'bondServices',
      name: 'BondServices',
      component: () => import('@/views/common/content/bondServices'),
      meta: { title: '债券服务' }
    // }, {
    //   path: 'help',
    //   name: 'Help',
    //   redirect: '/help/searchHomepage',
    //   component: () => import('@/views/common/help/index'),
    //   children: [
    //     {
    //       path: 'searchHomepage',
    //       name: 'SearchHomepage',
    //       component: () => import('@/views/common/help/module/searchHomepage'),
    //       meta: { title: '帮助' }
    //     },
    //     {
    //       path: 'searchResult',
    //       name: 'SearchResult',
    //       component: () => import('@/views/common/help/module/searchResult'),
    //       meta: { title: '搜索结果' }
    //     },
    //     {
    //       path: 'classifyFind',
    //       name: 'ClassifyFind',
    //       component: () => import('@/views/common/help/module/classifyFind'),
    //       children: [
    //         {
    //           path: 'classifyFindChild',
    //           name: 'ClassifyFindChild',
    //           component: () => import('@/views/common/help/module/commonChild/classifyFindChild'),
    //           meta: { title: '问题类型' }
    //         }
    //       ]
    //     }
    //   ]
    }, {
      path: 'agreement',
      name: 'Agreement',
      redirect: '/agreement/disclaimer',
      component: () => import('@/views/common/agreement'),
      children: [{
        path: 'disclaimer',
        name: 'Disclaimer',
        component: () => import('@/views/common/agreement/module/disclaimer'),
        meta: { title: '免责声明' }
      }, {
        path: 'registrationAgreement',
        name: 'RegistrationAgreement',
        component: () => import('@/views/common/agreement/module/registrationAgreement'),
        meta: { title: '注册协议' }
      }]
    }, {
      path: 'financialCommodityService',
      name: 'FinancialCommodityService',
      redirect: '/financialCommodityService/wmpDisclaimer',
      component: () => import('@/views/common/financialCommodityService'),
      children: [{
        path: 'warrant',
        name: 'Warrant',
        component: () => import('@/views/common/financialCommodityService/module/warrant'),
        meta: { title: '权证' }
      }, {
        path: 'wmp',
        name: 'Wmp',
        component: () => import('@/views/common/financialCommodityService/module/wmp'),
        meta: { title: '理财易' }
      }, {
        path: 'wmpDisclaimer',
        name: 'WmpDisclaimer',
        component: () => import('@/views/common/financialCommodityService/module/wmpDisclaimer'),
        meta: { title: '理财易声明' }
      }, {
        path: 'cbbc',
        name: 'Cbbc',
        component: () => import('@/views/common/financialCommodityService/module/cbbc'),
        meta: { title: '牛熊证' }
      }]
    }, {
      path: 'offshoreAccount',
      name: 'OffshoreAccount',
      component: () => import('@/views/common/content/offshoreAccount'),
      meta: { title: '离岸账户' }
    }, {
      path: 'comprehensiveAccount',
      name: 'ComprehensiveAccount',
      component: () => import('@/views/common/content/comprehensiveAccount'),
      meta: { title: '综合账户' }
    }, {
      path: 'informationService',
      name: 'InformationService',
      redirect: '/informationService/informationDownload',
      component: () => import('@/views/common/informationService'),
      children: [{
        path: 'antiMoney',
        name: 'AntiMoney',
        component: () => import('@/views/common/informationService/module/antiMoney'),
        meta: { title: '反洗钱新规' }
      }, {
        path: 'crs',
        name: 'CRS',
        component: () => import('@/views/common/informationService/module/CRS'),
        meta: { title: 'CRS资讯' }
      }, {
        path: 'fatca',
        name: 'FATCA',
        component: () => import('@/views/common/informationService/module/FATCA'),
        meta: { title: 'FATCA资讯' }
      }, {
        path: 'informationDownload',
        name: 'InformationDownload',
        component: () => import('@/views/common/informationService/module/informationDownload'),
        meta: { title: '表格下载' }
      }]
    }, {
      path: 'notice',
      name: 'notice',
      component: () => import('@/views/common/noticeList/notice/index'),
      meta: { title: '公告' }
    }, {
      path: 'noticeList',
      name: 'noticeList',
      component: () => import('@/views/common/noticeList/index'),
      meta: { title: '公告列表' }
    }
    ]
  },
  {
    path: '/forgetPwd',
    component: () => import('@/views/account/forgetPwd'),
    redirect: '/forgetPwd/stepOne',
    children: [{
      path: 'stepOne',
      name: 'StepOne',
      component: () => import('@/views/account/forgetPwd/module/stepOne'),
      meta: { title: '忘记密码-步骤一' }
    }, {
      path: 'stepTwo/:type',
      props: true,
      name: 'StepTwo',
      component: () => import('@/views/account/forgetPwd/module/stepTwo'),
      meta: { title: '忘记密码-步骤二' }
    }, {
      path: 'stepThree/:authCode',
      props: true,
      name: 'StepThree',
      component: () => import('@/views/account/forgetPwd/module/stepThree'),
      meta: { title: '忘记密码-步骤三' }
    }, {
      path: 'stepFour',
      name: 'StepFour',
      component: () => import('@/views/account/forgetPwd/module/stepFour'),
      meta: { title: '忘记密码-步骤四' }
    }, {
      path: 'stepMail/:object',
      props: true,
      name: 'stepMail',
      component: () => import('@/views/account/forgetPwd/module/stepMail'),
      meta: { title: '忘记密码-邮件发送成功' }
    }, {
      path: 'stepVerify/:authCode/:object',
      props: true,
      name: 'stepVerify',
      component: () => import('@/views/account/forgetPwd/module/stepVerify'),
      meta: { title: '忘记密码-跳转' }
    }]
  },
  {
    path: '/manage',
    component: () => import('@/views/account/manage'),
    redirect: '/manage/overview',
    children: [{
      path: 'overview',
      name: 'Overview',
      component: () => import('@/views/account/manage/module/overview'),
      meta: { title: '账户概况', requiresAuth: true }
    // }, {
    //   path: 'overviewDetail/:secType',
    //   name: 'OverviewDetail',
    //   props: true,
    //   component: () => import('@/views/account/manage/module/overviewDetail'),
    //   meta: { title: '账户概况明细', requiresAuth: true }
    // }, {
    //   path: 'asset',
    //   name: 'Asset',
    //   component: () => import('@/views/account/manage/module/asset'),
    //   redirect: '/manage/asset/assetDeposit',
    //   children: [
    //     {
    //       path: 'assetDeposit',
    //       name: 'AssetDeposit',
    //       component: () => import('@/views/account/manage/module/asset/module/assetDeposit'),
    //       meta: { title: '存入资金', requiresAuth: true }
    //     }, {
    //       path: 'assetOverview',
    //       name: 'AssetOverview',
    //       component: () => import('@/views/account/manage/module/asset/module/assetOverview'),
    //       meta: { title: '资金概况', requiresAuth: true }
    //     }, {
    //       path: 'assetWithdraw',
    //       name: 'AssetWithdraw',
    //       component: () => import('@/views/account/manage/module/asset/module/assetWithdraw'),
    //       meta: { title: '提取资金', requiresAuth: true }
    //     }, {
    //       path: 'assetRecord',
    //       name: 'AssetRecord',
    //       component: () => import('@/views/account/manage/module/asset/module/assetRecord'),
    //       meta: { title: '资金账单', requiresAuth: true }
    //     }
    //   ]
    // }, {
    //   path: 'invest',
    //   name: 'Invest',
    //   component: () => import('@/views/account/manage/module/invest'),
    //   redirect: '/manage/asset/assetDeposit',
    //   children: [
    //     {
    //       path: 'investOverview',
    //       name: 'InvestOverview',
    //       component: () => import('@/views/account/manage/module/invest/module/investOverview'),
    //       meta: { title: '资金概况', requiresAuth: true }
    //     }, {
    //       path: 'tradeRecord',
    //       name: 'TradeRecord',
    //       component: () => import('@/views/account/manage/module/invest/module/tradeRecord'),
    //       meta: { title: '交易账单', requiresAuth: true }
    //     }
    //   ]
    // }, {
    //   path: 'account',
    //   name: 'Account',
    //   component: () => import('@/views/account/manage/module/account/index'),
    //   meta: { title: '账户管理', requiresAuth: true }
    }]
  }
];

const createRouter = () => new Router({
  mode: 'history',
  routes: constantRoutes
});

const router = createRouter();

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher; // reset router
}

export default router;
