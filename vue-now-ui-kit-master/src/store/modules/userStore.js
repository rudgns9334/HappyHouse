import userAxios from "@/common/axios/user.js";

import jwtDecode from "jwt-decode";
import router from "../../router";
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
    namespaced: true,
    state: {
        isLogin: false,
        isValidToken: false,
        isModify: false,
        user:{
            userSeq: 0,
            userName: '',
            userPassword: 'admin1234!',
            userEmail: 'admin@ssafy.com',
            userProfileImageUrl: '',
            userRegisterDate: '',
            userState: '',
            userEventPart: '',
            userComment: '',
        },
        regist:{
            userName: '',
            userEmail: '',
            userPassword: '',
            userPassword2: '',

            isUserNameValid: false,
            isUserEmailValid: false,
            isUserPasswordValid: false,
            isUserPassword2Valid: false,

            isUserNameFocus: false,
            isUserEmailFocus: false,
            isUserPasswordFocus: false,
            isUserPassword2Focus: false,

            userNameErrMsg: '',
            userEmailErrMsg: '',
            userPasswordErrMsg: '',
            userPassword2ErrMsg: ''

        }
    },
    mutations: {
        SET_LOGIN_INFO(state, payload){
            state.isLogin=payload.isLogin;
            state.user = payload.user;
        },
        SET_IS_VALID_TOKEN: (state, isValidToken) => {
            state.isValidToken = isValidToken;
        },
        SET_LOGIN(state, isLogin){
            state.isLogin = isLogin;
        },
        SET_MODIFY(state){
            state.isModify=!state.isModify;
        },
        INIT_MODIFY(state){
            state.isModify=false;
        },
        CLEAR_USER(state){
            state.user.userSeq = 0;
            state.user.userName = '';
            state.user.userPassword = 'admin1234!';
            state.user.userEmail = 'admin@ssafy.com';
            state.user.ProfileImageUrl= '';
            state.user.userRegisterDate = '';
            state.user.userState = '';
            state.user.userEventPart = '';
            state.user.userComment = '';
        },
        CLEAR_REGISTER(state){
            state.regist.userName = '';
            state.regist.userPassword = '';
            state.regist.userPassword2 = '';
            state.regist.userEmail = '';
        },
        SET_ERR_USER_NAME(state, msg){
            state.regist.userNameErrMsg=msg;
        },
        SET_ERR_USER_EMAIL(state, msg){
            state.regist.userEmailErrMsg=msg;
        },
        SET_ERR_USER_PASSWORD(state, msg){
            state.regist.userPasswordErrMsg=msg;
        },
        SET_ERR_USER_PASSWORD2(state, msg){
            state.regist.userPassword2ErrMsg=msg;
        },
    },
    actions: {
        validateUserName({ commit }, value){
            this.state.userStore.regist.isUserNameValid = value.length > 0 ? true : false;
            if(!this.getters["userStore/isUserNameFocusAndValid"]) commit("SET_ERR_USER_NAME", "이름을 입력해주세요.");
            else commit("SET_ERR_USER_NAME", "");
        },

        validateUserEmail({ commit }, value){
            let regexp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            this.state.userStore.regist.isUserEmailValid = regexp.test(value) ? true : false;
            if(!this.getters["userStore/isUserEmailFocusAndValid"]) commit("SET_ERR_USER_EMAIL", "올바른 이메일을 입력해 주세요.");
            else commit("SET_ERR_USER_EMAIL", "");
        },

        validateUserPassword({ commit }, value){
            let patternEngAtListOne = new RegExp(/[a-zA-Z]+/); // + for at least one
            let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/); // + for at least one
            let patternNumAtListOne = new RegExp(/[0-9]+/); // + for at least one

            this.state.userStore.regist.isUserPasswordValid = 
            patternEngAtListOne.test(value) && 
            patternSpeAtListOne.test(value) && 
            patternNumAtListOne.test(value) && 
            value.length >= 8 ? true : false;
            if(!this.getters["userStore/isUserPasswordFocusAndValid"]) commit("SET_ERR_USER_PASSWORD", "1개 이상의 특수문자, 대소문자 및 숫자를 포함하고 8자리 이상이여야 합니다.");
            else commit("SET_ERR_USER_PASSWORD", "");
        },
        validateUserPassword2({ commit }, value){
            this.state.userStore.regist.isUserPassword2Valid = value == this.state.userStore.regist.userPassword ? true : false;
            if(!this.getters["userStore/isUserPassword2FocusAndValid"]) commit("SET_ERR_USER_PASSWORD2", "비밀번호가 일치하지 않습니다.");
            else commit("SET_ERR_USER_PASSWORD2", "");
        },
        
        register({commit}){
            if (!this.state.userStore.regist.isUserNameValid || 
                !this.state.userStore.regist.isUserEmailValid ||
                !this.state.userStore.regist.isUserPasswordValid) return;

            let body = {
                userName: this.state.userStore.regist.userName,
                userEmail: this.state.userStore.regist.userEmail,
                userPassword: this.state.userStore.regist.userPassword,
                userState: '001'
            };
            userAxios.userRegister(
                body,
                ({data}) => {
                    console.log(data);
                    console.log(this._vm);
                    if(data.result=="success"){
                        this._vm.$alertify.alert("회원가입을 축하합니다. 로그인 페이지로 이동합니다", function () {
                            router.push("/login");
                        });
                    }
                    else if(data.result == "duplicate"){
                        this._vm.$alertify.error("중복된 이메일이 존재합니다.");
                    }
                    
                },
                (error) => {
                    console.error(error);
                    console.log(data);
                    this._vm.$alertify.error("서버에 문제가 있습니다.");
                }
            )
        },
        withdraw({commit}){
            
            userAxios.userWithdraw(
                this.state.userStore.user.userSeq,
                ({data}) => {
                    console.log(data);
                    if(data.result=="success"){
                        commit("SET_LOGIN",false);
                        commit("CLEAR_USER");
                        commit("SET_IS_VALID_TOKEN", false);
                        this._vm.$alertify.alert("회원탈퇴가 완료 되었습니다. 더 좋은 인연으로 만나요.", function () {
                            router.push("/main");
                        });
                    }
                    
                },
                (error) => {
                    console.error(error);
                    this._vm.$alertify.error("서버에 문제가 있습니다.");
                }
            )
        },
        modify({commit}){

            let body = {
                userSeq: this.state.userStore.user.userSeq,
                userName: this.state.userStore.user.userName,
                userComment: this.state.userStore.user.userComment,
                userPassword: this.state.userStore.user.userPassword,
            };
            userAxios.userModify(
                body,
                ({data}) => {
                    console.log(data);
                    if(data.result=="success"){
                        this._vm.$alertify.success("정보 수정이 완료 되었습니다.");
                        commit("SET_MODIFY");
                    }
                    
                },
                (error) => {
                    console.error(error);
                    this._vm.$alertify.error("서버에 문제가 있습니다.");
                }
            )
        },
        toggleModify({commit}){
            commit("SET_MODIFY");
        },
        login({commit}){
            let body = {
                userEmail: this.state.userStore.user.userEmail,
                userPassword: this.state.userStore.user.userPassword,
            };
            console.log(body);
            userAxios.login(
                body,
                ({data}) => {
                    if(data.result == "success"){
                        let accessToken = data["access-token"];
                        let refreshToken = data["refresh-token"];
                        let dto = JSON.parse(data.userDto);
                        let payload = {
                            isLogin: true,
                            user: {
                                ...dto,
                            }
                        };
                        commit("SET_LOGIN_INFO",payload);
                        commit("SET_IS_VALID_TOKEN", true);
                        sessionStorage.setItem("access-token", accessToken);
                        sessionStorage.setItem("refresh-token", refreshToken);
                        userAxios.setHeader();
                        router.push("/main");
                    }else{
                        commit("SET_LOGIN", false);
                        commit("CLEAR_USER");
                        commit("SET_IS_VALID_TOKEN", false);
                    }
                },
                (error) => {
                    console.log(error);
                    if (error.code == "404") {
                        this._vm.$alertify.error("이메일 또는 비밀번호를 확인하세요.");
                    } else {
                        this._vm.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
                    }
                }
            )
        },checkToken({commit, dispatch, state}, token){
            let decodeToken = jwtDecode(token);
            if(decodeToken.userSeq === state.user.userSeq){
                userAxios.setHeader();
                userAxios.checkToken(
                    ({data}) => {
                        if(data.result==="success"){
                            console.log("www");
                        }
                    },
                    (error) => {
                        commit("SET_IS_VALID_TOKEN", false);
                        dispatch("tokenRegeneration");
                    }
                );
            }
            
        },
        tokenRegeneration({ commit, state }) {
            userAxios.tokenRegeneration(
              state.user,
              ({ data }) => {
                if (data.result === "success") {
                  let accessToken = data["access-token"];
                  console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
                  sessionStorage.setItem("access-token", accessToken);
                  commit("SET_IS_VALID_TOKEN", true);
                }
              },
              (error) => {
                // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
                if (error.response.status === 401) {
                  console.log("갱신 실패");
                  // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
                  userAxios.logout(
                    state.user.userSeq,
                    ({ data }) => {
                      if (data.message === "success") {
                        console.log("리프레시 토큰 제거 성공");
                      } else {
                        console.log("리프레시 토큰 제거 실패");
                      }
                      this._vm.$alertify.alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                      commit("SET_IS_LOGIN", false);
                      commit("CLEAR_USER");
                      commit("SET_IS_VALID_TOKEN", false);
                      router.push("/login");
                    },
                    (error) => {
                      console.log(error);
                      commit("SET_IS_LOGIN", false);
                      commit("SET_USER_INFO", null);
                    }
                  );
                }
              }
            );
          },
        logout({commit, state}){
            userAxios.logout(
                state.user.userSeq,
                ({data}) => {
                    if(data.result == "success"){
                        commit("SET_LOGIN", false);
                        commit("CLEAR_USER");
                        commit("SET_IS_VALID_TOKEN", false);
                        this._vm.$alertify.success("로그아웃에 성공하였습니다.");
                        router.push("/main");
                    }
                }
            )
        }
        
    },
    getters: {
        isLogin: function(state){
            return state.user.isLogin;
        },
        isUserName: function(state){
            return state.user.userName;
        },
        isUserNameFocusAndValid(state) {
            return state.regist.isUserNameFocus && state.regist.isUserNameValid;
         },
         isUserEmailFocusAndValid(state) {
            return state.regist.isUserEmailFocus && state.regist.isUserEmailValid;
         },
         isUserPasswordFocusAndValid(state) {
            return state.regist.isUserPasswordFocus && state.regist.isUserPasswordValid;
         },
         isUserPassword2FocusAndValid(state) {
            return state.regist.isUserPassword2Focus && state.regist.isUserPassword2Valid;
         },
         checkUserInfo: function (state) {
            return state.user;
          },
          checkToken: function (state) {
            return state.isValidToken;
          },
    }
};