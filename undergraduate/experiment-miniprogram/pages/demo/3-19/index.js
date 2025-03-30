Page({
    onReady: function(e) {
      this.audioCtx = wx.createAudioContext('myAudio')
    },
    data: {
      poster: 'https://p3.music.126.net/crhffTBIQfurrtUXYXWPYQ==/109951170566218638.jpg?param=320y320',
      name: '以渺小之名',
      author: '夏日入侵企画',
      src: 'https://m701.music.126.net/20250327100944/5266f763226bf50d0d608ebc5348f20b/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/58546677553/e0d5/5fdc/6613/29c5a5790ab8a5a266dfe2b915727c10.mp3?vuutv=Y9F6TU5mFikkXISaH5JDxqxFy19CQQFW3oJ6qpDm0s+Z4f06+YTh76m2tchcZDPcZDXTh2epLetaa4xGQvHkAIpLArpmLpHg/fuIifjgBXQ=',
    },
    audioPlay: function() {
      this.audioCtx.play() //播放音乐
    },
    audioPause: function() {
      this.audioCtx.pause() //暂停播放
    },
    audio10: function() {
      this.audioCtx.seek(10) //回到10秒
    },
  })