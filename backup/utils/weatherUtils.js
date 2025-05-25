// utils/weatherUtils.js
const getBackgroundImage = (weather, isDaytime) => {
    const weatherType = getWeatherType(weather);
    const time = isDaytime ? 'day' : 'night';

    const backgrounds = {
        'sunny': {
            'day': '/images/sunny-day.jpg',
            'night': '/images/sunny-night.jpg'
        },
        'cloudy': {
            'day': '/images/cloudy-day.jpg',
            'night': '/images/cloudy-night.jpg'
        },
        'rain': {
            'day': '/images/rain-day.jpg',
            'night': '/images/rain-night.jpg'
        },
        'snow': {
            'day': '/images/snow-day.jpg',
            'night': '/images/snow-night.jpg'
        },
        'thunder': {
            'day': '/images/thunder-day.jpg',
            'night': '/images/thunder-night.jpg'
        },
        'fog': {
            'day': '/images/fog-day.jpg',
            'night': '/images/fog-night.jpg'
        },
        'default': {
            'day': '/images/default-day.jpg',
            'night': '/images/default-night.jpg'
        }
    };

    return backgrounds[weatherType]?.[time] || backgrounds['default'][time];
};

const getWeatherIcon = (weather, isDaytime) => {
    const weatherType = getWeatherType(weather);
    const time = isDaytime ? 'day' : 'night';

    const icons = {
        'sunny': {
            'day': '/images/sun.png',
            'night': '/images/moon.png'
        },
        'cloudy': '/images/cloud.png',
        'rain': '/images/rain.png',
        'snow': '/images/snow.png',
        'thunder': '/images/thunder.png',
        'fog': '/images/fog.png',
        'default': '/images/cloud.png'
    };

    if (typeof icons[weatherType] === 'object') {
        return icons[weatherType][time] || icons[weatherType]['day'];
    }

    return icons[weatherType] || icons['default'];
};

const getWeatherType = (weather) => {
    weather = weather.toLowerCase();

    if (weather.includes('晴')) return 'sunny';
    if (weather.includes('云') || weather.includes('阴')) return 'cloudy';
    if (weather.includes('雨')) return 'rain';
    if (weather.includes('雪')) return 'snow';
    if (weather.includes('雷')) return 'thunder';
    if (weather.includes('雾') || weather.includes('霾')) return 'fog';

    return 'default';
};

module.exports = {
    getBackgroundImage,
    getWeatherIcon
};