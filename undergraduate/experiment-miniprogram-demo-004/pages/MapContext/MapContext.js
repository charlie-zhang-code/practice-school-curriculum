Page({
    data: {
        latitude: 26.5710542,
        longitude: 107.9769525,
        markers: [{
            id: 1,
            latitude: 26.5710542,
            longitude: 107.9769525,
            name: '凯里'
        }],
    },
    onReady: function (e) {
        //创建 map 上下文 MapContext 对象。
        this.zhizhen = wx.createMapContext('ditu')
    },
    //获取当前地图中心的经纬度
    getCenterLocation: function () {
        this.zhizhen.getCenterLocation({
            success: function (res) {
                console.log(res.longitude)
                console.log(res.latitude)
            }
        })
    },
    //将地图中心移动到当前定位点
    moveToLocation: function () {
        this.zhizhen.moveToLocation()
    },
    //平移marker，带动画
    translateMarker: function () {
        this.zhizhen.translateMarker({
            markerId: 1,
            autoRotate: true,
            duration: 2000,
            destination: {
                latitude: 26.5710542,
                longitude: 108.9769525,
            },
            animationEnd() {
                console.log('animation end')
            }
        })
    },
    //缩放视野展示所有经纬度
    includePoints: function () {
        this.zhizhen.includePoints({
            padding: [10],
            points: [{
                latitude: 26.5710542,
                longitude: 107.9769525,
            }, {
                latitude: 26.5710542,
                longitude: 107.9769525,
            }]
        })
    },
    //获取当前地图的缩放级别
    scaleClick: function () {
        this.zhizhen.getScale({
            success: function (res) {
                console.log(res.scale)
            }
        })
    },
    //获取当前地图的视野范围
    getRegionClick: function () {
        this.zhizhen.getRegion({
            success: function (res) {
                console.log(res.southwest)
                console.log(res.northeast)
            }
        })
    }
})
