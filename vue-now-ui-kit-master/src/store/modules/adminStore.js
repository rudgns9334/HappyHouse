
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
    namespaced: true,
    state: {
        notice:{
            isList: true,
            isInsert: false,
            isDetail: false,
            isUpdate: false,
        },
        user:{
            isList: true,
            isInsert: false,
            isDetail: false,
            isUpdate: false,
        },
        event:{
            isList: true,
            isInsert: false,
            isDetail: false,
            isUpdate: false,
        }
    },
    mutations: {
        
        INIT_NOTICE(state){
            state.notice.isList = true;
            state.notice.isInsert = false;
            state.notice.isDetail = false;
            state.notice.isUpdate = false;
        },
        SET_NOTICE_INSERT(state){
            state.notice.isList = false;
            state.notice.isInsert = true;
            state.notice.isDetail = false;
            state.notice.isUpdate = false;
        },
        SET_NOTICE_DETAIL(state){
            state.notice.isList = false;
            state.notice.isInsert = false;
            state.notice.isDetail = true;
            state.notice.isUpdate = false;
        },
        SET_NOTICE_UPDATE(state){
            state.notice.isList = false;
            state.notice.isInsert = false;
            state.notice.isDetail = false;
            state.notice.isUpdate = true;
        },
        INIT_USER(state){
            state.user.isList = true;
            state.user.isInsert = false;
            state.user.isDetail = false;
            state.user.isUpdate = false;
        },
        SET_USER_INSERT(state){
            state.user.isList = false;
            state.user.isInsert = true;
            state.user.isDetail = false;
            state.user.isUpdate = false;
        },
        SET_USER_DETAIL(state){
            state.user.isList = false;
            state.user.isInsert = false;
            state.user.isDetail = true;
            state.user.isUpdate = false;
        },
        SET_USER_UPDATE(state){
            state.user.isList = false;
            state.user.isInsert = false;
            state.user.isDetail = false;
            state.user.isUpdate = true;
        },
        INIT_EVENT(state){
            state.user.isList = true;
            state.user.isInsert = false;
            state.user.isDetail = false;
            state.user.isUpdate = false;
        },
        SET_EVENT_INSERT(state){
            state.user.isList = false;
            state.user.isInsert = true;
            state.user.isDetail = false;
            state.user.isUpdate = false;
        },
        SET_EVENT_DETAIL(state){
            state.user.isList = false;
            state.user.isInsert = false;
            state.user.isDetail = true;
            state.user.isUpdate = false;
        },
        SET_EVENT_UPDATE(state){
            state.user.isList = false;
            state.user.isInsert = false;
            state.user.isDetail = false;
            state.user.isUpdate = true;
        },
        
       
    },
    actions: {
        
       
    },
    getters: {
      
    }
}