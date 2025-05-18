var app = getApp();
Page({
    data: {
        scrollHeight: 569,
        imageWidth: 250,
        imageHeight: 250,
        curTarget: "",
        photoList: [
            {
                file: "https://n.sinaimg.cn/sinakd20220727s/560/w1080h1080/20220727/caa8-6cac889f438f8a1a0539a00d07a78bcf.jpg"
            },
            {
                file: "https://c-ssl.duitang.com/uploads/blog/202206/02/20220602221150_f6622.jpeg"
            },
        ]
    },

    saveImage: function (e) {
        wx.showLoading({ title: '下载中...' })

        wx.downloadFile({
            url: 'https://n.sinaimg.cn/sinakd20220727s/560/w1080h1080/20220727/caa8-6cac889f438f8a1a0539a00d07a78bcf.jpg',
            success: function (res) {
                console.log(res);
                //图片保存到本地
                wx.saveImageToPhotosAlbum({
                    filePath: res.tempFilePath,
                    success: () => {
                        wx.showToast({ title: '保存成功', icon: 'success' })
                    },
                    fail: (err) => {
                        // console.error('保存失败:', err)
                        // wx.showToast({ title: '保存失败 Case ' + err.errMsg, icon: 'none' })
                        if (err.errMsg === "saveImageToPhotosAlbum:fail:auth denied" || err.errMsg === "saveImageToPhotosAlbum:fail auth deny" || err.errMsg === "saveImageToPhotosAlbum:fail authorize no response") {
                            // 这边微信做过调整，必须要在按钮中触发，因此需要在弹框回调中进行调用
                            wx.showModal({
                                title: '提示',
                                content: '需要您授权保存相册',
                                showCancel: false,
                                success: modalSuccess => {
                                    wx.openSetting({
                                        success(settingdata) {
                                            console.log("settingdata", settingdata)
                                            if (settingdata.authSetting['scope.writePhotosAlbum']) {
                                                wx.showModal({
                                                    title: '提示',
                                                    content: '获取权限成功,再次点击图片即可保存',
                                                    showCancel: false,
                                                })
                                            } else {
                                                wx.showModal({
                                                    title: '提示',
                                                    content: '获取权限失败，将无法保存到相册哦~',
                                                    showCancel: false,
                                                })
                                            }
                                        },
                                        fail(failData) {
                                            console.log("failData", failData)
                                        },
                                        complete(finishData) {
                                            console.log("finishData", finishData)
                                        }
                                    })
                                }
                            })
                        }
                    }
                })
            },
            fail: (err) => {
                console.error('下载失败:', err)
                wx.showToast({ title: '下载失败', icon: 'none' })
            },
            complete: () => {
                wx.hideLoading()
            }
        })
    }
})