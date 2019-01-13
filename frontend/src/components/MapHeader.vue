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
      <div class="operation-buttons">
        <el-button icon="el-icon-check" circle></el-button>
        <el-button icon="el-icon-plus" circle></el-button>
        <el-button icon="el-icon-search" circle></el-button>
        <el-button icon="el-icon-download" circle></el-button>
        <el-button icon="el-icon-back" circle></el-button>
      </div>
    </el-col>
  </el-row>
</template>
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

  .topbar-wrap .topbar-timer span {
    display: inline-block;
    vertical-align: middle;
  }
  .topbar-wrap .operation-buttons {
    float: right;
    padding-right: 12px;
  }
  .el-button{
    color: #1881E3;
  }
</style>
