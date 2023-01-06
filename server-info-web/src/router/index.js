import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);

const router = new Router(
    {
        mode: 'history',
        routes: [
            {
                path: '/',  // 程序启动默认路由
                component: () => import('../components/Whole.vue'),
                meta: {title: '首页'},
                children: [
                    {
                        path: '/search',
                        component: () => import('../components/SearchResume.vue'),
                        meta: {title: 'golang服务器监控'}
                    },
                    {
                        path: '/upload',
                        component: () => import('../components/UploadResume.vue'),
                        meta: {title: 'java服务器监控'}
                    },
                ]
            },
        ]
    }
);


export default router
