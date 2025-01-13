<template>
  <!-- <div class="box">
    <div class="title">
      <h4>芙蓉楼送辛渐</h4>
      <span class="btn" @click="isShow = !isShow">
        {{ isShow ? '收起' : '展开' }}
      </span>
    </div>
    <div class="container" v-if="isShow">
      <p>寒雨连江夜入吴，</p>
      <p>平明送客楚山孤。</p>
      <p>洛阳亲友如相问，</p>
      <p>一片冰心在玉壶。</p>
    </div>
  </div> -->
  <div class="box">
    <div v-for="item in booksList" :key="item.id">
      {{item.id}}-{{item.name}}-{{item.author}}
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isShow: false,
      arr:[1,2,3,4,5],
      booksList: [
        { id: 1, name: '《红楼梦》', author: '曹雪芹' },
        { id: 2, name: '《西游记》', author: '吴承恩' },
        { id: 3, name: '《水浒传》', author: '施耐庵' },
        { id: 4, name: '《三国演义》', author: '罗贯中' },
      ],
    }
  },
 
  methods: {

  }
}
</script>

<style>
body {
  background-color: #ccc;
}

.box {
  width: 400px;
  margin: 20px auto;
  background-color: #fff;
  border: 4px solid blueviolet;
  border-radius: 1em;
  box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.5);
  padding: 1em 2em 2em;
}

.box h3 {
  text-align: center;
}

.title {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px solid #ccc;
  padding: 0 1em;
}

.title h4 {
  line-height: 2;
  margin: 0;
}

.container {
  border: 1px solid #ccc;
  padding: 0 1em;
}

.btn {
  /* 鼠标改成手的形状 */
  cursor: pointer;
}
</style>
