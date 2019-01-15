import * as API from './api'

export default {
  // 登录
  login: params => {
    return API.POST('user/login', params)
  },
  // 登出
  logout: params => {
    return API.GET('user/logout', params)
  },

  register: params => {
    return API.POST('user/register', params)
  },

  updateAccount: (id, params) => {
    return API.PUT(`user/${id}`, params)
  },

  getUser: id => {
    return API.GET(`user/${id}`)
  }
}
