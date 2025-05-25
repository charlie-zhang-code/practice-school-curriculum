// pages/index/index.js
Page({
    data: {
        currentTab: 0,
        alarms: [],
        alarmTypes: [
            { id: 1, name: '商务谈判' },
            { id: 2, name: '预约电话' },
            { id: 3, name: '工作会议' },
            { id: 4, name: '其他' }
        ],
        typeIndex: 0,
        title: '',
        time: '',
        location: '',
        notes: '',
        dateTimeArray: [],
        dateTime: [],
        formattedDateTime: ''
    },

    onLoad() {
        this.initDateTimePicker();
        this.setCurrentTime();
        this.loadAlarms();
    },
    // 新增方法
    initDateTimePicker() {
        // 生成日期时间选择器数据
        const years = [];
        const months = [];
        const days = [];
        const hours = [];
        const minutes = [];
        const seconds = [];

        // 年份：当前年到5年后
        for (let i = 0; i < 5; i++) {
            years.push({
                id: new Date().getFullYear() + i,
                name: (new Date().getFullYear() + i) + '年'
            });
        }

        // 月份：1-12月
        for (let i = 1; i <= 12; i++) {
            months.push({
                id: i,
                name: i + '月'
            });
        }

        // 日：1-31日（实际使用时需要根据月份调整）
        for (let i = 1; i <= 31; i++) {
            days.push({
                id: i,
                name: i + '日'
            });
        }

        // 时：0-23时
        for (let i = 0; i < 24; i++) {
            hours.push({
                id: i,
                name: (i < 10 ? '0' + i : i) + '时'
            });
        }

        // 分：0-59分
        for (let i = 0; i < 60; i++) {
            minutes.push({
                id: i,
                name: (i < 10 ? '0' + i : i) + '分'
            });
        }

        // 秒：0-59秒
        for (let i = 0; i < 60; i++) {
            seconds.push({
                id: i,
                name: (i < 10 ? '0' + i : i) + '秒'
            });
        }

        this.setData({
            dateTimeArray: [years, months, days, hours, minutes, seconds],
            dateTime: [0, new Date().getMonth(), new Date().getDate() - 1, new Date().getHours(), new Date().getMinutes(), 0]
        });
    },
    setCurrentDateTime() {
        const now = new Date();
        const formatted = `${now.getFullYear()}年${now.getMonth() + 1}月${now.getDate()}日 ${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}`;
        this.setData({
            formattedDateTime: formatted
        });
    },
    setCurrentTime() {
        const now = new Date();
        const hours = now.getHours().toString().padStart(2, '0');
        const minutes = now.getMinutes().toString().padStart(2, '0');
        this.setData({
            time: `${hours}:${minutes}`
        });
    },
    bindDateTimeChange(e) {
        const arr = e.detail.value;
        const { dateTimeArray } = this.data;

        const year = dateTimeArray[0][arr[0]].id;
        const month = dateTimeArray[1][arr[1]].id;
        const day = dateTimeArray[2][arr[2]].id;
        const hour = dateTimeArray[3][arr[3]].id;
        const minute = dateTimeArray[4][arr[4]].id;
        const second = dateTimeArray[5][arr[5]].id;

        const formatted = `${year}年${month}月${day}日 ${hour}:${minute}:${second}`;

        this.setData({
            dateTime: arr,
            formattedDateTime: formatted
        });
    },
    bindDateTimeColumnChange(e) {
        // 处理列变化，特别是当月份变化时需要调整天数
        const { column, value } = e.detail;
        const { dateTime, dateTimeArray } = this.data;

        if (column === 1) { // 月份变化
            const year = dateTimeArray[0][dateTime[0]].id;
            const month = value + 1; // 月份从0开始

            // 计算该月有多少天
            const daysInMonth = new Date(year, month, 0).getDate();

            // 更新天数数据
            const days = [];
            for (let i = 1; i <= daysInMonth; i++) {
                days.push({
                    id: i,
                    name: i + '日'
                });
            }

            // 更新dateTimeArray
            const newDateTimeArray = [...dateTimeArray];
            newDateTimeArray[2] = days;

            // 如果当前选择的日期大于新的最大天数，则调整为最大天数
            let dayIndex = dateTime[2];
            if (dayIndex >= daysInMonth) {
                dayIndex = daysInMonth - 1;
            }

            this.setData({
                dateTimeArray: newDateTimeArray,
                dateTime: [dateTime[0], value, dayIndex, dateTime[3], dateTime[4], dateTime[5]]
            });
        }
    },
    loadAlarms() {
        // 从本地存储加载闹钟
        const alarms = wx.getStorageSync('alarms') || [];
        // 计算每个闹钟的状态
        const now = new Date();
        const updatedAlarms = alarms.map(alarm => {
            const alarmTime = new Date(alarm.date);
            const diff = alarmTime - now;

            let status = 'pending';
            let statusText = '';

            if (diff > 0) {
                const hours = Math.floor(diff / (1000 * 60 * 60));
                const minutes = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
                statusText = `剩余 ${hours}小时${minutes}分钟`;
            } else {
                status = 'expired';
                const absDiff = Math.abs(diff);
                const hours = Math.floor(absDiff / (1000 * 60 * 60));
                const minutes = Math.floor((absDiff % (1000 * 60 * 60)) / (1000 * 60));
                statusText = `已过 ${hours}小时${minutes}分钟`;
            }

            return {
                ...alarm,
                status,
                statusText
            };
        });

        this.setData({ alarms: updatedAlarms });
    },

    switchTab(e) {
        this.setData({
            currentTab: parseInt(e.currentTarget.dataset.tab)
        });
    },

    bindTypeChange(e) {
        this.setData({
            typeIndex: e.detail.value
        });
    },

    bindTitleInput(e) {
        this.setData({
            title: e.detail.value
        });
    },

    bindTimeChange(e) {
        this.setData({
            time: e.detail.value
        });
    },

    bindLocationInput(e) {
        this.setData({
            location: e.detail.value
        });
    },

    bindNotesInput(e) {
        this.setData({
            notes: e.detail.value
        });
    },

    resetForm() {
        this.setData({
            typeIndex: 0,
            title: '',
            time: this.getCurrentTime(),
            location: '',
            notes: ''
        });
    },
    // 修改createAlarm方法
    createAlarm() {
        const { alarmTypes, typeIndex, title, dateTimeArray, dateTime, formattedDateTime, location, notes } = this.data;

        if (!title) {
            wx.showToast({
                title: '请输入标题',
                icon: 'none'
            });
            return;
        }

        // 解析选择的日期时间
        const year = dateTimeArray[0][dateTime[0]].id;
        const month = dateTimeArray[1][dateTime[1]].id;
        const day = dateTimeArray[2][dateTime[2]].id;
        const hour = dateTimeArray[3][dateTime[3]].id;
        const minute = dateTimeArray[4][dateTime[4]].id;
        const second = dateTimeArray[5][dateTime[5]].id;

        const alarmDate = new Date(year, month - 1, day, hour, minute, second);

        const newAlarm = {
            id: new Date().getTime(),
            type: alarmTypes[typeIndex].name,
            title,
            time: formattedDateTime,
            date: alarmDate.toString(),
            location,
            notes,
            status: 'pending'
        };

        // 保存到本地存储
        const alarms = wx.getStorageSync('alarms') || [];
        alarms.push(newAlarm);
        wx.setStorageSync('alarms', alarms);

        // 更新UI
        this.loadAlarms();
        this.setData({
            currentTab: 0
        });

        wx.showToast({
            title: '创建成功',
            icon: 'success'
        });
    },

    viewDetail(e) {
        const id = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: `/pages/detail/detail?id=${id}`
        });
    }
});