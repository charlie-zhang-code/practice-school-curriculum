const storage = require('../../utils/storage.js');

Page({
  data: {
    title: '',
    note: '',
    location: '',
    tempImagePath: ''
  },
  
  onLoad() {
    this.getLocation();
  },
  
  getLocation() {
    wx.getLocation({
      type: 'gcj02',
      success: (res) => {
        const { latitude, longitude } = res;
        this.setData({
          location: `纬度: ${latitude.toFixed(2)}, 经度: ${longitude.toFixed(2)}`
        });
      },
      fail: (err) => {
        console.error('获取位置失败', err);
        this.setData({
          location: '位置获取失败'
        });
      }
    });
  },
  
  takePhoto() {
    const ctx = wx.createCameraContext();
    ctx.takePhoto({
      quality: 'high',
      success: (res) => {
        this.setData({
          tempImagePath: res.tempImagePath
        });
        this.savePhoto();
      },
      fail: (err) => {
        console.error('拍照失败', err);
        wx.showToast({
          title: '拍照失败',
          icon: 'none'
        });
      }
    });
  },
  
  onTitleInput(e) {
    this.setData({ title: e.detail.value });
  },
  
  onNoteInput(e) {
    this.setData({ note: e.detail.value });
  },
  
  savePhoto() {
    const { title, note, location, tempImagePath } = this.data;
    const now = new Date();
    
    const record = {
      id: now.getTime().toString(),
      title: title || now.toLocaleString(),
      note,
      location,
      imagePath: tempImagePath,
      time: now.toLocaleString(),
      isFavorite: false
    };
    
    storage.addRecord(record);
    
    wx.showToast({
      title: '保存成功',
      icon: 'success'
    });
    
    setTimeout(() => {
      wx.navigateBack();
    }, 1500);
  }
});