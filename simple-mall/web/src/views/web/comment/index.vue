<template>
  <div class="product-comment-page">
    <!-- 商品信息 -->
    <div class="product-info">
      <h2>商品名称：{{ product.name }}</h2>
      <p>价格：{{ product.price }} 元</p>
      <!-- 使用 Element UI 的 el-avatar 组件显示用户头像 -->
      <el-avatar :src="product.image" class="product-image"></el-avatar>
    </div>

    <!-- 评论列表 -->
    <div v-for="(comment, index) in comments" :key="comment.id" class="comment-item">
      <!-- 使用 Element UI 的 el-card 组件展示每个评论 -->
      <el-card class="comment-card">
        <div class="comment-header">
          <!-- 使用 Element UI 的 el-avatar 组件显示用户头像 -->
          <el-avatar :size="40" :src="comment.user.avatar" class="header-img"></el-avatar>
          <div class="comment-info">
            <span class="user-name">{{ comment.user.name }}</span>
            <span class="comment-time">{{ comment.time }}</span>
            <span class="user-rating">评分：{{ comment.rating }} 星</span>
          </div>
          <div class="comment-actions">
            <!-- 使用 Element UI 的 el-button 组件提供回复按钮 -->
            <el-button type="text" @click="showReplyInput(index, comment.user.name, comment.id)">
              <i class="el-icon-chat-dot-round"></i> 回复
            </el-button>
          </div>
        </div>
        <div class="comment-content">
          <p>{{ comment.content }}</p>
          <div v-if="comment.images.length" class="comment-images">
            <img v-for="(image, idx) in comment.images" :key="idx" :src="image" alt="评论图片" class="comment-image" />
          </div>
        </div>
        <div class="reply-box">
          <div v-for="(reply, replyIndex) in comment.reply" :key="reply.id" class="reply-item">
            <!-- 使用 Element UI 的 el-avatar 组件显示用户头像 -->
            <el-avatar :size="40" :src="reply.user.avatar" class="header-img"></el-avatar>
            <div class="reply-info">
              <span class="reply-name">{{ reply.user.name }} 回复 {{ reply.to }}：</span>
              <span class="reply-time">{{ reply.time }}</span>
            </div>
            <div class="reply-content">
              <p>{{ reply.content }}</p>
            </div>
            <div class="reply-actions">
              <!-- 使用 Element UI 的 el-button 组件提供回复按钮 -->
              <el-button type="text" @click="showReplyInput(replyIndex, reply.user.name, reply.id)">
                <i class="el-icon-chat-dot-round"></i> 回复
              </el-button>
            </div>
          </div>
          <div v-show="_inputShow(index)" class="my-reply">
            <!-- 使用 Element UI 的 el-avatar 组件显示用户头像 -->
            <el-avatar :size="40" :src="myAvatar" class="header-img"></el-avatar>
            <div class="reply-info">
              <!-- 使用 Element UI 的 el-input 组件提供输入框 -->
              <el-input type="textarea" placeholder="回复 {{ to }} ..." v-model="replyComment"
                @input="onDivInput($event)"></el-input>
            </div>
            <div class="reply-btn-box">
              <!-- 使用 Element UI 的 el-button 组件提供提交按钮 -->
              <el-button class="reply-btn" size="medium" @click="sendCommentReply(index)"
                type="primary">发表评论</el-button>
            </div>
          </div>
        </div>
      </el-card>
    </div>

    <!-- 顶部评论输入框 -->
    <div v-clickoutside="hideReplyBtn" @click="inputFocus" class="my-reply">
      <!-- 使用 Element UI 的 el-avatar 组件显示用户头像 -->
      <el-avatar :size="40" :src="myHeader" class="header-img"></el-avatar>
      <div class="reply-info">
        <!-- 使用 Element UI 的 el-input 组件提供输入框 -->
        <el-input type="textarea" placeholder="输入评论..." v-model="replyComment" @input="onDivInput($event)"></el-input>
      </div>
      <div class="reply-btn-box" v-show="btnShow">
        <!-- 使用 Element UI 的 el-button 组件提供提交按钮 -->
        <el-button class="reply-btn" size="medium" @click="sendComment" type="primary">发表评论</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus';

export default {
  name: 'ProductComment',
  directives: {
    clickoutside: {
      // 初始化指令
      bind(el, binding, vnode) {
        function documentHandler(e) {
          if (!el.contains(e.target)) {
            binding.value();
          }
        }
        el.vueClickOutside = documentHandler;
        document.addEventListener('click', documentHandler);
      },
      unbind(el, binding) {
        document.removeEventListener('click', el.vueClickOutside);
        delete el.vueClickOutside;
      },
    },
  },
  data() {
    return {
      btnShow: false,
      index: 0,
      replyComment: '',
      myAvatar: 'https://example.com/avatar.png',
      myHeader: 'https://example.com/avatar.png',
      product: {
        name: '示例商品',
        price: 199.99,
        image: 'https://example.com/product-image.png',
      },
      comments: [
        {
          id: 1,
          user: {
            name: '用户A',
            avatar: 'https://example.com/avatar1.png',
          },
          rating: 5,
          content: '非常好的商品，强烈推荐！',
          time: '2025-02-08 10:00',
          commentNum: 2,
          images: [
            'https://example.com/image1.png',
            'https://example.com/image2.png',
          ],
          reply: [
            {
              id: 2,
              user: {
                name: '用户B',
                avatar: 'https://example.com/avatar2.png',
              },
              content: '我也觉得很好！',
              time: '2025-02-09 11:00',
              commentNum: 0,
              to: '用户A', // 添加被回复用户的名称
            },
          ],
          inputShow: false, // 添加输入框显示状态
        },
        {
          id: 3,
          user: {
            name: '用户C',
            avatar: 'https://example.com/avatar3.png',
          },
          rating: 4,
          content: '商品不错，但价格稍高。',
          time: '2025-02-10 12:00',
          commentNum: 1,
          images: [
            'https://example.com/image3.png',
          ],
          reply: [
            {
              id: 4,
              user: {
                name: '用户D',
                avatar: 'https://example.com/avatar4.png',
              },
              content: '价格确实有点高，但质量确实好。',
              time: '2025-02-11 13:00',
              commentNum: 0,
              to: '用户C', // 添加被回复用户的名称
            },
          ],
          inputShow: false, // 添加输入框显示状态
        },
      ],
    };
  },
  methods: {
    inputFocus() {
      this.btnShow = true;
    },
    showReplyBtn() {
      this.btnShow = true;
    },
    hideReplyBtn() {
      this.btnShow = false;
    },
    showReplyInput(i, name, id) {
      this.comments.forEach((comment) => {
        comment.inputShow = false;
      });
      this.index = i;
      this.comments[i].inputShow = true;
      this.to = name;
      this.toId = id;
    },
    _inputShow(i) {
      return this.comments[i].inputShow;
    },
    sendComment() {
      if (!this.replyComment) {
        ElMessage({
          showClose: true,
          type: 'warning',
          message: '评论不能为空',
        });
      } else {
        let a = {};
        let timeNow = new Date().getTime();
        let time = this.dateStr(timeNow);
        a.id = this.comments.length + 1;
        a.user = {
          name: '用户E', // 假设用户名称
          avatar: this.myAvatar,
        };
        a.rating = 5; // 默认评分
        a.content = this.replyComment;
        a.time = time;
        a.commentNum = 0;
        a.images = []; // 假设用户上传的图片
        this.comments.push(a);
        this.replyComment = '';
        ElMessage({
          message: '评论成功',
          type: 'success',
        });
      }
    },
    sendCommentReply(i) {
      if (!this.replyComment) {
        ElMessage({
          showClose: true,
          type: 'warning',
          message: '评论不能为空',
        });
      } else {
        let a = {};
        let timeNow = new Date().getTime();
        let time = this.dateStr(timeNow);
        a.id = this.comments[i].reply.length + 1;
        a.user = {
          name: '用户E', // 假设用户名称
          avatar: this.myAvatar,
        };
        a.content = this.replyComment;
        a.time = time;
        a.commentNum = 0;
        a.to = this.to; // 保存被回复用户的名称
        this.comments[i].reply.push(a);
        this.replyComment = '';
        ElMessage({
          message: '回复成功',
          type: 'success',
        });
      }
    },
    onDivInput(e) {
      this.replyComment = e.target.value;
    },
    dateStr(date) {
      var time = new Date().getTime();
      time = parseInt((time - date) / 1000);
      var s;
      if (time < 60 * 10) {
        return '刚刚';
      } else if ((time < 60 * 60) && (time >= 60 * 10)) {
        s = Math.floor(time / 60);
        return s + '分钟前';
      } else if ((time < 60 * 60 * 24) && (time >= 60 * 60)) {
        s = Math.floor(time / 60 / 60);
        return s + '小时前';
      } else if ((time < 60 * 60 * 24 * 30) && (time >= 60 * 60 * 24)) {
        s = Math.floor(time / 60 / 24 / 60);
        return s + '天前';
      } else {
        var date = new Date(parseInt(date));
        return date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + date.getDate();
      }
    },
  },
};
</script>

<style lang="stylus" scoped>
.product-comment-page
  padding 20px
  background-color #f5f5f5
  .product-info
    background-color #fff
    padding 20px
    margin-bottom 20px
    .product-image
      width 100%
      max-width 300px
      height auto
  .comment-item
    margin-bottom 20px
  .comment-card
    background-color #fff
    padding 15px
    border-radius 5px
    box-shadow 0 2px 4px rgba(0, 0, 0, 0.1)
    .comment-header
      display flex
      align-items center
      margin-bottom 10px
      .header-img
        margin-right 10px
      .comment-info
        flex-grow 1
        display flex
        flex-direction column
        .user-name
          font-weight bold
        .comment-time
          color #888
        .user-rating
          color #ff9800
      .comment-actions
        display flex
        align-items center
        margin-left 10px
        .el-button
          margin-left 5px
    .comment-content
      margin-bottom 10px
      .comment-images
        display flex
        flex-wrap wrap
        margin-bottom 10px
        .comment-image
          width 80px
          height 80px
          margin-right 10px
          margin-bottom 10px
          border-radius 5px
          object-fit cover
    .reply-box
      margin-top 10px
      .reply-item
        background-color #f9f9f9
        padding 10px
        margin-bottom 5px
        border-radius 5px
        .reply-info
          display flex
          flex-direction column
          .reply-name
            font-weight bold
          .reply-time
            color #888
        .reply-content
          margin-top 5px
          .reply-actions
            display flex
            align-items center
            .el-button
              margin-left 5px
      .my-reply
        padding 10px
        background-color #fff
        border-radius 5px
        box-shadow 0 2px 4px rgba(0, 0, 0, 0.1)
        .reply-info
          display flex
          align-items center
          .el-input
            flex-grow 1
            margin-right 10px
        .reply-btn-box
          height 25px
          margin-top 10px
          .reply-btn
            position relative
            float right
            margin-right 15px
</style>