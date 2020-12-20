<template>
  <div class="store">
    <Waterfall :options="options">
      <WaterfallItem
        v-for="(pet, index) of pets"
        :key="index"
        class="ant-col ant-col-6"
      >
        <img :src="pet" alt="" />
      </WaterfallItem>
    </Waterfall>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import { Waterfall, WaterfallItem } from "vue2-waterfall";
import PetService from "@/services/PetService";
import type { Pet } from "@/services/types";

export default Vue.extend({
  name: "PetStore",
  components: {
    Waterfall,
    WaterfallItem,
  },
  data() {
    return {
      pets: [] as string[],
      options: {},
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
      const petService = new PetService(this.$http);
      petService.findPetsByStatus("pending").then((resp: Pet[]) => {
        this.pets = resp
          .filter(
            (pet) =>
              pet.photoUrls &&
              Array.isArray(pet.photoUrls) &&
              pet.photoUrls.length
          )
          .map((pet) => [pet.photoUrls[0]])
          .reduce((x, y) => x.concat(y), []);
      });
    },
  },
});
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
.store {
  width: 60%;
  margin: 0 auto;
}
.store img {
  width: 100%;
  height: auto;
}
</style>
