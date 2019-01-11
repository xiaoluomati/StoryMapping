<template>
  <el-container>
    <!--头部-->
    <el-col :span="24">
      <router-view name="navheader"></router-view>
    </el-col>
    <el-col :span="24" class="main">
      左侧导航
      <router-view name="aside"></router-view>
      右侧内容区
      <section class="content-container">
        <div class="grid-content bg-purple-light">
          <transition name="fade" mode="out-in">
            <router-view></router-view>
          </transition>
        </div>
      </section>
    </el-col>
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

<style>

</style>
