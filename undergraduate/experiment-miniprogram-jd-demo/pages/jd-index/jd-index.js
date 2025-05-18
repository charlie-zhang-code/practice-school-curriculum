
Page({

    data: {

        autoplay: true,

        interval: 5000,

        imgUrls: [

            "https://tse1-mm.cn.bing.net/th/id/OIP-C.jqAI9s2EteFnnV7mkEdkwAHaCx?rs=1&pid=ImgDetMain",

            "https://tse2-mm.cn.bing.net/th/id/OIP-C.s0_YegaHqTqGI3EZKPTqxgHaCg?rs=1&pid=ImgDetMain",

            "https://tse3-mm.cn.bing.net/th/id/OIP-C.N70BQKvV5xyUMChde6sgBwHaC9?rs=1&pid=ImgDetMain"

        ],

        daohang: [

            "首页",

            "补贴爆品",

            "女鞋",

            "宠物",

            "爱车",

            "箱包皮具"

        ],

        elements: [{

            image: "/images/index/1.png",

            name: "京东超市",

        }, {

            image: "/images/index/2.png",

            name: "京东家电",

        },

        {

            image: "/images/index/3.png",

            name: " 京东服饰",

        },

        {

            image: "/images/index/4.png",

            name: "京东手机",

        },

        {

            image: "/images/index/5.png",

            name: "京喜",

        },

        {

            image: "/images/index/6.png",

            name: "一元疯抢",

        }, {

            image: "/images/index/7.png",

            name: "领京豆",

        }, {

            image: "/images/index/8.png",

            name: "   领优惠券",

        }, {

            image: "/images/index/9.png",

            name: "免费水果",

        }, {

            image: "/images/index/10.png",

            name: "充值中心",

        },

        ],

        elements2: [{
            id: 1,
            image: "https://m.360buyimg.com/seckillcms/s250x250_jfs/t1/209566/6/32667/17643/6613ac59F63ddff15/2f68713cdf48bdc9.jpg",

            name: "京东超市",

        }, {
            id: 2,
            image: "https://m.360buyimg.com/seckillcms/s250x250_jfs/t20271012/192998/36/48537/69546/6709e75fFd6db8e88/b2349e002dafe142.jpg",

            name: "京东家电",

        },

        {
            id: 3,
            image: "https://m.360buyimg.com/seckillcms/s250x250_jfs/t20280407/273871/23/17265/62374/67f4e3bdF182c8aef/a3d6e903933e76e2.jpg",

            name: " 京东服饰",

        },

        {
            id: 4,
            image: "https://m.360buyimg.com/seckillcms/s250x250_jfs/t20280316/277772/2/5052/6398/67d83317Ff33825c7/f9b5070ccc431f7d.jpg",

            name: " 京东服饰",

        },

        ],

    },

    navigateToDetail: function (e) {
        const productId = e.currentTarget.dataset.id; // 获取商品ID
        wx.navigateTo({
            url: `/pages/jd-detail/index?id=${productId}`, // 跳转到商品详情页面并传递ID
        });
    }

})
