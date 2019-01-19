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
        <div style="padding: 10px; height: 100%">
          <h1>{{ storymap.title }}</h1>
          <p>
            {{ storymap.description }}
          </p>
        </div>
      </el-card>
      <div class="operation">
        <el-button icon="el-icon-delete" circle class="storymap-icon" @click="handleDeleteStoryMap"></el-button>
        <el-button icon="el-icon-edit" circle class="storymap-icon" @click="handleEditStoryMap(storymap.id)"></el-button>
      </div>
    </el-col>

    <el-dialog title="用户故事地图编辑" :visible.sync="editStoryMapVisible" width="30%">
      <el-form :label-position="'top'" label-width="100px" :model="storymapEdit">
        <el-form-item label="地图名称">
          <el-input v-model="storymapEdit.title"></el-input>
        </el-form-item>
        <el-form-item label="地图描述">
          <el-input type="textarea" v-model="storymapEdit.description"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click.native="editStoryMapVisible = false">取消</el-button>
          <el-button type="primary" @click.native="updateStoryMap">保存</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
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
      searchSelect: '1',
      storymapEdit: {
        id: '',
        title: '',
        description: ''
      },
      editStoryMapVisible: false
    }
  },

  methods: {
    jumpTo (name, param) {
      this.$router.push({ path: `/${name}/${param}` }) // 用go刷新
    },

    initStoryMap () {
      let id = tool.getUserId()
      if (id == null) {
        // this.$message.error('请先登录')
        this.$router.push('/login')
      } else {
        API.getStoryMapList(id)
          .then(res => {
            let status = res.status
            if (status === 200) {
              // this.storymaps = res.data[0].storymaps
              // 测试的时候返回的是数组，运行时改成注释的样子
              this.storymaps = res.data.storymaps
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
    },

    deleteStoryMap () {
      API.deleteStoryMap(this.storymapEdit.id)
        .then(res => {
          let status = res.status
          if (status === 200) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          } else {
            this.$message({
              type: 'warning',
              message: '删除故事地图失败，请稍后重试'
            })
          }
        })
        .catch(() => {
          this.$message({
            type: 'warning',
            message: '删除故事地图失败，请稍后重试'
          })
        })
    },

    handleDeleteStoryMap () {
      this.$confirm('此操作将永久删除该故事地图, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        API.deleteStoryMap()
      }).catch(() => {

      })
    },

    updateStoryMap () {
      if (this.storymapEdit.title.trim().length === 0 ||
        this.storymapEdit.title.description().length === 0) {
        this.$message({
          type: 'warning',
          message: '内容不得为空'
        })
        return
      }
      API.updateStoryMap(this.storymapEdit.id, this.storymapEdit)
        .then(res => {
          let status = res.status
          if (status === 200) {
            this.initStoryMap()
            this.$message.success('更改成功')
            this.editStoryMapVisible = false
          } else {
            this.$message({
              type: 'warning',
              message: '更新故事地图失败，请稍后重试'
            })
          }
        })
        .catch(() => {
          this.$message({
            type: 'warning',
            message: '更新故事地图失败，请稍后重试'
          })
        })
    },

    handleEditStoryMap (storymapId) {
      this.editStoryMapVisible = true
      for (let i = 0; i < this.storymaps.length; i++) {
        if (this.storymaps[i].id === storymapId) {
          this.storymapEdit.id = this.storymaps[i].id
          this.storymapEdit.title = this.storymaps[i].title
          this.storymapEdit.description = this.storymaps[i].description
          break
        }
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

  .input-with-select {
    width: 500px;
  }

  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }

  .search {
    margin-bottom: 50px;
  }

  .operation {
    margin-top: -65px;
    margin-bottom: 50px;
    min-height: 20px;
  }

  .storymap-icon {
    float: right;
    margin: 0 10px 0 5px;
    font-size: 14px;
  }
</style>
