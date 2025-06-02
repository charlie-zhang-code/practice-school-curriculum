<script lang="ts" setup>
import { onShow } from '@dcloudio/uni-app'
import { useRoute, useRouter } from 'uni-mini-router'
import { nextTick, onMounted, ref } from 'vue'
import { useTabbar } from '@/composables/useTabbar'
import pagesJson from '@/pages.json'

const router = useRouter()

const route = useRoute()

const { activeTabbar, getTabbarItemValue, setTabbarItemActive, tabbarList } = useTabbar()

function handleTabbarChange({ value }: { value: string }) {
  setTabbarItemActive(value)
  router.pushTab({ name: value })
}

onMounted(() => {
  nextTick(() => {
    if (route.name && route.name !== activeTabbar.value.name) {
      setTabbarItemActive(route.name)
    }
  })
})

onShow(() => {
  // #ifdef APP-PLUS
  uni.hideTabBar()
  // #endif
})

function handleBack() {
  uni.navigateBack({})
  // router.back()
}

function handleBackHome() {
  uni.reLaunch({ url: '/pages/index/index' })
}

const pageTitle = ref('')
onShow(() => {
  const pages = getCurrentPages()
  const currentPage = pages[pages.length - 1]
  const route = currentPage.route

  // 从pages.json中匹配navigationBarTitleText打印
  const findPageTitle = (path: string) => {
    // 查找普通页面
    const page = pagesJson.pages.find(p => path.includes(p.path))
    if (page?.style?.navigationBarTitleText) {
      return page.style.navigationBarTitleText
    }

    // 查找subPackages中的页面
    if (pagesJson.subPackages) {
      for (const subPackage of pagesJson.subPackages) {
        const subPage = subPackage.pages?.find(p => path.includes(`${subPackage.root}/${p.path}`))
        if (subPage?.style?.navigationBarTitleText) {
          return subPage.style.navigationBarTitleText
        }
      }
    }

    return '未找到标题'
  }

  const title = findPageTitle(route)
  // console.log('当前页面标题:', title)
  pageTitle.value = title
})
</script>

<template>
  <wd-config-provider custom-style="min-height: 100vh">
    <wd-navbar :title="pageTitle" safe-area-inset-top left-arrow placeholder fixed>
      <template #capsule>
        <wd-navbar-capsule @back="handleBack" @back-home="handleBackHome" />
      </template>
    </wd-navbar>

    <slot />

    <wd-notify />
    <wd-toast />
    <wd-message-box />
  </wd-config-provider>
</template>
