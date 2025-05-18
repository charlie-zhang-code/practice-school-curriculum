Page({
    onReady(res) {
    },
    inputValue: '',
    data: {
        src: 'https://prod-vod-sign.miyoushe.com/o0QoxCA0lBYkO9MfSuA4sQhimUgKFvmhJI8Fsi?auth_key=1747442715-fab560f13f-0-441ba57e3f76ac749c217d72e254c829'
    },

    uploadvideo: function () {
        var that = this;
        wx.chooseVideo({
            sourceType: ['album', 'camera'],
            maxDuration: 60,
            camera: 'back',
            success(res) {
                that.setData({
                    src: res.tempFilePath
                })
                console.log(that.data.src)
            }
        })
    },
    audioPlay: function () {
        this.videoContext = wx.createVideoContext('myVideo')
        this.videoContext.play()
    },

    save: function () {
        var that = this;
        wx.downloadFile({

            url: this.data.src,   
            success: function (res) {
                if (res.statusCode === 200) {
                    wx.saveVideoToPhotosAlbum({
                        filePath: res.tempFilePath,
                        success(res) {
                            wx.showToast({
                                title: '保存视频成功！',
                            })
                        },
                        fail(res) {
                            wx.showToast({
                                title: '保存图片失败！',
                            })
                        }
                    })
                }

            }

        })
    }
})