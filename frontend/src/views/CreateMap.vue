<template>
  <div class="create">
    <p>地图名称：</p>
    <input v-model="mapName" placeholder="输入名称">

    <p>地图描述：</p>
    <textarea v-model="mapDescription" placeholder="输入描述"></textarea>

    <button style = "font-size:25px;">点我</button>
  </div>
</template>

<script>
export default {
  name: 'create'
}
</script>
