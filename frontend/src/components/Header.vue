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
      <div class="topbar-account topbar-btn">
        <el-dropdown trigger="click">
        <span class="el-dropdown-link userinfo-inner">
          <i class="iconfont icon-user"></i> {{ nickname }}   <i class="el-icon-caret-bottom"></i></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <div @click="accountFormVisible = true"><span style="color: #555;font-size: 14px;">个人信息</span></div>
            </el-dropdown-item>
            <el-dropdown-item>
              <div @click="changepwdFormVisible = true"><span style="color: #555;font-size: 14px;">修改密码</span></div>
            </el-dropdown-item>
            <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <el-dialog title="账号信息" :visible.sync="accountFormVisible" :width="accountFormWidth">
        <el-form :model="accountForm">
          <el-form-item label="昵称" :label-width="accountFormLabelWidth">
            <el-input v-model="accountForm.nickname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" :label-width="accountFormLabelWidth">
            <el-input v-model="accountForm.email" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="accountFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="accountFormVisible = false">确 定</el-button>
        </div>
      </el-dialog>
      <el-dialog title="修改密码" :visible.sync="changepwdFormVisible" :width="changepwdFormWidth">
        <el-form :model="pwdForm" :label-position="'left'">
          <el-form-item label="原密码" :label-width="changepwdFormLabelWidth">
            <el-input v-model="pwdForm.old" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="新密码" :label-width="changepwdFormLabelWidth">
            <el-input v-model="pwdForm.new1" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认" :label-width="changepwdFormLabelWidth">
            <el-input v-model="pwdForm.new2" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="changepwdFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="changepwdFormVisible = false">确 定</el-button>
        </div>
      </el-dialog>
    </el-col>
  </el-row>
</template>

<script>
import API from '@/api/api_user'

export default {
  data () {
    return {
      nickname: '',
      accountFormVisible: false,
      changepwdFormVisible: false,
      accountForm: {
        nickname: '',
        email: ''
      },
      accountFormLabelWidth: '60px',
      accountFormWidth: '500px',
      changepwdFormLabelWidth: '60px',
      changepwdFormWidth: '500px',
      pwdForm: {
        old: '',
        new1: '',
        new2: ''
      }
    }
  },

  methods: {
    jumpTo (url) {
      this.$router.push(url) // 用go刷新
    },
    handleSelect (index) {
      this.defaultActiveIndex = index
    },
    logout () {
      // logout
      this.$confirm('确认退出吗?', '提示', {
        confirmButtonClass: 'el-button--warning'
      }).then(() => {
        // 确认
        localStorage.removeItem('access-user')
        // road.$emit('goto', '/login');
      }).catch(() => {})
    }

  },

  mounted () {
    // let user = window.localStorage.getItem('access-user');
    // 登陆成功后会在 window.localStorage 里 set，现在先 mock
    let user = JSON.stringify(
      {
        id: '1',
        nickname: '小龙虾',
        email: '123456@789.com'
      }
    )
    if (user) {
      user = JSON.parse(user)
      this.nickname = user.nickname || ''
      this.accountForm.nickname = user.nickname || ''
      this.accountForm.email = user.email || ''
    }
  }
}
</script>

<style scoped>
  .el-input {
    width: 350px;
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
