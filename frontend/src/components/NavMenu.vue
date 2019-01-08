<template>
  <el-row class="tac">
    <el-col :span="15">
      <el-menu default-active="1" class="action-menu" @open="handleOpen" @close="handleClose"
               background-color="#F8F9F9" text-color="#17394D" active-text-color="#026AA7">
        <el-menu-item index="1">
          <i class="el-icon-circle-plus-outline"></i>
          <span slot="title">增加地图</span>
        </el-menu-item>
        <el-menu-item index="2">
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
    }
  }
}
</script>
