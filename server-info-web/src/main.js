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
import "echarts"
import VueEcharts from "vue-echarts"

Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.use(ViewUI);

const instance = axios.create({
    baseURL: ''
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
//axios.defaults.baseURL = 'http://1.15.106.156:9999';
//Vue.prototype.$echarts = echarts;
Vue.component("VueEcharts", VueEcharts)



new Vue({
    router,
    render: h => h(App),
}).$mount('#app')
