// pages/index/index.js
const cityData = require('../../utils/cityData');
import { getCurrentWeather, getForecastWeather } from '../../utils/api';
import { getWeatherIcon, getBackgroundImage } from '../../utils/weatherUtils';

Page({
    data: {
        currentCity: '定位中...',
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
        isLoading: false,
        lastRefreshTime: '',
        weatherTips: ''
    },

    onLoad() {
        this.initCityData();
        this.getLocation(); // 自动获取位置
        this.setBackgroundByTime();
    },

    onShow() {
        // 小程序从后台返回时检查是否需要刷新
        const now = Date.now();
        const lastRefreshTime = this.data.lastRefreshTime;
        if (lastRefreshTime && now - lastRefreshTime > 30 * 60 * 1000) {
            this.loadWeatherData(this.data.selectedLocation);
        }
    },

    onPullDownRefresh() {
        this.loadWeatherData(this.data.selectedLocation, () => {
            wx.stopPullDownRefresh();
        });
    },

    initCityData() {
        const provinces = cityData.map(item => ({ name: item.name }));
        const cities = cityData[0]?.children?.map(item => ({ name: item.name })) || [];

        this.setData({
            provinces,
            cities,
            selectedProvince: provinces[0]?.name || '',
            selectedCity: cities[0]?.name || '',
            selectedLocation: cities[0]?.location || ''
        });
    },

    async loadWeatherData(city, callback) {
        if (this.data.isLoading) return;

        this.setData({ isLoading: true });

        try {
            const [current, forecast] = await Promise.all([
                getCurrentWeather(city),
                getForecastWeather(city)
            ]);

            const weatherIcon = getWeatherIcon(current.now.icon);
            const weatherTips = this.generateWeatherTips(current.now, forecast.daily[0]);

            console.log(current.now);
            console.log(forecast.daily);

            this.setData({
                currentWeather: current.now,
                forecastData: forecast.daily,
                weatherIcon,
                weatherTips,
                lastRefreshTime: Date.now(),
                currentCity: this.data.selectedCity || '当前位置'
            });

            // 根据天气和时间设置背景
            this.setBackgroundByTime(current.now.icon);

            callback && callback();
        } catch (error) {
            console.error('获取天气失败:', error);
            wx.showToast({
                title: '获取天气失败',
                icon: 'none'
            });
        } finally {
            this.setData({ isLoading: false });
        }
    },

    setBackgroundByTime(weatherCode = '100') {
        const hour = new Date().getHours();
        let bgImage = getBackgroundImage(weatherCode, hour);
        this.setData({ bgImage });
    },

    generateWeatherTips(current, forecast) {
        const tips = [];

        // 温度提示
        const temp = parseInt(current.temp);
        if (temp > 30) {
            tips.push('天气炎热，注意防暑降温');
        } else if (temp < 10) {
            tips.push('天气寒冷，注意保暖');
        }

        // 降雨提示
        if (forecast.precip && parseFloat(forecast.precip) > 5) {
            tips.push('今天可能有雨，记得带伞');
        }

        // 风速提示
        if (current.windScale && parseInt(current.windScale) >= 6) {
            tips.push('风力较大，出行注意安全');
        }

        return tips.length > 0 ? tips.join('；') : '';
    },

    getLocation() {
        wx.showLoading({ title: '定位中...' });

        wx.getLocation({
            type: 'gcj02',
            success: async (res) => {
                try {
                    // 这里应该调用逆地理编码API获取城市名称
                    // 模拟数据 - 实际项目中替换为真实API调用
                    const cityName = '凯里市';
                    const location = `${res.longitude},${res.latitude}`;

                    this.setData({
                        selectedCity: cityName,
                        selectedLocation: location
                    });

                    await this.loadWeatherData(location);
                } catch (error) {
                    console.error('获取位置信息失败:', error);
                    wx.showToast({
                        title: '获取位置信息失败',
                        icon: 'none'
                    });
                } finally {
                    wx.hideLoading();
                }
            },
            fail: (err) => {
                console.error('获取位置失败', err);
                wx.hideLoading();
                wx.showToast({
                    title: '定位失败，将显示默认城市',
                    icon: 'none'
                });
                // 使用默认城市
                this.loadWeatherData(this.data.selectedLocation);
            }
        });
    },

    handleLocationTap() {
        wx.showActionSheet({
            itemList: ['刷新当前位置', '选择其他城市'],
            success: (res) => {
                if (res.tapIndex === 0) {
                    this.getLocation();
                } else if (res.tapIndex === 1) {
                    this.showCityPicker();
                }
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
        const cityIndex = value[1] || 0;

        const selectedProvince = this.data.provinces[provinceIndex].name;
        console.log(selectedProvince);

        const cities = cityData[provinceIndex].children.map(item => ({ name: item.name, location: item.location }));
        const selectedCity = cities[cityIndex].name;

        const selectedLocation = cities[cityIndex].location;

        this.setData({
            pickerValue: value,
            cities,
            selectedProvince,
            selectedCity,
            selectedLocation
        });
    },

    confirmCity() {
        if (!this.data.selectedCity) {
            wx.showToast({
                title: '请选择城市',
                icon: 'none'
            });
            return;
        }

        // BUG 切换城市的时候没有读取到 this.data.selectedLocation
        this.loadWeatherData(this.data.selectedLocation);
        this.hideCityPicker();
    },

    // 手势操作改进
    handleTouchStart(e) {
        this.startY = e.touches[0].clientY;
        this.startTime = e.timeStamp;
    },

    handleTouchMove(e) {
        if (!this.startY) return;

        const currentY = e.touches[0].clientY;
        this.direction = currentY - this.startY > 0 ? 'down' : 'up';
    },

    handleTouchEnd(e) {
        if (!this.startY) return;

        const endY = e.changedTouches[0].clientY;
        const distance = Math.abs(endY - this.startY);
        const duration = e.timeStamp - this.startTime;

        // 满足滑动距离和速度条件
        if (distance > 50 && duration < 300) {
            if (this.direction === 'up' && !this.data.showForecast) {
                this.setData({ showForecast: true });
            } else if (this.direction === 'down' && this.data.showForecast) {
                this.setData({ showForecast: false });
            }
        }

        this.startY = null;
        this.direction = null;
    },

    toggleForecast() {
        this.setData({ showForecast: !this.data.showForecast });
    },

    shareWeather() {
        wx.showShareMenu({
            withShareTicket: true
        });
    },

    onShareAppMessage() {
        return {
            title: `${this.data.currentCity}天气 - ${this.data.currentWeather.text} ${this.data.currentWeather.temp}°`,
            path: '/pages/index/index',
            imageUrl: this.data.weatherIcon
        };
    }
});