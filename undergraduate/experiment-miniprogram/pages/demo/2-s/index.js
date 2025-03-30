
Page({
  data:{
    goodsdata:[
        {
          id: 0,
          title: "五拼冻干全阶段猫粮",
          titleTwo: "猫干粮 成猫幼猫 海陆盛宴",
          price: "123.00",
          image: "https://img10.360buyimg.com/n1/s450x450_jfs/t1/284656/2/11239/106152/67e7329eFe443b817/6d45eb9f31f03ddf.jpg"
        },
        {
          id: 1,
          title: "ThinkPad联想笔记本支架电脑支架",
          titleTwo: "便携立式电脑桌支架铝合",
          price: "36.00",
          image: "https://img10.360buyimg.com/n1/s450x450_jfs/t1/283517/11/4585/67365/67d91137Fed0597de/a37c19d66e68eb44.jpg.avif"
    
        },
        {
          id: 2,
          title: "帕特 成犬狗粮",
          titleTwo: "果蔬搭配全价狗粮小型犬肠胃调理成犬狗粮",
          price: "388.00",
          image: "https://img12.360buyimg.com/n1/s450x450_jfs/t1/263457/11/30020/81581/67cc5619F2650cb25/1897571d4d42eeab.jpg.avif"
        },
        {
          id: 3,
          title: "小米（MI）小米自带线充电宝",
          titleTwo: "20000 33W 浅咖色",
          price: "149.00",
          image: "https://img12.360buyimg.com/n1/s450x450_jfs/t1/274085/30/10604/84612/67e3eedaF1e6a03f7/d7ef521769219caf.jpg.avif"
        },
        {
          id: 4,
          title: "352空气净化器",
          titleTwo: "除甲醛细菌流感病毒TVOC异味过敏原甲醛数",
          price: "799.00",
          image: "https://img12.360buyimg.com/n1/s450x450_jfs/t1/278567/39/10931/203880/67e52c80Fa0d91795/39b6e6da4462529a.jpg.avif"
        }
      ]
  },
  onLoad(){
  },
  todetail: function (e) {
    var listid = e.currentTarget.dataset.id
    console.log("点击了第" + (listid + 1) + "个商品")
    wx.navigateTo({
      url: './detail/detail?listid=' + listid
    })
  }
})