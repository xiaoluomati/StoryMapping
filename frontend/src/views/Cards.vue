<template>
  <div>
    <div v-for="h in map_height" :key="h" :style="{'width': map_width * card_width + 'px', 'height': card_width + 'px' }">
      <div v-for= "w in map_width" :key="w" style="float: left">
        <el-card class="box-card" shadow="hover" v-show="getCards(h,w) != null">
          <div slot="header" class="clearfix">
            <span>{{h}},{{w}}</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <div class="text item">sss</div>
        </el-card>
        <el-card class="box-card" shadow="hover" v-show="getCards(h,w) == null">
          <div class="text item">
            <el-button style="float: left; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
        </el-card></div>
    </div>
    <!--<div v-for="item in activities" style="clear: left">-->
      <!--<div v-for="task in item.tasks">-->
        <!--<el-card class="box-card">-->
          <!--<div slot="header" class="clearfix">-->
            <!--<span>{{ task.title }}</span>-->
            <!--<el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>-->
          <!--</div>-->
          <!--<div class="text item">{{ task.content }}</div>-->
        <!--</el-card>-->
      <!--</div>-->
    <!--</div>-->
  </div>
</template>
<script>
import API from '@/api/api_storymap'
export default {
  data () {
    return {
      card_width: 200,
      map_width: '',
      map_height: '',
      cards: [

      ]
      // cards: [
      //   {
      //     x: 0,
      //     y: 0,
      //     title: 'a1',
      //     content: 'b1',
      //     offset: 0,
      //     tasks: [
      //       {
      //         x: 1,
      //         y: 0,
      //         title: 'a11',
      //         content: 'b11'
      //       },
      //       {
      //         x: 1,
      //         y: 1,
      //         title: 'a12',
      //         content: 'b12'
      //       }
      //     ]
      //   },
      //   {
      //     x: 2,
      //     y: 0,
      //     title: 'a2',
      //     content: 'b2',
      //     offset: 1
      //   }
      // ]
    }
  },
  methods: {
    getCards (x, y) {
      if (parseInt(x) === 1 && (parseInt(y) === 1 || parseInt(y) === 2)) {
        return null
      }
      return 0
    },

    initStoryMap () {
      console.log(this.$route.params.storymapid)
      API.getStoryMap(this.$route.params.storymapid).then(res => {
        let map = res
        if (map) {
          this.map_width = map.map_width
          this.map_height = map.map_height
          this.cards = map.cards
        }
      })
    }
  },
  mounted () {
    this.initStoryMap()
  }
}
</script>
<style>
  .box-card {
    width: 180px;
    height: 180px;
    margin: 10px;
  }
</style>
