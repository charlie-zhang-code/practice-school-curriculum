Page({
  data: {
    receiveData: null //数据初始化为空值
  },
  onLoad: function(option) {
    var receiveData = option.id; //接受参数并赋值
    console.log(option);
    this.setData({
      receiveData: receiveData
    }) //更新receiveData值
  },
})