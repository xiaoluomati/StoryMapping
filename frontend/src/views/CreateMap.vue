<template>
  <el-form :label-position="labelPosition" label-width="300px" :model="storymap">
    <el-form-item label="地图名称">
      <el-input v-model="storymap.storyName"></el-input>
    </el-form-item>
    <el-form-item label="地图描述">
      <el-input type="textarea" v-model="storymap.storyDescription"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="success" @click.native="submitForm">保存</el-button>
      <el-button type="primary" @click.native="submitAndOpen">保存并打开</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import API from '@/api/api_storymap'
import tool from '@/util/tool'

export default {
  name: 'create',
  data () {
    return {
      labelPosition: 'top',
      storymap: {
        storyId: '',
        storyName: '',
        storyDescription: '',
        release: 0,
        userId: ''
      }
    }
  },

  methods: {
    submitForm () {
      if (this.storymap.storyName.trim().length === 0 ||
        this.storymap.storyDescription.trim().length === 0) {
        this.$message.error('请检查输入')
        return
      }

      let userId = tool.getUserId()
      if (userId == null) {
        this.$message.error('请先登录')
        this.$router.push('/login')
      } else {
        this.storymap.userId = userId
        API.addStoryMap(this.storymap)
          .then(res => {
            let status = res.status
            if (status === 200) {
              this.$router.push('/')
            } else {
              this.$message.error('网络波动，请稍后重试')
            }
          })
          .catch(() => {
            this.$message.error('网络波动，请稍后重试')
          })
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
