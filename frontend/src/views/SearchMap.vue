<template>
  <el-row>
    <el-col :offset="1" :span="18">
      <div class="search">
        <el-input placeholder="请输入内容" v-model="searchInput" class="input-with-select"  @keyup.enter.native="handleSearch">
          <el-select v-model="searchSelect" slot="prepend" placeholder="请选择"
                     style="width: 80px">
            <el-option label="名称" value="1"></el-option>
            <el-option label="描述" value="2"></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search" @click="handleSearch"></el-button>
        </el-input>
        <el-button @click="resetSearch" style="margin-left: 20px;">重置</el-button>
      </div>
    </el-col>
    <el-col :span="6" v-for="storymap in storymapsOnShow" :key="storymap.id" :offset=1>
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
      storymaps: '',
      storymapsOnShow: '',
      searchInput: '',
      searchSelect: '1'
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
              this.storymapsOnShow = this.storymaps.slice()
            } else {
              this.$message.error('读取数据出错，请刷新页面')
            }
          })
          .catch(() => {
            this.$message.error('读取数据出错，请刷新页面')
          })
      }
    },

    handleSearch () {
      if (this.searchInput.trim().length === 0) {
        this.storymapsOnShow = this.storymaps.slice()
        return
      }

      let result = []
      let maps = this.storymaps.slice()
      if (this.searchSelect === '1') {
        for (let i = 0; i < maps.length; i++) {
          if (maps[i].title.indexOf(this.searchInput) > -1) {
            result.push(maps[i])
          }
        }
      } else if (this.searchSelect === '2') {
        for (let i = 0; i < maps.length; i++) {
          if (maps[i].description.indexOf(this.searchInput) > -1) {
            result.push(maps[i])
          }
        }
      }
      this.storymapsOnShow = result
    },

    resetSearch () {
      this.searchInput = ''
      this.searchSelect = '1'
      this.storymapsOnShow = this.storymaps.slice()
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

  .input-with-select {
    width: 500px;
  }

  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }

  .search {
    margin-bottom: 50px;
  }
</style>
