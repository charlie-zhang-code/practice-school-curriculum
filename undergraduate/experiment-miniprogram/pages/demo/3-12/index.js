Page({
    //页面的初始数据
    data: {
      oneItems: ["老虎", "大象", "狮子"],
      doubleItems: [
        ["千岛湖", "洞庭湖", "玄武湖"],
        ["鼓浪屿", "平遥古城", "坝上草原"],
        ["泰山", "黄山", "华山"]
      ]
    },
    selectorChange: function(e) {
      var i = e.detail.value; //获得数组的下标
      var value = this.data.oneItems[i]; //获得选项的值
      this.setData({
        selector: value //将用户选择的值更新赋给selector
      });
    },
    multiSelectorChange: function(e) {
      var arrayIndex = e.detail.value; //获得数组的下标
      var value = new Array(); //声明一个空数组，用于存放用户选择的值
      for (var i = 0; i < arrayIndex.length; i++) {
        var m = arrayIndex[i]; //通过数组的遍历，获得第i个数组元素的下标
        var n = this.data.doubleItems[i][m]; //获得第i个数组的元素值
        value.push(n); //往数组中追加新的值
      }
      this.setData({
        multiSelector: value //将用户选择的值更新赋给multiSelector
      });
    },
    timeChange: function(e) {
      var value = e.detail.value; //获得选择的时间
      this.setData({
        time: value //将用户选择的值更新赋给time
      });
    },
    dateChange: function(e) {
      var value = e.detail.value; //获得选择的日期
      this.setData({
        date: value //将用户选择的值更新赋给date
      });
    },
    regionChange: function(e) {
      var value = e.detail.value; //获得选择的省市区
      this.setData({
        region: value //将用户选择的值更新赋给region
      });
    },
  })