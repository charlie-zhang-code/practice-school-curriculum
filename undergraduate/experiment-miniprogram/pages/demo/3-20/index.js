Page({
    onReady: function(res) {
      this.videoContext = wx.createVideoContext('myVideo')
    },
    inputValue: '', //创建一个空字符串用于保存弹幕内容
    data: {
      src: "http://vodpub6.v.news.cn/yqfbzx-original/20230818/20230818bd5de7446029436cb41e0ff2c37e1c54_606f38a617a746988c4bb396538066dd.mp4",
      danmuList: [{
          text: "强国有我",
          color: "red",
          time: 1
        },

      ]
    },
    bindInputBlur: function(e) {
      this.inputValue = e.detail.value //获取输入内容
    },
    bindSendDanmu: function() {
      this.videoContext.sendDanmu({
        text: this.inputValue, //发送弹幕 
      })
    },
    bindPlay: function() {
      this.videoContext.play() //播放视频
    },
    bindPause: function() {
      this.videoContext.pause() //暂停视频
    },
  })