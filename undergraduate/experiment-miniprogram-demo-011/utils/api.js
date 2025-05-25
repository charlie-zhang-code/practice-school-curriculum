// utils/api.js
const API_KEY = '76507b5d68a54fc18a475cdf1c30e66c';
const BASE_URL = 'https://nd3yfqb2gt.re.qweatherapi.com/v7';
const CACHE_TIME = 30 * 60 * 1000; // 30分钟缓存

const cache = {};

const request = (url, method = 'GET', data = {}) => {
  // 检查缓存
  const cacheKey = `${url}-${JSON.stringify(data)}`;
  const cachedData = cache[cacheKey];
  const now = Date.now();
  
  if (cachedData && now - cachedData.timestamp < CACHE_TIME) {
    return Promise.resolve(cachedData.data);
  }

  return new Promise((resolve, reject) => {
    wx.request({
      url: `${BASE_URL}${url}`,
      method,
      data,
      success: (res) => {
        if (res.statusCode === 200 && res.data.code === '200') {
          // 缓存数据
          cache[cacheKey] = {
            data: res.data,
            timestamp: now
          };
          resolve(res.data);
        } else {
          // 尝试返回缓存数据
          if (cachedData) {
            resolve(cachedData.data);
          } else {
            reject(new Error(res.data.message || 'API请求失败'));
          }
        }
      },
      fail: (err) => {
        // 网络错误时尝试返回缓存数据
        if (cachedData) {
          resolve(cachedData.data);
        } else {
          reject(err);
        }
      }
    });
  });
};

// 获取实时天气
export const getCurrentWeather = (location) => {
  return request(`/weather/now?key=${API_KEY}&location=${encodeURIComponent(location)}`);
};

// 获取未来3天预报
export const getForecastWeather = (location) => {
  return request(`/grid-weather/3d?key=${API_KEY}&location=${encodeURIComponent(location)}`);
};

// 新增 - 根据经纬度获取城市信息
export const getCityByLocation = (longitude, latitude) => {
  return request(`/city/lookup?key=${API_KEY}&location=${longitude},${latitude}`);
};