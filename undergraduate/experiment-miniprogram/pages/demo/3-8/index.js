Page({
    data: {
      items: [
        { name: "tiger", value: "老虎" },
        { name: "elephant", value: "大象" },
        { name: "lion", value: "狮子", checked: "true" },
        { name: "penguin", value: "企鹅" },
        { name: "elk", value: "麋鹿" },
        { name: "swan", value: "天鹅" },
      ]
    },
    checkboxChange:function(e) {
      console.log("checkbox发生change事件，携带value值为：", e.detail.value)
    }
  })
  