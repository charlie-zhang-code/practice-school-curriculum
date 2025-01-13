import AutoImport from 'unplugin-auto-import/vite'
import { NaiveUiResolver } from 'unplugin-vue-components/resolvers'
import Components from 'unplugin-vue-components/vite'


// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2024-11-01',
  devtools: { enabled: false ,   timeline: {
    enabled: false,
  },},
  modules: ['@pinia/nuxt', 'nuxt-echarts', 'nuxtjs-naive-ui', '@unocss/nuxt', '@nuxt/icon'],
  echarts: {
    renderer: ['svg', 'canvas'],
    charts: ['BarChart', 'LineChart', 'PieChart', 'RadarChart'],
    components: ['DatasetComponent', 'GridComponent', 'TooltipComponent'],
    features: ['LabelLayout', 'UniversalTransition'],
  },
  css: [
    '@unocss/reset/tailwind-compat.css',
  ],
  vite: {
    plugins: [
      AutoImport({
        dts: '.nuxt/vite_config/auto-imports.d.ts',
        imports: [
          {
            'naive-ui': ['useDialog', 'useMessage', 'useNotification', 'useLoadingBar'],
          },
        ],
      }),
      Components({
        dts: '.nuxt/vite_config/components.d.ts',
        resolvers: [NaiveUiResolver()],
      }),
    ],
  },
})