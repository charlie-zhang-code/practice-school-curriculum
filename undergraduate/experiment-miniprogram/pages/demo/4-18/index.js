Page({
    data: {
      indicatorDots: true,
      autoplay: true,
      interval: 3000,
      imgUrls: [
        "https://ts1.tc.mm.bing.net/th/id/R-C.31df3a5a2d8462228734f95d459883e2?rik=7EE6TeWDk%2f%2bctQ&riu=http%3a%2f%2fwww.quazero.com%2fuploads%2fallimg%2f140303%2f1-140303214331.jpg&ehk=SpI7mz%2byLqOkT8BL79jcd3iCtQYNFlBHQzbtF1p0vuQ%3d&risl=&pid=ImgRaw&r=0",
        "https://img.51miz.com/Element/00/94/35/84/e0966302_E943584_9699d713.jpg",
      ],
      movies: [{
          id: 1,
        image: "https://p0.pipi.cn/mmdb/54ecde9a2c9f2a51ba06d67d795a9434b8421.jpg?imageView2/1/w/464/h/644",
        name: "我不是药神",
        type: "类型: 剧情 喜剧",
        director: "导演：文牧野",
        actor: "主演：徐峥 王传君",
        showTime: "上映：2018年",
      }, {
          id: 2,
        image: "https://p0.pipi.cn/mmdb/54ecde3311ef2abe12f0ee281b37b5fc0bc2b.jpg?imageView2/1/w/464/h/644",
        name: "肖申克的救赎",
        type: "类型: 剧情 犯罪",
        director: "导演：弗兰克·德拉邦特",
        actor: "主演：蒂姆·罗宾斯",
        showTime: "上映：1994年",
      }]
    },
    navigateToDetail: function (e) {
        const movieId = e.currentTarget.dataset.id; // 获取电影ID
        wx.navigateTo({
          url: `/pages/demo/4-18/detail/index?id=${movieId}` // 跳转到详情页面，并传递电影ID
        });
      }
  })