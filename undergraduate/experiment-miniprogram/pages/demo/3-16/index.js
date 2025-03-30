Page({
    switch1Change: function(e) {
      console.log("switch1触发switch1Change事件，携带value值为：" + e.detail.value)
    },
    switch2Change: function(e) {
      console.log("switch2触发switch2Change事件，携带value值为：" + e.detail.value)
    }
  })