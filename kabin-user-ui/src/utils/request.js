// src/utils/request.js
import axios from 'axios'

const request = axios.create({
  baseURL: process.env.VUE_APP_API_URL, // 根据需要替换
  timeout: 10000
})

// 请求拦截器
request.interceptors.request.use(config => {
  // 可以在这里添加 token 或其他请求头
  config.headers['Authorization'] = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImxvZ2luX3VzZXJfa2V5IjoiNjA5MWIyZTktYjhjMi00OGE3LWI4ZWYtNTY3MTczZjdjNzNmIn0.-WEiIQ80QwOO3NF_KE8ef7Rzb2TIiYYVsPf2GORXxW9p6mXDtz7SbxP50vJlIXh7I5gCCVmRpAR12uueT40WTQ"
  return config
}, error => {
  return Promise.reject(error)
})

// 响应拦截器
request.interceptors.response.use(response => {
  return response.data
}, error => {
  return Promise.reject(error)
})

export default request
