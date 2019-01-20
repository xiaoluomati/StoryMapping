<template>
  <el-container>
    <!--头部-->
    <el-header>
      <router-view name="navheader"></router-view>
    </el-header>
    <el-container class="main-content">
      <el-main>
        <router-view name="cards"></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'storymap',
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

  .el-container {
    height: 100%;
    background: #EFEFEF;
  }
</style>
