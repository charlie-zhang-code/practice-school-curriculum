Page({
    data: {
      li: ['默认排序', '离我最近', '价格最低', '价格最高'],
      shownavindex: 0 //数据初始化
    },
    //下拉事件
    listmenu: function(e) {
      if (this.data.openif) {
        this.setData({
          openif: false, //当前菜单没有下拉
          shownavindex: 0 //控制图标样式
        })
      } else {
        this.setData({
          content: this.data.li, //获取数组数据
          openif: true, //当前菜单下拉
          shownavindex: e.currentTarget.dataset.nav //控制图标样式
        })
      }
    }
  })