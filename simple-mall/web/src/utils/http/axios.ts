import axios from "axios";
import type { AxiosRequestConfig, AxiosResponse, AxiosError } from "axios";

export const AXIOS = axios.create({
  baseURL: "http://localhost:8083",
});

// 请求拦截
AXIOS.interceptors.request.use(
  (config: AxiosRequestConfig): any => {
    const headers = config.headers || {};
    // headers["Authorization"] = "xxx";
    // config.headers = headers;

    // 设置默认的 Content-Type 为 application/json
    if (!config.headers) {
      config.headers = {};
    }
    config.headers['Content-Type'] = 'application/json';

    // 如果需要设置 Authorization 头部，可以在这里取消注释并替换 "xxx" 为实际的 token
    // config.headers['Authorization'] = 'Bearer xxx';
    
    return config;
  },
  (error: AxiosError) => {
    return Promise.reject(error);
  }
);

// 响应拦截器
AXIOS.interceptors.response.use(
  (response: AxiosResponse) => {
    return response;
  },
  (error: AxiosError) => {
    return Promise.reject(error);
  }
);

