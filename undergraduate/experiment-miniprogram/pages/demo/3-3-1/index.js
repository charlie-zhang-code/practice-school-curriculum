// pages/demo/3-3-1/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    swiperMargin: wx.getSystemInfoSync().windowWidth > 380 ? '60rpx' : '50rpx',

    swiperCurrent: 0,
    swiperList: [
        "/images/cat1.jpg",
        "/images/cat2.jpg",
        "/images/cat3.jpg", 
    ]
  },

  // 监听swiper切换
  swiperChange: function(e) {
    let current = e.detail.current;
    this.setData({
      swiperCurrent: current
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