import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import http from "@/common/axios.js";
import util from "@/common/util.js";

export default new Vuex.Store({
    state: {
        user: {
            isLogin: false,
            userSeq: 0,
            userName: '',
            userPassword: 'admin1234!',
            userEmail: 'admin@ssafy.com',
            userProfileImageUrl: '',
            userRegisterDate: '',
            userState: '',
            userEventPart: '',
        },
        board: {
            // list
            list: [],
            limit: 8,
            offset: 0,
            searchWord: "",

            // pagination
            listRowCount: 8,
            pageLinkCount: 8,
            currentPageIndex: 1,

            totalListItemCount: 0,

            // detail, update, delete

            detail: {
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
            
        }
    },
    mutations: {
        SET_LOGIN(state, payload){
            state.user = {
                isLogin: payload.isLogin,
                ...payload.user
            };
        },
        SET_BOARD_LIST(state, list){
            state.board.list = list;
        },
        SET_BOARD_TOTAL_LIST_ITEM_COUNT(state, count) {
            state.board.totalListItemCount = count;
         },
   
         SET_BOARD_MOVE_PAGE(state, pageIndex) {
            state.board.offset = (pageIndex - 1) * state.board.listRowCount;
            state.board.currentPageIndex = pageIndex;
         },
         SET_BOARD_DETAIL(state, payload) {
            state.board.detail.boardId = payload.boardId;
            state.board.detail.userSeq = payload.userSeq;
            state.board.detail.title = payload.title;
            state.board.detail.content = payload.content;
            state.board.detail.userName = payload.userName;
            state.board.detail.regDate = util.makeDateStr(payload.regDt.date.year, payload.regDt.date.month, payload.regDt.date.day, ".");
            state.board.detail.regTime = util.makeTimeStr(payload.regDt.time.hour, payload.regDt.time.minute, payload.regDt.time.second, ":");
            state.board.detail.readCount = payload.readCount;
            state.board.detail.fileList = payload.fileList;
            state.board.detail.sameUser = payload.sameUser;
         },
         // for UpdateModal title v-modal
         SET_BOARD_TITLE(state, title) {
            state.board.detail.title = title;
         },
    },
    actions: {
        async boardList(context, boardCode) {
            let params = {
               limit: this.state.board.limit,
               offset: this.state.board.offset,
               searchWord: this.state.board.searchWord,
               boardCode: boardCode
            };
   
            try {
               let { data } = await http.get("/boards", { params }); // params: params shorthand property, let response 도 제거
               console.log("BoardMainVue: data : ");
               console.log(data);
               if (data.result == "login") {
                  router.push("/login");
               } else {
                  context.commit("SET_BOARD_LIST", data.list);
                  context.commit("SET_BOARD_TOTAL_LIST_ITEM_COUNT", data.count);
               }
            } catch (error) {
               console.error(error);
            }
         },
         
    },
    getters:{
        isLogin: function(state){
            return state.user.isLogin;
        },
        isUserName: function(state){
            return state.user.userName;
        },
        getBoardList: function (state) {
            return state.board.list;
         },
         // pagination
        getPageCount: function (state) {
            return Math.ceil(state.board.totalListItemCount / state.board.listRowCount);
        },
        getStartPageIndex: function (state) {
            if (state.board.currentPageIndex % state.board.pageLinkCount == 0) {
            //10, 20...맨마지막
            return (state.board.currentPageIndex / state.board.pageLinkCount - 1) * state.board.pageLinkCount + 1;
            } else {
            return Math.floor(state.board.currentPageIndex / state.board.pageLinkCount) * state.board.pageLinkCount + 1;
            }
        },
        getEndPageIndex: function (state, getters) {
            let ret = 0;
            if (state.board.currentPageIndex % state.board.pageLinkCount == 0) {
            //10, 20...맨마지막
            ret = (state.board.currentPageIndex / state.board.pageLinkCount - 1) * state.board.pageLinkCount + state.board.pageLinkCount;
            } else {
            ret = Math.floor(state.board.currentPageIndex / state.board.pageLinkCount) * state.board.pageLinkCount + state.board.pageLinkCount;
            }
            // 위 오류나는 코드를 아래와 같이 비교해서 처리
            return ret > getters.getPageCount ? getters.getPageCount : ret;
        },
        getPrev: function (state) {
            if (state.board.currentPageIndex <= state.board.pageLinkCount) {
            return false;
            } else {
            return true;
            }
        },
        getNext: function (state, getters) {
            if (Math.floor(getters.getPageCount / state.board.pageLinkCount) * state.board.pageLinkCount < state.board.currentPageIndex) {
            return false;
            } else {
            return true;
            }
        },
    }
});