<template>
  <view class="hot-analyzer-container">
    <view class="title">热点分析器</view>

    <!-- 输入区域 -->
    <view class="input-section">
      <textarea
        v-model="inputText"
        placeholder="请输入要分析的文本内容..."
        class="text-input"
      ></textarea>
      <button @click="analyze" class="analyze-btn">开始分析</button>
    </view>

    <!-- 分析结果 -->
    <view v-if="showResults" class="results-section">
      <!-- 选项卡 -->
      <view class="tabs">
        <view
          v-for="tab in tabs"
          :key="tab.id"
          :class="['tab', { 'active': activeTab === tab.id }]"
          @click="activeTab = tab.id"
        >
          {{ tab.name }}
        </view>
      </view>

      <!-- 关键词列表 -->
      <view v-show="activeTab === 'keywords'" class="tab-content">
        <view class="keywords-list">
          <view
            v-for="(word, index) in keywords"
            :key="index"
            class="keyword-item"
            :style="{ fontSize: 14 + word.count * 2 + 'px', color: getColor(index) }"
          >
            {{ word.text }} ({{ word.count }})
          </view>
        </view>
      </view>

      <!-- 词云展示 -->
      <view v-show="activeTab === 'wordcloud'" class="tab-content">
        <canvas
          canvas-id="wordcloud"
          class="wordcloud-canvas"
          @touchstart="handleTouchStart"
          @touchmove="handleTouchMove"
        ></canvas>
      </view>

      <!-- 热度趋势 -->
      <view v-show="activeTab === 'trend'" class="tab-content">
        <view class="trend-chart">
          <canvas canvas-id="trendChart" class="chart-canvas"></canvas>
        </view>
      </view>

      <!-- 数据统计 -->
      <view v-show="activeTab === 'stats'" class="tab-content">
        <view class="stats-grid">
          <view class="stat-item">
            <text class="stat-value">{{ stats.totalWords }}</text>
            <text class="stat-label">总词数</text>
          </view>
          <view class="stat-item">
            <text class="stat-value">{{ stats.uniqueWords }}</text>
            <text class="stat-label">独特词</text>
          </view>
          <view class="stat-item">
            <text class="stat-value">{{ stats.topWord }}</text>
            <text class="stat-label">高频词</text>
          </view>
          <view class="stat-item">
            <text class="stat-value">{{ stats.avgLength.toFixed(1) }}</text>
            <text class="stat-label">平均长度</text>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { onReady } from '@dcloudio/uni-app'

interface Keyword {
  text: string
  count: number
}

interface Stats {
  totalWords: number
  uniqueWords: number
  topWord: string
  avgLength: number
}

// 数据状态
const inputText = ref('')
const keywords = ref<Keyword[]>([])
const stats = ref<Stats>({
  totalWords: 0,
  uniqueWords: 0,
  topWord: '',
  avgLength: 0
})
const showResults = ref(false)
const activeTab = ref('keywords')

// 选项卡配置
const tabs = [
  { id: 'keywords', name: '关键词' },
  { id: 'wordcloud', name: '词云' },
  { id: 'trend', name: '趋势' },
  { id: 'stats', name: '统计' }
]

// 颜色生成器
const colors = [
  '#FF5A5F', '#FFB400', '#007A87', '#8CE071',
  '#7B0051', '#00D1C1', '#FFAA91', '#B4A76C',
  '#9CA299', '#565A5C', '#00A699', '#FC642D'
]

const getColor = (index: number) => {
  return colors[index % colors.length]
}

// 分析文本
const analyze = () => {
  if (!inputText.value.trim()) {
    uni.showToast({ title: '请输入文本内容', icon: 'none' })
    return
  }

  // 分词和统计
  const words = inputText.value
    .replace(/[^\u4e00-\u9fa5a-zA-Z0-9]/g, ' ') // 去除非字母数字汉字字符
    .split(/\s+/) // 按空格分割
    .filter(word => word.length > 1) // 过滤掉单字

  // 统计词频
  const wordCount: Record<string, number> = {}
  words.forEach(word => {
    wordCount[word] = (wordCount[word] || 0) + 1
  })

  // 转换为数组并排序
  keywords.value = Object.entries(wordCount)
    .map(([text, count]) => ({ text, count }))
    .sort((a, b) => b.count - a.count)
    .slice(0, 50) // 取前50个关键词

  // 计算统计数据
  stats.value = {
    totalWords: words.length,
    uniqueWords: keywords.value.length,
    topWord: keywords.value[0]?.text || '',
    avgLength: words.reduce((sum, word) => sum + word.length, 0) / words.length || 0
  }

  showResults.value = true

  // 绘制图表
  setTimeout(() => {
    drawWordCloud()
    drawTrendChart()
  }, 300)
}

// 绘制词云
const drawWordCloud = () => {
  const ctx = uni.createCanvasContext('wordcloud', this)
  const width = 300
  const height = 200

  // 清空画布
  ctx.clearRect(0, 0, width, height)

  // 绘制每个关键词
  keywords.value.slice(0, 20).forEach((word, index) => {
    const size = 10 + word.count * 2
    const x = Math.random() * (width - 50)
    const y = Math.random() * (height - 30)
    const angle = Math.random() * 30 - 15

    ctx.save()
    ctx.translate(x, y)
    ctx.rotate(angle * Math.PI / 180)
    ctx.setFontSize(size)
    ctx.setFillStyle(getColor(index))
    ctx.fillText(word.text, 0, 0)
    ctx.restore()
  })

  ctx.draw()
}

// 绘制趋势图
const drawTrendChart = () => {
  const ctx = uni.createCanvasContext('trendChart', this)
  const width = 300
  const height = 200
  const padding = 20
  const data = keywords.value.slice(0, 10).map(w => w.count)
  const maxValue = Math.max(...data, 1)

  // 清空画布
  ctx.clearRect(0, 0, width, height)

  // 绘制坐标轴
  ctx.setStrokeStyle('#ccc')
  ctx.setLineWidth(1)
  ctx.beginPath()
  ctx.moveTo(padding, padding)
  ctx.lineTo(padding, height - padding)
  ctx.lineTo(width - padding, height - padding)
  ctx.stroke()

  // 绘制柱状图
  const barWidth = (width - 2 * padding) / data.length - 5
  data.forEach((value, index) => {
    const barHeight = (value / maxValue) * (height - 2 * padding - 20)
    const x = padding + index * (barWidth + 5) + 5
    const y = height - padding - barHeight

    ctx.setFillStyle(getColor(index))
    ctx.fillRect(x, y, barWidth, barHeight)

    // 显示数值
    ctx.setFontSize(10)
    ctx.setFillStyle('#333')
    ctx.fillText(value.toString(), x + barWidth/2 - 5, y - 5)
  })

  ctx.draw()
}

// 触摸事件处理
const touchStartX = ref(0)
const handleTouchStart = (e: any) => {
  touchStartX.value = e.touches[0].clientX
}

const handleTouchMove = (e: any) => {
  const moveX = e.touches[0].clientX
  const diff = touchStartX.value - moveX

  if (Math.abs(diff) > 50) { // 滑动阈值
    const currentIndex = tabs.findIndex(tab => tab.id === activeTab.value)
    let newIndex = diff > 0 ? currentIndex + 1 : currentIndex - 1

    // 循环切换
    if (newIndex < 0) newIndex = tabs.length - 1
    if (newIndex >= tabs.length) newIndex = 0

    activeTab.value = tabs[newIndex].id
    touchStartX.value = moveX

    // 切换到图表标签时重新绘制
    if (activeTab.value === 'wordcloud') {
      setTimeout(drawWordCloud, 100)
    } else if (activeTab.value === 'trend') {
      setTimeout(drawTrendChart, 100)
    }
  }
}

// 初始化
onReady(() => {
  // 可以添加一些示例文本
  inputText.value = `热点分析器可以帮助您快速了解文本中的关键词和热门话题。
  只需输入一段文字，本工具会自动提取其中的关键词并分析出现频率。
  您可以通过词云直观地看到哪些词汇出现最多，也可以通过趋势图了解词汇分布情况。
  这个工具适用于文章分析、用户反馈整理、社交媒体内容挖掘等多种场景。`
})
</script>

<style scoped lang="scss">
.hot-analyzer-container {
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

  .input-section {
    margin-bottom: 30rpx;

    .text-input {
      width: 100%;
      height: 300rpx;
      padding: 20rpx;
      border: 1rpx solid #ddd;
      border-radius: 10rpx;
      font-size: 28rpx;
      margin-bottom: 20rpx;
    }

    .analyze-btn {
      background-color: #007A87;
      color: white;
      height: 80rpx;
      line-height: 80rpx;
      border-radius: 40rpx;
      font-size: 32rpx;
      border: none;
    }
  }

  .results-section {
    flex: 1;
    display: flex;
    flex-direction: column;

    .tabs {
      display: flex;
      border-bottom: 1rpx solid #eee;
      margin-bottom: 20rpx;

      .tab {
        flex: 1;
        text-align: center;
        padding: 20rpx 0;
        font-size: 28rpx;
        color: #666;

        &.active {
          color: #007A87;
          font-weight: bold;
          border-bottom: 4rpx solid #007A87;
        }
      }
    }

    .tab-content {
      flex: 1;
      overflow: hidden;

      .keywords-list {
        display: flex;
        flex-wrap: wrap;
        gap: 20rpx;
        padding: 20rpx;

        .keyword-item {
          padding: 10rpx 20rpx;
          border-radius: 30rpx;
          background-color: #f5f5f5;
        }
      }

      .wordcloud-canvas {
        width: 100%;
        height: 400rpx;
      }

      .trend-chart {
        width: 100%;
        height: 400rpx;

        .chart-canvas {
          width: 100%;
          height: 100%;
        }
      }

      .stats-grid {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 30rpx;
        padding: 30rpx;

        .stat-item {
          background-color: #f5f5f5;
          border-radius: 20rpx;
          padding: 30rpx;
          display: flex;
          flex-direction: column;
          align-items: center;

          .stat-value {
            font-size: 48rpx;
            font-weight: bold;
            color: #007A87;
            margin-bottom: 10rpx;
          }

          .stat-label {
            font-size: 26rpx;
            color: #666;
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
  "name": "hotAnalyzer",
  "style": {
    "navigationBarTitleText": "热点分析器"
  }
}
</route>
