Page({
    data: {
      billboards: [
        { id: '1', title: '亚洲新歌榜', image: 'https://img.jammyfm.com/wordpress/wp-content/uploads/images/2017/08/27/1503833824135961.jpg?x-oss-process=style/750' },
        { id: '2', title: '热歌榜', image: 'https://ts1.tc.mm.bing.net/th/id/R-C.82f69f93ec86771642b755bab072d53f?rik=JMINVie6mXjd7Q&riu=http%3a%2f%2fimg4.kwcdn.kuwo.cn%2fstar%2fupload%2f2%2f2%2f1543919658018_.png&ehk=QgS5h2vajl6bo%2fFly1Wt4mhG1ij7J%2bCPzJ%2bFkcqeGQ0%3d&risl=&pid=ImgRaw&r=0' },
        { id: '3', title: '华语欧美榜', image: 'https://tse3-mm.cn.bing.net/th/id/OIP-C.k8bkU-xKaR0qaEcUamMKYwHaHa?rs=1&pid=ImgDetMain' },
        { id: '4', title: '飙升榜', image: 'https://ts1.tc.mm.bing.net/th/id/R-C.876ba49f66748050eb4269c56101d722?rik=gis08z%2b6IALI9Q&riu=http%3a%2f%2fp1.music.126.net%2frIi7Qzy2i2Y_1QD7cd0MYA%3d%3d%2f109951170048506929.jpg&ehk=TN0%2bitJjUVUjHTvECRPD%2bctjb42TeVCmetVxRtQSjl8%3d&risl=&pid=ImgRaw&r=0' }
      ],
    },
    navigateToMusicList(e) {
      const { id, title } = e.currentTarget.dataset;
      console.log(id,title);
      wx.navigateTo({
        url: `/pages/demo/3-p-1/pages/music-list/music-list?id=${id}&title=${encodeURIComponent(title)}`
      });
    }
  });