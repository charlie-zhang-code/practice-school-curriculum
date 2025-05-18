App({
    globalData: {
      cards: [] // 存储所有急救卡
    },
    
    onLaunch() {
      // 初始化时从本地存储加载数据
      const cards = wx.getStorageSync('medicalCards') || []
      this.globalData.cards = cards
    },
    
    // 添加急救卡
    addCard(card) {
      this.globalData.cards.unshift(card)
      this.saveCards()
    },
    
    // 更新急救卡
    updateCard(index, card) {
      this.globalData.cards[index] = card
      this.saveCards()
    },
    
    // 删除急救卡
    deleteCard(index) {
      this.globalData.cards.splice(index, 1)
      this.saveCards()
    },
    
    // 保存到本地存储
    saveCards() {
      wx.setStorageSync('medicalCards', this.globalData.cards)
    }
  })