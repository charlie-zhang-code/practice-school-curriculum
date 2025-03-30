import type {App} from 'vue'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

export * from './token'

export function setupPinia(app: App) {
    const pinia = createPinia()
    pinia.use(piniaPluginPersistedstate)
    app.use(pinia)
}