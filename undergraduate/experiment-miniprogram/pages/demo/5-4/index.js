Page({
    data:{
      configs:[] //定义一个空数组保存数据
    },
    onLoad:function(options){
      var configs = wx.getStorageSync('configs'); //获取缓存
      this.setData({configs:configs});
    },
    sliderChange:function(e){
      var id = e.target.id;
      this.data.configs[id].time = e.detail.value
      wx.setStorageSync('configs', this.data.configs);  //将设置的数值放入缓冲
      console.log(this.data.configs)
    },
    radioChange:function(e){
      var id = e.target.id;
      this.data.configs[id].voice = e.detail.value
      wx.setStorageSync('configs', this.data.configs);  //将设置的数值放入缓冲
      console.log(this.data.configs)
    }
  })