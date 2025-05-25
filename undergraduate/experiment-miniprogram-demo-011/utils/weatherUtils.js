// utils/weatherUtils.js
// 天气图标映射
const WEATHER_ICONS = {
    '100': 'https://cdn2.iconfinder.com/data/icons/weather-flat-14/64/weather07-1024.png',          // 晴
    '101': 'https://cdn-icons-png.flaticon.com/512/1850/1850730.png',         // 多云
    '102': 'https://cdn-icons-png.flaticon.com/512/1850/1850730.png',     // 少云
    '103': 'https://cdn-icons-png.flaticon.com/512/1850/1850730.png',  // 晴间多云
    '104': 'https://cdn3.iconfinder.com/data/icons/weather-16/256/Overcast-1024.png',       // 阴
  };
  
  // 背景图片映射（白天/夜晚）
  const BACKGROUND_IMAGES = {
    day: {
      '100': 'https://wallpaperaccess.com/full/3265126.jpg',
      '101': 'https://img.redocn.com/sheying/20220629/yuncai_12475321.jpg.400.jpg',
      '104': 'https://c-ssl.duitang.com/uploads/item/201506/24/20150624223749_yNEfw.jpeg',
      // 默认
      'default': 'https://bpic.588ku.com/back_list_pic/20/12/07/957a9dae9b3bd02827a05a49d6ea488a.jpg'
    },
    night: {
      '100': 'https://ts1.tc.mm.bing.net/th/id/R-C.7aed29ed875f844f0f33c6aa34ce3c3f?rik=ZHK9jC59Z4%2bPeQ&riu=http%3a%2f%2fpic.qianye88.com%2f4kfengjingc07d0980-2859-32f2-b92c-4ee340540fc4.jpg',
      '101': 'https://img.ixintu.com/download/jpg/201911/379fdef649e41135e8adb207a99a1ce3.jpg',
      // 默认
      'default': 'https://bpic.588ku.com/back_pic/06/16/08/8962b6e2ea2731d.jpg'
    }
  };
  
  // 获取天气图标
  export const getWeatherIcon = (code = '100') => {
    return WEATHER_ICONS[code] || WEATHER_ICONS['100'];
  };
  
  // 根据时间和天气代码获取背景图片
  export const getBackgroundImage = (code = '100', hour = new Date().getHours()) => {
    const isDayTime = hour >= 6 && hour < 18;
    const timeKey = isDayTime ? 'day' : 'night';
    const images = BACKGROUND_IMAGES[timeKey];
    
    return images[code] || images['default'];
  };