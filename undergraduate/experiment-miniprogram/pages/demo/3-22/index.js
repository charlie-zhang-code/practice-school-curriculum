Page({
    data: {
      latitude: 26.578343,
      longitude: 106.713478,
      markers: [{
        latitude: 26.578343,
        longitude: 106.713478,
        iconPath: "/images/cat1.jpg",
        label: {
          content: "贵阳市"
        }
      }],
    },
    onReady: function(e) {
      this.mapCtx = wx.createMapContext("myMap")
    },
    getCenterLocation: function() { //获取位置
      this.mapCtx.getCenterLocation({
        success: function(res) {
          console.log(res.longitude)
          console.log(res.latitude)
        }
      })
    },
    moveToLocation: function() { //移动位置
      this.mapCtx.moveToLocation()
    },
    includePoints: function() { //缩放视野
      this.mapCtx.includePoints({
        padding: [10],
        points: [{
          latitude: 23.10229,
          longitude: 113.3345211,
        }, {
          latitude: 23.00229,
          longitude: 113.3345211,
        }]
      })
    }
  })