Component({
    properties: {
      // 接收外部传入的歌手列表数据
      rankList: {
        type: Array,
        value:  [
            { id: 1, name: '周杰伦', avatarUrl: 'https://d.musicapp.migu.cn/prod/playlist-service/playListimg/402bdb81-c298-4582-b208-543920fb8b08.jpg' },
            { id: 2, name: '林俊杰', avatarUrl: 'https://y.gtimg.cn/music/photo_new/T001R300x300M000001BLpXF2DyJe2.jpg?max_age=2592000' },
            { id: 3, name: '陈奕迅', avatarUrl: 'https://c-ssl.duitang.com/uploads/item/201607/07/20160707120740_QkXRh.jpeg'},
            { id: 4, name: '王力宏', avatarUrl: 'https://img.baizhan.net/d/file/20190611/cb93cb1731865197444a5ff328a0537c.png'},
            { id: 5, name: '张学友', avatarUrl: 'https://d.musicapp.migu.cn/data/oss/service66/00/2a/n7/zy'},
            { id: 6, name: '张惠妹', avatarUrl: 'https://y.gtimg.cn/music/photo_new/T062M000004NWYgg3YSeql.jpg?max_age=0'},
            { id: 7, name: '吴莫愁', avatarUrl: 'https://ts1.tc.mm.bing.net/th/id/R-C.8e653459ad3d543a8d749d61d107727b?rik=TQMAthJ3jXBUSA&riu=http%3a%2f%2fy0.ifengimg.com%2fa%2f2016_04%2f27ab77d3f3ae2e6.jpg&ehk=GEwgldilBrxRlzairstbPlu9FYEkEnMskJ30QPcPSE8%3d&risl=&pid=ImgRaw&r=0'},
            { id: 8, name: '孙楠', avatarUrl: 'https://tse2-mm.cn.bing.net/th/id/OIP-C.I6hh8zCrq6lB_fambrCCiwHaFs?rs=1&pid=ImgDetMain'},
            { id: 9, name: '老狼', avatarUrl: 'https://ts1.tc.mm.bing.net/th/id/R-C.ed21e5c2452d4d70472d650140ea3471?rik=Dr%2bBXIij4ff%2fxA&riu=http%3a%2f%2fwenhui.whb.cn%2fu%2fcms%2fwww%2f201907%2f081454240p5e.jpg&ehk=dr0mddtx68g7RPcnfc4NFC%2fVtFMFzp3e4yx41%2bJAfmA%3d&risl=&pid=ImgRaw&r=0'},
            { id: 10, name: '陈楚生', avatarUrl: 'https://ts1.tc.mm.bing.net/th/id/R-C.67c9dc915e6af0f2e041af7e7726a9a4?rik=x3Fyl%2fsQg8NPHQ&riu=http%3a%2f%2fn.sinaimg.cn%2ftransform%2f20150109%2fG3z2-cczmvun4842569.JPG&ehk=HkR756wtkMRJlol%2ffZaGhl05Nuyp%2f7lbkk4RMbr5sT0%3d&risl=&pid=ImgRaw&r=0'},
            { id: 11, name: '邓紫棋', avatarUrl: 'https://c-ssl.dtstatic.com/uploads/blog/202111/20/20211120012303_cee16.thumb.1000_0.jpeg'}
          ]
      },
      // 接收外部传入的标题
      title: {
        type: String,
        value: '歌手排行榜'
      }
    },
  
    methods: {
      // 如果需要对数据进行排序，可以在这里处理
      sortRankList() {
        const sortedList = this.data.rankList.slice().sort((a, b) => b.score - a.score);
        this.setData({
          rankList: sortedList
        });
      },
      navigateToSingerSongs(event) {
        const singerId = event.currentTarget.dataset.id;
        wx.navigateTo({
          url: `/pages/demo/3-p-1/pages/singer-music/singer-music?id=${singerId}`,
        });
      },
      onAvatarError(event) {
        const { index } = event.currentTarget.dataset;
        const defaultAvatar = 'https://static.vecteezy.com/system/resources/previews/008/442/086/non_2x/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg'; // 默认头像地址
      
        // 检查 index 是否有效
        if (index === undefined || index < 0 || index >= this.data.rankList.length) {
          console.error('Invalid index:', index);
          return;
        }
      
        // 更新 rankList 中的头像
        const rankList = this.data.rankList;
        rankList[index].avatarUrl = defaultAvatar; // 替换为默认头像
        this.setData({
          rankList
        });
      }
    },
  
    // 组件生命周期函数，在组件加载时自动排序
    attached() {
      this.sortRankList();
    }
  });