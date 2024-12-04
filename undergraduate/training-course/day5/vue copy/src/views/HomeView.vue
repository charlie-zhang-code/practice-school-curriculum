<template>
    <div>
        <div class="header">
            <div class="box">首页</div>
            <div class="box">我的</div>
        </div>
        <div>
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
export default {
    name: 'HomeView',
}
</script>

<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

.header {
    display: flex;
    width: 100%;
    text-align: center;
    background-color: #333;
    color: #ccc;
}

.header .box {
    flex: 1;
    padding: 20px 0;
    line-height: 20px;
    background-color: #333;
    text-decoration: none;
    color: #ccc;
    border: 1px solid black;
    cursor: pointer;
}
</style>