<template>
  <el-row>
    <el-col :span="24" class="topbar-wrap">
      <div class="topbar-logo topbar-btn">
        <!--<a href="/"><img src="../assets/logo.png" style="padding-left:8px;"></a>-->
        <!--<a href="/"><img src="../assets/images.jpg" style="padding-left:8px;"></a>-->
        <i class="el-icon-success"></i>
      </div>
      <div class="topbar-logos">
        <a href="/" style="color: #f9f9f9;">story-map</a>
      </div>
      <div class="home">
        <a href="/" style="color: #f9f9f9; font-size: 20px; font-weight: 600; ">主页</a>
      </div>
      <div class="topbar-account topbar-btn">
        <el-dropdown trigger="click">
        <span class="el-dropdown-link userinfo-inner">
          <i class="iconfont icon-user"></i> {{ nickname }}   <i class="el-icon-caret-bottom"></i></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <div @click="handleToUpdateAccount"><span style="color: #555;font-size: 14px;">个人信息</span></div>
            </el-dropdown-item>
            <el-dropdown-item>
              <div @click="handleToChangePwd"><span style="color: #555;font-size: 14px;">修改密码</span></div>
            </el-dropdown-item>
            <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <el-dialog title="账号信息" :visible.sync="accountFormVisible" :width="accountFormWidth">
        <el-form :model="account" status-icon :rules="accountRules" ref="account">
          <el-form-item label="昵称" :label-width="accountFormLabelWidth" prop="nickname">
            <el-input v-model="account.nickname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" :label-width="accountFormLabelWidth" prop="email">
            <el-input v-model="account.email" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="accountFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="updateAccount('account')" :loading="loading">确 定</el-button>
        </div>
      </el-dialog>
      <el-dialog title="修改密码" :visible.sync="changepwdFormVisible" :width="changepwdFormWidth">
        <el-form :model="pwdForm" :label-position="'left'" status-icon
                 :rules="pwdRules" ref="pwdForm">
          <el-form-item label="原密码" :label-width="changepwdFormLabelWidth" prop="old">
            <el-input type="password" v-model="pwdForm.old" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="新密码" :label-width="changepwdFormLabelWidth" prop="new1">
            <el-input type="password" v-model="pwdForm.new1" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认" :label-width="changepwdFormLabelWidth" prop="new2">
            <el-input type="password" v-model="pwdForm.new2" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="changepwdFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleChangePwd('pwdForm')" :loading="loading">确 定</el-button>
        </div>
      </el-dialog>
    </el-col>
  </el-row>
</template>

<script>
import API from '@/api/api_user'
import tool from '@/util/tool'

export default {
  data () {
    let validateEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱'))
      } else {
        if (tool.isEmail(value)) {
          callback()
        } else {
          callback(new Error('请输入正确的邮箱'))
        }
      }
    }

    let validateOldPwd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入旧密码'))
      } else {
        callback()
      }
    }

    let validateNewPwd1 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入新密码'))
      } else {
        callback()
      }
    }

    let validateNewPwd2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入新密码'))
      } else {
        if (this.pwdForm.new1 !== '') {
          if (!this.isPwdSame()) {
            callback(new Error('两次输入的密码不同，请重新输入'))
          } else {
            callback()
          }
        }
        callback()
      }
    }

    return {
      loading: false,
      nickname: '',
      accountFormVisible: false,
      changepwdFormVisible: false,
      account: {
        id: '',
        nickname: '',
        email: ''
      },

      accountRules: {
        nickname: [
          { required: true, message: '请输入昵称', trigger: 'blur' }
        ],
        email: [
          { validator: validateEmail, trigger: 'blur' }
        ]
      },

      accountFormLabelWidth: '60px',
      accountFormWidth: '500px',
      changepwdFormLabelWidth: '60px',
      changepwdFormWidth: '500px',
      pwdForm: {
        old: '',
        new1: '',
        new2: ''
      },

      pwdRules: {
        old: [
          { validator: validateOldPwd, trigger: 'blur' }
        ],
        new1: [
          { validator: validateNewPwd1, trigger: 'blur' }
        ],
        new2: [
          { validator: validateNewPwd2, trigger: 'blur' }
        ]
      }
    }
  },

  methods: {
    jumpTo (url) {
      this.$router.push(url) // 用go刷新
    },

    logout () {
      this.$confirm('确认退出吗?', '提示', {
        confirmButtonClass: 'el-button--warning'
      }).then(() => {
        localStorage.removeItem('access-user')
        this.jumpTo('/login')
      }).catch(() => {
      })
    },

    handleToUpdateAccount () {
      this.updateUserInfo()
      this.accountFormVisible = true
      if (this.$refs['account'] !== undefined) {
        this.$refs['account'].resetFields()
      }
    },

    updateAccount (formName) {
      this.loading = true

      this.$refs[formName].validate((valid) => {
        if (valid) {
          API.updateAccount(this.account.id, this.account)
            .then(res => {
              let status = res.status
              if (status === 200) {
                this.$message({
                  message: '账号信息更改成功',
                  type: 'success'
                })
                this.accountFormVisible = false
                this.updateUserInfo()
              } else {
                this.$message.error('账号信息更改失败')
              }
            })
            .catch(() => {
              this.$message.error('账号信息更改失败')
            })
        } else {
          this.$message.error('请检查输入')
          return false
        }
      })
      this.loading = false
    },

    updateUserInfo () {
      this.loading = true
      API.getUser(tool.getUserId())
        .then(res => {
          let user = res.data
          if (user) {
            this.nickname = user.nickname || ''
            this.account = user
          }
        })
        .catch(() => {
          // this.$message.error('获取用户信息失败，请重试')
        })
      this.loading = false
    },

    changePwd () {
      let pwd = { id: this.account.id, oldPassword: this.pwdForm.old, newPassword: this.pwdForm.new1 }
      API.updatePassword(this.account.id, pwd)
        .then(res => {
          let status = res.status
          if (status === 200) {
            this.changepwdFormVisible = false
            this.$message({
              message: '密码更改成功',
              type: 'success'
            })
          }
        })
        .catch(error => {
          if (error.response.status === 400) {
            this.$message.error('旧密码输入错误')
            this.$refs['pwdForm'].resetFields()
          } else {
            this.$message.error('密码更改失败，请重试')
            this.$refs['pwdForm'].resetFields()
          }
        })
    },

    handleChangePwd (formName) {
      this.loading = true

      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.changePwd()
        } else {
          this.$message.error('请检查输入')
          return false
        }
      })

      this.loading = false
    },

    handleToChangePwd () {
      this.changepwdFormVisible = true
      if (this.$refs['pwdForm'] !== undefined) {
        this.$refs['pwdForm'].resetFields()
      }
    },

    isPwdSame () {
      return this.pwdForm.new1 === this.pwdForm.new2
    }
  },

  mounted () {
    this.updateUserInfo()
  }
}
</script>

<style scoped>
  .el-input {
    width: 350px;
  }

  .home {
    float: left;
    width: 100px;
  }

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

  .topbar-wrap .topbar-logo img, .topbar-wrap .topbar-logos img {
    height: 30px;
    margin-top: 12px;
    margin-left: 2px;
  }

  .topbar-wrap .topbar-title {
    float: left;
    text-align: left;
    padding-left: 10px;
    border-left: 1px solid #000;
  }

  .topbar-title .el-menu--horizontal {
    background-color: transparent;
  }

  .el-menu--horizontal > .el-menu-item:not(.is-disabled):hover, .el-menu--horizontal > .el-menu-item:not(.is-disabled):focus, .el-menu--horizontal > .el-menu-item.is-active {
    color: #1e1e1e;
    background-color: transparent;
    border-bottom: 2px solid #409EFF !important;
  }

  .topbar-title .el-menu--horizontal > .el-menu-item {
    height: 50px;
    line-height: 50px;
    color: #1e1e1e;
  }

  .el-menu-item .iconfont {
    margin-right: 5px;
    display: inline-block;
    width: 24px;
    text-align: center;
    font-size: 18px;
    vertical-align: middle;
  }

  .topbar-wrap .topbar-account {
    float: right;
    padding-right: 12px;
  }

  .topbar-wrap .topbar-timer {
    display: inline-block;
  }

  .topbar-wrap .topbar-timer span {
    display: inline-block;
    vertical-align: middle;
  }

  .topbar-wrap .topbar-timer .login-name {
    margin: 0 6px;
    font-style: normal;
  }

  .topbar-wrap .userinfo-inner {
    cursor: pointer;
    color: #f9f9f9;
    padding-left: 10px;
    font-size: 20px;
  }

  .topbar-wrap .userinfo-inner img {
    margin-left: 6px;
    width: 42px;
    height: 42px;
    border: 1px solid #504d4d;
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    border-radius: 50%;
    vertical-align: middle;
  }
</style>
