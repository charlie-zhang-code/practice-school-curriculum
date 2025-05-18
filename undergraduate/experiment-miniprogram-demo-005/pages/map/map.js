Page({
    data: {
        longitude: 116.3134541, // 默认北京中关村坐标
        latitude: 39.97940202,
        location: "",
        markers: [{
            id: 1,
            longitude: 116.3134541, // 默认北京中关村坐标
            latitude: 39.97940202,
            name: "会议地点",
            iconPath: "https://img1.ppt118.com/png/detail/2019/09/04/9235b357.jpg",
            width: 30,
            height: 30
        }]
    },

    onLoad: function (options) {
        if (options.location) {
            this.setData({
                location: decodeURIComponent(options.location)
            });

            // 实际开发中这里应该调用地图API获取具体坐标
            // 这里使用默认坐标
        }
    },

    openLocation: function () {
        wx.openLocation({
            latitude: this.data.latitude,
            longitude: this.data.longitude,
            name: "会议地点",
            address: this.data.location,
            scale: 18
        });
    },

    copyAddress: function () {
        wx.setClipboardData({
            data: this.data.location,
            success: function () {
                wx.showToast({
                    title: '地址已复制',
                    icon: 'success'
                });
            }
        });
    }
});