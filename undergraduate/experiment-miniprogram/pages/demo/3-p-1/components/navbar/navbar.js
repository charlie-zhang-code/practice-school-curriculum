// tab-bar.js
Component({
    properties: {
      activeIndex: {
        type: Number,
        value: 0
      }
    },
    data: {
      list: [
        {
          text: '音乐榜单',
          icon: 'https://tse2-mm.cn.bing.net/th/id/OIP-C.GcRlpNTMNf06GOD3l3pILgHaHa?rs=1&pid=ImgDetMain',
          activeIcon: 'https://tse2-mm.cn.bing.net/th/id/OIP-C.GcRlpNTMNf06GOD3l3pILgHaHa?rs=1&pid=ImgDetMain'
        },
        {
          text: '歌手榜单',
          icon: 'https://tse2-mm.cn.bing.net/th/id/OIP-C.GMhe5yqlUrbfgWMwn0ZYTgHaHa?rs=1&pid=ImgDetMain',
          activeIcon: 'https://tse2-mm.cn.bing.net/th/id/OIP-C.GMhe5yqlUrbfgWMwn0ZYTgHaHa?rs=1&pid=ImgDetMain'
        },
        {
          text: '个人中心',
          icon: 'https://static.vecteezy.com/system/resources/previews/008/442/086/non_2x/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg',
          activeIcon: 'https://static.vecteezy.com/system/resources/previews/008/442/086/non_2x/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg'
        }
      ]
    },
    methods: {
      handleTabChange(e) {
        const index = e.currentTarget.dataset.index
        this.triggerEvent('tabchange', { index })
      }
    }
  })