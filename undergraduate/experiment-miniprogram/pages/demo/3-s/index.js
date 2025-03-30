Page({
    universityChange: function (e) {
      console.log("你选择的现在大几：", e.detail.value)
    },
    mobilChange: function (e) {
      console.log("你选择使用手机的最大用途是：", e.detail.value)
    },
    timechange: function (e) {
      console.log("你选择的每天使用手机的时间是：", e.detail.value + "小时")
    },
    programChange: function (e) {
      console.log("你选择的是否使用过微信小程序：", e.detail.value)
    },
    onSubmit(e) {
      console.log("你输入的对小程序发展前途的看法是"+e.detail.value.textarea)
  
    },
    onReset() {
      console.log("表单被重置")
    }
  })