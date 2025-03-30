Page({
    getInput: function(e) {
      console.log("getInput触发，输入框的内容发生改变，当前值为:" + e.detail.value);
    },
    getBlur: function(e) {
      console.log("getBlur触发，文本框失去了焦点，当前值为:" + e.detail.value);
    },
  })