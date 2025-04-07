const app = getApp();

Page({
  data: {
    currentSong: {},
    isPlaying: false,
    audioContext: null,
    currentIndex: 0,
    songs: [
        {
          id: 1,
          name: '你(想要你的爱)',
          album: '你',
          artist: '郑润泽',
          imageUrl: 'https://p3.music.126.net/OvnvQlFc8QSD7UnqtM9YYw==/109951164512103081.jpg?param=320y320',
          url: 'https://m7.music.126.net/20250330110804/1c446d8b85559068aa08dc62fac2f30d/ymusic/555b/0008/0f08/b1621de5ce7b69b62ef88bc56d6c5c7d.mp3?vuutv=Y/PLiXao+PoG1TJscFioiHon0zf1xWd3k6PA0aH4NT8YFlUrnbfArwOfWP3T02RH1sYW9jWmIhRGdxhKMhtStVCpg7xIUsZBhzLYRK0Pavw='
        },
        {
          id: 2,
          name: '小胡同',
          album: '小胡同',
          artist: '郑润泽',
          imageUrl: 'https://p3.music.126.net/QsebYbDgQtKelH6r1iE2Fg==/109951167129280730.jpg?param=320y320',
          url: 'https://m8.music.126.net/20250330111554/6001bf5df3a53187a424ab10f853ea27/ymusic/obj/w5zDlMODwrDDiGjCn8Ky/14055025967/5a0a/cc5d/2055/e7bc382dc04175f439e9090fcda99944.mp3?vuutv=z1fgzyO34OEzgPJ6JHUca+n2P3EV1yfhTU8LQ/oXA37qlJrNfYyQFubhR0VgXkducdacKQAkTqJiJWKSrI06j34uzIdulVSvTFqxuSLmb+0='
        },
        {
          id: 3,
          name: '风铃(Falling U中文版)',
          album: '风铃',
          artist: '蒋孜怡',
          imageUrl: 'https://p3.music.126.net/jZ5OWv4FbZA8SqjJbWEamA==/109951170627452426.jpg?param=320y320',
          url: 'https://m801.music.126.net/20250330111711/6ff6d66f41a454cae64c6fa7e5fb7575/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/58826933152/4039/f4c0/4cf5/dbc699088c6bd8970e78b4129e223a17.mp3?vuutv=H/8MT3L/RpQVS2gRNpmfwLNImKKylZyYYnygVFfggCSDaLkr+bzVp+p7W1lEz5gt7pJ8jyCI+psrH+DjZ1OnypGOkmB76s8s3AIZaCgkz4U='
        },
        {
          id: 4,
          name: 'time machine (feat. aren park)',
          album: 'seen 11:23 pm',
          artist: 'mj apanay/aren park',
          imageUrl: 'https://p3.music.126.net/b15pmn1qj28nyam8DdcCHA==/109951167480966719.jpg?param=320y320',
          url: 'https://m701.music.126.net/20250330111819/b27a044de4fd113a9f34733b5c5fc506/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/13008549806/f0df/3607/ec22/b0e3810dd35b5872a0a1cecc6c80adde.mp3?vuutv=+cTBCD4qeNDsDnK/noV2V+xHqD9QVL/kmKkMkZmAE+gSZRTN12c7t2CwPWzGnD7GdKPPE3krfhME7O/4MDPXVwNEPPDe+x8SUFqwuZFzaio='
        },
        {
          id: 5,
          name: '走在小路上',
          album: '走在小路上',
          artist: 'Badago吧嗒/校长$even',
          imageUrl: 'https://p3.music.126.net/eoAThpnqOYi-neE9jFGC-Q==/109951170498022745.jpg?param=320y320',
          url: 'https://m801.music.126.net/20250330111943/091ab446929ace691c4fa89f82fc0026/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/58274797769/3665/28d9/1249/714233b119e393e765cfa92d2071f7ce.mp3?vuutv=3npl/T1PJ+6b/K+VozgS/biFax9lKbj4c1oH9oux+iqapgEeN2yJx2AZHD2iiJBvZvE16bOG7Q/kIC/I/E9BOEGTi+WEUIJ1aokmU+Hm7dA='
        },
        {
          id: 6,
          name: '烟圈',
          album: '泡芙小姐 什么是真爱',
          artist: '万乐体/999PUNKSTA/Zakiya晴子',
          imageUrl: 'https://p3.music.126.net/ByghgO3woaNPQ0hpFO7R6A==/109951170638966772.jpg?param=320y320',
          url: 'https://m701.music.126.net/20250330112042/bd68027b56100c00a4d86804d2012396/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/58896822393/a53a/f7b9/3c45/0aea98789eb9164a6dd1ecd9ce0df324.mp3?vuutv=uZDH55JoYPrdGAQ/8gXHoAGciW8zTRrd725NvgGN/Fx5vTn+HnU8yKgRjNc+1nLuk0wRbcB5kIJhxa4oqY3tFvvdO1eDnaMa1Upyp3EcgEE='
        }
      ],
    playMode: 'sequence', // 默认顺序播放
    randomOrder: [],
    currentTime: 0, // 当前播放时间
    duration: 0    // 音频总时长
  },

  onLoad: function (options) {
    const songId = parseInt(options.id);
    const song = this.data.songs.find(s => s.id === songId);
    if (song) {
      this.setData({
        currentSong: song,
        randomOrder: this.shuffleArray(this.data.songs.slice()) // 初始化随机顺序
      });
      this.initAudioContext(song.url);
    }
  },

  initAudioContext: function (url) {
    this.data.audioContext = wx.createInnerAudioContext();
    this.data.audioContext.src = url;
    this.data.audioContext.onPlay(() => {
      this.setData({ isPlaying: true });
      this.getDuration(); // 获取音频总时长
    });
    this.data.audioContext.onPause(() => {
      this.setData({ isPlaying: false });
    });
    this.data.audioContext.onEnded(() => {
      this.playNextSong();
    });
    this.data.audioContext.onTimeUpdate(() => {
        this.updateProgress(); // 实时更新进度条
      });
  },
  formatTime: function (seconds) {
    if (isNaN(seconds)) return '00:00';
    const minute = Math.floor(seconds / 60);
    const sec = Math.floor(seconds % 60);
    return `${minute.toString().padStart(2, '0')}:${sec.toString().padStart(2, '0')}`;
  },
  getDuration: function () {
    if (!this.data.audioContext) {
      console.error('audioContext is not initialized');
      return;
    }

    this.data.audioContext.getDuration({
      success: (res) => {
        this.setData({
          duration: Math.floor(res)
        });
      },
      fail: (err) => {
        console.error('getDuration failed:', err);
      }
    });
  },

  updateProgress: function () {
    if (!this.data.audioContext) {
      console.error('audioContext is not initialized');
      return;
    }

    this.data.audioContext.getCurrentTime({
      success: (res) => {
        if (this.data.duration > 0) {
          const progressPercentage = ((res / this.data.duration) * 100).toFixed(2);
          this.setData({
            currentTime: Math.floor(res),
            progressPercentage: progressPercentage
          });
        }
      },
      fail: (err) => {
        console.error('getCurrentTime failed:', err);
      }
    });
  },
  togglePlay: function () {
    if (this.data.isPlaying) {
      this.data.audioContext.pause();
    } else {
      this.data.audioContext.play();
    }
  },

  prevSong: function () {
    this.playPrevSong();
  },

  nextSong: function () {
    this.playNextSong();
  },

  playNextSong: function () {
    if (this.data.playMode === 'random') {
      const randomIndex = Math.floor(Math.random() * this.data.songs.length);
      this.loadSong(randomIndex);
    } else if (this.data.playMode === 'loop') {
      this.loadSong(this.data.currentIndex); // 单曲循环，重新加载当前歌曲
    } else {
      this.loadSong((this.data.currentIndex + 1) % this.data.songs.length);
    }
  },

  playPrevSong: function () {
    if (this.data.playMode === 'random') {
      const randomIndex = Math.floor(Math.random() * this.data.songs.length);
      this.loadSong(randomIndex);
    } else {
      this.loadSong((this.data.currentIndex - 1 + this.data.songs.length) % this.data.songs.length);
    }
  },

  loadSong: function (index) {
    const song = this.data.songs[index];
    this.setData({
      currentSong: song,
      currentIndex: index
    });
    this.data.audioContext.src = song.url;
    if (this.data.isPlaying) {
      this.data.audioContext.play();
    }
    this.getDuration();
  },

  setMode: function (e) {
    const mode = e.currentTarget.dataset.mode;
    this.setData({ playMode: mode });
  },

  shuffleArray: function (array) {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }
});