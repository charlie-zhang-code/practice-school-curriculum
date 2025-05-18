const app = getApp()

Page({
  data: {
    currentDate: new Date().toISOString().split('T')[0],
    bloodTypes: ['A型', 'B型', 'AB型', 'O型', '未知'],
    birthDate: '',
    bloodType: '',
    isOrganDonor: false,
    isEdit: false,
    editIndex: -1,
    // 添加表单数据初始值
    formData: {
      medicalCondition: '',
      medicalNotes: '',
      allergies: '',
      medications: '',
      height: '',
      weight: '',
      emergencyContact: ''
    }
  },
  
  onLoad(options) {
    if (options.index) {
      // 编辑模式
      const index = parseInt(options.index)
      const card = app.globalData.cards[index]
      
      this.setData({
        isEdit: true,
        editIndex: index,
        birthDate: card.birthDate,
        bloodType: card.bloodType,
        isOrganDonor: card.isOrganDonor,
        // 填充表单数据
        formData: {
          medicalCondition: card.medicalCondition || '',
          medicalNotes: card.medicalNotes || '',
          allergies: card.allergies || '',
          medications: card.medications || '',
          height: card.height || '',
          weight: card.weight || '',
          emergencyContact: card.emergencyContact || ''
        }
      })
    }
  },
  
  // 日期选择
  bindDateChange(e) {
    this.setData({
      birthDate: e.detail.value
    })
  },
  
  // 血型选择
  bindBloodTypeChange(e) {
    this.setData({
      bloodType: this.data.bloodTypes[e.detail.value]
    })
  },
  
  // 开关切换
  switchChange(e) {
    this.setData({
      isOrganDonor: e.detail.value
    })
  },
  
  // 表单输入变化
  onInputChange(e) {
    const { field } = e.currentTarget.dataset
    this.setData({
      [`formData.${field}`]: e.detail.value
    })
  },
  
  // 提交表单
  submitForm(e) {
    const formData = e.detail.value
    const birthDate = this.data.birthDate
    const bloodType = this.data.bloodType
    const isOrganDonor = this.data.isOrganDonor
    
    if (!birthDate) {
      wx.showToast({
        title: '请选择出生日期',
        icon: 'none'
      })
      return
    }
    
    if (!bloodType) {
      wx.showToast({
        title: '请选择血型',
        icon: 'none'
      })
      return
    }
    
    // 计算年龄
    const birthYear = new Date(birthDate).getFullYear()
    const currentYear = new Date().getFullYear()
    const age = currentYear - birthYear
    
    const card = {
      ...this.data.formData,
      ...formData, // 确保获取最新的表单数据
      birthDate,
      bloodType,
      isOrganDonor,
      age
    }
    
    if (this.data.isEdit) {
      // 更新急救卡
      app.updateCard(this.data.editIndex, card)
      wx.showToast({
        title: '更新成功',
        icon: 'success'
      })
    } else {
      // 新增急救卡
      app.addCard(card)
      wx.showToast({
        title: '创建成功',
        icon: 'success'
      })
    }
    
    setTimeout(() => {
      wx.navigateBack()
    }, 1500)
  },
  
  // 删除急救卡
  deleteCard() {
    wx.showModal({
      title: '提示',
      content: '确定要删除此急救卡吗？',
      success: (res) => {
        if (res.confirm) {
          app.deleteCard(this.data.editIndex)
          wx.showToast({
            title: '删除成功',
            icon: 'success'
          })
          setTimeout(() => {
            wx.navigateBack()
          }, 1500)
        }
      }
    })
  }
})