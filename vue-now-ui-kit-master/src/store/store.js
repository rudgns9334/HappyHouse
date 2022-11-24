import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex);

import userStore from "@/store/modules/userStore.js";
import boardStore from "@/store/modules/boardStore.js";
import aptStore from "@/store/modules/aptStore.js";
import friendStore from "@/store/modules/friendStore.js";
import bookMarkStore from "@/store/modules/bookMarkStore.js";
import eventStore from "@/store/modules/eventStore.js";
import alermStore from "@/store/modules/alermStore.js";
import adminStore from "@/store/modules/adminStore.js";
import reviewStore from "@/store/modules/reviewStore.js";

export default new Vuex.Store({
  modules: {
    userStore,
    boardStore,
    aptStore,
    friendStore,
    bookMarkStore,
    eventStore,
    alermStore,
    adminStore,
    reviewStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
