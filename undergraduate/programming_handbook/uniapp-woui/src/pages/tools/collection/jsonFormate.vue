<template>
  <view class="json-formatter-container">
    <view class="title">JSON 格式化工具</view>

    <view class="toolbar">
      <button @click="formatJson" class="btn format">格式化</button>
      <button @click="minifyJson" class="btn minify">压缩</button>
      <button @click="validateJson" class="btn validate">校验</button>
      <button @click="copyToClipboard" class="btn copy">复制</button>
      <button @click="clearAll" class="btn clear">清空</button>
    </view>

    <view class="editor-container">
      <textarea
        v-model="jsonInput"
        placeholder="请输入或粘贴 JSON 内容..."
        class="json-input"
      ></textarea>

      <view v-if="showResult" class="result-container">
        <view class="result-title">处理结果：</view>
        <scroll-view scroll-y class="json-output">
          <text :class="['output-text', { 'error': isError }]">{{ jsonOutput }}</text>
        </scroll-view>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const jsonInput = ref('')
const jsonOutput = ref('')
const isError = ref(false)
const showResult = ref(false)

const formatJson = () => {
  try {
    const parsed = JSON.parse(jsonInput.value)
    jsonOutput.value = JSON.stringify(parsed, null, 2)
    isError.value = false
    showResult.value = true
    uni.showToast({ title: '格式化成功', icon: 'success' })
  } catch (e) {
    handleError(e)
  }
}

const minifyJson = () => {
  try {
    const parsed = JSON.parse(jsonInput.value)
    jsonOutput.value = JSON.stringify(parsed)
    isError.value = false
    showResult.value = true
    uni.showToast({ title: '压缩成功', icon: 'success' })
  } catch (e) {
    handleError(e)
  }
}

const validateJson = () => {
  try {
    JSON.parse(jsonInput.value)
    jsonOutput.value = '✅ JSON 格式正确'
    isError.value = false
    showResult.value = true
    uni.showToast({ title: '校验通过', icon: 'success' })
  } catch (e) {
    handleError(e)
  }
}

const copyToClipboard = () => {
  if (!jsonOutput.value) return

  uni.setClipboardData({
    data: jsonOutput.value,
    success: () => {
      uni.showToast({ title: '已复制到剪贴板', icon: 'none' })
    }
  })
}

const clearAll = () => {
  jsonInput.value = ''
  jsonOutput.value = ''
  showResult.value = false
}

const handleError = (e: unknown) => {
  const error = e as Error
  jsonOutput.value = `❌ 错误: ${error.message}`
  isError.value = true
  showResult.value = true
  uni.showToast({ title: '处理失败', icon: 'error' })
}
</script>

<style scoped lang="scss">
.json-formatter-container {
  padding: 30rpx;
  height: 100vh;
  display: flex;
  flex-direction: column;

  .title {
    font-size: 36rpx;
    font-weight: bold;
    text-align: center;
    margin-bottom: 30rpx;
    color: #333;
  }

  .toolbar {
    display: flex;
    flex-wrap: wrap;
    gap: 15rpx;
    margin-bottom: 30rpx;

    .btn {
      flex: 1;
      min-width: 120rpx;
      height: 70rpx;
      line-height: 70rpx;
      border-radius: 8rpx;
      font-size: 26rpx;
      color: white;
      border: none;
      padding: 0 10rpx;

      &.format {
        background-color: #07c160;
      }

      &.minify {
        background-color: #1aad19;
      }

      &.validate {
        background-color: #10aeff;
      }

      &.copy {
        background-color: #9a60b4;
      }

      &.clear {
        background-color: #dd524d;
      }
    }
  }

  .editor-container {
    flex: 1;
    display: flex;
    flex-direction: column;

    .json-input {
      flex: 1;
      width: 100%;
      padding: 20rpx;
      border: 1rpx solid #ddd;
      border-radius: 8rpx;
      font-size: 28rpx;
      font-family: Consolas, Monaco, monospace;
      margin-bottom: 30rpx;
    }

    .result-container {
      flex: 1;
      display: flex;
      flex-direction: column;
      border: 1rpx solid #eee;
      border-radius: 8rpx;
      padding: 15rpx;

      .result-title {
        font-size: 28rpx;
        color: #666;
        margin-bottom: 15rpx;
      }

      .json-output {
        flex: 1;
        padding: 15rpx;
        background-color: #f8f8f8;
        border-radius: 6rpx;
        font-family: Consolas, Monaco, monospace;
        font-size: 26rpx;
        white-space: pre-wrap;

        .output-text {
          color: #333;

          &.error {
            color: #dd524d;
          }
        }
      }
    }
  }
}
</style>

<route lang="json">
  {
  "layout": "base",
  "name": "jsonFormate",
  "style": {
  "navigationBarTitleText": "Json格式化"
  }
  }
</route>
