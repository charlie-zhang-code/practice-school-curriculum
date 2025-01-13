<script setup lang="ts">
// 定义一个颜色数组，用于循环设置柱状图的颜色
const colors = ['#d48265', '#91c7ae', '#749f83', '#ca8622'];

const eventOption = ref<ECOption>({
    tooltip: {
        trigger: 'item',
    },
    xAxis: {
        type: 'category',
        axisLabel: {
            color: '#ffffff'
        }
    },
    yAxis: {
        type: 'value',
        // 设置字体白色
        axisLabel: {
            color: '#ffffff'
        }
    },
    series: [
        {
            type: 'bar',
            label: {
                show: true,
                position: 'top',
                color: '#ffffff' // 设置文字颜色为白色
            },
            itemStyle: {
                // 使用回调函数为每个柱子设置颜色
                color: function (params) {
                    return colors[params.dataIndex % colors.length];
                }
            },
        }
    ]
})

const userActionRef = ref()
onMounted(async () => {
    const data = await fetchReposAction()
    userActionRef.value = data
    console.log(data)

    eventOption.value.xAxis.data = data?.event
    eventOption.value.series[0].data = data?.event_count

    // 每10分钟更新一次数据
    setInterval(async () => {
        const data = await fetchReposAction()
        userActionRef.value = data
        console.log(data)

        eventOption.value.xAxis.data = data?.event
        eventOption.value.series[0].data = data?.event_count
    }, 600000);
})

</script>

<template>
    <VChart :option="eventOption" />
</template>