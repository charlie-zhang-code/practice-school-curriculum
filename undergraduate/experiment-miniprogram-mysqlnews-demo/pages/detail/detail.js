var app = getApp()
Page({
    data: {
        id1: 1,
        shuzu: []
    },
    onLoad: function (options) {
        var that = this
        wx.request({
            url: 'http://127.0.0.1:8080/api/news/detail',
            data: { id: decodeURIComponent(options.id) },
            method: 'POST',
            header: {
                'content-type': 'application/x-www-form-urlencoded'
            },
            success(res) {
                console.log(res.data)
                that.setData({
                    shuzu: res.data
                })
            }
        })
    },
    closepage: function () {
        wx.navigateBack()
    },
    toastChange: function () {
        this.setData({ toastHidden: true })
        wx.navigateBack()
    },
})