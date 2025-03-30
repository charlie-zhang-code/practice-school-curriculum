//获取应用实例
const app = getApp()

Page({
  data: {
    hours:0,
  },
  onLoad: function () {
    var myDate = new Date();
    var hours=myDate.getHours();       
    this.setData({
      hours: hours
    })
  }
})
