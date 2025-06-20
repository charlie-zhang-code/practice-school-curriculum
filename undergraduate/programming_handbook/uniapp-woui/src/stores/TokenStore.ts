import { defineStore } from 'pinia'

export const useTokenStore = defineStore('tokenStore', {
  state: () => {
    return {
      token: null as string | null,
    }
  },
  getters: {
    getToken(state): string | null {
      return state.token
    },
    isLogined(state) {
      return !!state.token
    },
  },
  actions: {
    async setToken(token: string) {
      this.token = token
    },

    async resetToken() {
      this.$reset()
    },

    async logoutAndRedirect() {
      this.resetToken()
      window.location.reload()
    },
  },
  persist: {
    storage: localStorage,
  },
})
