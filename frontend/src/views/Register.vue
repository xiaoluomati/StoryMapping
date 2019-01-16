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
            <el-form :model="register" :label-position="'top'">
              <el-form-item label="账号">
                <el-input v-model="register.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="昵称">
                <el-input v-model="register.nickname" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                <el-input v-model="register.password" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input v-model="register.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="userRegister" class="submit">注册</el-button>
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

export default {
  name: 'register',
  data () {
    return {
      loading: false,
      register: {
        name: '',
        nickname: '',
        password: '',
        email: ''
      },
      registerFormLabelWidth: '120px'
    }
  },

  methods: {
    userRegister () {
      API.register(this.register)
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

  .submit {
    margin-top: 15px;
  }

  .register-title {
    margin-top: 10px;
    margin-bottom: 1.5em;
  }

  .register-card {
    width: 500px;
    margin: 30px auto 0 auto;
    box-shadow: 0 14px 30px rgba(0,0,0,0.1);
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
