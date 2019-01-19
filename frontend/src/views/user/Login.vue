<template>
  <el-container>
    <el-header>
      <el-row>
        <el-col :span="24" class="topbar-wrap">
          <div class="topbar-logo topbar-btn">
            <i class="el-icon-success"></i>
          </div>
          <div class="topbar-logos">
            <a href="/" style="color: #f9f9f9;">story-map</a>
          </div>
          <el-button type="success" class="login-nav" @click="jumpTo('/register')">注册</el-button>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <div class="login-title">
        <h2>登录</h2>
      </div>
      <el-card class="box-card login-card" :shadow="'never'">
        <el-row>
          <el-col :span="18" :offset="3">
            <el-form :model="login" :label-position="'top'" status-icon
                     :rules="loginRules" ref="login">
              <el-form-item label="账号" prop="name">
                <el-input v-model="login.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="login.password" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="userlogin('login')" class="submit" :loading="loading">登录</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </el-card>
    </el-main>
  </el-container>
</template>
<script>
import API from '@/api/api_user'
import tool from '@/util/tool'
import axios from 'axios'

export default {
  name: 'login',
  data () {
    let validateName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'))
      } else {
        let isEnglish = tool.isEnglish(value)
        if (!isEnglish) {
          callback(new Error('请输入英文或数字'))
        }
        callback()
      }
    }

    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }

    return {
      loading: false,
      login: {
        name: '',
        password: ''
      },
      loginFormLabelWidth: '120px',
      loginRules: {
        name: [
          { validator: validateName, trigger: 'blur' }
        ],
        password: [
          { validator: validatePass, trigger: 'blur' }
        ]
      }
    }
  },

  methods: {
    userlogin (formName) {
      this.loading = true

      // for test , 假装 id 是 1
      // localStorage.setItem('access-user', '1')
      // this.jumpTo('/storymap-manager')
      // for test

      this.$refs[formName].validate((valid) => {
        if (valid) {
          API.login(this.login)
            .then(res => {
              console.log(res.status)
              let status = res.status
              if (status === 200) {
                console.log(res.data)
                localStorage.setItem('access-user', res.data)
                console.log(localStorage.getItem('access-user'))
                this.jumpTo('/storymap-manager')
              }
            })
            .catch(error => {
              this.loading = false
              if (error.response.status === 400) {
                this.$message.error('账号或密码错误')
                this.login.password = ''
              } else {
                this.$message.error('网络错误，请重试')
              }
            })
        } else {
          this.$message.error('请检查输入')
          this.loading = false
          return false
        }
      })
    },

    jumpTo (url) {
      this.$router.push(url) // 用go刷新
    },

    test () {
      axios.get('api/exportExcel?storyId=1', {
        responseType: 'blob' //指定返回数据的格式为blob
      })
        .then(res => {
          console.log(res)
          let linkElement = document.createElement('a')
          try {
            // let blob = new Blob([res.data], { type: 'application/x-xls' })
            let url = window.URL.createObjectURL(res.data)
            linkElement.setAttribute('href', url)
            linkElement.setAttribute("download", 'storymap.xlsx')
            let clickEvent = new MouseEvent("click", {
              "view": window,
              "bubbles": true,
              "cancelable": false
            })
            linkElement.dispatchEvent(clickEvent)
          } catch (ex) {
            console.log(ex)
          }
        })
        .catch(error => {
          console.log(error.response)
        })
    }
  },

  mounted () {
    // this.test()
  }
}

</script>
<style scoped>
  .el-header {
    padding: 0;
  }

  .el-main h2 {
    text-align: center;
    font-size: 2em;
    font-weight: 600;
    color: #484848;
  }

  .el-container {
    height: 100%;
    background-color: #eee;
  }

  .el-input {
    max-width: 350px;
    width: 100%;
  }

  .el-form {
    margin: 10px auto 0 auto;
  }

  .login-nav {
    float: right;
    margin: 5px 10px 0 0;
    font-weight: bold;
    color: #fff;
  }

  .submit {
    margin-top: 15px;
    float: right;
  }

  .login-title {
    margin-top: 10px;
    margin-bottom: 1.5em;
  }

  .login-card {
    width: 500px;
    margin: 30px auto 0 auto;
    box-shadow: 0 14px 30px rgba(0, 0, 0, 0.1);
    font-size: 20px;
  }

  /*navbar start*/
  .topbar-wrap {
    height: 50px;
    line-height: 50px;
    background: #026AA7;
    padding: 0;
  }

  .topbar-wrap .topbar-btn {
    color: #1e1e1e;
  }

  .topbar-logo i {
    font-size: 30px;
    vertical-align: middle;
    color: #f9f9f9;
    margin-left: 10px;
  }

  .topbar-wrap .topbar-logo {
    float: left;
    width: 60px;
    /*line-height: 26px;*/
  }

  .topbar-wrap .topbar-logos {
    float: left;
    width: 200px;
    line-height: 48px;
    font-size: 24px;
    font-style: italic;
    font-weight: 600;
  }

  /*navbar end*/

</style>
