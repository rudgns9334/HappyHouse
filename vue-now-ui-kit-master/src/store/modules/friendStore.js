import friendAxios from "@/common/axios/friend.js";

import router from "../../router";
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
    namespaced: true,
    state: {
        isFriendList: false,
        userList:[],
        list:[],
        user: null,
        searchWord: ''
        
    },
    mutations: {
        INIT(state){
            state.isFriendList = false;
            state.list = [];
        },
        INIT_SEARCH(state){
            state.searchWord = '';
            state.userList = [];
        },
        SET_IS_FRIEND_LIST(state, is){
            state.isFriendList = is;
        },
        SET_FRIEND_INFO(state, payload){
            state.user = payload;
        },
        SET_LIST(state, list){
            state.list = list;
        },
        SET_USER_LIST(state, list){
            state.userList = list;
        },
    },
    actions: {
        friendListSearchWord({commit, state}){
            let params = {
                searchWord: state.searchWord,
                sendSeq: this.state.userStore.user.userSeq
            }
            friendAxios.friendListSearchWord(
                params,
                ({data}) => {
                    if(data.result == 1){
                        console.log(data.list);
                        commit("SET_USER_LIST", data.list);
                    }
                },
                (error) => {
                    console.log(error);
                }
            )
        },
       friendList({commit}, userSeq){
        friendAxios.friendList(
            `/${userSeq}`,
            ({data}) => {
                if(data.result == 1){
                    console.log("친구목록불러오기 성공");
                    commit("SET_LIST", data.list);
                }
            },
            (error) => {
                this._vm.$alertify.error("서버에 문제가 있습니다.");
                console.log(error);
            }
        )
       },
       friendDetail({commit, state}, friendSeq){
        friendAxios.friendDetail(
            `/${friendSeq}`,
            ({data}) => {
                if(data.result == 1){
                    console.log("친구불러오기 성공");
                    this._vm.$alertify.success("친구불러오기 성공");
                    commit("SET_FRIEND_INFO", data.friend);
                }
            },
            (error) => {
                this._vm.$alertify.error("서버에 문제가 있습니다.");
                console.log(error);
            }
        )
       },
       friendRequest({dispatch}, receiveSeq){
        let body = {
            sendSeq: this.state.userStore.user.userSeq,
            receiveSeq
        }
        friendAxios.friendRequest(
            body,
            ({data}) => {
                if(data.result == 1){
                    console.log("친구 요청 성공");
                    dispatch("friendListSearchWord");
                }
            },
            (error) => {
                console.log(error);
                this._vm.$alertify.error("서버에 문제가 있습니다.");
            }
        )
       },
       friendAccept({dispatch}, sendSeq){
        let body = {
            sendSeq,
            receiveSeq: this.state.userStore.user.userSeq
        }
        friendAxios.friendAccept(
            body,
            ({data}) => {
                if(data.result == 1){
                    console.log("친구 수락 성공");
                    this._vm.$alertify.success("친구 등록이 되었습니다.");
                    dispatch("friendList", this.state.userStore.user.userSeq);
                }
            },
            (error) => {
                console.log(error);
                this._vm.$alertify.error("서버에 문제가 있습니다.");
            }
        )
       },
       friendDelete({dispatch, state}, sendSeq){
        let params = {
            sendSeq,
            receiveSeq: this.state.userStore.user.userSeq
        }
        friendAxios.friendDelete(
            params,
            ({data}) => {
                if(data.result == 1){
                    console.log("친구 삭제 성공");
                    this._vm.$alertify.success("친구 삭제 성공");
                    dispatch("friendList", this.state.userStore.user.userSeq);
                    dispatch("friendListSearchWord");
                }
            },
            (error) => {
                this._vm.$alertify.error("서버에 문제가 있습니다.");
                console.log(error);
            }
        )
       },
    },
    getters: {
       isFriend(state){
        return state.isFriend;
       },
       FriendInfo(state){
        return state.user;
       },
       getFriendList(state){
        return state.list;
       },
       getUserList(state){
        return state.userList;
       }
    }
};