// 在 request.ts 中添加
import axios from "axios";

axios.interceptors.response.use(
  response => response,
  error => {
    const message = error.response?.data?.message || error.message;
    return Promise.reject(new Error(message));
  }
);