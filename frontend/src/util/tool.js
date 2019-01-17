export default {

  isEnglish (str) {
    let reg = /^[a-zA-Z0-9]+$/
    return reg.test(str)
  },

  isEmail (str) {
    let reg = /^\w+@[a-zA-Z0-9]{2,10}(?:\.[a-z]{2,4}){1,3}$/
    return reg.test(str)
  },

  getUserId () {
    let id = window.localStorage.getItem('access-user')
    if (id) {
      return id
    }
    return null
  }
}
