<script setup lang="ts">
const containerRef = ref(null);

function startScrolling() {
  if (containerRef.value) {
    let index = 0;
    setInterval(() => {
      // 将第一个元素移到最后，创建滚动效果
      if (userCommits.value.length > 0) {
        userCommits.value.push(userCommits.value.shift());
      }
      index++;
      // 如果你想要停止滚动，可以在这里添加条件
    }, 2000); // 每2秒移动一次
  }
}


const userCommits = ref()
onMounted(async () => {
  const data = await fetchUserAction()
  userCommits.value = data
  console.log(data)

  startScrolling();

  // 每10分钟更新一次数据
  setInterval(async () => {
    const data = await fetchUserAction()
    userCommits.value = data
    console.log(data)
  }, 600000);
})
</script>

<template>
  <div ref="containerRef" class="scroll-container">
    <table border="1" cellpadding="10" cellspacing="0">
      <tbody>
        <tr v-for="(commit, index) in userCommits" :key="index">
          <td>
            <NTag type="success">
              <NEllipsis style="max-width: 80px">
                {{ commit.repo_name }}
              </NEllipsis>
            </NTag>
          </td>
          <td>
            <NTag type="info">
              <NEllipsis style="max-width: 80px">
                {{ commit.commit_message }}
              </NEllipsis>
            </NTag>
          </td>
          <td>
            <NTag type="warning">{{ commit.commit_time }}</NTag>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.scroll-container {
  width: 100%;
  height: 300px;
  /* 设置容器的高度 */
  overflow: hidden;
  /* 隐藏超出容器的内容 */
  position: relative;
}

table {
  width: 100%;
  border-collapse: collapse;
  color: white;
  table-layout: fixed;
  /* 确保表格布局固定 */
}

th,
td {
  /* overflow: hidden; */
  white-space: nowrap;
  /* max-width: 160px;
  text-overflow: ellipsis; */
}
</style>