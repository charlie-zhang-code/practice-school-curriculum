<script setup lang="ts">
import type { TabsPaneContext } from 'element-plus'
// 商品详情页面  {{ $route.params.id }}
import { Get, Post } from '@/utils';
import { router } from '@/router';

const radio1 = ref('1')

const activeName = ref('first')

const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab, event)
}

const route = useRoute()

const productsDataRef = ref()
function productsData() {
  Get<any>(`/api/v1/product/${route.params.id}`).then((res: any) => {
    console.log(res)
    productsDataRef.value = res
  })
}

onMounted(() => {
  productsData()
})

function addTrolley() {
  Post<any>(`/api/v1/trolley`, {
    id: 0,
    productId: productsDataRef.value?.data.id,
  }).then((res: any) => {
    if (res) {
      console.log(res)
      router.push('/trolley')
    }
  })
}
</script>

<template>
  <div class="flex flex-col min-h-xl gap-4">
    <div class="flex items-center">
      <el-image class="w-70 h-70" :src="productsDataRef?.data?.img" fit="cover" />

      <div class="flex flex-col gap-4 p-x-4">
        <span class="text-25px">
          {{ productsDataRef?.data?.name }}
        </span>
        <!-- <div class="flex items-center gap-2">
          <span>
            评分
          </span>
          <el-rate />
        </div> -->
        <div>
          <el-tag>
            {{ productsDataRef?.data?.specification }}
          </el-tag>
        </div>
        <div>
          <el-tag type="danger">
            {{ productsDataRef?.data?.price }}
          </el-tag>
        </div>
        <el-radio-group v-model="radio1">
          <el-radio value="1" size="large">在线支付</el-radio>
        </el-radio-group>
        <el-button type="danger" @click="addTrolley">
          加入购物车
        </el-button>
      </div>
    </div>

    <el-tabs v-model="activeName" type="card" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane label="简介" name="first">
        {{ productsDataRef?.data?.description }}
      </el-tab-pane>
      <el-tab-pane label="评论" name="second">
        <div class="flex flex-col gap-2">
          <div class="flex flex-col gap-2">
            <el-input type="textarea" autocomplete="off" />
            <div class="flex justify-end">
              <el-button>
                评论
              </el-button>
            </div>
          </div>
          <el-card shadow="never" v-for="index in 2" :key="index">
            <div class="flex flex-col gap-2">
              <div class="flex items-center gap-2">
                <el-avatar :size="50" />
                <div class="flex flex-col gap-2">
                  <span>
                    用户名
                  </span>
                  <div>
                    <el-tag>
                      规格
                    </el-tag>
                  </div>
                </div>
              </div>
              评论内容
            </div>
          </el-card>
          <!-- <el-pagination class="flex justify-center" background layout="prev, pager, next" :total="1000" /> -->
           <el-button @click="router.push(`/comment/${productsDataRef?.data?.id}`)">
            更多
           </el-button>
        </div>
      </el-tab-pane>
      <!-- <el-tab-pane label="提问" name="third">
        <div class="flex flex-col gap-2">
          <div class="flex flex-col gap-2">
            <el-input type="textarea" autocomplete="off" />
            <div class="flex justify-end">
              <el-button>
                提问
              </el-button>
            </div>
          </div>
          <el-card shadow="never" v-for="index in 5" :key="index">
            <div class="flex flex-col gap-2">
              <div class="flex items-center gap-2">
                <el-avatar :size="50" />
                <div class="flex flex-col gap-2">
                  <span>
                    用户名
                  </span>
                  <div>
                    <el-tag>
                      网龄
                    </el-tag>
                  </div>
                </div>
              </div>
              提问内容
              <el-card shadow="never" v-for="index in 2" :key="index">
                <div class="flex flex-col gap-2">
                  <div class="flex items-center gap-2">
                    <el-avatar :size="50" />
                    <div class="flex flex-col gap-2">
                      <span>
                        用户名
                      </span>
                      <div>
                        <el-tag>
                          网龄
                        </el-tag>
                      </div>
                    </div>
                  </div>
                  提问内容
                </div>
              </el-card>

              <div class="flex justify-end">
                <el-button>
                  回复
                </el-button>
              </div>
            </div>
          </el-card>
          <el-pagination class="flex justify-center" background layout="prev, pager, next" :total="1000" />
        </div>
      </el-tab-pane> -->
    </el-tabs>
  </div>
</template>

<style scoped></style>
