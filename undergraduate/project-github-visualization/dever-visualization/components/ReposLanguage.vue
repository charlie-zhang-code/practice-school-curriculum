<script setup lang="ts">
const username = useRoute().params.username as string

const reposOption = ref<ECOption>({
  tooltip: {
    trigger: 'item',
  },
  // legend: {
  //   data: ['Size'],
  //   // 设置label文本颜色
  //   textStyle: {
  //     color: '#fff'
  //   }
  // },
  radar: {
    // shape: 'circle',
    indicator: [
      {}
      // { name: 'C', max: 6500 },
      // { name: 'Java', max: 16000 },
      // { name: 'CPP', max: 30000 },
    ],
    name: { //修改indicator文字的颜色
      textStyle: {
        color: "white" // 设置文字颜色为白色
      }
    }
  },
  series: [
    {
      name: 'Budget vs spending',
      type: 'radar',
      // label: {
      //   show: true,
      //   position: 'top',
      //   color: '#ffffff' // 设置文字颜色为白色
      // },
      data: [
        // {
        //   value: [4200, 3000, 20000, 35000, 50000, 18000],
        //   name: 'Allocated Budget'
        // },
        {
          // value: [5000, 14000, 28000],
          // name: 'Actual Spending'
          
        }
      ]
    }
  ]
})

const userReposLanguagesRef = ref()
onMounted(async () => {
  const languageData = await fetchReposLanguages()
  userReposLanguagesRef.value = languageData
  console.log(languageData)

  reposOption.value.radar.indicator = languageData?.indicator
  reposOption.value.series[0].data = languageData?.data

  // 每10分钟更新一次数据
  setInterval(async () => {
    const data = await fetchReposLanguages()
    userReposLanguagesRef.value = languageData
    console.log(languageData)

    reposOption.value.radar.indicator = languageData?.indicator
    reposOption.value.series[0].data = languageData?.data
  }, 600000);
})
</script>

<template>
  <VChart :option="reposOption" />
</template>