import Vue from "vue";
import Vuex from "vuex";
// import aptAxios from "@/common/axios/apt.js";

Vue.use(Vuex);

import http from "@/common/axios.js";

export default {
    namespaced: true,
    state: {
        sidos: [{ value: null, text: "선택하세요" }],
        guguns: [{ value: null, text: "선택하세요" }],
        dongs: [{ value: null, text: "선택하세요" }],
        houses: [],
        house: null,
    },
    mutations: {
        SET_SIDO_LIST(state, sidos) {
            sidos.forEach((sido) => {
                state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
        });
        },
        SET_GUGUN_LIST(state, guguns) {
            guguns.forEach((gugun) => {
                state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
        });
        },
        SET_DONG_LIST(state, dongs) {
          dongs.forEach((dong) => {
              state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
      },
        CLEAR_SIDO_LIST(state) {
            state.sidos = [{ value: null, text: "선택하세요" }];
        },
        CLEAR_APT_LIST(state) {
            state.houses = [];
            state.house = null;
        },
        CLEAR_GUGUN_LIST(state) {
            state.guguns = [{ value: null, text: "선택하세요" }];
        },
        CLEAR_DONG_LIST(state) {
          state.dongs = [{ value: null, text: "선택하세요" }];
        },
        SET_HOUSE_LIST(state, houses) {
            state.houses = houses;
            console.log(houses);
        },
        SET_DETAIL_HOUSE(state, house) {
        // console.log("Mutations", house);
            state.house = house;
        },
    },
    actions: {
        /////////////////////////////// House start /////////////////////////////////////
        // getSido(context) {
        //   aptAxios.getSido(
        //     ({ data }) => {
        //       if(data.result == 'login'){
        //           router.push("/login");
        //       }else{
        //           context.commit("SET_SIDO_LISTT", data);
        //       }
        //   },
        //   (error) => {
        //       console.error(error);
        //       this.$alertify.error("서버에 문제가 있습니다.");
        //   });
        // },
        getSido({ commit }) {
            http
              .get(`/apts/sido`)
              .then(({ data }) => {
                console.log(data.result);
                if(data.result == 'login'){
                  router.push("/login");
                } else {
                  // console.log(data);
                  commit("SET_SIDO_LIST", data.sgdList);
                }
              })
              .catch((error) => {
                console.log(error);
              });
          },
          getGugun({ commit }, sidoCode) {
            const params = { 
              code: sidoCode 
            };
            http
              .get(`/apts/gugun`, { params })
              .then(({ data }) => {
                // console.log(commit, response);
                commit("SET_GUGUN_LIST", data.sgdList);
              })
              .catch((error) => {
                console.log(error);
              });
          },
          getDong({ commit }, gugunCode) {
            console.log(gugunCode)
            const params = { 
              code: gugunCode
            };
            http
              .get(`/apts/dong`, { params })
              .then(({ data }) => {
                console.log(data);
                // console.log(commit, response);
                commit("SET_DONG_LIST", data.sgdList);
              })
              .catch((error) => {
                console.log(error);
              });
          },
          getHouseList({ commit }, dongCode) {
            console.log(dongCode);
            const params = {
              code: dongCode,
            };
            http
              .get('/apts', { params })
              .then(({ data }) => {
                console.log(data);
                commit("SET_HOUSE_LIST", data.list)
              })
              .catch((error) => {
                console.log(error);
              });
          },
          detailHouse({ commit }, house) {
            // 나중에 house.일련번호를 이용하여 API 호출
            console.log(house);
            commit("SET_DETAIL_HOUSE", house);
          },
    },
    modules: {},

}