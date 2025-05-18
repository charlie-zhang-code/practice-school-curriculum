Page({
    data: {
      configs: [] //定义一个空数组保存数据
    },
    onShow: function(options) {
      var configs = wx.getStorageSync('configs'); //获取缓存
      this.setData({
        configs: configs
      });
    },
  })