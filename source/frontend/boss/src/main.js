import Vue from 'vue';

import 'normalize.css/normalize.css'; // A modern alternative to CSS resets

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import locale from 'element-ui/lib/locale/lang/zh-CN'; // lang i18n
import VCharts from 'v-charts'; // 新增

import '@/styles/index.scss'; // global css

import App from './App';
import store from './store';
import router from './router';
import http from '@/utils/http';
import verify from '@/utils/verify'; // 添加
import moment from 'moment';
import Cookies from 'js-cookie'; // 添加
import filters from '@/utils/filters';

import '@/icons'; // icon
import '@/permission'; // permission control

import '@/styles/registerOpen.scss'; // 添加
import '@/styles/operation.scss'; // 添加

import VueQuillEditor from 'vue-quill-editor'; // 富文本
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import 'quill/dist/quill.bubble.css';
Vue.use(VueQuillEditor);

// set ElementUI lang to EN
Vue.use(ElementUI, { locale });
Vue.use(VCharts);

Vue.config.productionTip = false;

Vue.prototype.$http = http;
Vue.prototype.$moment = moment;
Vue.prototype.$verify = verify; // 添加
Vue.prototype.$Cookies = Cookies; // 添加
for (var key in filters) {
  Vue.filter(key, filters[key]);
}
new Vue({
  el: '#app',
  router,
  store,
  http,
  render: h => h(App)
});
