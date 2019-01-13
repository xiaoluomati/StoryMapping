import axios from 'axios'

axios.defaults.withCredentials = false
axios.defaults.headers.post['Content-Type'] = 'application/jsoncharset=UTF-8' // 配置请求头
// 基地址
let base = 'api/'

// 通用方法
export const POST = (url, params) => {
  return axios.post(`${base}${url}`, params).then(res => res.data)
}

export const GET = (url, params) => {
  return axios.get(`${base}${url}`, { params: params }).then(res => res.data)
}

export const PUT = (url, params) => {
  return axios.put(`${base}${url}`, params).then(res => res.data)
}

export const DELETE = (url, params) => {
  return axios.delete(`${base}${url}`, { params: params }).then(res => res.data)
}
