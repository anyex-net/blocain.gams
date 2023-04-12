import Vue from 'vue';

import VCharts from 'v-charts';

import 'normalize.css/normalize.css'; // A modern alternative to CSS resets

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import locale from 'element-ui/lib/locale/lang/en'; // lang i18n

import '@/styles/index.scss'; // global css
import '@/styles/openAccount.scss'; // register css

import App from './App';
import store from './store';
import router from './router';
import http from '@/utils/http';
import i18n from '@/utils/i18n';
import moment from 'moment';
import VueAMap from 'vue-amap';
import verify from '@/utils/openAccount/verify';
import Cookies from 'js-cookie';

import '@/permission'; // permission control
import filters from '@/utils/filters';
// set ElementUI lang to EN
Vue.use(ElementUI, { locale });
Vue.use(VCharts);
Vue.use(VueAMap);
VueAMap.initAMapApiLoader({
  key: 'YOUR_KEY',
  plugin: ['AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType'],
  v: '1.4.4'
});

Vue.config.productionTip = false;

Vue.prototype.$http = http;
Vue.prototype.$moment = moment;
Vue.prototype.$verify = verify;
Vue.prototype.$Cookies = Cookies;
for (var key in filters) {
  Vue.filter(key, filters[key]);
}

var vue = new Vue({
  el: '#app',
  router,
  store,
  http,
  i18n,
  render: h => h(App)
});
export default vue;
