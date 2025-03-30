import type {App} from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import { routes } from './fiexd'

export const router = createRouter({
    history: createWebHistory(),
    routes
})

export async function setupRouter(app: App) {
    // TODO 路由守卫
    app.use(router)
    await router.isReady()
}