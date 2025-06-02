<template>
  <view class="countdown-container">
    <!-- 标题 -->
    <view class="title">倒计时工具</view>

    <!-- 时间显示 -->
    <view class="display">
      <text class="time">{{ formattedTime }}</text>
    </view>

    <!-- 控制按钮 -->
    <view class="controls">
      <button @click="toggleTimer" :class="['btn', isRunning ? 'pause' : 'start']">
        {{ isRunning ? '暂停' : '开始' }}
      </button>
      <button @click="resetTimer" class="btn reset">重置</button>
    </view>

    <!-- 时间设置 -->
    <view class="time-set">
      <picker mode="selector" :range="hours" @change="setHours">
        <view class="picker">{{ selectedHours }} 小时</view>
      </picker>
      <picker mode="selector" :range="minutes" @change="setMinutes">
        <view class="picker">{{ selectedMinutes }} 分钟</view>
      </picker>
      <picker mode="selector" :range="seconds" @change="setSeconds">
        <view class="picker">{{ selectedSeconds }} 秒</view>
      </picker>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref, computed, onUnmounted } from 'vue'

// 倒计时数据
const totalSeconds = ref(0)
const remainingSeconds = ref(0)
const isRunning = ref(false)
let timer: number | null = null

// 时间选择器数据
const hours = Array.from({ length: 24 }, (_, i) => i)
const minutes = Array.from({ length: 60 }, (_, i) => i)
const seconds = Array.from({ length: 60 }, (_, i) => i)
const selectedHours = ref(0)
const selectedMinutes = ref(0)
const selectedSeconds = ref(0)

// 格式化显示时间
const formattedTime = computed(() => {
  const hours = Math.floor(remainingSeconds.value / 3600)
  const minutes = Math.floor((remainingSeconds.value % 3600) / 60)
  const seconds = remainingSeconds.value % 60

  return [
    hours.toString().padStart(2, '0'),
    minutes.toString().padStart(2, '0'),
    seconds.toString().padStart(2, '0')
  ].join(':')
})

// 设置时间
const setHours = (e: any) => {
  selectedHours.value = e.detail.value
  updateTotalTime()
}

const setMinutes = (e: any) => {
  selectedMinutes.value = e.detail.value
  updateTotalTime()
}

const setSeconds = (e: any) => {
  selectedSeconds.value = e.detail.value
  updateTotalTime()
}

const updateTotalTime = () => {
  totalSeconds.value =
    selectedHours.value * 3600 +
    selectedMinutes.value * 60 +
    selectedSeconds.value
  resetTimer()
}

// 控制倒计时
const toggleTimer = () => {
  if (totalSeconds.value <= 0) return

  if (isRunning.value) {
    pauseTimer()
  } else {
    startTimer()
  }
}

const startTimer = () => {
  if (remainingSeconds.value <= 0) {
    remainingSeconds.value = totalSeconds.value
  }

  isRunning.value = true
  timer = setInterval(() => {
    remainingSeconds.value--

    if (remainingSeconds.value <= 0) {
      pauseTimer()
      uni.showToast({
        title: '时间到！',
        icon: 'none'
      })
    }
  }, 1000)
}

const pauseTimer = () => {
  isRunning.value = false
  if (timer) {
    clearInterval(timer)
    timer = null
  }
}

const resetTimer = () => {
  pauseTimer()
  remainingSeconds.value = totalSeconds.value
}

// 组件卸载时清除定时器
onUnmounted(() => {
  pauseTimer()
})
</script>

<style scoped lang="scss">
.countdown-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40rpx;

  .title {
    font-size: 36rpx;
    font-weight: bold;
    margin-bottom: 40rpx;
    color: #333;
  }

  .display {
    margin: 40rpx 0;
    .time {
      font-size: 80rpx;
      font-family: monospace;
      color: #2b9939;
    }
  }

  .controls {
    display: flex;
    gap: 30rpx;
    margin-bottom: 60rpx;

    .btn {
      width: 200rpx;
      height: 80rpx;
      line-height: 80rpx;
      border-radius: 40rpx;
      font-size: 32rpx;
      color: white;
      border: none;

      &.start {
        background-color: #07c160;
      }

      &.pause {
        background-color: #f0ad4e;
      }

      &.reset {
        background-color: #dd524d;
      }
    }
  }

  .time-set {
    width: 100%;
    display: flex;
    justify-content: space-around;

    .picker {
      padding: 20rpx 40rpx;
      background-color: #f5f5f5;
      border-radius: 10rpx;
      font-size: 28rpx;
    }
  }
}
</style>

<route lang="json">
{
  "layout": "base",
  "name": "cutdown",
  "style": {
    "navigationBarTitleText": "倒计时"
  }
}
</route>
