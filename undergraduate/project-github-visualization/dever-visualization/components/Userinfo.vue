<script setup lang="ts">
const username = useRoute().params.username as string

const userInfoDataRef = ref()
onMounted(async () => {
    const userInfo = await fetchUserInfo()
    userInfoDataRef.value = userInfo
    console.log(userInfo)

    // 每10分钟更新一次数据
    setInterval(async () => {
        const userInfo = await fetchUserInfo()
        userInfoDataRef.value = userInfo
    }, 600000);
})

function formatDateTime(isoString: string): string {
    const date = new Date(isoString);
    const year = date.getFullYear(); // 获取四位数年份
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 获取两位数月份
    const day = date.getDate().toString().padStart(2, '0'); // 获取两位数日期
    return `${year}年${month}月${day}日`;
}
</script>

<template>
    <NFlex justify="center" align="center" vertical>
        <NAvatar round :size="64" :src="userInfoDataRef?.avatar_url" />
        <div class="text-xl font-bold color-white">
            {{ userInfoDataRef?.name }}
        </div>
        <NTag type="success">
            {{ userInfoDataRef?.login }}
        </NTag>

        <!-- <NTag type="success">
            {{ formatDateTime(userInfoDataRef?.created_at) }}
        </NTag> -->
    </NFlex>
</template>