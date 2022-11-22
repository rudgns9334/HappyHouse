<template>
  <div>
    <b-row>
      <b-col class="selectBox">
        <b-form-select class="sidoSel" v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
        <b-form-select class="gugunSel" v-model="gugunCode" :options="guguns" @change="dongList"></b-form-select>
        <b-form-select class="dongSel" v-model="dongCode" :options="dongs"></b-form-select>
      </b-col>
    </b-row>
    <input class="searchBar" v-model="searchWord" type="text" placeholder="Search by name" style="width: 235px; margin-left: -2px" />
    <button
      @click="searchApt"
      type="button"
      class="btn btn-primary"
      style="width: 25%; padding-left: -20px; background-color: #005555"
      ref="selectBtn"
    >
      SEARCH
    </button>
  </div>
</template>

<script>
import {mapState, mapActions, mapMutations} from "vuex";
const aptStore = "aptStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      searchWord: null,
      positoins: [],
    };
  },
  computed: {
    ...mapState(aptStore, ["sidos", "guguns", "dongs", "houses", "container"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(aptStore, ["getSido", "getGugun", "getDong", "getHouseList"]),
    ...mapMutations(aptStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_APT_LIST",
      "SHOW_IN_MAP",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      console.log(this.gugunCode);
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      this.CLEAR_APT_LIST();
      console.log(this.dongCode, this.searchWord);
      const searchData = {
        code: this.dongCode,
        searchWord: this.searchWord
      }
      if (this.dongCode) {
        this.getHouseList(searchData);
      }
    },
  },
};
</script>

<style>
.sidoSel,
.gugunSel,
.dongSel {
  width: 30% !important;
  margin: 5px;
}
.selectBox {
  margin-left: -7px;
  margin-top: 10px;
}
</style>
