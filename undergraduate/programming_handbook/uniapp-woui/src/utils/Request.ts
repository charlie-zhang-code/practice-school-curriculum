import { createAlova } from 'alova'
import AdapterUniapp from '@alova/adapter-uniapp'
import { useTokenStore } from '@/stores'

const gateway = 'http://localhost:8000'

function showErrorMessage(jsonData: Record<string, any>) {
  if (jsonData.code) {
    if (jsonData.message) {
      uni.showToast({
        icon: 'none',
        title: `${jsonData.message} (${jsonData.code})`,
      })
    }
    if (jsonData.error) {
      uni.showToast({
        icon: 'none',
        title: `${jsonData.message} (${jsonData.code})`,
      })
    }
  }
  else {
    if (jsonData.message) {
      uni.showToast({
        icon: 'none',
        title: `${jsonData.message}`,
      })
    }
    if (jsonData.error) {
      uni.showToast({
        icon: 'none',
        title: `${jsonData.message}`,
      })
    }
  }
}

const alovaInstance = createAlova({
  cacheFor: null,
  baseURL: gateway,
  timeout: 15 * 1000,
  ...AdapterUniapp(),
  beforeRequest: (method) => {
    const tokenStore = useTokenStore()
    method.config.headers.Authorization = tokenStore.getToken

    if (method.meta?.isFormPost) {
      method.config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
      method.data = new URLSearchParams(method.data as URLSearchParams).toString()
    }
  },
  responded: {
    onSuccess: async (response, _method) => {
      const { statusCode, data } = response as { statusCode: number, data: any }

      if (statusCode === 200) {
        // 获取json数据中的code字段，判断是否为成功状态，默认为"200"
        if (data.code === 200) {
          return data
        }

        // 业务请求失败
        showErrorMessage(data)
        return data
      }

      if (statusCode === 401) {
        // 登录过期
        showErrorMessage(data)
        return data
      }

      // 接口请求失败
      showErrorMessage(data)
      return data
    },

    onError: async (error, _method) => {
      uni.showToast({
        icon: 'none',
        title: `${error.message}`,
      })
    },

    onComplete: async (_method) => { },
  },
})

export const request = alovaInstance
