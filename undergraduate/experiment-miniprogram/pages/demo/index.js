// pages/demo/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    demos: [
        { name: 'demo0', label: 'Demo 0' },
        { name: 'demo1', label: 'Demo 1' },
        { name: '2-4', label: 'Demo 2-4' },
        { name: '2-5', label: 'Demo 2-5' },
        { name: '2-6', label: 'Demo 2-6' },
        { name: '2-7', label: 'Demo 2-7' },
        { name: '2-8', label: 'Demo 2-8' },
        { name: '2-9', label: 'Demo 2-9' },
        { name: '2-s', label: 'Demo 2实训' },
        { name: '3-1', label: 'Demo 3-1' },
        { name: '3-2', label: 'Demo 3-2' },
        { name: '3-3', label: 'Demo 3-3' },
        { name: '3-3-1', label: 'Demo 3-3-1' },
        { name: '3-4', label: 'Demo 3-4' },
        { name: '3-5', label: 'Demo 3-5' },
        { name: '3-6', label: 'Demo 3-6' },
        { name: '3-7', label: 'Demo 3-7' },
        { name: '3-8', label: 'Demo 3-8' },
        { name: '3-9', label: 'Demo 3-9' },
        { name: '3-10', label: 'Demo 3-10' },
        { name: '3-11', label: 'Demo 3-11' },
        { name: '3-12', label: 'Demo 3-12(样式出现问题)' },
        { name: '3-13', label: 'Demo 3-13' },
        { name: '3-14', label: 'Demo 3-14' },
        { name: '3-15', label: 'Demo 3-15' },
        { name: '3-16', label: 'Demo 3-16' },
        { name: '3-17', label: 'Demo 3-17(样式出现问题)' },
        { name: '3-18', label: 'Demo 3-18路由导航' },
        { name: '3-19', label: 'Demo 3-19音频组件' },
        { name: '3-20', label: 'Demo 3-20视频组件' },
        { name: '3-21', label: 'Demo 3-21图片组件' },
        { name: '3-22', label: 'Demo 3-22地图组件' },
        { name: '3-s', label: 'Demo 3实训' },
        { name: '3-p-1', label: 'Demo 3 音乐盒子' },
        { name: '4-18', label: 'Demo 4-18' },
        { name: '4-20', label: 'Demo 4-20' },
        { name: '4-21', label: 'Demo 4-21' },
        { name: 's-3-1', label: '实验三（Part1）' },
        { name: 's-3-2', label: '实验三（Part2）' },
      ]
  },

  navigateToDemo: function(event) {
    const page = event.currentTarget.dataset.page;
    const url = `/pages/demo/${page}/index`;
    wx.navigateTo({
        url: url,
        success: () => {},
        fail: () => {
          wx.showToast({
            title: '页面不存在',
            icon: 'none'
          });
        }
      });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})