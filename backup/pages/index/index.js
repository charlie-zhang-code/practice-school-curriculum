// pages/index/index.js
const cityData = require('../../utils/cityData');
// 在页面的 js 文件中
import {
    getCurrentWeather,
    getForecastWeather,
} from '../../utils/api';

Page({
    data: {
        currentCity: '',
        currentWeather: {},
        forecastData: [],
        bgImage: '',
        weatherIcon: '',
        showForecast: false,
        showCityPicker: false,
        provinces: [],
        cities: [],
        pickerValue: [0, 0],
        selectedProvince: '',
        selectedLocation: '',
        selectedCity: '',

        startY: 0,      // 触摸起始Y坐标
        moveY: 0,       // 触摸移动Y坐标
        isMoving: false // 是否正在移动
    },

    onLoad() {
        this.initCityData();
        this.loadWeatherData(this.data.selectedLocation);
    },

    onPullDownRefresh() {
        this.loadWeatherData(this.data.selectedLocation, () => {
            wx.stopPullDownRefresh();
        });
    },

    initCityData() {
        const provinces = cityData.map(item => ({ name: item.name }));
        const cities = cityData[0].children.map(item => ({ name: item.name }));

        this.setData({
            provinces,
            cities,
            selectedProvince: provinces[0].name,
            selectedCity: cities[0].name,
            selectedLocation: cities[0].location
        });
    },

    loadWeatherData(city, callback) {
        getCurrentWeather(city)
            .then(data => {
                console.log('实时天气数据:', data);
                this.setData({ currentWeather: data.now }); // 更新页面数据
            })
            .catch(error => {
                console.error('获取实时天气失败:', error);
                wx.showToast({
                    title: '获取天气失败',
                    icon: 'none'
                });
            });

        getForecastWeather(city)
            .then(data => {
                console.log('3D天气数据:', data);
                this.setData({ forecastData: data.daily }); // 更新页面数据
            })
            .catch(error => {
                console.error('获取3D天气失败:', error);
                wx.showToast({
                    title: '获取天气失败',
                    icon: 'none'
                });
            });
    },

    getLocation() {
        wx.showLoading({ title: '定位中...' });

        wx.getLocation({
            type: 'gcj02',
            success: (res) => {
                // 模拟根据经纬度获取城市名称
                // 实际项目中这里应该调用逆地理编码API
                setTimeout(() => {
                    this.loadWeatherData('上海市');
                    wx.hideLoading();
                }, 1000);
            },
            fail: (err) => {
                console.error('获取位置失败', err);
                wx.hideLoading();
                wx.showToast({
                    title: '定位失败',
                    icon: 'none'
                });
            }
        });
    },

    showCityPicker() {
        this.setData({ showCityPicker: true });
    },

    hideCityPicker() {
        this.setData({ showCityPicker: false });
    },

    cityChange(e) {
        const value = e.detail.value;
        const provinceIndex = value[0];
        const cityIndex = value[1];

        const selectedProvince = this.data.provinces[provinceIndex].name;
        const cities = cityData[provinceIndex].children.map(item => ({ name: item.name }));
        const selectedCity = cities[cityIndex]?.name || cities[0].name;

        this.setData({
            pickerValue: value,
            cities,
            selectedProvince,
            selectedCity
        });
    },

    confirmCity() {
        this.loadWeatherData(this.data.selectedCity);
        this.hideCityPicker();
    },

    // 新增触摸事件处理方法
    handleTouchStart(e) {
        this.setData({
            startY: e.touches[0].clientY,
            isMoving: false
        });

    },

    handleTouchMove(e) {
        const currentY = e.touches[0].clientY;
        const deltaY = currentY - this.data.startY;

        // 垂直滑动距离大于30px才认为是有效滑动
        if (Math.abs(deltaY) > 30) {
            this.setData({
                moveY: currentY,
                isMoving: true
            });
        }
    },

    handleTouchEnd(e) {
        if (!this.data.isMoving) return;

        const deltaY = this.data.moveY - this.data.startY;

        // 上滑操作，显示预报
        if (deltaY < -50 && !this.data.showForecast) {
            this.setData({ showForecast: true });
        }
        // 下滑操作，隐藏预报
        else if (deltaY > 50 && this.data.showForecast) {
            this.setData({ showForecast: false });
        }

        this.setData({ isMoving: false });
    },

    hideForecast() {
        this.setData({ showForecast: false });
        wx.pageScrollTo({ scrollTop: 0, duration: 300 });
    }
});