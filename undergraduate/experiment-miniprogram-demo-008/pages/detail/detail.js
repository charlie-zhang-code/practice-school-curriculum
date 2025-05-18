const storage = require('../../utils/storage.js');

Page({
    data: {
        currentPhoto: null
    },

    onLoad(options) {
        const { id } = options;
        const photoList = storage.getAllRecords();
        const currentPhoto = photoList.find(item => item.id === id);

        if (currentPhoto) {
            this.setData({ currentPhoto });
            wx.setNavigationBarTitle({
                title: currentPhoto.title
            });
        } else {
            wx.showToast({
                title: '记录不存在',
                icon: 'none'
            });
            setTimeout(() => {
                wx.navigateBack();
            }, 1500);
        }
    },

    toggleFavorite() {
        const { currentPhoto } = this.data;
        const isFavorite = !currentPhoto.isFavorite;

        storage.updateRecord(currentPhoto.id, { isFavorite });
        this.setData({
            currentPhoto: { ...currentPhoto, isFavorite }
        });

        wx.showToast({
            title: isFavorite ? '已收藏' : '已取消收藏',
            icon: 'success'
        });

        // 更新上级页面数据
        this.getOpenerEventChannel().emit('photoUpdated', {
            id: currentPhoto.id,
            isFavorite
        });
    },

    deletePhoto() {
        const { currentPhoto } = this.data;
        wx.showModal({
            title: '确认删除',
            content: '确定要删除这条记录吗？',
            success: (res) => {
                if (res.confirm) {
                    const records = storage.getAllRecords();
                    const newRecords = records.filter(item => item.id !== currentPhoto.id);
                    storage.saveAllRecords(newRecords);

                    wx.showToast({
                        title: '删除成功',
                        icon: 'success'
                    });

                    //   // 通知上级页面更新
                    //   this.getOpenerEventChannel().emit('photoDeleted', currentPhoto.id);
                    // 安全地获取事件通道
                    const eventChannel = this.getOpenerEventChannel();
                    if (eventChannel) {
                        eventChannel.emit('photoDeleted', currentPhoto.id);
                    } else {
                        console.warn('无法获取事件通道，可能不是通过navigateTo打开的页面');
                    }

                    setTimeout(() => {
                        wx.navigateBack();
                    }, 1000);
                }
            }
        });
    }
});