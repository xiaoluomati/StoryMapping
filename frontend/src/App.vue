<!--<template>-->
  <!--<div id="app">-->
    <!--<img src="./assets/logo.png">-->
    <!--<div>-->
      <!--<p>-->
        <!--If Element is successfully added to this project, you'll see an-->
        <!--<code v-text="'<el-button>'"></code>-->
        <!--below-->
      <!--</p>-->
      <!--<el-button>el-button</el-button>-->
    <!--</div>-->
    <!--<HelloWorld msg="Welcome to Your Vue.js App"/>-->
  <!--</div>-->
<!--</template>-->

<!--<script>-->
<!--import HelloWorld from './components/HelloWorld.vue'-->

<!--export default {-->
  <!--name: 'app',-->
  <!--components: {-->
    <!--HelloWorld-->
  <!--}-->
<!--}-->
<!--</script>-->

<!--<style>-->
<!--#app {-->
  <!--font-family: 'Avenir', Helvetica, Arial, sans-serif;-->
   <!-- -webkit-font-smoothing: antialiased;-->
   <!-- -moz-osx-font-smoothing: grayscale;-->
  <!--text-align: center;-->
  <!--color: #2c3e50;-->
  <!--margin-top: 60px;-->
<!--}-->
<!--</style>-->

<template>
  <div id="app">

    <!--<el-container>-->
      <!--<el-header>-->
        <!--<navheader></navheader>-->
      <!--</el-header>-->
      <!--<el-container>-->
        <!--<el-aside width="200px">-->
          <!--<navmenu></navmenu>-->
        <!--</el-aside>-->
        <!--<el-main>Main</el-main>-->
      <!--</el-container>-->
    <!--</el-container>-->
    <el-row class="container">
      <!--头部-->
      <el-col :span="24"><navheader></navheader></el-col>
      <el-aside width="200px">
        <el-col :span="24" class="main">
          <!--左侧导航-->
          <navmenu></navmenu>
        </el-col>
      </el-aside>

    </el-row>
  </div>
</template>

<script>
import NavMenu from './components/NavMenu.vue'
import Header from './components/Header.vue'

export default {
  name: 'app',
  components: {
    'navmenu': NavMenu,
    'navheader': Header
  }
}
</script>

<style>

</style>
