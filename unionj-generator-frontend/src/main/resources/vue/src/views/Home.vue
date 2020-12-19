<template>
  <div class="home">
    <a href="https://github.com/Tsingxiao/unionj-generator"><img alt="Vue logo" src="../assets/logo.png" id="logo"></a>
    <Usage msg="Thanks for trying unionj-generator scaffolding tool"/>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import Usage from '@/components/Usage.vue';

export default Vue.extend({
  name: 'Home',
  components: {
    Usage,
  },
});
</script>

<style lang="less">
#logo{
  width: 200px;
  height: 200px;
}
</style>
