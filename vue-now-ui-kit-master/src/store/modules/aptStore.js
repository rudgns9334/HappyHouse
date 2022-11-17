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
              state.dongs.push({ value: dong.dongName, text: dong.dongName });
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
              .get(`/apt/sido`)
              .then(({ data }) => {
                console.log(data.result);
                if(data.result == 'login'){
                  router.push("/login");
                } else {
                  // console.log(data);
                  commit("SET_SIDO_LIST", data);
                }
              })
              .catch((error) => {
                console.log(error);
              });
          },
          getGugun({ commit }, sidoCode) {
            const params = { sido: sidoCode };
            http
              .get(`/apt/gugun`, { params })
              .then(({ data }) => {
                // console.log(commit, response);
                commit("SET_GUGUN_LIST", data);
              })
              .catch((error) => {
                console.log(error);
              });
          },
          getDong({ commit }, gugunCode) {
            console.log(gugunCode)
            const params = { gugun: gugunCode };
            http
              .get(`/apt/dong`, { params })
              .then(({ data }) => {
                console.log(data);
                // console.log(commit, response);
                commit("SET_DONG_LIST", data);
              })
              .catch((error) => {
                console.log(error);
              });
          },
          getHouseList({ commit }, dongName) {
            console.log("GETHOUSELIST");
            console.log(dongName);
            // // vue cli enviroment variables 검색
            // //.env.local file 생성.
            // // 반드시 VUE_APP으로 시작해야 한다.
            // const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
            // // const SERVICE_KEY =
            // //   "######################## Service Key ########################";
            // const SERVICE_URL =
            //   "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
            // const params = {
            //   LAWD_CD: gugunCode,
            //   DEAL_YMD: "202207",
            //   serviceKey: decodeURIComponent(SERVICE_KEY),
            // };
            // http
            //   .get(SERVICE_URL, { params })
            //   .then(({ data }) => {
            //     // console.log(commit, data);
            //     commit("SET_HOUSE_LIST", data.response.body.items.item);
            //   })
            //   .catch((error) => {
            //     console.log(error);
            //   });
            const params = {dong: dongName};
            http
              .get('/apt/aptlist', { params })
              .then(({ data }) => {
                console.log(data);
                commit("SET_HOUSE_LIST", data)
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