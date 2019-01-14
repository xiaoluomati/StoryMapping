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
export default {
  name: 'storymap-manager',
  data () {
    return {
      storymaps: [
        {
          id: 1,
          title: 'storymap',
          description: 'this is a test description'
        },
        {
          id: 2,
          title: 'storymap',
          description: 'this is a test description this is a test description this is a test description this is a test description this is a test description this is a test description'
        },
        {
          id: 3,
          title: 'storymap',
          description: 'this is a test description this is a test description this is a test description this is a test description this is a test description this is a test description'
        },
        {
          id: 4,
          title: 'storymap',
          description: 'this is a test description this is a test description this is a test description this is a test description this is a test description this is a test description'
        }
      ]
    }
  },

  methods: {
    jumpTo (name, param) {
      console.log(name)
      console.log(param)
      // this.$router.push({ name: name, params: { id : param }}) // 用go刷新
      this.$router.push({ path: `/${name}/${param}` }) // 用go刷新
    }
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
