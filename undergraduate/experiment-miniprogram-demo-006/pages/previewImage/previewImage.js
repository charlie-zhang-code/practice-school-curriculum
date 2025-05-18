var app = getApp();
Page({
    data: {
        scrollHeight: 569,
        imageWidth: 250,
        imageHeight: 250,
        photoList: [
            'https://n.sinaimg.cn/sinakd20220727s/560/w1080h1080/20220727/caa8-6cac889f438f8a1a0539a00d07a78bcf.jpg',
            'https://c-ssl.dtstatic.com/uploads/blog/202206/22/20220622234841_8990c.thumb.1000_0.jpeg',
            'https://c-ssl.duitang.com/uploads/blog/202107/24/20210724124953_b7b86.jpeg',
            'https://tse2-mm.cn.bing.net/th/id/OIP-C.Qe6wVlpiE_522YBW_6HjwQHaHa?rs=1&pid=ImgDetMain',
        ]
    },

    previewPhoto: function (e) {
        console.log(e);
        var curTarget = e.currentTarget.id
        console.log(curTarget);
        var shuzu = [e.currentTarget.id]
        wx.previewImage({
            urls: this.data.photoList,
            success: function (res) {

                console.log(res);
            }
        })
    }
})