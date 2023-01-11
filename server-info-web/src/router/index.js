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
                        path: '/java',
                        component: () => import('../components/ServerInfoJava.vue'),
                        meta: {title: 'java服务器监控'}
                    },
                    {
                        path: '/golang',
                        component: () => import('../components/ServerInfoGolang.vue'),
                        meta: {title: 'golang服务器监控'}
                    },
                ]
            },
        ]
    }
);

router.beforeEach((to, from, next) => {
    /* 路由发生变化修改页面title */
    if (to.meta.title) {
        document.title = '服务器监控'
    }
    next()
})
export default router
