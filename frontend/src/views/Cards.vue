<template>
  <div>
    <div v-for="item in activities" >
      <el-card class="box-card" :style="{'margin-left':  item.offset * 180 + 'px'}">
        <div slot="header" class="clearfix">
          <span>{{ item.title }}</span>
          <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
        </div>
        <div class="text item">{{ item.content }}</div>
      </el-card>
    </div>
    <div v-for="item in activities" style="clear: left">
      <div v-for="task in item.tasks">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>{{ task.title }}</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <div class="text item">{{ task.content }}</div>
        </el-card>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      activities: [
        {
          x: 0,
          y: 0,
          title: "a1",
          content: "b1",
          offset: 0,
          tasks: [
            {
              x: 1,
              y: 0,
              title: "a11",
              content: "b11"
            },
            {
              x: 1,
              y: 1,
              title: "a12",
              content: "b12"
            }
          ]
        },
        {
          x: 2,
          y: 0,
          title: "a2",
          content: "b2",
          offset: 1
        }
      ]
    }
  }
}
</script>
<style>
  .box-card {
    width: 180px;
    height: 180px;
    margin: 10px;
    float: left;
  }
</style>
