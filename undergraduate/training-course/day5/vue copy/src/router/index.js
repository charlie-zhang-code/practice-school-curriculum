import VueRouter from 'vue-router'
import Vue from 'vue'
import HomeView from '@/views/HomeView.vue'
import SubView1 from '@/views/SubView1.vue'
import SubView2 from '@/views/SubView2.vue'

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        // {
        //     path: '/',
        //     redirect: '/home'
        // },
        // {
        //     path: '/home',
        //     name: 'home',
        //     component: HomeView
        // },
        {
            path: '/',
            name: 'home',
            component: HomeView,
            children:[
                {
                    path: 'my',
                    name: 'my',
                    component: SubView1
                },
                {
                    path: 'your',
                    name: 'your',
                    component: SubView2
                }
            ]
        },
    ]
})

export default router
