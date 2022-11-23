import friendAxios from "@/common/axios/friend.js";

import router from "../../router";
import Vue from "vue";
import VueAlertify from "vue-alertify";

Vue.use(VueAlertify);

export default {
  namespaced: true,
  state: {
    isFriendList: false,
    list: [],
    user: null,
    mySeq: 0,
  },
  mutations: {
    SET_IS_FRIEND_LIST(state, is) {
      state.isFriendList = is;
    },
    SET_FRIEND_INFO(state, payload) {
      state.user = payload;
    },
    SET_LIST(state, list) {
      state.list = list;
    },
    SET_SEQ(state, seq) {
      state.mySeq = seq;
    },
  },
  actions: {
    friendList({commit}, userSeq) {
      commit("SET_SEQ", userSeq);
      friendAxios.friendList(
        `/${userSeq}`,
        ({data}) => {
          if (data.result == 1) {
            console.log("친구목록불러오기 성공");
            commit("SET_LIST", data.list);
          }
        },
        error => {
          console.log("불러오기 실패");
          console.log(error);
        },
      );
    },
    friendDetail({commit, state}, friendSeq) {
      friendAxios.friendDetail(
        `/${state.mySeq}/${friendSeq}`,
        ({data}) => {
          if (data.result == 1) {
            console.log("친구불러오기 성공");
            commit("SET_FRIEND_INFO", data.friend);
          }
        },
        error => {
          console.log("불러오기 실패");
          console.log(error);
        },
      );
    },
  },
  getters: {
    isFriend(state) {
      return state.isFriend;
    },
    FriendInfo(state) {
      return state.user;
    },
    getFriendList(state) {
      return state.list;
    },
  },
};
