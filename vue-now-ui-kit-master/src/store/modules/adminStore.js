
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
        
       
    },
    actions: {
        
       
    },
    getters: {
      
    }
}