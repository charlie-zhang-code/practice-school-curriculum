// pages/detail/detail.js
Page({
    data: {
        alarm: null
    },

    onLoad(options) {
        const id = parseInt(options.id);
        const alarms = wx.getStorageSync('alarms') || [];
        const alarm = alarms.find(item => item.id === id);

        if (alarm) {
            // 计算状态文本
            const now = new Date();
            const alarmTime = new Date(alarm.date);
            const diff = alarmTime - now;

            let statusText = '';

            if (diff > 0) {
                const hours = Math.floor(diff / (1000 * 60 * 60));
                const minutes = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
                statusText = `剩余 ${hours}小时${minutes}分钟`;
            } else {
                const absDiff = Math.abs(diff);
                const hours = Math.floor(absDiff / (1000 * 60 * 60));
                const minutes = Math.floor((absDiff % (1000 * 60 * 60)) / (1000 * 60));
                statusText = `已过 ${hours}小时${minutes}分钟`;
            }

            this.setData({
                alarm: {
                    ...alarm,
                    statusText
                }
            });
        }
    },

    makePhoneCall() {
        wx.makePhoneCall({
            phoneNumber: this.data.alarm.notes 
        });
    },

    addContact() {
        wx.addPhoneContact({
            firstName: this.data.alarm.title,
            mobilePhoneNumber: this.data.alarm.notes 
        });
    },

    deleteAlarm() {
        wx.showModal({
            title: '确认删除',
            content: '确定要删除这个闹钟吗？',
            success: (res) => {
                if (res.confirm) {
                    const id = this.data.alarm.id;
                    let alarms = wx.getStorageSync('alarms') || [];
                    alarms = alarms.filter(item => item.id !== id);
                    wx.setStorageSync('alarms', alarms);

                    wx.showToast({
                        title: '删除成功',
                        icon: 'success',
                        complete: () => {
                            setTimeout(() => {
                                wx.navigateBack();
                            }, 1500);
                        }
                    });
                }
            }
        });
    }
});