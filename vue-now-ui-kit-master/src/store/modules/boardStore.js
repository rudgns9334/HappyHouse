import util from "@/common/util.js";
import boardAxios from "@/common/axios/board.js";
import router from "../../router";

import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
    namespaced: true,
    state: {
         // list
         list: [],
         countCurrentList: 0,
         limit: 8,
         offset: 0,
         searchWord: "",

         // pagination
         listRowCount: 8,
         pageLinkCount: 8,
         currentPageIndex: 1,

         totalListItemCount: 0,

         // detail, update, delete

         board: {
             boardId: 0,
             userSeq: 0,
             title: "",
             content: "",
             userName: "",
             regDate: "",
             regTime: "",
             readCount: 0,
             fileList: [],
             sameUser: false,
         }
    },
    mutations: {
        SET_BOARD_LIST(state, list){
            state.list = list;
            state.countCurrentList = list.length;
        },
        SET_BOARD_TOTAL_LIST_ITEM_COUNT(state, count) {
            state.totalListItemCount = count;
         },
   
         SET_BOARD_MOVE_PAGE(state, pageIndex) {
            state.offset = (pageIndex - 1) * state.listRowCount;
            state.currentPageIndex = pageIndex;
         },
         SET_BOARD_DETAIL(state, payload) {
            state.board.boardId = payload.boardId;
            state.board.userSeq = payload.userSeq;
            state.board.title = payload.title;
            state.board.content = payload.content;
            state.board.userName = payload.userName;
            state.board.regDate = util.makeDateStr(payload.regDt.date.year, payload.regDt.date.month, payload.regDt.date.day, ".");
            state.board.regTime = util.makeTimeStr(payload.regDt.time.hour, payload.regDt.time.minute, payload.regDt.time.second, ":");
            state.board.readCount = payload.readCount;
            state.board.fileList = payload.fileList;
            state.board.sameUser = payload.sameUser;
         },
         // for UpdateModal title v-modal
         SET_BOARD_TITLE(state, title) {
            state.board.title = title;
         },
    },
    actions: {
        boardList(context, boardCode){
            let params = {
                limit: this.state.boardStore.limit,
                offset: this.state.boardStore.offset,
                searchWord: this.state.boardStore.searchWord,
                boardCode: boardCode
             };
             boardAxios.boardList(
                params,
                ({ data }) => {
                    if(data.result == 'login'){
                        router.push("/login");
                    }else{
                        console.log(data.list);
                        context.commit("SET_BOARD_LIST", data.list);
                        context.commit("SET_BOARD_TOTAL_LIST_ITEM_COUNT", data.count);
                    }
                },
                (error) => {
                    console.error(error);
                    this.$alertify.error("서버에 문제가 있습니다.");
                });
        }
    },
    getters:{
        getBoardList: function (state) {
            return state.list;
         },
         // pagination
        getPageCount: function (state) {
            return Math.ceil(state.totalListItemCount / state.listRowCount);
        },
        getStartPageIndex: function (state) {
            if (state.currentPageIndex % state.pageLinkCount == 0) {
            //10, 20...맨마지막
            return (state.currentPageIndex / state.pageLinkCount - 1) * state.pageLinkCount + 1;
            } else {
            return Math.floor(state.currentPageIndex / state.pageLinkCount) * state.pageLinkCount + 1;
            }
        },
        getEndPageIndex: function (state, getters) {
            let ret = 0;
            if (state.currentPageIndex % state.pageLinkCount == 0) {
            //10, 20...맨마지막
            ret = (state.currentPageIndex / state.pageLinkCount - 1) * state.pageLinkCount + state.pageLinkCount;
            } else {
            ret = Math.floor(state.currentPageIndex / state.pageLinkCount) * state.pageLinkCount + state.pageLinkCount;
            }
            // 위 오류나는 코드를 아래와 같이 비교해서 처리
            return ret > getters.getPageCount ? getters.getPageCount : ret;
        },
        getPrev: function (state) {
            if (state.currentPageIndex <= state.pageLinkCount) {
            return false;
            } else {
            return true;
            }
        },
        getNext: function (state, getters) {
            if (Math.floor(getters.getPageCount / state.pageLinkCount) * state.pageLinkCount < state.currentPageIndex) {
            return false;
            } else {
            return true;
            }
        },
    }
};