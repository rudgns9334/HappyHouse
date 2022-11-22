<template>
  <div>
    <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
      <house-list-item @detail="passToAptMap" v-for="(house, index) in houses" :key="index" :house="house" :idx="index" />
    </b-container>
    <b-container v-else class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem";
import {mapState} from "vuex";

const aptStore = "aptStore";
export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(aptStore, ["houses"]),
  },
  methods: {
    passToAptMap(idx) {
      this.$emit('detail', idx);
    }
  }

};
</script>

<style></style>
