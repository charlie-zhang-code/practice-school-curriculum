import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-plugin-persistedstate'

export * from './TokenStore'

const stores = createPinia()
stores.use(
  createPersistedState({
    storage: {
      getItem: uni.getStorageSync,
      setItem: uni.setStorageSync,
    },
  }),
)

export default stores
