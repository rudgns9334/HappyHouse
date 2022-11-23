import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex);

import userStore from "@/store/modules/userStore.js";
import boardStore from "@/store/modules/boardStore.js";
import aptStore from "@/store/modules/aptStore.js";
import friendStore from "@/store/modules/friendStore.js";
import bookMarkStore from "@/store/modules/bookMarkStore.js";
import alermStore from "@/store/modules/AlermStore.js";

export default new Vuex.Store({
    modules : {
        userStore,
        boardStore,
        aptStore,
        friendStore,
        bookMarkStore,
        alermStore
    },
    plugins: [
        createPersistedState({
          // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
          storage: sessionStorage,
        }),
      ],
});

