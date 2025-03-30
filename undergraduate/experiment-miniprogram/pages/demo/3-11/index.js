Page({
    onSubmit(e) {
      console.log("form发生了submit事件，携带数据为：")
      console.log(e.detail.value)
    },
    onReset() {
      console.log("form发生了reset事件，表单已被重置")
    }
  })