import alermAxios from "@/common/axios/alerm.js";

import router from "../../router";
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
    namespaced: true,
    state: {
        list: [],
        isAlerm: false,
        isNew: false,
        count: 0,
    },
    mutations: {
        
        INIT(state){
            state.list = [];
            state.count = 0;
            state.isNew = false;
            state.isAlerm = false;
        },
        SET_LIST(state, list){
            state.list = list;
        },
        SET_IS_ALERM(state, is){
            state.isAlerm = is;
        },
        SET_COUNT(state, cnt){
            state.count = cnt;
        },
        SET_IS_NEW(state, is){
            state.isNew = is;
        }
       
    },
    actions: {
        
       alermList({commit,state}){
        let params = {
            receiveUserSeq : this.state.userStore.user.userSeq
        }
        alermAxios.alermList(
            params,
            ({data}) => {
                if(data.result == 1){
                    console.log("알람목록불러오기 성공");
                    commit("SET_LIST", data.list);
                    commit("SET_COUNT", data.count);
                    if(data.isNew){
                        commit("SET_IS_NEW", true);
                    }
                }
            },
            (error) => {
                this._vm.$alertify.error("서버에 문제가 있습니다.");
                console.log(error);
            }
        )
       },
       alermReadAll({commit}){
        let body = {
            receiveUserSeq: this.state.userStore.user.userSeq,
        }
        alermAxios.alermReadAll(
            body,
            ({data}) => {
                if(data.result == 1){
                    console.log("읽기 처리 완료");
                    commit("SET_IS_NEW", false);
                }
            },
            (error) => {
                console.log(error);
                this._vm.$alertify.error("서버에 문제가 있습니다.");
            }
        )
       },
       alermSend({commit}, params){
        let body = {
            sendUserSeq: this.state.userStore.user.userSeq,
            ...params
        }
        alermAxios.alermSend(
            body,
            ({data}) => {
                if(data.result == 1){
                    console.log("친구 요청 알람 전송 완료");
                }
            },
            (error) => {
                console.log(error);
                this._vm.$alertify.error("서버에 문제가 있습니다.");
            }
        )
       },
       alermDelete({dispatch}, alermId){
        let params = {
            alermId
        }
        alermAxios.alermDelete(
            params,
            ({data}) => {
                if(data.result == 1){
                    console.log("알람 삭제 성공");
                    this._vm.$alertify.success("알람 삭제 성공");
                    dispatch("alermList");
                }
            },
            (error) => {
                this._vm.$alertify.error("서버에 문제가 있습니다.");
                console.log(error);
            }
        )
       },
       alermDeleteWithSeq({commit, dispatch}, receiveUserSeq){
        let params = {
            sendUserSeq: this.state.userStore.user.userSeq,
            receiveUserSeq
        }
        console.log(params);
        alermAxios.alermDeleteWithSeq(
            params,
            ({data}) => {
                if(data.result == 1){
                    console.log("알람 삭제 성공");
                    this._vm.$alertify.success("알람 삭제 성공");
                    dispatch("alermList");
                }
            },
            (error) => {
                this._vm.$alertify.error("서버에 문제가 있습니다.");
                console.log(error);
            }
        )
       },
       alermDeleteAll({commit, state}){
        let params = {
            receiveUserSeq : this.state.userStore.user.userSeq
        }
        alermAxios.alermDeleteAll(
            params,
            ({data}) => {
                if(data.result == 1){
                    console.log("알람 전체 삭제 성공");
                    this._vm.$alertify.success("알람 전체 삭제 성공");
                    commit("INIT");
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
       getIsAlerm(state){
        return state.isAlerm;
       },
       getList(state){
        return state.list;
       },
       getCount(state){
        return state.count;
       }
    }
}