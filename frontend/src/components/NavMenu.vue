<template>
  <el-row class="tac">
    <el-col :span="11" :offset="11">
      <el-menu class="action-menu" @open="handleOpen" @close="handleClose"
               text-color="#17394D" active-text-color="#026AA7">
        <el-menu-item index="1" @click="jumpTo('/create')">
          <i class="el-icon-circle-plus-outline"></i>
          <span slot="title">增加地图</span>
        </el-menu-item>
        <el-menu-item  index="2">
          <i class="el-icon-search"></i>
          <span slot="title">寻找地图</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
export default {
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    jumpTo (url) {
      this.$router.push(url) // 用go刷新
    }
  }
}
</script>

<style scoped>
  .el-menu-item {
    border-radius: 3px;
  }

</style>
