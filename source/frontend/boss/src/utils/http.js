import axios from 'axios';
import store from '@/store';
import { getSid } from '@/utils/auth';

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  withCredentials: true, // send cookies when cross-domain requests
  timeout: 30000 // request timeout
});

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent
    if (store.getters.sid) {
      // let each request carry token
      // ['Sid'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['Bid'] = getSid();
    }
    return config;
  },
  error => {
    // do something with request error
    this.$message.error(error); // for debug
    return Promise.reject(error);
  }
);

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    return response.data;
  },
  error => {
    // do something with request error
    console.log('err' + error); // for debug
    return Promise.reject(error);
  }
);

export default service;
