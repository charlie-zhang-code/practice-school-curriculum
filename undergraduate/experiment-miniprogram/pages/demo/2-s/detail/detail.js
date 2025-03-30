const app = getApp();
Page({
  data: {
    data:null
  },
  onLoad: function(option) {
    var id = option.listid
    this.setData({
      data: app.goodsdata[id]
    })
  }
})