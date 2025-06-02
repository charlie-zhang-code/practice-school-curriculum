<script setup lang="ts">
  import { onLoad } from '@dcloudio/uni-app';
  import { ref } from 'vue'
  import { request } from '@/utils'
  import MarkdownIt from 'markdown-it'

  const detail = ref()
  const content = ref()
  onLoad(async (options) => {
    const response = await request.Get(`/api/articles/${options.id}`)
    // const response = await request.Get(`/api/articles/13`)
    detail.value = response.data

    const md = new MarkdownIt()
    content.value = md.render(response.data.content)
  })
</script>

<template>
  <view>
    <wd-cell-group>
      <wd-cell title="作者" :value="detail?.author" />
      <wd-cell title="分类" :value="detail?.category.name" />
      <wd-cell title="发布时间" :value="detail?.time_since_published" />
    </wd-cell-group>
    <wd-card type="rectangle">
      <view class="markdown-body" v-html="content"></view>
    </wd-card>
  </view>
</template>

<style scoped lang="scss">
  // 使用深度选择器修改样式
  :deep(.markdown-body) {

    // 段落样式
    p {
      line-height: 1.5;
      margin-bottom: 20px;
      // color: #333;
    }

    // 标题样式
    h1,
    h2,
    h3,
    h4,
    h5,
    h6 {
      margin-top: 28px;
      margin-bottom: 16px;
      line-height: 1.3;
      font-weight: bold;
    }

    // 列表样式
    ul,
    ol {
      padding-left: 24px;
      margin-bottom: 16px;

      li {
        margin-bottom: 8px;
        line-height: 1.6;
      }
    }

    // 代码块样式
    pre {
      background-color: #f6f8fa;
      border-radius: 6px;
      padding: 16px;
      margin-bottom: 16px;
      overflow: auto;
    }

    // 链接样式
    a {
      color: #0366d6;
      text-decoration: none;

      &:hover {
        text-decoration: underline;
      }
    }
  }
</style>

<route lang="json">
  {
  "layout": "base",
  "name": "article",
  "style": {
  "navigationBarTitleText": "文章"
  },
  "usingComponents": {
  "markdown-it": "@components/markdown-it/markdown-it"
  }
  }
</route>
