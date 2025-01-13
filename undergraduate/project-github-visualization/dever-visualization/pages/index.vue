<script lang="ts" setup>
const username = ref('')
const token = ref('')

async function goToView() {
  if (token.value) {
    const data = await fetchConfigUser(username.value, token.value)
    if (data['code'] === 200) {
      useRouter().push('/view')
    } else {
      alert('User not found')
    }
  } else {
    const data = await fetchConfigUserNoToken(username.value)
    if (data['code'] === 200) {
      useRouter().push('/view')
    } else {
      alert('User not found')
    }
  }

}
</script>

<template>
  <NFlex vertical class="w-screen h-screen" justify="center" align="center"
    style="background-image: url('/bg.jpg'); background-size: cover; background-repeat: no-repeat;background-position: center;">
    <div class="floating-title text-2xl font-bold flex justify-center color-white">
      系统配置
    </div>
    <div style="gap: 10px; display: flex; flex-direction: column; justify-content: center; align-items: center">
      <!-- <h2 style="color: white; " class="color-white font-size-24px">配置</h2> -->
      <NInput v-model:value="username" placeholder="用户名" style="width: 250px;" />
      <NInput v-model:value="token" placeholder="Token" />
      <NButton @click="goToView" style="width: 120px; color: white;">
        配置
      </NButton>
    </div>
  </NFlex>
</template>

<style scoped>
.floating-title {
  position: fixed;
  top: 40px;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  font-size: 2xl;
  font-weight: bold;
  color: white;
  z-index: 1000;
  padding: 20px;
  border-radius: 10px;
}
</style>
