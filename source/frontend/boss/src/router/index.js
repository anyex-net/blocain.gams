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

  {
    path: '/',
    component: Layout,
    redirect: '/index',
    children: [{
      path: 'index',
      name: 'Index',
      component: () => import('@/views/index'),
      meta: { title: '首页', affix: true }
    }
    ]
  },
  {
    path: '/404',
    name: 'Page404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login'),
    meta: { title: '登录' }
  },
  {
    path: '/center',
    component: Layout,
    children: [{
      path: '',
      name: 'Center',
      component: () => import('@/views/common/center'),
      meta: { title: '个人信息' }
    }]
  },

  // GAMS 路由
  {
    path: '/common',
    component: Layout,
    meta: { title: '公共模块' },
    children: [
      {
        path: 'dictionary',
        name: 'Dictionary',
        component: () => import('@/views/common/dictionary'),
        meta: { title: '数据字典' }
      },
      {
        path: 'template',
        name: 'Template',
        component: () => import('@/views/common/template'),
        meta: { title: '消息模版' }
      },
      {
        path: 'region',
        name: 'Region',
        component: () => import('@/views/common/region'),
        meta: { title: '区域代码' }
      },
      {
        path: 'parameter',
        name: 'Parameter',
        component: () => import('@/views/common/parameter'),
        meta: { title: '系统参数' }
      },
      {
        path: 'banner',
        name: 'banner',
        component: () => import('@/views/common/banner'),
        meta: { title: 'Banner管理' }
      },
      {
        path: 'express',
        name: 'express',
        component: () => import('@/views/common/express'),
        meta: { title: '快讯管理' }
      },
      {
        path: 'helpCenter',
        name: 'HelpCenter',
        component: () => import('@/views/common/helpCenter'),
        meta: { title: '帮助中心' }
      },
      {
        path: 'newsBulletin',
        name: 'newsBulletin',
        component: () => import('@/views/common/newsbulletin/index'),
        meta: { title: '消息公告' }
      },
      {
        path: 'help',
        name: 'help',
        component: () => import('@/views/common/help/help'),
        meta: { title: '客服答疑' }
      },
      {
        path: 'feedback',
        name: 'feedback',
        component: () => import('@/views/common/feedback/index'),
        meta: { title: '问题反馈' }
      }
    ]
  },
  {
    path: '/system',
    component: Layout,
    meta: { title: '系统管理' },
    children: [
      {
        path: 'organiz',
        name: 'Organization',
        component: () => import('@/views/system/organization'),
        meta: { title: '机构管理' }
      },
      {
        path: 'resource',
        name: 'Resource',
        component: () => import('@/views/system/resource'),
        meta: { title: '资源管理' }
      },
      {
        path: 'role',
        name: 'Role',
        component: () => import('@/views/system/role'),
        meta: { title: '角色管理' }
      },
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/system/user'),
        meta: { title: '用户管理' }
      }
    ]
  },
  {
    path: '/stock',
    component: Layout,
    meta: { title: '证劵管理' },
    children: [
      {
        path: 'stockExchange',
        name: 'StockExchange',
        component: () => import('@/views/stock/stockExchange'),
        meta: { title: '交易市场' }
      },
      {
        path: 'stockInfo',
        name: 'StockInfo',
        component: () => import('@/views/stock/stockInfo'),
        meta: { title: '证劵信息' }
      },
      {
        path: 'stockWarrant',
        name: 'StockWarrant',
        component: () => import('@/views/stock/stockWarrant'),
        meta: { title: '权证信息' }
      },
      {
        path: 'stockWmp',
        name: 'StockWmp',
        component: () => import('@/views/stock/stockWmp'),
        meta: { title: '理财产品' }
      },
      {
        path: 'stockHotConfig',
        name: 'StockHotConfig',
        component: () => import('@/views/stock/stockHotConfig'),
        meta: { title: '热门证券' }
      }
    ]
  },
  {
    path: '/quote',
    component: Layout,
    meta: { title: '证券行情' },
    children: [
      {
        path: 'quoteStock',
        name: 'QuoteStock',
        component: () => import('@/views/quote/quoteStock'),
        meta: { title: '股票行情' }
      },
      {
        path: 'quoteBond',
        name: 'QuoteBond',
        component: () => import('@/views/quote/quoteBond'),
        meta: { title: '债券行情' }
      },
      {
        path: 'quoteFutures',
        name: 'QuoteFutures',
        component: () => import('@/views/quote/quoteFutures'),
        meta: { title: '期货行情' }
      },
      {
        path: 'quoteOption',
        name: 'QuoteOption',
        component: () => import('@/views/quote/quoteOption'),
        meta: { title: '期权行情' }
      },
      {
        path: 'quoteForeignex',
        name: 'QuoteForeignex',
        component: () => import('@/views/quote/quoteForeignex'),
        meta: { title: '外汇行情' }
      },
      {
        path: 'quoteFund',
        name: 'QuoteFund',
        component: () => import('@/views/quote/quoteFund'),
        meta: { title: '基金行情' }
      }, {
        path: 'quoteWarrant',
        name: 'QuoteWarrant',
        component: () => import('@/views/quote/quoteWarrant'),
        meta: { title: '权证行情' }
      }
    ]
  },

  { // - 账户-开户管理路由
    path: '/account',
    component: Layout,
    meta: { title: '账户管理' },
    children: [
      {
        path: 'registerOpen',
        name: 'RegisterOpen',
        component: () => import('@/views/account/registerOpen/registerOpen'),
        meta: { title: '开户管理' },
        children: [
          {
            path: 'registerOpenDetails',
            name: 'RegisterOpenDetails',
            component: () => import('@/views/account/registerOpen/registerOpenDetails'),
            meta: { title: '开户详情' }
          }
        ]
      },
      {
        path: 'account',
        name: 'Account',
        component: () => import('@/views/account/accountManagement/account'),
        meta: { title: '账户管理' },
        children: [
          {
            path: 'accountDetails',
            name: 'accountDetails',
            component: () => import('@/views/account/accountManagement/accountDetails/accountDetails'),
            meta: { title: '账户详情' }
          }
        ]
      }
    ]
  },
  {
    path: '/ib',
    component: Layout,
    meta: { title: 'IB数据管理' },
    children: [
      {
        path: 'ibAccountFamily',
        name: 'IbAccountFamily',
        component: () => import('@/views/ib/ibAccountFamily'),
        meta: { title: 'IB账户家族' }
      },
      {
        path: 'ibAccountBalanceMargin',
        name: 'IbAccountBalanceMargin',
        component: () => import('@/views/ib/ibAccountBalanceMargin'),
        meta: { title: 'IB账户资金' }
      },
      {
        path: 'ibAccountDailyPnL',
        name: 'IbAccountDailyPnL',
        component: () => import('@/views/ib/ibAccountDailyPnL'),
        meta: { title: 'IB当日盈亏' }
      },
      {
        path: 'ibAccountTrade',
        name: 'IbAccountTrade',
        component: () => import('@/views/ib/ibAccountTrade'),
        meta: { title: 'IB交易流水' }
      },
      {
        path: 'ibAccountPosition',
        name: 'IbAccountPosition',
        component: () => import('@/views/ib/ibAccountPosition'),
        meta: { title: 'IB账户持仓' }
      },
      {
        path: 'ibAccountMarketValue',
        name: 'IbAccountMarketValue',
        component: () => import('@/views/ib/ibAccountMarketValue'),
        meta: { title: 'IB账户市值' }
      },
      {
        path: 'ibAccountMarketValueHis',
        name: 'IbAccountMarketValueHis',
        component: () => import('@/views/ib/ibAccountMarketValueHis'),
        meta: { title: 'IB账户市值历史' }
      }
    ]
  },
  {
    path: '/asset',
    component: Layout,
    meta: { title: '资金管理' },
    children: [
      {
        path: 'assetCurrent',
        name: 'AssetCurrent',
        component: () => import('@/views/asset/assetCurrent'),
        meta: { title: '资金流水' }
      },
      {
        path: 'assetDeposit',
        name: 'AssetDeposit',
        component: () => import('@/views/asset/assetDeposit'),
        meta: { title: '充值入金' }
      },
      {
        path: 'assetPosition',
        name: 'AssetPosition',
        component: () => import('@/views/asset/assetPosition'),
        meta: { title: '资产持仓' }
      },
      {
        path: 'assetWithdraw',
        name: 'AssetWithdraw',
        component: () => import('@/views/asset/assetWithdraw'),
        meta: { title: '提现出金' }
      }
    ]
  },
  {
    path: '/query',
    component: Layout,
    meta: { title: '综合查询' },
    children: [
      {
        path: 'userLog',
        name: 'UserLog',
        component: () => import('@/views/query/userLog'),
        meta: { title: '用户日志' }
      },
      {
        path: 'accountLog',
        name: 'AccountLog',
        component: () => import('@/views/query/accountLog'),
        meta: { title: '账户日志' }
      },
      {
        path: 'msgLog',
        name: 'MsgLog',
        component: () => import('@/views/query/msgLog'),
        meta: { title: '消息日志' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
];

const createRouter = () => new Router({
  routes: constantRoutes
});

const router = createRouter();

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher; // reset router
}

export default router;
