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
              <div @click="jumpTo('/user/profile')"><span style="color: #555;font-size: 14px;">个人信息</span></div>
            </el-dropdown-item>
            <el-dropdown-item>
              <div @click="jumpTo('/user/changepwd')"><span style="color: #555;font-size: 14px;">修改密码</span></div>
            </el-dropdown-item>
            <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data () {
    return {
      nickname: 'admin'
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
      // //logout
      // this.$confirm('确认退出吗?', '提示', {
      //   confirmButtonClass: 'el-button--warning'
      // }).then(() => {
      //   //确认
      //   localStorage.removeItem('access-user');
      //   road.$emit('goto', '/login');
      // }).catch(() => {});
    }
  }
}
</script>

<style scoped>
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
