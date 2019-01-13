import * as API from './api'

export default {
  // 登录
  login: params => {
    return API.POST('/api/user/login', params)
  },
  // 登出
  logout: params => {
    return API.GET('/api/user/logout', params)
  },

  updateAccount: (id, params) => {
    return API.PUT(`/api/user/${id}`, params)
  }
}
