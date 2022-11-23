import bookMarkAxios from "@/common/axios/bookmark.js";

import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
  namespaced: true,
  state: {
    list: [],
    selectedIdx: 0,
    isEmpty: false
  },
  mutations: {
    SET_IS_EMPTY(state, is){
      state.isEmpty = is;
    },
    SET_BOOKMARK_LIST(state, list) {
      state.list = list;
    },
    SET_SELECTED_IDX(state, idx) {
      state.selectedIdx = idx;
    },
  },
  getters: {
    getWishList: function (state) {
      return state.list;
    },
  },
  actions: {
    bookMarkList({commit}, userSeq) {
      bookMarkAxios.bookMarkList(
        `/${userSeq}`,
        ({data}) => {
          if (data.result == 1) {
            console.log("북마크목록불러오기 성공");
            if(data.list.length == 0){
              commit("SET_IS_EMPTY",true);
            }else{
              commit("SET_IS_EMPTY",false);
            }
            commit("SET_BOOKMARK_LIST", data.list);
          }
        },
        error => {
          console.log("불러오기 실패");
          console.log(error);
        },
      );
    },
    bookMarkInsert({dispatch}, body) {
      bookMarkAxios.bookMarkInsert(
        body,
        ({data}) => {
          console.log(data);
          if (data.result == 1) {
            alertify.alert("Add in WishList!!").setHeader("<em> SUCCESS! </em> ");
            dispatch("bookMarkList", this.state.userStore.user.userSeq);
          }
        },
        error => {
          alertify.alert("Server Error").setHeader("<em> ERROR </em> ");
          console.log(error);
        },
      );
    },
    bookMarkDelete({commit, dispatch}, id) {
      console.log(id);
      bookMarkAxios.bookMarkDelete(
        `/${id}`,
        ({data}) => {
          if (data.result == 1) {
            alertify.alert("Delete this Wish Item!!").setHeader("<em> SUCCESS! </em> ");
            dispatch("bookMarkList", this.state.userStore.user.userSeq);
          }
        },
        error => {
          alertify.alert("Server Error").setHeader("<em> ERROR </em> ");
          console.log(error);
        },
      );
    },
  },
  getters: {
    getSelectedIdx(state) {
      return state.selectedIdx;
    },
    getWishList(state) {
      return state.list;
    },
  },
};
