// pages/demo/2-4/index.js
//获取应用实例
var app=getApp();

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  
  onLoad: function (options) {
    console.log("page执行onLoad函数")
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    console.log("page执行onReady函数")
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    console.log("page执行onShow函数")
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    console.log("page执行onHide函数")
  }

 
 
})
