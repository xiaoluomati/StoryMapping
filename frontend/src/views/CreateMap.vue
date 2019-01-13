<template>
  <el-form :label-position="labelPosition" label-width="300px" :model="formLabelAlign">
    <el-form-item label="地图名称">
      <el-input v-model="formLabelAlign.name"></el-input>
    </el-form-item>
    <el-form-item label="地图描述">
      <el-input type="textarea" v-model="formLabelAlign.description"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="success" @click.native="submitForm('')">保存</el-button>
      <el-button type="primary" @click.native="submitAndOpen('')">保存并打开</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
export default {
  name: 'create',
  data () {
    return {
      labelPosition: 'top',
      formLabelAlign: {
        name: '',
        description: ''
      }
    }
  }
}
</script>
<style scoped>
  .el-form {
    width: 400px;
  }
</style>
