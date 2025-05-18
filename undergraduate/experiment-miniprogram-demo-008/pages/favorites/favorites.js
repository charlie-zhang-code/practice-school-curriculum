const storage = require('../../utils/storage.js');

Page({
  data: {
    favoriteList: []
  },
  
  onLoad() {
    this.loadFavorites();
  },
  
  onShow() {
    this.loadFavorites();
  },
  
  loadFavorites() {
    const favoriteList = storage.getFavorites();
    this.setData({ favoriteList });
  },
  
  viewDetail(e) {
    const id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: `/pages/detail/detail?id=${id}`,
      events: {
        photoUpdated: (data) => {
          if (!data.isFavorite) {
            this.removeFavorite(data.id);
          }
        },
        photoDeleted: (id) => {
          this.removeFavorite(id);
        }
      }
    });
  },
  toggleFavorite(e) {
    const id = e.currentTarget.dataset.id;
    const photoList = this.data.favoriteList;
    const index = photoList.findIndex(item => item.id === id);
    
    if (index !== -1) {
      const isFavorite = !photoList[index].isFavorite;
      storage.updateRecord(id, { isFavorite });
      this.loadFavorites();
    }
  },
  removeFavorite(id) {
    this.setData({
      favoriteList: this.data.favoriteList.filter(item => item.id !== id)
    });
  }
});