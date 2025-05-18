// cart.js
Page({
    data: {
        cartItems: [
            // 示例数据，实际应用中应从服务器获取
            { id: 1, name: '卫仕 五拼冻干全价全阶段猫粮 猫干粮 成猫幼猫乳鸽 海陆盛宴...', price: 123.75, image: 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/277910/12/26339/96259/6808855eF384cdca8/96ad0e0d576adbaf.jpg', quantity: 11 },
            { id: 2, name: '帕特 果蔬搭配全价狗粮小型犬肠胃调理成犬狗粮 蓝莓牛肉24斤...', price: 388.00, image: 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/271483/40/24065/89768/680752dbFe510c55c/0cd7967c50ddbe15.jpg.avif', quantity: 2 },
            // 可以添加更多商品
        ],
        totalPrice: 0, // 总价
    },

    /**
     * 增加商品数量
     */
    increaseQuantity: function (e) {
        const id = e.currentTarget.dataset.id;
        this.setData({
            cartItems: this.data.cartItems.map(item =>
                item.id === id ? { ...item, quantity: item.quantity + 1 } : item
            )
        });
        this.calculateTotalPrice();
    },

    /**
     * 减少商品数量，确保不为负数
     */
    decreaseQuantity: function (e) {
        const id = e.currentTarget.dataset.id;
        this.setData({
            cartItems: this.data.cartItems.map(item =>
                item.id === id ? { ...item, quantity: item.quantity > 1 ? item.quantity - 1 : 1 } : item
            )
        });
        this.calculateTotalPrice();
    },

    /**
     * 计算总价
     */
    calculateTotalPrice: function () {
        const total = this.data.cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);
        this.setData({
            totalPrice: total
        });
    },

    /**
     * 结算按钮点击事件
     */
    checkout: function () {
        if (this.data.cartItems.length === 0) {
            wx.showToast({
                title: '购物车为空',
                icon: 'none'
            });
            return;
        }
        // 这里可以添加跳转到结算页面的逻辑
        // wx.navigateTo({
        //     url: '/pages/checkout/checkout', // 确保你有这个页面
        // });
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        // 可以在这里初始化购物车数据，如从本地存储获取
        // 示例中已经初始化了 cartItems，可以根据实际情况修改
        this.calculateTotalPrice();
    }
});