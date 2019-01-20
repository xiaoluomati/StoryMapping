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
          <el-button type="success" class="login-nav" @click="jumpTo('/login')">登录</el-button>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <div class="register-title">
        <h2>欢迎注册</h2>
      </div>
      <el-card class="box-card register-card" :shadow="'never'">
        <el-row>
          <el-col :span="18" :offset="3">
            <el-form :model="register" :label-position="'top'" ref="register" status-icon :rules="registerRules">
              <el-form-item label="账号" prop="name">
                <el-input v-model="register.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="昵称" prop="nickname">
                <el-input v-model="register.nickname" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="register.password" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" prop="email">
                <el-input v-model="register.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="userRegister('register')"
                           class="submit" :loading="loading">
                  注册
                </el-button>
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

export default {
  name: 'register',
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

    let validateNickName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入昵称'))
      } else {
        callback()
      }
    }

    let validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }

    let validateEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱'))
      } else {
        if (!tool.isEmail(value)) {
          callback(new Error('请输入正确的邮箱'))
        }
        callback()
      }
    }

    return {
      loading: false,
      register: {
        name: '',
        nickname: '',
        password: '',
        email: ''
      },
      registerFormLabelWidth: '120px',
      registerRules: {
        name: [
          { validator: validateName, trigger: 'blur' }
        ],
        nickname: [
          { validator: validateNickName, trigger: 'blur' }
        ],
        password: [
          { validator: validatePassword, trigger: 'blur' }
        ],
        email: [
          { validator: validateEmail, trigger: 'blur' }
        ]
      }
    }
  },

  methods: {

    apiRegister () {
      API
        .register(this.register)
        .then(res => {
          let status = res.status
          if (status === 200) {
            localStorage.setItem('access-user', res.data)
            this.jumpTo('/storymap-manager')
          }
        })
        .catch(error => {
          this.loading = false
          if (error.response.status === 400) {
            this.$message.error('该账号已存在')
          } else {
            this.$message.error('网络错误，请重试')
          }
        })
    },

    userRegister (formName) {
      this.loading = true

      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.apiRegister()
        } else {
          this.$message.error('请检查输入')
          this.loading = false
          return false
        }
      })
    },

    jumpTo (url) {
      this.$router.push(url) // 用go刷新
    }
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

  .register-title {
    margin-top: 10px;
    margin-bottom: 1.5em;
  }

  .register-card {
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
