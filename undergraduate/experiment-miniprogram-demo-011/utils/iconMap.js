// utils/iconMap.js
export const weatherIcons = {
    '100': { day: '/images/sun.png', night: '/images/moon.png' }, // 晴
    '101': '/images/cloudy.png', // 多云
    '102': '/images/partly-cloudy.png', // 少云
    '103': '/images/partly-cloudy.png', // 晴间多云
    '104': '/images/overcast.png', // 阴
    // 添加更多天气图标映射...
    '300': '/images/rain.png', // 阵雨
    '301': '/images/heavy-rain.png', // 强阵雨
    '302': '/images/thunder.png', // 雷阵雨
    '303': '/images/thunder-rain.png', // 强雷阵雨
    // 其他天气代码...
  };
  
  export const getWeatherIcon = (iconCode, isDaytime = true) => {
    const icon = weatherIcons[iconCode];
    if (!icon) return '/images/cloud.png';
    
    if (typeof icon === 'object') {
      return isDaytime ? icon.day : icon.night;
    }
    
    return icon;
  };