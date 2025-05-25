// utils/api.js
const API_KEY = '76507b5d68a54fc18a475cdf1c30e66c';
const BASE_URL = 'https://nd3yfqb2gt.re.qweatherapi.com/v7';

const request = (url, method = 'GET', data = {}) => {
  return new Promise((resolve, reject) => {
    wx.request({
      url: `${BASE_URL}${url}`,
      method,
      data,
      success: (res) => {
        if (res.statusCode === 200 && res.data.code === '200') {
          resolve(res.data);
        } else {
          reject(new Error('API请求失败'));
        }
      },
      fail: (err) => {
        reject(err);
      }
    });
  });
};

// 获取实时天气
export const getCurrentWeather = (location) => {
  return request(`/weather/now?key=${API_KEY}&location=${location}`);
};

// 获取未来3天预报
export const getForecastWeather = (location) => {
  return request(`/grid-weather/3d?key=${API_KEY}&location=${location}`);
};

// 根据城市名搜索位置
export const searchLocation = (cityName) => {
  return request(`/city/lookup?key=${API_KEY}&location=${cityName}`);
};