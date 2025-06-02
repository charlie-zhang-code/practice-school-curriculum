<template>
  <view class="dice-container">
    <view class="title">骰子小工具</view>

    <!-- 骰子展示区 -->
    <view class="dice-area">
      <view
        v-for="(dice, index) in diceList"
        :key="index"
        class="dice"
        :class="[`face-${dice.value}`, { 'rolling': dice.rolling }]"
        @click="rollSingle(index)"
      >
        <view class="dot" v-for="dot in getDots(dice.value)" :key="dot"></view>
      </view>
    </view>

    <!-- 控制区 -->
    <view class="control-panel">
      <view class="dice-count">
        <text>骰子数量:</text>
        <slider
          :value="diceCount"
          min="1"
          max="6"
          @change="changeDiceCount"
          activeColor="#FF5A5F"
        />
        <text>{{ diceCount }}</text>
      </view>

      <button class="roll-btn" @click="rollAll">掷骰子</button>
    </view>

    <!-- 历史记录 -->
    <view class="history" v-if="history.length > 0">
      <view class="history-title">历史记录</view>
      <scroll-view scroll-y class="history-list">
        <view
          v-for="(record, index) in history"
          :key="index"
          class="history-item"
        >
          <text>{{ formatTime(record.time) }}</text>
          <text class="result">{{ record.result.join(', ') }}</text>
          <text class="sum">总和: {{ record.sum }}</text>
        </view>
      </scroll-view>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'

interface Dice {
  value: number
  rolling: boolean
}

interface HistoryRecord {
  time: Date
  result: number[]
  sum: number
}

const diceCount = ref(2)
const diceList = ref<Dice[]>([])
const history = ref<HistoryRecord[]>([])

// 初始化骰子
const initDices = () => {
  diceList.value = Array.from({ length: diceCount.value }, () => ({
    value: 1,
    rolling: false
  }))
}

// 骰子面数对应的点数布局
const getDots = (value: number) => {
  const dotsMap: Record<number, number[]> = {
    1: [1],
    2: [1, 3],
    3: [1, 3, 5],
    4: [1, 2, 3, 4],
    5: [1, 2, 3, 4, 5],
    6: [1, 2, 3, 4, 5, 6]
  }
  return dotsMap[value] || []
}

// 改变骰子数量
const changeDiceCount = (e: any) => {
  diceCount.value = e.detail.value
  initDices()
}

// 滚动单个骰子
const rollSingle = (index: number) => {
  if (diceList.value[index].rolling) return

  diceList.value[index].rolling = true
  setTimeout(() => {
    diceList.value[index].value = Math.floor(Math.random() * 6) + 1
    diceList.value[index].rolling = false
  }, 800)
}

// 滚动所有骰子
const rollAll = () => {
  if (diceList.value.some(d => d.rolling)) return

  // 设置滚动状态
  diceList.value.forEach(d => {
    d.rolling = true
  })

  // 延迟出结果
  setTimeout(() => {
    const results = diceList.value.map(d => {
      d.value = Math.floor(Math.random() * 6) + 1
      d.rolling = false
      return d.value
    })

    // 记录历史
    history.value.unshift({
      time: new Date(),
      result: results,
      sum: results.reduce((a, b) => a + b, 0)
    })

    // 限制历史记录数量
    if (history.value.length > 10) {
      history.value.pop()
    }
  }, 1000)
}

// 格式化时间
const formatTime = (time: Date) => {
  return time.toLocaleTimeString('zh-CN', {
    hour12: false,
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit'
  })
}

// 初始化
initDices()
</script>

<style scoped lang="scss">
.dice-container {
  padding: 30rpx;
  height: 100vh;
  display: flex;
  flex-direction: column;

  .title {
    font-size: 36rpx;
    font-weight: bold;
    text-align: center;
    margin-bottom: 40rpx;
    color: #333;
  }

  .dice-area {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 30rpx;
    margin-bottom: 50rpx;

    .dice {
      width: 120rpx;
      height: 120rpx;
      background-color: white;
      border-radius: 20rpx;
      box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.1);
      display: flex;
      flex-wrap: wrap;
      justify-content: space-around;
      align-content: space-around;
      padding: 20rpx;
      position: relative;
      transition: transform 0.8s ease;

      &.rolling {
        animation: shake 0.8s infinite;
      }

      .dot {
        width: 20rpx;
        height: 20rpx;
        background-color: #333;
        border-radius: 50%;
        opacity: 0;
      }

      // 不同面数的点数显示
      &.face-1 .dot:nth-child(1) { opacity: 1; }
      &.face-2 .dot:nth-child(1),
      &.face-2 .dot:nth-child(2) { opacity: 1; }
      &.face-3 .dot:nth-child(1),
      &.face-3 .dot:nth-child(2),
      &.face-3 .dot:nth-child(3) { opacity: 1; }
      &.face-4 .dot { opacity: 1; }
      &.face-5 .dot { opacity: 1; }
      &.face-6 .dot { opacity: 1; }
    }
  }

  .control-panel {
    background-color: #f8f8f8;
    border-radius: 20rpx;
    padding: 30rpx;
    margin-bottom: 30rpx;

    .dice-count {
      display: flex;
      align-items: center;
      margin-bottom: 30rpx;

      text {
        font-size: 28rpx;
        color: #666;
        min-width: 120rpx;

        &:last-child {
          text-align: right;
          min-width: 40rpx;
        }
      }

      slider {
        flex: 1;
        margin: 0 20rpx;
      }
    }

    .roll-btn {
      background-color: #FF5A5F;
      color: white;
      height: 90rpx;
      line-height: 90rpx;
      border-radius: 45rpx;
      font-size: 32rpx;
      border: none;
    }
  }

  .history {
    flex: 1;
    background-color: #f8f8f8;
    border-radius: 20rpx;
    padding: 20rpx;

    .history-title {
      font-size: 30rpx;
      font-weight: bold;
      margin-bottom: 20rpx;
      color: #333;
    }

    .history-list {
      max-height: 300rpx;

      .history-item {
        display: flex;
        justify-content: space-between;
        padding: 15rpx 0;
        border-bottom: 1rpx solid #eee;
        font-size: 26rpx;

        .result {
          flex: 1;
          text-align: center;
          color: #FF5A5F;
          font-weight: bold;
        }

        .sum {
          color: #666;
        }
      }
    }
  }
}

@keyframes shake {
  0% { transform: rotate(0deg); }
  10% { transform: rotate(-5deg); }
  20% { transform: rotate(5deg); }
  30% { transform: rotate(-5deg); }
  40% { transform: rotate(5deg); }
  50% { transform: rotate(-5deg); }
  60% { transform: rotate(5deg); }
  70% { transform: rotate(-5deg); }
  80% { transform: rotate(5deg); }
  90% { transform: rotate(-5deg); }
  100% { transform: rotate(0deg); }
}
</style>

<route lang="json">
{
  "layout": "base",
  "name": "dice",
  "style": {
    "navigationBarTitleText": "骰子工具"
  }
}
</route>
