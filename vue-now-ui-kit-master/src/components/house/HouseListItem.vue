<template>
  <b-row
    class="m-2 aptListDiv"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{'mouse-over-bgcolor': isColor}"
  >
    <b-col cols="3" class="text-center align-self-center">
      <b-img src="images/apt-thumbnail2.png" alt="Image 1"></b-img>
    </b-col>
    <b-col cols="7" class="align-self-center"
      ><strong>{{ house.aptName }}</strong
      ><br />{{ house.dealAmount }},000원</b-col
    >
  </b-row>
</template>

<script>
import {mapMutations} from "vuex";
const aptStore = "aptStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
    idx: Number,
  },
  computed: {},
  methods: {
    ...mapMutations(aptStore, ["SET_CLICK_HOUSE"]),
    colorChange(flag) {
      this.isColor = flag;
    },
    getIndex() {
      var select = document.querySelector(".aptListDiv");
      var index = select.rowIndex;
      console.log(index);
    },
    selectHouse() {
      this.SET_CLICK_HOUSE(this.house);
      this.$emit('detail', this.idx);
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: rgba(226, 226, 226, 0.644);
}
</style>
