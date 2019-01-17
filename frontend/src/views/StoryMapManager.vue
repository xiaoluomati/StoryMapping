<template>
  <el-row>
    <el-col :span="6" v-for="storymap in storymaps" :key="storymap.id" :offset=1>
      <el-card :body-style="{ padding: '0px' }" class="storymap" shadow="never"
               @click.native="jumpTo('storymap', storymap.id)">
        <div style="padding: 10px;">
          <h1>{{ storymap.title }}</h1>
          <p>
            {{ storymap.description }}
          </p>
          <a></a>
        </div>

      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import API from '@/api/api_storymap'
import tool from '@/util/tool'

export default {
  name: 'storymap-manager',
  data () {
    return {
      storymaps: ''
    }
  },

  methods: {
    jumpTo (name, param) {
      this.$router.push({ path: `/${name}/${param}` }) // 用go刷新
    },

    initStoryMap () {
      let id = tool.getUserId()
      if (id == null) {
        this.$message.error('请先登录')
        this.$router.push('/login')
      } else {
        API.getStoryMapList(id)
          .then(res => {
            let status = res.status
            if (status === 200) {
              this.storymaps = res.data[0].storymaps
              // 测试的时候返回的是数组，运行时改成注释的样子
              // this.storymaps = res.data.storymaps
            } else {
              this.$message.error('读取数据出错，请刷新页面')
            }
          })
          .catch(() => {
            this.$message.error('读取数据出错，请刷新页面')
          })
      }
    }
  },

  mounted () {
    this.initStoryMap()
  }
}
</script>

<style scoped>

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .storymap {
    margin: 0 0 20px 0;
    height: 200px;
    background: #007bbc;
    color: #ffffff;
    border-radius: 3px;
  }

  h1 {
    font-size: 24px;
  }

  p {
    margin: 10px 0 0 0;
  }

  .el-card:hover {
    box-shadow: 0 7px 16px 0 rgba(0, 0, 0, 0.4)
  }
</style>
