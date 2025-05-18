Page({
    data: {
        list: ["热搜推荐", "食品酒水", "生鲜果蔬", "美妆护肤", "个护清洁", "精品男装", "精品女装", "内衣配饰", "鞋靴箱包", "手机数码", "家用电器", "电脑办公", "运动户外"],
        elements: [
            {
                id: 1,
                image: "https://img11.360buyimg.com/n7/jfs/t1/233118/8/31400/69085/67b75f94F2762660e/ecb045314f3ac12b.jpg",
                name: "手机",
                category: '热搜推荐'
            },
            {
                id: 2,
                image: "https://img13.360buyimg.com/n5/s720x720_jfs/t1/276524/1/20208/154690/67fb4281F7383b1ec/ae9e97d9384d7328.jpg.avif",
                name: "冰箱",
                category: '热搜推荐'
            },
            {
                id: 3,
                image: "https://img11.360buyimg.com/n5/s114x114_jfs/t1/255930/11/22882/115400/67b7110fFba6402c1/18a5e0184a0f700c.jpg.avif",
                name: " 华为",
                category: '食品酒水'
            },
            {
                id: 4,
                image: "https://img14.360buyimg.com/n5/s114x114_jfs/t1/277681/26/20747/130563/67fce1e9F96f4c1c9/7b348bb62ef77289.png.avif",
                name: "白酒",
                category: '生鲜果蔬'
            }
        ],
        currentCategory: '热搜推荐', // 当前选中的分类
        currentElements: [] // 当前显示的商品数据
    },
    onLoad() {
        // 页面加载时显示默认分类的商品数据
        this.updateCurrentElements(this.data.currentCategory);
    },
    onCategoryClick(e) {
        const index = e.currentTarget.dataset.index;
        const category = this.data.list[index];
        this.setData({
            currentCategory: category
        });
        this.updateCurrentElements(category);
    },

    updateCurrentElements(category) {
        const filteredElements = this.data.elements.filter(item => item.category === category);
        this.setData({
            currentElements: filteredElements
        });
    },
    clickchoose: function () {
        wx.navigateTo({
            url: '../jd-goods/jd-goods',
        })
    }
})