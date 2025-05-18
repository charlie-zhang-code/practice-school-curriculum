Page({
  data: {
    placeholderText: "连接后端服务器中...",
    messagelist: [],
    socketOpen: false,
  },
  onLoad: function (options) {
    var that = this;
    console.log("将要连接后端服务器。");
    wx.connectSocket({
      url: 'ws://localhost:8080'
    });

    wx.onSocketOpen(function (res) {
      console.log("连接后台服务器成功。");
      that.setData({
        placeholderText: "连接成功，请输入您的昵称。",
        socketOpen: true
      });
    });

    wx.onSocketMessage(function (res) {
      console.log('收到后端服务器内容：' + res.data);
      var data = res.data;
      var dataArray = data.split("_");
      var newMessage = {
        type: dataArray[0],
        name: dataArray[1],
        time: dataArray[2],
        message: dataArray[3]
      };
      var newArray = that.data.messagelist.concat(newMessage);
      that.setData({
        messagelist: newArray,
        placeholderText: "请输入聊天信息"
      });
    });
  },

  onUnload: function () {
    wx.closeSocket();
  },

  fasong: function (e) {
    var that = this
    if (e.detail.value.inputValue != "") {
      if (this.data.socketOpen) {
        wx.sendSocketMessage({
          data: e.detail.value.inputValue
        })
      }
    }
  }
});
