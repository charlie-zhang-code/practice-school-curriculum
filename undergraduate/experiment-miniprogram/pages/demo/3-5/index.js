var initData = "2019年，中国要推进这70个工程项目：制定实施新时期“互联网+”行动，实施数字经济、“互联网+”重大工程，建设人工智能创新应用先导区，持续推进大数据综合试验区建设。加快5G商用步伐和IPv6规模部署，加强人工智能、工业互联网、物联网等新型基础设施建设和融合应用。"
var extraLine = []; //创建一个空数组
Page({
  data: {
    text: initData
  },
  add: function(e) {
    extraLine.push("增加一行") //增加一行
    this.setData({
      text: initData + '\n' + extraLine.join('\n') //更新数组值
    })
  },
  reduce: function(e) {
    if (extraLine.length > 0) {
      extraLine.pop() //删除一行
      this.setData({
        text: initData + '\n' + extraLine.join('\n') //更新数组值
      })
    }
  }
})