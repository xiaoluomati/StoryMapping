<template>
  <el-row>
    <el-col :span="6" v-for="storymap in storymaps" :key="storymap.storyId" :offset=1>
      <el-card :body-style="{ padding: '0px' }" class="storymap" shadow="never"
               @click.native="jumpTo('storymap', storymap.storyId)">
        <div style="padding: 10px; height: 100%">
          <h1>{{ storymap.storyName }}</h1>
          <p>
            {{ storymap.storyDescription }}
          </p>
        </div>
      </el-card>
      <div class="operation">
        <el-button icon="el-icon-delete" circle class="storymap-icon"
                   @click="handleDeleteStoryMap(storymap.storyId)"></el-button>
        <el-button icon="el-icon-edit" circle class="storymap-icon"
                   @click="handleEditStoryMap(storymap.storyId)"></el-button>
        <el-button icon="el-icon-circle-plus-outline" circle class="storymap-icon"
                   @click="handleCollaborator(storymap.storyId)"></el-button>
      </div>
    </el-col>

    <el-dialog title="用户故事地图编辑" :visible.sync="editStoryMapVisible" width="30%">
      <el-form :label-position="'top'" label-width="100px" :model="storymapEdit">
        <el-form-item label="地图名称">
          <el-input v-model="storymapEdit.storyName"></el-input>
        </el-form-item>
        <el-form-item label="地图描述">
          <el-input type="textarea" v-model="storymapEdit.storyDescription"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click.native="editStoryMapVisible = false">取消</el-button>
          <el-button type="primary" @click.native="updateStoryMap">保存</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="添加协作者" :visible.sync="addCollaboratorVisible" width="30%">
      <el-form :label-position="'top'" label-width="100px" :model="collaborator">
        <el-form-item label="用户名称">
          <el-input v-model="collaborator.name"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click.native="addCollaboratorVisible = false">取消</el-button>
          <el-button type="primary" @click.native="addCollaborator">保存</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </el-row>
</template>

<script>
import API from '@/api/api_storymap'
import API_U from '@/api/api_user'
import tool from '@/util/tool'

export default {
  name: 'storymap-manager',
  data () {
    return {
      storymaps: '',
      storymapEdit: {
        storyId: '',
        storyName: '',
        storyDescription: '',
        release: 0,
        userId: '',
        editor: '', // 编辑者
      },
      editStoryMapVisible: false,
      addCollaboratorVisible: false,
      collaborator: {
        name: '',
        storyId: ''
      }
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
              // console.log(res.data)
              this.storymaps = res.data
            } else {
              this.$message.error('读取数据出错，请刷新页面')
            }
          })
          .catch(() => {
            this.$message.error('读取数据出错，请刷新页面')
          })
      }
    },

    deleteStoryMap () {
      // console.log(this.storymapEdit)
      API.deleteStoryMap(this.storymapEdit)
        .then(res => {
          let status = res.status
          if (status === 200) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.initStoryMap()
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

    handleDeleteStoryMap (storymapId) {
      this.updateStoryMapEdit(storymapId)
      this.$confirm('此操作将永久删除该故事地图, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteStoryMap()
      }).catch(() => {

      })
    },

    updateStoryMap () {
      if (this.storymapEdit.storyName.trim().length === 0 ||
        this.storymapEdit.storyDescription.trim().length === 0) {
        this.$message({
          type: 'warning',
          message: '内容不得为空'
        })
        return
      }
      API.updateStoryMap(this.storymapEdit)
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
      this.updateStoryMapEdit(storymapId)
    },

    updateStoryMapEdit (storymapId) {
      for (let i = 0; i < this.storymaps.length; i++) {
        if (this.storymaps[i].storyId === storymapId) {
          this.storymapEdit.storyId = this.storymaps[i].storyId
          this.storymapEdit.storyName = this.storymaps[i].storyName
          this.storymapEdit.storyDescription = this.storymaps[i].storyDescription
          this.storymapEdit.userId = this.storymaps[i].userId
          this.storymapEdit.release = this.storymaps[i].release
          this.storymapEdit.editor = tool.getUserId()
          break
        }
      }
    },

    handleCollaborator (storyId) {
      this.addCollaboratorVisible = true
      this.collaborator.name = ''
      this.collaborator.storyId = storyId
    },

    addCollaborator () {
      let collaborators = []
      API_U.listUser()
        .then(res => {
          let status = res.status
          if (status === 200) {
            collaborators = res.data

            if (this.collaborator.name.trim().length > 0) {
              for (let i = 0; i < collaborators.length; i++) {
                let c = collaborators[i]
                if (c.name === this.collaborator.name && c.id !== tool.getUserId()) {
                  API.addCollaborators(c.id, this.collaborator.storyId)
                  this.$message.success('更改成功')
                  this.addCollaboratorVisible = false
                  return
                }
              }
              this.$message.error('没有找到用户')
            }
          }
        })
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
