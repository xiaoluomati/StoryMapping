<template>
  <div>
    <div v-for="h in map_height" :key="h" :style="{'width': map_width * card_width + 'px', 'height': card_width + 'px' }">
      <div v-for= "w in map_width" :key="w" style="float: left">
        <el-card class="box-card" shadow="hover" v-if="getCard(h,w) != null" v-bind:class="{ highlightcard : isHighlight(getCard(h,w).cardId)}">
          <div slot="header" class="clearfix">
            <span>{{getCard(h,w).title}}</span>
            <span style="float: right" v-if="h > 2">{{getCard(h,w).state}}</span>
            <span style="float: right" v-if="h === 1">
              <el-button icon="el-icon-myicon-user" size="mini" @click="initCardRoles(h,w)" circle></el-button>
            </span>
          </div>
          <div class="text item">{{getCard(h,w).content}}</div>
          <div style="text-align:right">
            <el-button icon="el-icon-delete" @click="deleteCard(h, w)" size="mini" circle></el-button>
          </div>
          <div style="text-align:right">
            <el-button icon="el-icon-plus" @click="addCard(h, w)" size="mini" circle></el-button>
          </div>
          <div style="text-align:right">
            <el-button icon="el-icon-edit" @click="editCard(h, w)" size="mini" circle></el-button>
          </div>
         </el-card>
        <el-card class="box-card" shadow="hover" v-if="getCard(h,w) == null">
          <div class="text item"></div>
        </el-card>
      </div>
    </div>
    <!--<el-button icon="el-icon-delete" @click="test()" size="mini" circle></el-button>-->
    <!--<div style="width: 200px" id="test">-->
      <!--test-->
    <!--</div>-->
    <el-dialog title="修改角色" :visible.sync="dialogRoleVisible" @close="editCardRoles()">
      <span><el-transfer v-model="allRoles" :titles="['所有角色', '相关角色']" :data="addedRoleIds"></el-transfer></span>
    </el-dialog>
    <el-dialog title="删除确认" :visible.sync="dialogVisible" width="30%">
      <span>这会删除所有子卡片，继续吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmDelete()">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="新建卡片" :visible.sync="dialogFormVisible">
      <el-form :model="addform">
        <el-form-item label="卡片名称" :label-width="formLabelWidth">
          <el-input v-model="addform.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="卡片描述" :label-width="formLabelWidth">
          <el-input v-model="addform.descr" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="卡片状态" :label-width="formLabelWidth">
          <el-select v-model="addform.cardstate" placeholder="请选择卡片状态">
            <el-option label="1" value="1"></el-option>
            <el-option label="2" value="2"></el-option>
            <el-option label="3" value="3"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmAdd()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="编辑卡片" :visible.sync="dialogEditFormVisible" @close="confirmEdit()">
      <el-form :model="editform">
        <el-form-item label="卡片名称" :label-width="formLabelWidth">
          <el-input v-model="editform.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="卡片描述" :label-width="formLabelWidth">
          <el-input v-model="editform.descr" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="卡片状态" :label-width="formLabelWidth">
          <el-select v-model="editform.cardstate" placeholder="请选择卡片状态">
            <el-option label="1" value="1"></el-option>
            <el-option label="2" value="2"></el-option>
            <el-option label="3" value="3"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogEditFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmEdit()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import API from '@/api/api_cards'
import API_R from '@/api/api_roles'
import { eventBus } from '../main'
export default {
  data () {
    return {
      dialogRoleVisible: false,
      srcAddedRoleIds: [],
      addedRoleIds: [],
      allRoles: [],
      mapRoles: [],
      currentCardId: '',
      currentCardRelations: '',
      dialogVisible: false,
      deleteCardLoc: {
        x: '',
        y: ''
      },
      dialogEditFormVisible: false,
      editform: {
        x: '',
        y: '',
        name: '',
        descr: '',
        cardstate: ''
      },
      dialogFormVisible: false,
      addform: {
        x: '',
        y: '',
        name: '',
        descr: '',
        cardstate: ''
      },
      formLabelWidth: '120px',
      card_width: 200,
      map_width: '',
      map_height: '',
      cards: [

      ],
      searchWord: ''
    }
  },
  methods: {
    initCardRoles (x, y) {
      let card = this.getCard(x, y)
      this.currentCardId = card.cardId
      for (let item in this.mapRoles) {
        this.allRoles.push({
          key: `${this.mapRoles[item].roleId}`,
          value: `${this.getRole(this.mapRoles[item].roleId).roleName}`
        })
      }
      API_R.getRelationList(card.cardId).then(
        res => {
          let status = res.status
          if (status !== 200) {
            this.$notify.error('获取关系列表失败')
          }
          let relations = res.data
          for (let item in relations) {
            this.addedRoleIds.push(`${relations[item].roleId}`)
            this.srcAddedRoleIds.push(`${relations[item].roleId}`)
          }
          this.currentCardRelations = relations
        })
      this.dialogRoleVisible = true
    },
    getRole (roleId) {
      for (let item in this.mapRoles) {
        if (this.mapRoles[item].roleId === roleId) {
          return this.mapRoles[item]
        }
      }
      return null
    },
    editCardRoles () {
      for (let item in this.srcAddedRoleIds) {
        let deletedId = this.srcAddedRoleIds[item]
        if (this.addedRoleIds.indexOf(deletedId) === -1) {
          let relationId = ''
          for (let j in this.currentCardRelations) {
            if (this.currentCardRelations[j].roleId === deletedId) {
              relationId = deletedId
            }
          }
          if (relationId !== '') {
            API_R.deleteRelation(relationId)
          }
        }
      }
      for (let item in this.addedRoleIds) {
        let addId = this.addedRoleIds[item]
        if (this.srcAddedRoleIds.indexOf(addId) === -1) {
          API_R.addRelation({
            'cardId': this.currentCardId,
            'roleId': addId
          })
        }
      }
    },
    getCard (x, y) {
      for (let item in this.cards) {
        if (this.cards[item].positionX === x && this.cards[item].positionY === y) {
          return this.cards[item]
        }
      }
      return null
    },
    initStoryMap () {
      API.getCardList(this.$route.params.storymapid).then(res => {
        let status = res.status
        if (status !== 200) {
          this.$notify.error('获取卡片列表失败')
        }
        let map = res.data
        if (map) {
          this.map_width = map.mapWide
          this.map_height = map.mapLong
          this.cards = map.cardVos
        }
      })
      API_R.getStoryRoleList(this.$route.params.storymapid).then(res => {
        let status = res.status
        if (status !== 200) {
          this.$notify.error('获取角色列表失败')
        }
        let roles = res.data
        if (roles) {
          this.mapRoles = roles
        }
      })
    },
    addCard (x, y) {
      this.dialogFormVisible = true
      if (x === 1 || x === 2) {
        let i = y + 1
        while (i < this.map_width && this.getCard(x, i) === null) {
          i++
        }
        this.addform.x = x
        this.addform.y = i
      } else {
        this.addform.x = x + 1
        this.addform.y = y
      }
      console.log('x=' + this.addform.x)
      console.log('y=' + this.addform.y)
    },
    confirmAdd () {
      this.dialogFormVisible = false
      API.addCard({ 'storyId': this.$route.params.storymapid,
        'title': this.addform.name,
        'content': this.addform.descr,
        'state': this.addform.cardstate,
        'cost': 0,
        'positionX': this.addform.x,
        'positionY': this.addform.y
      }).then(res => {
        let status = res.status
        if (status === 200) {
          this.$message.success('添加卡片成功')
        } else {
          this.$message.error('添加卡片失败')
        }
      })
      this.initStoryMap()
      this.addform.name = ''
      this.addform.cardstate = ''
      this.addform.descr = ''
    },
    deleteCard (x, y) {
      this.dialogVisible = true
      this.deleteCardLoc.x = x
      this.deleteCardLoc.y = y
    },
    confirmDelete () {
      this.dialogVisible = false
      API.deleteCard({ 'positionX': this.deleteCardLoc.x,
        'positionY': this.deleteCardLoc.y,
        'storyId': this.$route.params.storymapid
      }).then(res => {
        let status = res.status
        if (status === 200) {
          this.$message.success('删除卡片成功')
        } else {
          this.$message.error('删除卡片失败')
        }
      })
      this.initStoryMap()
      this.deleteCardLoc.x = ''
      this.deleteCardLoc.y = ''
    },
    editCard (x, y) {
      this.dialogEditFormVisible = true
      this.editform.x = x
      this.editform.y = y
      let card = this.getCard(x, y)
      this.editform.name = card.title
      this.editform.descr = card.content
      this.editform.cardstate = card.state
    },
    confirmEdit () {
      this.dialogEditFormVisible = false
      API.updateCard({ 'storyId': this.$route.params.storymapid,
        'title': this.editform.name,
        'content': this.editform.descr,
        'state': this.editform.cardstate,
        'cost': 0,
        'positionX': this.editform.x,
        'positionY': this.editform.y
      }).then(res => {
        let status = res.status
        if (status === 200) {
          this.$message.success('修改卡片成功')
        } else {
          this.$message.error('修改卡片失败')
        }
      })
      this.initStoryMap()
      console.log(this.editform.x)
    }
  },
  computed: {
    isHighlight: function () {
      return function (cardId) {
        if (this.searchWord === '') {
          return false
        }
        for (let item in this.cards) {
          if (this.cards[item].cardId === cardId && (this.cards[item].title.indexOf(this.searchWord) !== -1 || this.cards[item].content.indexOf(this.searchWord) !== -1)) {
            return true
          }
        }
        return false
      }
    }
  },
  mounted () {
    this.initStoryMap()
  },
  created () {
    eventBus.$on('searchWords', (message) => {
      this.searchWord = message
    })
  }
}
</script>
<style scoped>
  .box-card {
    width: 180px;
    height: 180px;
    margin: 10px;
  }
  .highlightcard {
    -webkit-box-shadow: 0 0 5px rgba(0,113,241,1)
  }
</style>
