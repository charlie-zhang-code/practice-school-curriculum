Page({
    //页面的初始数据
    data: {
      touristone: ["五台山", "普陀山", "峨眉山"],
      touristtwo: ["莫高窟", "云冈石窟", "龙门石窟"],
      touristthree: ["法门寺", "佛光寺", "大相国寺"],
      value: [0, 0, 0], //设置默认的每个选项的数组下标
    },
    pickerviewChange: function(e) {
      var v = e.detail.value; //获得数组的下标
      var travel = []; //声明一个空数组，用于存放用户选择的值
      travel.push(this.data.touristone[v[0]]); //追加用户选择第一个数组的元素
      travel.push(this.data.touristtwo[v[1]]); //追加用户选择第二个数组的元素
      travel.push(this.data.touristthree[v[2]]); //追加用户选择第三个数组的元素
      this.setData({
        travel: travel //将用户选择的值更新赋给travel
      });
    },
  })