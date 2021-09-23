<template>
  <div class="home">
    <a href="https://github.com/unionj-cloud/unionj-generator"><img alt="Union logo" src="../assets/logo.png" class="logo"></a>
    <a href="https://github.com/mswjs/msw"><img alt="Mswjs logo" src="../assets/mswjs.png" class="logo"></a>
    <h2>Mock response from Mswjs handler</h2>
    <PetStore />
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import PetStore from '@/components/PetStore.vue';

export default Vue.extend({
  name: 'Msw',
  components: {
    PetStore,
  },
});
</script>

<style lang="less">
.logo{
  width: 200px;
  height: auto;
}
</style>
