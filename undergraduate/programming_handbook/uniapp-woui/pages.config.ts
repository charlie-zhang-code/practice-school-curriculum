import { defineUniPages } from '@uni-helper/vite-plugin-uni-pages'

export default defineUniPages({
  pages: [
  ],
  globalStyle: {
    navigationBarTitleText: 'uniapp-woui-start',
    navigationBarTextStyle: 'white',
    navigationStyle: 'custom',
  },
  tabBar: {
    custom: true,
    height: '0',
    color: '#bfbfbf',
    selectedColor: '#0165FF',
    list: [
      {
        pagePath: 'pages/index/index',
      },
      {
        pagePath: 'pages/command/index',
      },
      {
        pagePath: 'pages/tools/index',
      },
      {
        pagePath: 'pages/code/index',
      },
    ],
  },
})
