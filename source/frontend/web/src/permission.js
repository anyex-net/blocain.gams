import router from './router';
import store from './store';
import NProgress from 'nprogress'; // progress bar
import 'nprogress/nprogress.css'; // progress bar style
import { getTitle } from '@/utils/common';
import verify from '@/utils/openAccount/verify';
// import { setSid } from '@/utils/auth';

NProgress.configure({ showSpinner: false }); // NProgress Configuration

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start();

  // set page title
  getTitle();

  // if (to.path === '/manage/overview' && to.query.sid) {
  //   // if is logged in, set sid
  //   setSid(to.query.sid);
  //   store.commit('user/SET_SID', to.query.sid);
  // }

  // determine whether the user has logged in and need auth
  if (to.path === '/login') {
    // if is logged in, redirect to the home page
    window.location.href = process.env.VUE_APP_SERVER_ADDRESS + '/login';
    NProgress.done();
  } else {
    const hasGetUserInfo = store.getters.name;
    if (hasGetUserInfo) {
      if (to.meta.routerPermissions === true) {
        if (store.state.user.openAccount.status) {
          next();
          NProgress.done();
        } else {
          verify.routerPermissions(store.state.user.openAccount.currentPosition);
          NProgress.done();
        }
      } else {
        next();
        NProgress.done();
      }
    } else {
      // get info
      store.dispatch('user/getInfo').then((res) => {
        if (res.code === 200 || to.meta.requiresAuth !== true) {
          if (to.meta.routerPermissions === true) {
            if (store.state.user.openAccount.status) {
              next();
              NProgress.done();
            } else {
              verify.routerPermissions(store.state.user.openAccount.currentPosition);
              NProgress.done();
            }
          } else {
            next();
            NProgress.done();
          }
        } else {
          window.location.href = process.env.VUE_APP_SERVER_ADDRESS + '/login';
          NProgress.done();
        }
      });
    }
  }
});

router.afterEach(() => {
  // finish progress bar
  NProgress.done();
});
