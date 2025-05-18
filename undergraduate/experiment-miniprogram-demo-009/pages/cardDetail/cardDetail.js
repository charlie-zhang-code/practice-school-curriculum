const app = getApp()

Page({
  data: {
    card: {},
    index: -1
  },
  
  onLoad(options) {
    const index = parseInt(options.index)
    const card = app.globalData.cards[index]
    
    this.setData({
      card,
      index
    })
  },
  
  // 拨打紧急联系人
  callEmergencyContact() {
    wx.makePhoneCall({
      phoneNumber: this.data.card.emergencyContact
    })
  },
  
  // 编辑急救卡
  editCard() {
    wx.navigateTo({
      url: `/pages/createCard/createCard?index=${this.data.index}`
    })
  },
  
  // 删除急救卡
  deleteCard() {
    wx.showModal({
      title: '删除确认',
      content: '确定要删除此医疗急救卡吗？此操作不可撤销。',
      confirmColor: '#E64340',
      success: (res) => {
        if (res.confirm) {
          // 调用全局方法删除卡片
          app.deleteCard(this.data.index)
          
          wx.showToast({
            title: '删除成功',
            icon: 'success',
            duration: 1500
          })
          
          // 删除后返回首页
          setTimeout(() => {
            wx.navigateBack()
          }, 1500)
        }
      }
    })
  }
})