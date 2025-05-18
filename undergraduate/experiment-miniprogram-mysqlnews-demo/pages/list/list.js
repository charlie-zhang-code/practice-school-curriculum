Page({
    data: {
        id1: 1,
        shuzu: []
    },
    onLoad: function (options) {
        var that = this
        wx.request({
            url: 'http://127.0.0.1:8080/api/news/list',
            data: {
                x: '',
                y: ''
            },
            header: {
                'content-type': 'application/json'
            },
            success(res) {
                console.log(res)
                that.setData({
                    shuzu: res.data
                })
            }
        })

    },

    dian: function (e) {
        var a = e.target.id
        console.log(a)
        wx.navigateTo({
            url: "/pages/detail/detail?id=" + a,
        })
    },
})