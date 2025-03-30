Component({
    properties: {
      // 外部传入的用户信息
      userInfo: {
        type: Object,
        value: {
          avatarUrl: 'https://tse2-mm.cn.bing.net/th/id/OIP-C.g5M-iZUiocFCi9YAzojtRAAAAA?rs=1&pid=ImgDetMain',
          nickname: 'Charlie',
          bio: '不语'
        }
      }
    },
  
    data: {
      // 功能列表
      functions: [
        { id: 1, name: '播放记录', icon: 'https://ts1.tc.mm.bing.net/th/id/R-C.ef3b2b31f6c2789ec078e47eb08cde79?rik=nQvwKhZtSbmMeQ&riu=http%3a%2f%2fwww.kuaipng.com%2fUploads%2fpic%2fw%2f2022%2f01-17%2f118506%2fwater_118506_698_698_.png&ehk=OPxUhbmj%2b1pKn4mnYHAJfd2zVeC%2f5Tdh6BVGwwSNDz4%3d&risl=&pid=ImgRaw&r=0' },
        { id: 2, name: '收藏夹', icon: 'https://ts1.tc.mm.bing.net/th/id/R-C.07317495dc7e8c02ad59f878396b9be0?rik=Ziek%2ftSmzOlvHA&riu=http%3a%2f%2fwww.kuaipng.com%2fUploads%2fpic%2fw%2f2021%2f10-25%2f111586%2fwater_111586_698_698_.png&ehk=u3ckQzvVVRU4FfPLp51dRitiqwEUJ9g8VGprcxhRomo%3d&risl=&pid=ImgRaw&r=0' },
        { id: 4, name: '设置', icon: 'https://tse1-mm.cn.bing.net/th/id/OIP-C.7mzBNatmW4WPMuI1TNK1twHaHa?rs=1&pid=ImgDetMain' }
      ]
    },
  
    methods: {
      // 功能按钮点击事件
      onFunctionClick: function (e) {
        const functionName = e.currentTarget.dataset.name;
        this.triggerEvent('functionClick', { functionName }); // 触发父组件事件
      }
    }
  });