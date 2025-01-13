<script setup lang="ts">
const username = useRoute().params.username as string

const reposOption = ref<ECOption>({
    tooltip: {
        trigger: 'item',
        position: 'right',
    },
    series: [
        {
            name: '仓库大小',
            type: 'pie',
            radius: '50%',
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
})

const reposDataRef = ref()
onMounted(async () => {
    const repos = await fetchRepos()
    reposDataRef.value = repos
    console.log(repos)
    reposOption.value.series[0].data = repos

    // 每10分钟更新一次数据
    setInterval(async () => {
        const data = await fetchRepos()
        reposDataRef.value = data
        console.log(data)

        reposOption.value.series[0].data = repos
    }, 600000);
})
</script>

<template>
    <VChart :option="reposOption" />
</template>