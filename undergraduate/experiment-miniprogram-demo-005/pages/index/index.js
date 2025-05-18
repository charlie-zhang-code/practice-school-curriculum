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
        }],
      meetingInfo: {
        theme: "数字化转型与未来企业发展",
        speakers: [
          {
            name: "张明",
            title: "某科技公司CEO",
            avatar: "https://tse4-mm.cn.bing.net/th/id/OIP-C.GZBa_hixAIn5uPFkv0J4NwHaHa?rs=1&pid=ImgDetMain"
          },
          {
            name: "李华",
            title: "知名数字化转型专家",
            avatar: "https://tse4-mm.cn.bing.net/th/id/OIP-C.GZBa_hixAIn5uPFkv0J4NwHaHa?rs=1&pid=ImgDetMain"
          },
          {
            name: "王芳",
            title: "某咨询公司合伙人",
            avatar: "https://tse4-mm.cn.bing.net/th/id/OIP-C.GZBa_hixAIn5uPFkv0J4NwHaHa?rs=1&pid=ImgDetMain"
          }
        ],
        time: "2023年12月15日 14:00-17:00",
        location: "北京市海淀区中关村软件园会议中心3楼报告厅"
      }
    },
    
    navigateToMap: function() {
      wx.navigateTo({
        url: '/pages/map/map?location=' + encodeURIComponent(this.data.meetingInfo.location)
      });
    },
    
    onLoad: function() {
      // 可以在这里获取会议信息
    }
  });