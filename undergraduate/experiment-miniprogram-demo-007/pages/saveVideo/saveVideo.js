Page({

    inputValue: '',
    data: {
        src: 'http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400&bizid=1023&hy=SH&fileparam=302c020101042530230204136ffd93020457e3c4ff02024ef202031e8d7f02030f42400204045a320a0201000400'
    },

    save: function () {
        var that = this;
        wx.downloadFile({

            url: 'http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400&bizid=1023&hy=SH&fileparam=302c020101042530230204136ffd93020457e3c4ff02024ef202031e8d7f02030f42400204045a320a0201000400',     //仅为示例，并非真实的资源
            success: function (res) {
                // 只要服务器有响应数据，就会把响应内容写入文件并进入 success 回调，业务需要自行判断是否下载到了想要的内容
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