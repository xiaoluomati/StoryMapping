<template>
  <div>
    <div v-for="h in map_height" :key="h" :style="{'width': map_width * card_width + 'px', 'height': card_width + 'px' }">
      <div v-for= "w in map_width" :key="w" style="float: left">
        <el-card class="box-card" shadow="hover" v-if="getCard(h,w) != null">
          <div slot="header" class="clearfix">
            <span>{{getCard(h,w).title}}</span>
            <span style="float: right">{{getCard(h,w).state}}</span>
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
import API from '@/api/api_storymap'
export default {
  data () {
    return {
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

      ]

    }
  },
  methods: {
    getCard (x, y) {
      for (let item in this.cards) {
        if (this.cards[item].positionX === x && this.cards[item].positionY === y) {
          return this.cards[item]
        }
      }
      return null
    },
    initStoryMap () {
      API.getStoryMap(this.$route.params.storymapid).then(res => {
        let map = res
        if (map) {
          this.map_width = map.mapWide
          this.map_height = map.mapLong
          this.cards = map.cardVos
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
      // TODO API操作
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
      // TODO API操作
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
      // TODO API操作
      console.log(this.editform.x)
    }
  },
  mounted () {
    this.initStoryMap()
  }
}
</script>
<style scoped>
  .box-card {
    width: 180px;
    height: 180px;
    margin: 10px;
  }
</style>
