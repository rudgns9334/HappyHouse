import Vue from "vue";
import Vuex from "vuex";
// import aptAxios from "@/common/axios/apt.js";

Vue.use(Vuex);

import http from "@/common/axios.js";

export default {
  namespaced: true,
  state: {
    sidos: [{value: null, text: "시/도 구분"}],
    guguns: [{value: null, text: "구/군 구분"}],
    dongs: [{value: null, text: "동 구분"}],
    houses: [],
    house: null,
    container: null,
    clickHouse: [],
    isCenter: false,
    lats: [],
    lngs: [],
  },
  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach(sido => {
        state.sidos.push({value: sido.sidoCode, text: sido.sidoName});
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach(gugun => {
        state.guguns.push({value: gugun.gugunCode, text: gugun.gugunName});
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach(dong => {
        state.dongs.push({value: dong.dongCode, text: dong.dongName});
      });
    },
    SET_CONTAINER(state, container) {
      state.container = container;
      console.log(container);
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{value: null, text: "시/도 구분"}];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{value: null, text: "구/군 구분"}];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{value: null, text: "동 구분"}];
      console.log(state.dongs);
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
      console.log(houses);
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_CLICK_HOUSE(state, house) {
      state.clickHouse = house;
    },
  },
  actions: {
    getSido({commit}) {
      http
        .get(`/apts/sido`)
        .then(({data}) => {
          console.log(data.result);
          if (data.result == "login") {
            router.push("/login");
          } else {
            // console.log(data);
            commit("SET_SIDO_LIST", data.sgdList);
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    getGugun({commit}, sidoCode) {
      const params = {
        code: sidoCode,
      };
      http
        .get(`/apts/gugun`, {params})
        .then(({data}) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data.sgdList);
        })
        .catch(error => {
          console.log(error);
        });
    },
    getDong({commit}, gugunCode) {
      console.log(gugunCode);
      const params = {
        code: gugunCode,
      };
      http
        .get(`/apts/dong`, {params})
        .then(({data}) => {
          console.log(data);
          // console.log(commit, response);
          commit("SET_DONG_LIST", data.sgdList);
        })
        .catch(error => {
          console.log(error);
        });
    },
    getHouseList({commit}, dongCode) {
      console.log(dongCode);
      const params = {
        code: dongCode,
      };
      http
        .get("/apts", {params})
        .then(({data}) => {
          console.log(data);
          commit("SET_HOUSE_LIST", data.list);
        })
        .catch(error => {
          console.log(error);
        });
    },
  },
  modules: {},
};
