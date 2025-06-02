<script lang="ts" setup>
function goTosSubpage() { uni.navigateTo({ url: '/pages/example/index' }) }
</script>

<template>
  <view>
    <wd-card type="rectangle">
      <view class="flex items-center justify-between">
        <view class="flex items-center gap-2">
          <wd-img
            :enable-preview="true" :width="55" :height="55" round
            src="https://www.keaitupian.cn/cjpic/frombd/1/253/1215285637/1396751085.jpg"
          />
          <view class="flex flex-col">
            <wd-badge model-value="L1" type="warning">
              <view style="color: rgba(0,0,0,0.85); font-size: 20px;">
                用户名
              </view>
            </wd-badge>
            <view style="color: rgba(0,0,0,0.25); font-size: 12px;">
              神秘的用户
            </view>
          </view>
        </view>
        <view class="flex items-center">
          <wd-button type="icon" icon="qrcode" />
          <!-- <wd-button type="icon" icon="chevron-right"></wd-button> -->
          <wd-icon name="chevron-right" />
        </view>
      </view>
    </wd-card>

    <wd-cell-group border>
      <wd-cell title="收藏" icon="star" is-link />
      <wd-cell title="订单" icon="list" is-link />
      <wd-cell title="历史" icon="history" is-link />
      <wd-cell title="设置" icon="setting" is-link />
    </wd-cell-group>

    <wd-cell-group title="测试" border>
      <wd-cell title="子页面/网络请求" icon="star" is-link @click="goTosSubpage" />
    </wd-cell-group>

    <button type="warn" class="mt-2 h-10 w-full flex items-center justify-center bg-white">
      <view class="flex items-center justify-center gap-2">
        <wd-icon name="logout" />
        <view>登出</view>
      </view>
    </button>
  </view>
</template>

<style scoped lang="scss">

</style>

<route lang="json">
  {
  "layout": "default",
  "name": "account",
  "style": {
  "navigationBarTitleText": "我的"
  }
  }
</route>
