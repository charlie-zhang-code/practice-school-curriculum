function getRandomColor() {
    const rgb = []
    for (let i = 0; i < 3; ++i) {
        let color = Math.floor(Math.random() * 256).toString(16)
        color = color.length == 1 ? '0' + color : color
        rgb.push(color)
    }
    return '#' + rgb.join('')
}

Page({
    data: {
        videoList: [
            {
                id: 1,
                title: "二周年特别动画：「不虚此行」",
                url: "https://prod-vod-sign.miyoushe.com/o0QoxCA0lBYkO9MfSuA4sQhimUgKFvmhJI8Fsi?auth_key=1747442715-fab560f13f-0-441ba57e3f76ac749c217d72e254c829",
                cover: "/image/Snipaste_2025-05-13_20-55-51.jpg",
                danmuList: [
                    { text: "第一条弹幕", color: "#ff0000", time: 1 },
                    { text: "哈哈哈", color: "#00ff00", time: 3 }
                ]
            },
            {
                id: 2,
                title: "3.3版本PV：「‍在黎明升起时坠落」",
                url: "https://prod-vod-sign.miyoushe.com/oc4cC0HrDIdsibqlAFE5oocqcJHQMeI4RThAwj?auth_key=1747445410-5e59e30b15-0-0886dbe9a3a54f1b4dfb9a72e577435a",
                cover: "https://upload-bbs.miyoushe.com/upload/2025/05/08/288909600/f36e57c71414ebb5e4ef00332aebe19c_2250461566250050011.jpg",
                danmuList: []
            },
            {
                id: 3,
                title: "《崩坏：星穹铁道》2025演唱会：「‍希望有羽毛和翅膀」",
                url: "https://prod-vod-sign.miyoushe.com/o8ALF1LCl9wYqimSI0s4GWKhEhvpQiCxghUDeC?auth_key=1747445502-caee6a7f68-0-4723e9db09333051c7cc6f485e80ff3c",
                cover: "https://upload-bbs.miyoushe.com/upload/2025/05/02/288909600/03556093d9643f85c4f5c9da7b54a553_7419433289501268652.jpg",
                danmuList: []
            },
        ],
        currentVideo: null,
        danmuList: [
            { text: "第一条弹幕", color: "#ff0000", time: 1 },
            { text: "哈哈哈", color: "#00ff00", time: 3 }
        ],
        danmuText: "",
        videoContext: null,
        currentTime: null
    },

    onTimeUpdate(e) {
        const currentTime = e.detail.currentTime;
        this.setData({
            currentTime: currentTime
        })
    },
    onLoad() {
        this.setData({
            currentVideo: this.data.videoList[0]
        });
        this.videoContext = wx.createVideoContext('myVideo');
    },

    // 切换视频
    switchVideo(e) {
        const id = e.currentTarget.dataset.id;
        const video = this.data.videoList.find(item => item.id === id);
        if (video) {
            this.setData({
                currentVideo: video,
                danmuList: [] // 切换视频清空弹幕
            });
            this.videoContext.seek(0);
        }
    },

    // 输入弹幕
    onInput(e) {
        this.setData({
            danmuText: e.detail.value
        });
    },
    bindInputBlur(e) {
        this.setData({
            danmuText: e.detail.value
        });
    },
    bindSendDanmu() {
        // 1. 检查输入是否为空
        if (!this.data.danmuText.trim()) {
            wx.showToast({
                title: '弹幕内容不能为空',
                icon: 'none'
            });
            return;
        }

        // 关键步骤1：通过视频上下文发送弹幕
        this.videoContext.sendDanmu({
            text: this.data.danmuText,
            color: getRandomColor(),
            success: () => {
                console.log('弹幕发送成功');

                // 6. 更新 danmuList（确保时间正确）
                this.setData({
                    danmuList: [...this.data.danmuList, newDanmu],
                    danmuText: "" // 清空输入框
                }, () => {
                    console.log('数据更新成功：', this.data.danmuList);
                });
            },
            fail: (err) => {
                console.error('弹幕发送失败', err);
                wx.showToast({
                    title: `弹幕发送失败：${err.errMsg}`,
                    icon: 'none'
                });
            }
        });

        // 关键步骤2：更新数据（即使不显示也需要）

    },
    // 发送弹幕
    sendDanmu() {
        if (!this.data.danmuText.trim()) return;

        const colors = ['#ff0000', '#00ff00', '#0000ff', '#ffff00', '#ff00ff'];

        const currentTime = this.data.currentTime

        const newDanmu = {
            text: this.data.danmuText,
            color: colors[Math.floor(Math.random() * colors.length)],
        };

        // 关键步骤1：通过视频上下文发送弹幕
        this.videoContext.sendDanmu({
            text: this.data.danmuText,
            color: colors[Math.floor(Math.random() * colors.length)],
            success: () => {
                console.log('弹幕发送成功');
            },
            fail: (err) => {
                console.error('弹幕发送失败', err);
                wx.showToast({
                    title: `弹幕发送失败：${err.errMsg}`,
                    icon: 'none'
                });
            }
        });

        // 关键步骤2：更新数据（即使不显示也需要）
        this.setData({
            danmuList: [...this.data.danmuList, newDanmu],
            danmuText: ""
        }, () => {
            console.log('数据更新成功：', this.data.danmuList);
        });
    },

    // 播放事件
    onPlay() {
        console.log('视频开始播放');
    },

    // 暂停事件
    onPause() {
        console.log('视频暂停');
    }
});