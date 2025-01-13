<script lang="ts" setup>
import {
  NLoadingBarProvider,
  NDialogProvider,
  NNotificationProvider,
  NMessageProvider,
} from 'naive-ui'
</script>

<template>
  <NConfigProvider
    inline-theme-disabled
    :locale="null"
    :date-locale="null"
    :breakpoints="{ s: 640, m: 768, l: 1024 }"
  >
    <NLoadingBarProvider>
      <NDialogProvider>
        <NNotificationProvider :max="5">
          <NMessageProvider>
            <slot />
          </NMessageProvider>
        </NNotificationProvider>
      </NDialogProvider>
    </NLoadingBarProvider>
  </NConfigProvider>
</template>

<style scoped></style>
