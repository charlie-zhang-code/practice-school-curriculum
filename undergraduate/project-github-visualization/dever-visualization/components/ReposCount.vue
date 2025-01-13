<script setup lang="ts">
const colors = ['#5470C6', '#91CC75', '#EE6666', '#FAC858', '#73C0DE', '#3BA272', '#FC8452', '#9A60B4', '#ea7ccc'];

const reposOption = ref<ECOption>({
    color: colors,

    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross'
        }
    },
    grid: {
        right: '20%'
    },
    toolbox: {
        feature: {
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
        }
    },
    xAxis: [
        {
            type: 'category',
            axisTick: {
                alignWithLabel: true
            },
            axisLabel: {
                color: '#ffffff'
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: 'Com.',
            position: 'left',
            alignTicks: true,
            axisLine: {
                show: true,
                lineStyle: {
                    color: colors[0]
                }
            },
            axisLabel: {
                formatter: '{value}'
            }
        },
        {
            type: 'value',
            name: 'Tag.',
            position: 'left',
            alignTicks: true,
            offset: 30,
            axisLine: {
                show: true,
                lineStyle: {
                    color: colors[1]
                }
            },
            axisLabel: {
                formatter: '{value}'
            }
        },
        {
            type: 'value',
            name: 'Iss.',
            position: 'right',
            alignTicks: true,
            axisLine: {
                show: true,
                lineStyle: {
                    color: colors[2]
                }
            },
            axisLabel: {
                formatter: '{value}'
            }
        },
        {
            type: 'value',
            name: 'PR',
            position: 'right',
            alignTicks: true,
            offset: 30,
            axisLine: {
                show: true,
                lineStyle: {
                    color: colors[3]
                }
            },
            axisLabel: {
                formatter: '{value}'
            }
        },
        {
            type: 'value',
            name: 'Rel.',
            position: 'right',
            alignTicks: true,
            offset: 60,
            axisLine: {
                show: true,
                lineStyle: {
                    color: colors[4]
                }
            },
            axisLabel: {
                formatter: '{value}'
            }
        },
        {
            type: 'value',
            name: 'Sta.',
            position: 'right',
            alignTicks: true,
            offset: 90,
            axisLine: {
                show: true,
                lineStyle: {
                    color: colors[5]
                }
            },
            axisLabel: {
                formatter: '{value}'
            }
        },
        {
            type: 'value',
            name: 'Wat.',
            position: 'right',
            alignTicks: true,
            offset: 120,
            axisLine: {
                show: true,
                lineStyle: {
                    color: colors[6]
                }
            },
            axisLabel: {
                formatter: '{value}'
            }
        }
    ],
    series: []
})




const userReposDataRef = ref()
onMounted(async () => {

    const data = await fetchReposDetailData()
    userReposDataRef.value = data

    console.log("data", data)

    reposOption.value.xAxis[0].data = data?.categories

    reposOption.value.series = data?.series

    // 定时更新一次数据
    setInterval(async () => {
        const data = await fetchReposDetailData()
        userReposDataRef.value = data
        console.log(data)

        reposOption.value.xAxis[0].data = data?.categories

        reposOption.value.series = data?.series
    }, 600000);
})
</script>

<template>
    <VChart :option="reposOption" />
</template>