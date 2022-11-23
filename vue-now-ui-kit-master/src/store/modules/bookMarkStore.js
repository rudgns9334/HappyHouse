import bookMarkAxios from "@/common/axios/bookmark.js";

import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
    namespaced: true,
    state: {
        list:[],
        selectedIdx: 0,
    },
    mutations: {
        SET_BOOKMARK_LIST(state, list){
            state.list = list;
        },
        SET_SELECTED_IDX(state, idx){
            state.selectedIdx = idx;
        },
    },
    actions: {
        bookMarkList({commit}, userSeq){
            bookMarkAxios.bookMarkList(
            `/${userSeq}`,
            ({data}) => {
                if(data.result == 1){
                    console.log("북마크목록불러오기 성공");
                    commit("SET_BOOKMARK_LIST", data.list);
                }
            },
            (error) => {
                console.log("불러오기 실패");
                console.log(error);
            }
        )
       },
       bookMarkInsert(body){
        bookMarkAxios.bookMarkInsert(
            body,
            ({data}) => {
                if(data.result == 1){
                    console.log("관심지역 등록 성공");
                }
            },
            (error) => {
                console.log("등록 실패");
                console.log(error);
            }
        )
       },
       bookMarkDelete(id){
        bookMarkAxios.bookMarkDelete(
            `/${id}`,
            ({data}) => {
                if(data.result == 1){
                    console.log("관심지역 삭제 성공");
                }
            },
            (error) => {
                console.log("삭제 실패");
                console.log(error);
            }
        )
       },
    },
    getters: {
        getSelectedIdx(state){
        return state.selectedIdx;
       },
       getList(state){
        return state.list;
       }
    }
};