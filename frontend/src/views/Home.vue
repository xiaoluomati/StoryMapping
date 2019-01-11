<template>
  <el-container>
    <!--头部-->
    <el-header>
      <router-view name="navheader"></router-view>
    </el-header>
    <el-container class="main-content">
      <el-aside>
        <router-view name="aside"></router-view>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'home',
  data () {
    return {
      loading: false
    }
  }
}
</script>

<style scoped>
  .el-header {
    padding: 0;
  }

  .main-content {
    padding: 20px 20px 0 20px;
  }
</style>
