Page({
    data: {
      username: '',
      password: '123456',
      confirmPassword: '123456',
      loading: false,
      errorMsg: '',
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
  
    // 确认密码输入事件
    onConfirmPasswordInput(e) {
      this.setData({
        confirmPassword: e.detail.value
      });
    },
  
    // 手机号码输入事件
    onPhoneInput(e) {
      this.setData({
        phone: e.detail.value
      });
    },
  
    // 注册处理函数
    handleRegister(e) {
      const { username, password, confirmPassword } = e.detail.value;
      
      // 表单验证
      if (!username.trim()) {
        this.setData({ errorMsg: '请输入用户名' });
        return;
      }
      
      if (!password.trim()) {
        this.setData({ errorMsg: '请输入密码' });
        return;
      }
      
      if (password !== confirmPassword) {
        this.setData({ errorMsg: '两次输入的密码不一致' });
        return;
      }
      
      // 清除之前的错误信息
      this.setData({ errorMsg: '' });
      
      // 显示加载状态
      this.setData({ loading: true });
      
      // 调用注册接口
      wx.request({
        url: 'http://localhost:8080/api/user/register',
        method: 'POST',
        data: {
          username,
          password,
        },
        header: {
          'Content-Type': 'application/json'
        },
        success: (res) => {
          if (res.statusCode === 200) {
            // 注册成功处理
            wx.showToast({
              title: '注册成功',
              icon: 'success',
              duration: 2000,
              success: () => {
                // 跳转到登录页
                setTimeout(() => {
                  wx.reLaunch({
                    url: '/pages/login/login'
                  });
                }, 2000);
              }
            });
          } else {
            this.setData({ 
              errorMsg: res.data.message || '注册失败，请稍后重试' 
            });
          }
        },
        fail: (err) => {
          console.error('注册请求失败', err);
          this.setData({ errorMsg: '网络错误，请检查网络连接' });
        },
        complete: () => {
          // 隐藏加载状态
          this.setData({ loading: false });
        }
      });
    }
  });