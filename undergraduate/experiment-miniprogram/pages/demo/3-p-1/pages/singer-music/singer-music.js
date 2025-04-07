Page({
    data: {
        songs: [
            {
              id: 1,
              name: 'Always Online',
              album: 'JJ陆',
              artist: '林俊杰',
              imageUrl: 'https://p3.music.126.net/q8RW-c9LY27XNVEaqAa3NA==/109951170565907820.jpg?param=320y320',
              url: 'https://m702.music.126.net/20250401210323/d4c7455edf5cc082237634211feb0999/jd-musicrep-ts/775d/7ec4/10dc/a8524539b3659c65d3248af24a593a91.mp3?vuutv=zWcXh5belfZqb5Bw26dRxWVzLILCSNNjN2zktlC6R5z+m09BHLQa0Zfr2h/x/5Fv1+g+utAd2Y1gf6A/eqgEmIlKV+srKWwyuibUwb7PmSNVsA7PxuNdlOwvTNrg2nJtf31OA+jqS77xSZnSa/Qoxg=='
            },
            {
              id: 2,
              name: '愿与愁',
              album: '愿与愁',
              artist: '林俊杰',
              imageUrl: 'https://p3.music.126.net/vtnI8JpimWnZSzkXdmIB3w==/109951168558210782.jpg?param=320y320',
              url: 'https://m801.music.126.net/20250401210415/74ac6189b504ac35650197f0614c41b9/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/26742698081/fb63/5908/a63b/4f12e89dce9ee7d5c44c193bdea46de7.mp3?vuutv=wbvuvwdAIEhtGgtYqvyzD5PLrdYFDwhGP5Nhu7TvKDK1GxG7L2gw7sMwI1HIuRp0SoZM3q4LW9xPepAlOya7Q/72CFn0//XEPRjvSJcatHo='
            },
            {
              id: 3,
              name: '心墙(Wall)',
              album: '她说 概念自选辑',
              artist: '林俊杰',
              imageUrl: 'https://p3.music.126.net/EtbYjpw06KOdTxisgUs4-w==/109951168852557941.jpg?param=320y320',
              url: 'https://m802.music.126.net/20250401210457/53b283c4c66b17f3c8595e44e627b019/jd-musicrep-ts/7d0f/5ab4/a4ad/9c10ce90ad064ff1ce8415f3188ba4ba.mp3?vuutv=MsQeULJP4oE0sIBlAKfXMAIPxjWQP18ri1FGUCqNyplX5m65IYsYAVpMzhvFbNnDlHReTHShJI2AOCq3pNP4GhXDTHxnmaN3AyDroMhwBkhe6QO/v4Rvd4jFGhSAF1TFJPIGz+hOVWPYEe0Mue++nw=='
            },
            {
              id: 4,
              name: '江南(River South)',
              album: '第二天堂',
              artist: '林俊杰',
              imageUrl: 'https://p3.music.126.net/_0OAhWhIbg-nOP-6e4o-SA==/109951168111265583.jpg?param=320y320',
              url: 'https://m802.music.126.net/20250401210540/ed3284566b2acd7da5f9b32d1f17c7f0/jd-musicrep-ts/6c0b/fc34/01bc/ad672b31fcfae24ec12dbf1b4d1e0e43.mp3?vuutv=COK4t+yx96zsj2p/2Uh5UGJkJ8Fbo3KfHimnOcQEJbIxEWmcqAFwwYQtc69JGUGY+7l23X0g4cXAljXhLeFe98t4d3jrNd15ZHFetNZgpwNIkTH7JHdI8L1d6YaeGZWpiZY0+ncrXq8Zi08fkvOXRw=='
            }
          ]
    },
  
    navigateToPlay: function (e) {
        const songId = e.currentTarget.dataset.id;
        const selectedSong = this.data.songs.find(song => song.id === songId);
        wx.navigateTo({
        url: `/pages/demo/3-p-1/pages/player/player?id=${songId}`
      });
    }
  });