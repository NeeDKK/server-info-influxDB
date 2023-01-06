import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'element-ui/lib/theme-chalk/index.css';
import ElementUI from 'element-ui';
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import axios from 'axios';
import './assets/css/main.css';
import './assets/css/theme.scss';

Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.use(ViewUI);

const instance = axios.create({
  baseURL: '/api'
});

// ���ù���response
instance.interceptors.response.use((response) => {
  if (response) {
    if (response.data.code === 'FALSE') {
      return 'FALSE';
    }
    return response;
  }
}, error => {
  return Promise.reject(error);
});

// ��������ʱ�Ƿ���Ҫʹ��ƾ֤
instance.defaults.withCredentials = true;
instance.defaults.headers.post['Content-Type'] = 'application/json';
instance.defaults.headers.get['Content-Type'] = 'application/json';

Vue.prototype.$axios = instance;
axios.defaults.baseURL = 'http://localhost:9999';

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
