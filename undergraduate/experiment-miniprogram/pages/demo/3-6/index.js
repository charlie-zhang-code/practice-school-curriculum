var per = 30;
Page({
  data: {
    progress: per, //数据初始化
  },
  add: function(e) {
    per += 10; //增加10%
    if (per > 100) {
      per = 100; //进度超过100%不增加
    }
    this.setData({
      progress: per // 更新进度值
    })
  },
  reduce: function(e) {
    per -= 10; //减少10%
    if (per < 0) {
      per = 0; //进度小于0%不减少
    }
    this.setData({
      progress: per // 更新进度值
    })
  }
})