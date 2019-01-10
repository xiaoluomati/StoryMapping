<template>
  <el-row class="wrap">
    <el-col :span="24" class="wrap-breadcrumb">
      <el-breadcrumb seporate="/">
        <el-breadcrumb-item :to="{ path: '/'}"><b>首页</b></el-breadcrumb-item>
      </el-breadcrumb>
      <el-col :span="24" class="wrap-main">
      </el-col>
    </el-col>
  </el-row>
</template>
<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

</style>
<script>
export default {
  name: 'storymap-manager',
  data () {
    return {
      chartColumn: null,
      chartBar: null
    }
  }
}
</script>
