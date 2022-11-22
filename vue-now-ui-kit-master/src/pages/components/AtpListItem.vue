<template>
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{'mouse-over-bgcolor': isColor}"
  >
    <b-col cols="2" class="text-center align-self-center">
      <b-img thumbnail src="https://picsum.photos/250/250/?image=58" alt="Image 1"></b-img>
    </b-col>
    <b-col cols="10" class="align-self-center"> [{{ house.일련번호 }}] {{ house.아파트 }} </b-col>
  </b-row>
</template>

<script>
import {mapState} from "vuex";
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
  },
  computed: {
    ...mapState(aptStore, ["houses"]),
  },
  methods: {
    // ...mapActions(aptStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.detailHouse(this.house);
      // console.log(selLat, selLng);
      console.log(this.houses);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: #f5f5f5;
}
</style>
