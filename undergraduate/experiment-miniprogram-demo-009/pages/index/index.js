const app = getApp()

Page({
  data: {
    cards: []
  },
  
  onLoad() {
    this.loadCards()
  },
  
  onShow() {
    this.loadCards()
  },
  
  loadCards() {
    this.setData({
      cards: app.globalData.cards
    })
  },
  
  // 跳转到创建页面
  navigateToCreate() {
    wx.navigateTo({
      url: '/pages/createCard/createCard'
    })
  },
  
  // 跳转到详情页面
  navigateToDetail(e) {
    const index = e.currentTarget.dataset.index
    wx.navigateTo({
      url: `/pages/cardDetail/cardDetail?index=${index}`
    })
  }
})