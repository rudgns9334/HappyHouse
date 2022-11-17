
export default {
    namespaced: true,
    state: {
        isLogin: false,
        user:{
            userSeq: 0,
            userName: '',
            userPassword: 'admin1234!',
            userEmail: 'admin@ssafy.com',
            userProfileImageUrl: '',
            userRegisterDate: '',
            userState: '',
            userEventPart: '',
        }
    },
    mutations: {
        SET_LOGIN(state, payload){
            state.isLogin=payload.isLogin;
            state.user = payload.user;
        },
    },
    actions: {

    },
    getters: {
        isLogin: function(state){
            return state.user.isLogin;
        },
        isUserName: function(state){
            return state.user.userName;
        },
    }
};