<template>
  <div id="root">
    <div class="root">
    </div>
    <div id="pic" ref="pic">
      <div style="text-align:left">
        <el-button icon="el-icon-plus" @click="addCard(1, 1)" v-if="cards.length === 0" circle></el-button>
      </div>

      <div v-for="h in map_height" :key="h" :style="{'width': map_width * card_width + 'px', 'height': card_width + 'px' }">
        <draggable v-model="card_table[h]" @end="dropCard">
          <div v-for="(item, index) in card_table[h]" :key="index" style="float: left">
            <el-card class="box-card" shadow="hover" v-if="item != null" v-bind:class="{ highlightcard : isHighlight(item.cardId), 'card-activity' : h === 1, 'card-task' : h === 2, 'card-story' : h >= 3 }">
              <div slot="header" class="clearfix">
                <span>{{item.title}}</span>
                <!--<span style="float: right" v-if="h > 2">{{getCard(h,w).state}}</span>-->
                <span style="float: right" v-if="h === 1">
                  <el-button icon="el-icon-myicon-user" size="mini" @click="initCardRoles(h, index+1)" circle></el-button>
                </span>
              </div>
              <div class="text item">{{item.content}}</div>
            </el-card>
            <div class="operation-btns" v-if="item != null" >
              <el-button class="card-button" icon="el-icon-delete" @click="deleteCard(h, index+1)" size="mini" circle></el-button>
              <el-button class="card-button" icon="el-icon-arrow-right" @click="addCard(h, index+1)" v-if="h <= 2" size="mini" circle></el-button>
              <el-button class="card-button" icon="el-icon-edit" @click="editCard(h, index+1)" size="mini" circle></el-button>
              <el-button class="card-button" icon="el-icon-arrow-down" @click="addChildCard(h, index+1)" size="mini" circle></el-button>
            </div>
            <el-card class="box-card" shadow="hover" v-if="item == null">
              <div class="text item"></div>
            </el-card>
          </div>
        </draggable>
      </div>
      <!--<div v-for="h in map_height" :key="h" :style="{'width': map_width * card_width + 'px', 'height': card_width + 'px' }">-->
        <!--<draggable :list="cards" @end="dropCard">-->
          <!--<div v-for= "w in map_width" :key="w" style="float: left">-->
            <!--<div>-->
              <!--<el-card class="box-card" shadow="hover" v-if="getCard(h,w) != null" v-bind:class="{ highlightcard : isHighlight(getCard(h,w).cardId), 'card-activity' : h === 1, 'card-task' : h === 2, 'card-story' : h >= 3 }">-->
              <!--<div slot="header" class="clearfix">-->
                <!--<span>{{getCard(h,w).title}}</span>-->
                <!--&lt;!&ndash;<span style="float: right" v-if="h > 2">{{getCard(h,w).state}}</span>&ndash;&gt;-->
                <!--<span style="float: right" v-if="h === 1">-->
                  <!--<el-button icon="el-icon-myicon-user" size="mini" @click="initCardRoles(h,w)" circle></el-button>-->
                <!--</span>-->
              <!--</div>-->
              <!--<div class="text item">{{getCard(h,w).content}}</div>-->
            <!--</el-card>-->
              <!--<div class="operation-btns" v-if="getCard(h,w) != null" >-->
              <!--<el-button class="card-button" icon="el-icon-delete" @click="deleteCard(h, w)" size="mini" circle></el-button>-->
              <!--<el-button class="card-button" icon="el-icon-arrow-right" @click="addCard(h, w)" v-if="h <= 2" size="mini" circle></el-button>-->
              <!--<el-button class="card-button" icon="el-icon-edit" @click="editCard(h, w)" size="mini" circle></el-button>-->
              <!--<el-button class="card-button" icon="el-icon-arrow-down" @click="addChildCard(h, w)" size="mini" circle></el-button>-->
            <!--</div>-->
              <!--<el-card class="box-card" shadow="hover" v-if="getCard(h,w) == null">-->
              <!--<div class="text item"></div>-->
              <!--</el-card>-->
            <!--</div>-->
            <!--&lt;!&ndash;{{getCard(h,w).content}}&ndash;&gt;-->
          <!--</div>-->
        <!--</draggable>-->
      <!--</div>-->
    </div>
    <!--<el-button icon="el-icon-delete" @click="test()" size="mini" circle></el-button>-->
    <!--<div style="width: 200px" id="test">-->
    <!--test-->
    <!--</div>-->
    <el-dialog title="修改角色" :visible.sync="dialogRoleVisible" @close="editCardRoles()">
      <span><el-transfer v-model="addedRoleIds" :titles="['所有角色', '相关角色']" :data="allRoles"></el-transfer></span>
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
        <!--<el-form-item label="卡片状态" :label-width="formLabelWidth">-->
          <!--<el-select v-model="addform.cardstate" placeholder="请选择卡片状态">-->
            <!--<el-option label="1" value="1"></el-option>-->
            <!--<el-option label="2" value="2"></el-option>-->
            <!--<el-option label="3" value="3"></el-option>-->
          <!--</el-select>-->
        <!--</el-form-item>-->
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
        <!--<el-form-item label="卡片状态" :label-width="formLabelWidth">-->
          <!--<el-select v-model="editform.cardstate" placeholder="请选择卡片状态">-->
            <!--<el-option label="1" value="1"></el-option>-->
            <!--<el-option label="2" value="2"></el-option>-->
            <!--<el-option label="3" value="3"></el-option>-->
          <!--</el-select>-->
        <!--</el-form-item>-->
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
import html2canvas from 'html2canvas'
import draggable from 'vuedraggable'

export default {
    components: {
      draggable,
    },
  data () {
    return {
      fromWhere: '',
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
        // cardstate: '',
        cardId: ''
      },
      dialogFormVisible: false,
      addform: {
        x: '',
        y: '',
        name: '',
        descr: ''
        // cardstate: ''
      },
      formLabelWidth: '120px',
      card_width: 200,
      map_width: '',
      map_height: '',
      cards: [

      ],
      searchWord: '',
      card_table: [
      ],
      tasks: [
        {
          card: "",
          activities: [
            {
              card: "",
              stories: [
                {
                  card: ""
                }
              ]
            }
          ]
        }
      ]
    }
  },
  methods: {
    getImage () {
      setTimeout(function () {
        html2canvas(document.getElementById('pic'), {
        }).then((canvas) => {
          let dataURL = canvas.toDataURL('image/png')
          this.dataURL = dataURL
          console.log(dataURL)
          let a = document.createElement('a')
          let event = new MouseEvent('click')
          a.download = name || 'photo'
          a.href = dataURL
          a.dispatchEvent(event)
        })
      }, 1000)
    },
    initCardRoles (x, y) {
      let card = this.getCard(x, y)
      this.allRoles = []
      this.addedRoleIds = []
      this.srcAddedRoleIds = []
      this.currentCardId = card.cardId
      for (let item in this.mapRoles) {
        this.allRoles.push({
          key: `${this.mapRoles[item].roleId}`,
          label: `${this.getRole(this.mapRoles[item].roleId).roleName}`
        })
      }
      console.log(this.allRoles)
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
      console.log('srcAddedRoleIds:' + this.srcAddedRoleIds)
      for (let item in this.srcAddedRoleIds) {
        let deletedId = this.srcAddedRoleIds[item]
        console.log('deletedId:' + deletedId)
        console.log(this.addedRoleIds.indexOf(deletedId))
        if (this.addedRoleIds.indexOf(deletedId) === -1) {
          let relationId = ''
          for (let j in this.currentCardRelations) {
            if (parseInt(this.currentCardRelations[j].roleId) === parseInt(deletedId)) {
              relationId = this.currentCardRelations[j].relationId
            }
            console.log('currentCardRelations:' + this.currentCardRelations[j].roleId)
          }
          console.log('relationId:' + relationId)
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
        if (this.isXYCard(x, y, item)) {
          return this.cards[item]
        }
      }
      return null
    },
    isXYCard(x, y, item) {
      return this.cards[item].positionX === x && this.cards[item].positionY === y
    },
    initStoryMap () {
      API.getCardList(this.$route.params.storymapid).then(res => {
        let status = res.status
        if (status !== 200) {
          this.$notify.error('获取卡片列表失败')
        }
        let map = res.data
        if (map) {
          this.map_width = map.mapLong
          this.map_height = map.mapWide
          this.cards = map.cardVos
          // console.log(this.cards)
          this.initCardTable()
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
    addChildCard (x, y) {
      this.dialogFormVisible = true
      this.fromWhere = 'down'
      this.addform.x = x + 1
      this.addform.y = y
      console.log('x=' + this.addform.x)
      console.log('y=' + this.addform.y)
    },
    addCard (x, y) {
      this.dialogFormVisible = true
      this.fromWhere = 'right'
      if (x === 1 && y === 1 && this.getCard(1, 1) === null) {
        this.addform.x = x
        this.addform.y = y
      } else if (x === 1) {
        let i = y + 1
        while (i <= this.map_width && this.getCard(x, i) === null) {
          i++
        }
        this.addform.x = x
        this.addform.y = i
      } else if (x === 2) {
        this.addform.x = x
        this.addform.y = y + 1
      }
      console.log('x=' + this.addform.x)
      console.log('y=' + this.addform.y)
    },
    confirmAdd () {
      this.dialogFormVisible = false
      API.addCard(this.fromWhere,
        { 'storyId': this.$route.params.storymapid,
          'title': this.addform.name,
          'content': this.addform.descr,
          'state': 'DOING',
          'cost': 0,
          'positionX': this.addform.x,
          'positionY': this.addform.y
        }).then(res => {
        let status = res.status
        if (status === 200) {
          this.$message.success('添加卡片成功')
          this.initStoryMap()
        } else {
          this.$message.error('添加卡片失败')
        }
      })

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
          this.initStoryMap()
        } else {
          this.$message.error('删除卡片失败')
        }
      })
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
      this.editform.cardId = card.cardId
    },
    confirmEdit () {
      this.dialogEditFormVisible = false
      API.updateCard({ 'storyId': this.$route.params.storymapid,
        'title': this.editform.name,
        'content': this.editform.descr,
        'state': this.editform.cardstate,
        'cost': 0,
        'positionX': this.editform.x,
        'positionY': this.editform.y,
        'cardId': this.editform.cardId
      }).then(res => {
        let status = res.status
        if (status === 200) {
          this.$message.success('修改卡片成功')
          this.initStoryMap()
        } else {
          this.$message.error('修改卡片失败')
        }
      })
      console.log(this.editform.x)
    },
    dropCard (evt) {
      let allCards = []
      for (let i = 1; i < this.map_height + 1; i++) {
        for (let j = 0; j < this.map_width; j++) {
          let newCard = this.card_table[i][j]
          if (newCard != null) {
            if (newCard.positionY !== j + 1) {
              newCard.positionX = i
              newCard.positionY = j + 1
            }
            allCards.push(newCard)
          }
        }
      }
      // console.log(allCards)
      API.updateAllCards(allCards)
    },

    initCardTable () {
      this.card_table = []
      for (let i = 0; i < this.map_height + 1; i++) {
        this.card_table[i] = new Array(this.map_width)
      }

      for (let i = 0; i < this.map_height + 1; i++) {
        for (let j = 0; j < this.map_width; j++) {
          this.card_table[i][j] = null
        }
      }

      for (let item in this.cards) {
        let card = this.cards[item]
        let x = card.positionX
        let y = card.positionY
        this.card_table[x][y - 1] = card
      }
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
    eventBus.$on('searchWords', (message) => {
      this.searchWord = message
    })
    eventBus.$on('updateRole', (message) => {
      this.initStoryMap()
    })
    eventBus.$on('getImage', (message) => {
      this.getImage()
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
    -webkit-box-shadow: 0 0px 15px rgb(30, 30, 30)
  }
  .operation-btns {
    margin: -45px 20px 0 0;
  }
  .card-button {
    float: right;
    margin: 0 0 0 3px !important;
  }
  .card-activity {
    background: #AED9E9;
  }
  .card-task {
    background: #F4E459;
  }
  .card-story {
    background: #C2EE87;
  }
</style>
