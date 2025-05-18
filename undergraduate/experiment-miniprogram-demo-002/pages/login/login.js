Page({
    data: {
      username: 'tom1',
      password: '123456',
      loading: false,
      errorMsg: ''
    },
  
    // 用户名输入事件
    onUsernameInput(e) {
      this.setData({
        username: e.detail.value
      });
    },
  
    // 密码输入事件
    onPasswordInput(e) {
      this.setData({
        password: e.detail.value
      });
    },
  
    // 登录处理函数
    handleLogin(e) {
      const { username, password } = e.detail.value;
      
      // 表单验证
      if (!username.trim()) {
        this.setData({ errorMsg: '请输入用户名' });
        return;
      }
      
      if (!password.trim()) {
        this.setData({ errorMsg: '请输入密码' });
        return;
      }
      
      // 清除之前的错误信息
      this.setData({ errorMsg: '' });
      
      // 显示加载状态
      this.setData({ loading: true });
      
      // 调用登录接口
      wx.request({
        url: 'http://localhost:8080/api/user/login', // 替换为你的后端接口地址
        method: 'POST',
        data: {
          username,
          password
        },
        header: {
          'Content-Type': 'application/json'
        },
        success: (res) => {
          if (res.statusCode === 200) {
            // 登录成功处理
            const data = res.data;
            if (data) {
              // 这里可以根据需要存储用户信息到本地
              wx.setStorageSync('isLoggedIn', true);
              wx.setStorageSync('userInfo', data);
              wx.showToast({
                title: '登录成功',
                icon: 'success',
                duration: 2000,
                success: () => {
                  // 跳转到首页或其他页面
                  setTimeout(() => {
                    wx.reLaunch({
                      url: '/pages/user/user'
                    });
                  }, 2000);
                }
              });
            } else {
              // 处理其他成功响应
              wx.showToast({
                title: data,
                icon: 'none'
              });
            }
          } else {
            // 处理其他状态码
            this.setData({ errorMsg: '登录失败，请稍后重试' });
          }
        },
        fail: (err) => {
          console.error('登录请求失败', err);
          this.setData({ errorMsg: '网络错误，请检查网络连接' });
        },
        complete: () => {
          // 隐藏加载状态
          this.setData({ loading: false });
        }
      });
    }
  });