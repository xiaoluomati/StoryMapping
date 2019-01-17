import axios from 'axios'

axios.defaults.withCredentials = false
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8' // 配置请求头
// 基地址
let base = 'api/'

// 通用方法
export const POST = (url, params) => {
  return axios.post(`${base}${url}`, params)
}

export const GET = (url, params) => {
  return axios.get(`${base}${url}`, { params: params })
}

export const PUT = (url, params) => {
  return axios.put(`${base}${url}`, params)
}

export const DELETE = (url, params) => {
  return axios.delete(`${base}${url}`, { params: params })
}
