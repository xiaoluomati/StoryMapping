<template>
  <el-row>
    <el-col :span="24" class="topbar-wrap">
      <div class="topbar-logo topbar-btn">
        <!--<a href="/"><img src="../assets/logo.png" style="padding-left:8px;"></a>-->
        <!--<a href="/"><img src="../assets/images.jpg" style="padding-left:8px;"></a>-->
        <i class="el-icon-success"></i>
      </div>
      <div class="topbar-logos">
        <a href="/" style="color: #f9f9f9;">story-map</a>
      </div>
      <div class="operation-buttons">
        <el-popover placement="bottom" width="350" trigger="click">
          <el-tabs type="border-card">
            <el-tab-pane label="角色管理">
              <el-collapse v-model="activeNames">
                <el-collapse-item v-for="role in roles" :key="role.roleId" :title="role.roleName" :name="role.roleId">
                  <div>
                    {{role.roleDetail}}
                    <el-button icon="el-icon-edit" style="float: right" @click="editRole(role.roleId)" size="mini" circle></el-button>
                    <el-button icon="el-icon-delete" style="float: right" @click="deleteRole(role.roleId)" size="mini" circle></el-button>
                  </div>
                </el-collapse-item>
              </el-collapse>
            </el-tab-pane>
            <el-button style="margin-top: 10px" icon="el-icon-plus" @click="addRole()" circle></el-button>
          </el-tabs>
          <el-button slot="reference" icon="el-icon-myicon-user" circle></el-button>
        </el-popover>
        <el-popover placement="bottom" trigger="click" @hide="messageCards('')">
          <el-form :inline="true" :model="formInline" class="demo-form-inline" style="display: flex">
            <el-form-item >
              <el-input v-model="formInline.searchWords"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="messageCards(formInline.searchWords)">搜索</el-button>
            </el-form-item>
          </el-form>
          <el-button slot="reference" icon="el-icon-search" circle></el-button>
        </el-popover>
        <el-popover placement="bottom" trigger="click">
          <el-radio-group v-model="radio" style="float: left">
            <el-radio label="excel">导出为XLSX格式</el-radio>
            <el-radio label="png" disabled>导出为PNG格式</el-radio>

          </el-radio-group>
          <el-button type="primary" @click="downloadFile()">下载</el-button>
          <el-button slot="reference" icon="el-icon-download"  circle></el-button>
        </el-popover>
        <el-button icon="el-icon-back" circle></el-button>
      </div>
    </el-col>
    <el-dialog title="新建角色" :visible.sync="dialogFormVisible">
      <el-form :model="addform">
        <el-form-item label="角色名称" :label-width="formLabelWidth">
          <el-input v-model="addform.roleName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" :label-width="formLabelWidth">
          <el-input v-model="addform.roleDetail" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmAdd()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="删除确认" :visible.sync="dialogVisible" width="30%">
      <span>删除该角色？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmDelete()">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="编辑角色" :visible.sync="dialogEditFormVisible">
      <el-form :model="editform">
        <el-form-item label="角色名称" :label-width="formLabelWidth">
          <el-input v-model="editform.roleName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" :label-width="formLabelWidth">
          <el-input v-model="editform.roleDetail" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogEditFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmEdit()">确 定</el-button>
      </div>
    </el-dialog>
  </el-row>
</template>
<script>
import API from '@/api/api_storymap'
import { eventBus } from '../main'
export default {
  data () {
    return {
      formLabelWidth: '120px',
      activeName: 'first',
      activeNames: ['1'],
      roles: [],
      dialogEditFormVisible: false,
      editform: {
        roleId: '',
        roleName: '',
        roleDetail: ''
      },
      dialogVisible: false,
      deleteRoleId: '',
      dialogFormVisible: false,
      addform: {
        roleName: '',
        roleDetail: ''
      },
      formInline: {
        searchWords: ''
      },
      radio: 'excel'
    }
  },
  methods: {
    downloadFile(){
      // TODO API操作
    },
    testMapId () {
      console.log(this.$route.params.storymapid)
    },
    messageCards (words) {
      eventBus.$emit('searchWords', words)
    },
    initStoryMapRoles () {
      API.getStoryMapRoles(this.$route.params.storymapid).then(res => {
        let status = res.status
        if (status !== 200) {
          this.$notify.error('获取角色列表失败')
        }
        let rolelist = res.data
        if (rolelist) {
          this.roles = rolelist.roles
        }
      })
    },
    getRole (roleId) {
      for (let item in this.roles) {
        if (this.roles[item].roleId === roleId) {
          return this.roles[item]
        }
      }
      return null
    },
    addRole () {
      this.dialogFormVisible = true
    },
    confirmAdd () {
      this.dialogFormVisible = false
      console.log('roleName=' + this.addform.roleName)
      console.log('roleDetail=' + this.addform.roleDetail)
      // TODO API操作
      this.addform.roleName = ''
      this.addform.roleDetail = ''
    },
    editRole (roleId) {
      this.dialogEditFormVisible = true
      let role = this.getRole(roleId)
      this.editform.roleId = role.roleId
      this.editform.roleName = role.roleName
      this.editform.roleDetail = role.roleDetail
    },
    confirmEdit () {
      this.dialogEditFormVisible = false
      // TODO API操作
      console.log(this.editform.roleId)
    },
    deleteRole (roleId) {
      this.dialogVisible = true
      this.deleteRoleId = roleId
    },
    confirmDelete () {
      this.dialogVisible = false
      // TODO API操作
    }
  },
  mounted () {
    this.initStoryMapRoles()
  }
}
</script>
<style scoped>
  .topbar-wrap {
    height: 50px;
    line-height: 50px;
    background: #026AA7;
    padding: 0;
  }

  .topbar-wrap .topbar-btn {
    color: #1e1e1e;
  }

  .topbar-logo i {
    font-size: 30px;
    vertical-align: middle;
    color: #f9f9f9;
    margin-left: 10px;
  }
  .topbar-wrap .topbar-logo {
    float: left;
    width: 60px;
    /*line-height: 26px;*/
  }

  .topbar-wrap .topbar-logos {
    float: left;
    width: 200px;
    line-height: 48px;
    font-size: 24px;
    font-style: italic;
    font-weight: 600;
  }

  .topbar-wrap .topbar-logo img, .topbar-wrap .topbar-logos img {
    height: 30px;
    margin-top: 12px;
    margin-left: 2px;
  }

  .topbar-wrap .topbar-title {
    float: left;
    text-align: left;
    padding-left: 10px;
    border-left: 1px solid #000;
  }

  .topbar-wrap .topbar-timer span {
    display: inline-block;
    vertical-align: middle;
  }
  .topbar-wrap .operation-buttons {
    float: right;
    padding-right: 12px;
  }

  .topbar-wrap .operation-buttons .el-button {
    margin-left: 5px;
    margin-right: 5px;
  }
</style>
