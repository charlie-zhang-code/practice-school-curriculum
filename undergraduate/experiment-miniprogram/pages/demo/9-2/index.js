//index.js
//获取应用实例
const app = getApp()

Page({
    data: {
        tapIndex: 0,
        src: ["https://cdn.pixabay.com/photo/2024/04/15/21/21/dusk-8698720_1280.jpg"]
    },

    share: function (e) {
        var that = this
        console.log(e.currentTarget.id)
        wx.showActionSheet({
            itemList: ['保存图片', '预览图片', '复制图片地址'],
            success(res) {
                console.log(res.tapIndex)
                console.log(res)
                if (res.tapIndex == 0) {
                    wx.downloadFile({
                        url: that.data.src[0],
                        success(res) {
                            wx.saveImageToPhotosAlbum({
                                filePath: res.tempFilePath
                            })
                            wx.showToast({
                                title: '保存成功',
                                icon: 'success',
                                duration: 1500
                            });
                        }
                    })

                }
                if (res.tapIndex == 1) {
                    wx.previewImage({
                        current: that.data.src[0],
                        urls: that.data.src

                    })
                }

            },
            fail(res) {
                console.log(res.errMsg)
            }
        });
    }
})
