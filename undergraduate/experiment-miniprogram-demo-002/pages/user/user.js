Page({
    data: {
        userInfo: {}, // 用户信息
        userId: '',   // 用户ID
        isLoggedIn: false // 是否已登录
    },

    onLoad() {
        // 页面加载时从本地存储获取用户信息
        this.getUserInfoFromStorage();
    },

    // 从本地存储获取用户信息
    getUserInfoFromStorage() {
        try {
            const userInfo = wx.getStorageSync('userInfo');
            if (userInfo) {
                this.setData({
                    userInfo: userInfo,
                    userId: userInfo.id || '暂无',
                    isLoggedIn: true
                });
            } 
            else {
                 // 跳转登陆页面
                 wx.reLaunch({
                    url: '/pages/login/login'
                });
            }
        } catch (e) {
            console.error('获取用户信息失败', e);
            this.setData({
                isLoggedIn: false
            });
        }
    },

    // 处理退出登录
    handleLogout() {
        wx.showModal({
            title: '提示',
            content: '确定要退出登录吗？',
            success(res) {
                if (res.confirm) {
                    // 清除本地存储的用户信息
                    wx.removeStorageSync('userInfo');
                    wx.removeStorageSync('isLoggedIn')
                    // 跳转登陆页面
                    wx.reLaunch({
                        url: '/pages/login/login'
                    });
                }
            }
        });
    }
});