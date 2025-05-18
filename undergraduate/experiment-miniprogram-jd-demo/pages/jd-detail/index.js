// pages/jd-detail/index.js
Page({

    /**
     * Page initial data
     */
    data: {
        product: {}
    },

    /**
     * Lifecycle function--Called when page load
     */
    onLoad(options) {
        const productId = options.id; // 获取传递过来的商品ID
        console.log("传入的ID" + productId);

        // 模拟数据
        const mockProducts = [
            { id: 1, name: '卫仕 五拼冻干全价全阶段猫粮 猫干粮 成猫幼猫乳鸽 海陆盛宴...', price: '123.75', description: '卫仕品牌，是上海宠幸宠物用品有限公司自主创立的宠物健康食品品牌，连续4年蝉联天猫 / 京东宠物营养品销售额NO.1，守护中国犬猫健康17年。', image: 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/277910/12/26339/96259/6808855eF384cdca8/96ad0e0d576adbaf.jpg' },
            { id: 2, name: '帕特 果蔬搭配全价狗粮小型犬肠胃调理成犬狗粮 蓝莓牛肉24斤...', price: '388.00', description: '帕特遵循自然演化饮食的喂养原则，致力于还原自然界宠物猎物原型，追溯宠物原始猎食习性，提出以80%肌肉、10%骨骼和10%内脏比例的“8:1:1生骨肉配方。', image: 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/271483/40/24065/89768/680752dbFe510c55c/0cd7967c50ddbe15.jpg.avif' },
        ];

        const product = mockProducts.find(item => item.id === parseInt(productId));

        if (product) {
            this.setData({
                product: product
            });
        } else {
            this.setData({
                product: {
                    id: productId,
                    name: '未找到商品',
                    price: '',
                    description: '抱歉，未找到该商品的详细信息。',
                    image: '/images/not_found.jpg'
                }
            });
        }
    },

    addToCart: function() {
        // 加入购物车逻辑
        wx.showToast({
            title: '已加入购物车',
            icon: 'success',
            duration: 2000
        });
        // 模拟存储（本地存储，显示在购物车中）
    },

    buyNow: function() {
        // 立即购买逻辑
        wx.showToast({
            title: '立即购买',
            icon: 'none',
            duration: 2000
        });
        // 这里可以调用API进行购买操作
    },

    /**
     * Lifecycle function--Called when page is initially rendered
     */
    onReady() {

    },

    /**
     * Lifecycle function--Called when page show
     */
    onShow() {

    },

    /**
     * Lifecycle function--Called when page hide
     */
    onHide() {

    },

    /**
     * Lifecycle function--Called when page unload
     */
    onUnload() {

    },

    /**
     * Page event handler function--Called when user drop down
     */
    onPullDownRefresh() {

    },

    /**
     * Called when page reach bottom
     */
    onReachBottom() {

    },

    /**
     * Called when user click on the top right corner to share
     */
    onShareAppMessage() {

    }
})