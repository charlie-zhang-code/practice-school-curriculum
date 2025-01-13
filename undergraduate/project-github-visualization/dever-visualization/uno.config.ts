import { defineConfig } from 'unocss'

export default defineConfig({
  shortcuts: {
    'header-height': 'h-14', // 3.5rem
    'header-style': 'header-height bg-white p-x flex items-center z-999 sticky top-0 border-solid border-b-1',

    // footer
    'footer-height': 'h-14', // 3.5rem
    'footer-style': 'footer-height bg-white p-x flex items-center justify-center',

    // layout
    'practice-layout': 'h-[calc(100vh-3.5rem)]', // 100vh - 3.5rem
    'status-detail-layout': 'h-[calc(100vh-3.5rem-3.5rem-2rem)]', // 100vh - 8.5rem
    // 'lay-main-m': 'h-[calc(100vh-3.5rem-6rem)]', // 100vh - 3.5rem - 6rem
    // 'lay-main-header': 'h-[calc(100vh-3.5rem)]', // 100vh - 3.5rem
    // 'lay-main-footer': 'h-[calc(100vh-8.5rem)]', // 100vh - 8.5rem
    // 'lay-main-footer-m': 'h-[calc(100vh-6rem)]', // 100vh - 6rem
  },
})
