<script lang="ts" setup>
const userInfoDataRef = ref()
onMounted(async () => {
    const userInfo = await fetchUserInfo()
    userInfoDataRef.value = userInfo
    console.log(userInfo)
})

const show = ref(false)

const username = ref('')
const token = ref('')

async function goToView() {

    if (token.value) {
        const data = await fetchConfigUser(username.value, token.value)
        if (data['code'] === 200) {
            // 刷新页面
            location.reload()
        } else {
            alert('User not found')
        }
    } else {
        const data = await fetchConfigUserNoToken(username.value)
        if (data['code'] === 200) {
            // 刷新页面
            location.reload()
        } else {
            alert('User not found')
        }
    }
}
</script>

<template>
    <NFlex vertical class="w-screen h-screen"
        style="background-image: url('/bg.jpg'); background-size: cover; background-repeat: no-repeat;background-position: center;">
        <NFlex justify="space-between" align="center" style="padding: 10px 20px;">
            <NFlex align="center">
                <NFlex>
                    <NFlex justify="center" align="center">
                        <div class="color-white">Repos</div>
                        <NTag type="success">
                            {{ userInfoDataRef?.public_repos }}
                        </NTag>
                    </NFlex>
                    <NFlex justify="center" align="center">
                        <div class="color-white">Following</div>
                        <NTag type="success">
                            {{ userInfoDataRef?.following }}
                        </NTag>
                    </NFlex>
                    <NFlex justify="center" align="center">
                        <div class="color-white">Followers</div>
                        <NTag type="success">
                            {{ userInfoDataRef?.followers }}
                        </NTag>
                    </NFlex>
                </NFlex>
            </NFlex>
            <div class="floating-title text-2xl font-bold flex justify-center color-white">
                用户仓库可视化
            </div>
            <div>
                <NButton style="color: white;" @click="show = true">设置</NButton>
                <NDrawer v-model:show="show" :width="502">
                    <NDrawerContent title="设置" closable>
                        <NFlex vertical>
                            <NInput v-model:value="username" placeholder="Github username" />
                            <NInput v-model:value="token" placeholder="Token" />
                            <NButton @click="goToView">
                                重新加载
                            </NButton>
                        </NFlex>
                    </NDrawerContent>
                </NDrawer>
            </div>
        </NFlex>
        <NGrid responsive="screen" :x-gap="8" :y-gap="8">
            <NGridItem span="5">
                <div class="w-full h-280px">
                    <Repos />
                </div>
                <UserCommits />
            </NGridItem>

            <NGridItem span="14">
                <NFlex justify="center" align="center" vertical>
                    <div class="w-full h-650px">
                        <ReposCount />
                    </div>
                </NFlex>
            </NGridItem>

            <NGridItem span="5">
                <NFlex justify="center" align="center" vertical>
                    <Userinfo />

                    <div class="w-full h-280px">
                        <ReposLanguage />
                    </div>

                    <div class="w-full h-290px mt--40px">
                        <EventsCount class="mt--20px" />
                    </div>

                </NFlex>
            </NGridItem>

        </NGrid>
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
