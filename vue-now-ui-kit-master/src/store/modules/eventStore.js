import util from "@/common/util.js";
import eventAxios from "@/common/axios/event.js";
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

         // pagination
         listRowCount: 8,
         pageLinkCount: 8,
         currentPageIndex: 1,

         totalListItemCount: 0,

         // detail, update, delete

         event: {
             eventId: 0,
             eventName: "",
             startDt: "",
             endDt: "",
             imgUrl: "",
         }
    },
    mutations: {
        SET_EVENT_LIST(state, list){
            state.list = list;
            state.countCurrentList = list.length;
        },
        SET_EVENT_TOTAL_LIST_ITEM_COUNT(state, count) {
            state.totalListItemCount = count;
         },
   
         SET_EVENT_MOVE_PAGE(state, pageIndex) {
            state.offset = (pageIndex - 1) * state.listRowCount;
            state.currentPageIndex = pageIndex;
         },
         SET_EVENT_DETAIL(state, payload) {
            state.event.eventId = payload.eventId;
            state.event.eventName = payload.eventName;
            state.event.startDt = util.makeDateStr(payload.startDt.date.year, payload.startDt.date.month, payload.startDt.date.day, ".");
            state.event.endDt = util.makeDateStr(payload.endDt.date.year, payload.endDt.date.month, payload.endDt.date.day, ".");
            state.event.imgUrl = payload.imgUrl;
         },
    },
    actions: {
        eventList(context, eventId){
            let params = {
                limit: this.state.eventStore.limit,
                offset: this.state.eventStore.offset,
                eventId: eventId
             };
             eventAxios.boardList(
                params,
                ({ data }) => {
                    if(data.result == 'login'){
                        router.push("/login");
                    }else{
                        console.log(data.list);
                        context.commit("SET_EVENT_LIST", data.list);
                        context.commit("SET_EVENT_TOTAL_LIST_ITEM_COUNT", data.count);
                    }
                },
                (error) => {
                    console.error(error);
                    this._vm.$alertify.error("서버에 문제가 있습니다.");
                });
        }
    },
    getters:{
        getEventList: function (state) {
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