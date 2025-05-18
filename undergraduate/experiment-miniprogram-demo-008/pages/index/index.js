const storage = require('../../utils/storage.js');

Page({
  data: {
    photoList: []
  },
  
  onLoad() {
    this.loadPhotoList();
  },
  
  onShow() {
    this.loadPhotoList();
  },
  
  loadPhotoList() {
    const photoList = storage.getAllRecords();
    this.setData({ photoList });
  },
  
  goToCamera() {
    wx.navigateTo({
      url: '/pages/camera/camera'
    });
  },
  viewDetail(e) {
    const id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: `/pages/detail/detail?id=${id}`,
      events: {
        photoUpdated: (data) => {
          this.updatePhotoStatus(data.id, { isFavorite: data.isFavorite });
        },
        photoDeleted: (id) => {
          this.removePhoto(id);
        }
      }
    });
  },
  removePhoto(id) {
    const records = storage.getAllRecords();
    const newRecords = records.filter(item => item.id !== id);
    storage.saveAllRecords(newRecords);
    this.loadPhotoList();
  },
  
  updatePhotoStatus(id, updates) {
    storage.updateRecord(id, updates);
    this.loadPhotoList();
  },
  
  goToFavorites() {
    wx.navigateTo({
      url: '/pages/favorites/favorites'
    });
  },
  
  toggleFavorite(e) {
    const id = e.currentTarget.dataset.id;
    const photoList = this.data.photoList;
    const index = photoList.findIndex(item => item.id === id);
    
    if (index !== -1) {
      const isFavorite = !photoList[index].isFavorite;
      storage.updateRecord(id, { isFavorite });
      this.loadPhotoList();
    }
  },






  // 记录触摸开始的 X 坐标
  onTouchStart(e) {
    const id = e.currentTarget.dataset.id;
    this.setData({
      touchStartX: e.touches[0].clientX,
      touchTargetId: id
    });
  },

  // 判断滑动方向
  onTouchEnd(e) {
    const { touchStartX, touchTargetId } = this.data;
    const endX = e.changedTouches[0].clientX;
    const deltaX = endX - touchStartX;

    if (Math.abs(deltaX) > 30) { // 设置最小滑动距离阈值
      if (deltaX > 0) {
        // 右滑 -> 收藏
        this.updatePhotoStatus(touchTargetId, { isFavorite: true });
      } else {
        // 左滑 -> 取消收藏
        this.updatePhotoStatus(touchTargetId, { isFavorite: false });
      }
    }
  },
});