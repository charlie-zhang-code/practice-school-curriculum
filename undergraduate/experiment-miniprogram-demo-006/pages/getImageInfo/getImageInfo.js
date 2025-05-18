//index.js
//获取应用实例
const app = getApp()

Page({
    data: {
        type: '',
        height: null,
        width: null,
        tempFilePath: '', // 存储图片临时路径
        isDownloading: false // 下载状态

    },

    onLoad: function () {
    },

    choose: function (e) {
        var that = this;
        wx.chooseImage({
            success(res) {
                const tempFilePath = res.tempFilePaths[0];
                wx.getImageInfo({
                    src: res.tempFilePaths[0],
                    success(res) {
                        that.data.type = res.type,
                            that.data.height = res.height,
                            that.data.width = res.width
                        console.log(res)
                        that.setData({
                            type: res.type,
                            height: res.height,
                            width: res.width,
                            tempFilePath: tempFilePath
                        })
                    }
                })
            }
        })

    },
    // 预览图片
    previewImage: function () {
        if (!this.data.tempFilePath) return;

        wx.previewImage({
            current: this.data.tempFilePath, // 当前显示图片的http链接
            urls: [this.data.tempFilePath] // 需要预览的图片http链接列表
        });
    },
    // 下载图片到相册
    downloadImage: function () {
        if (!this.data.tempFilePath) {
            wx.showToast({
                title: '请先选择图片',
                icon: 'none'
            });
            return;
        }

        const that = this;
        that.setData({ isDownloading: true });

        // 先保存到本地临时文件
        wx.saveFile({
            tempFilePath: this.data.tempFilePath,
            success(res) {
                const savedFilePath = res.savedFilePath;
                // 保存到相册
                wx.saveImageToPhotosAlbum({
                    filePath: savedFilePath,
                    success() {
                        wx.showToast({
                            title: '保存成功',
                            icon: 'success'
                        });
                    },
                    fail(err) {
                        console.error('保存失败', err);
                        wx.showToast({
                            title: '保存失败',
                            icon: 'none'
                        });
                        // 提示用户去设置权限
                        if (err.errMsg.indexOf('auth deny') !== -1) {
                            wx.showModal({
                                title: '提示',
                                content: '需要相册权限才能保存图片，是否去设置？',
                                success(res) {
                                    if (res.confirm) {
                                        wx.openSetting();
                                    }
                                }
                            });
                        }
                    },
                    complete() {
                        that.setData({ isDownloading: false });
                    }
                });
            },
            fail(err) {
                console.error('临时保存失败', err);
                wx.showToast({
                    title: '保存失败',
                    icon: 'none'
                });
                that.setData({ isDownloading: false });
            }
        });
    }
})
