import util from "@/common/util.js";
import userAxios from "@/common/axios/user.js";

import store from "@/store/store.js";
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
    user: {
      userSeq: 0,
      userName: "",
      userPassword: "admin1234!",
      userEmail: "admin@ssafy.com",
      userProfileImageUrl: "",
      userRegisterDate: "",
      userState: "",
      userEventPart: "",
      userComment: "",
    },
    regist: {
      userName: "",
      userEmail: "",
      userPassword: "",
      userPassword2: "",

      isUserNameValid: false,
      isUserEmailValid: false,
      isUserPasswordValid: false,
      isUserPassword2Valid: false,

      isUserNameFocus: false,
      isUserEmailFocus: false,
      isUserPasswordFocus: false,
      isUserPassword2Focus: false,

      userNameErrMsg: "",
      userEmailErrMsg: "",
      userPasswordErrMsg: "",
      userPassword2ErrMsg: "",
    },
    admin:{
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
         user: {
          userSeq: 0,
          userName: "",
          userPassword: "",
          userEmail: "",
          userProfileImageUrl: "",
          userRegisterDate: "",
          userState: "",
          userEventPart: "",
          userComment: "",
        },
    }
  },
  mutations: {
    SET_LOGIN_INFO(state, payload) {
      state.isLogin = payload.isLogin;
      state.user = payload.user;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_LOGIN(state, isLogin) {
      state.isLogin = isLogin;
    },
    SET_MODIFY(state) {
      state.isModify = !state.isModify;
    },
    INIT_MODIFY(state) {
      state.isModify = false;
    },
    CLEAR_USER(state) {
      state.user.userSeq = 0;
      state.user.userName = "";
      state.user.userPassword = "admin1234!";
      state.user.userEmail = "admin@ssafy.com";
      state.user.ProfileImageUrl = "";
      state.user.userRegisterDate = "";
      state.user.userState = "";
      state.user.userEventPart = "";
      state.user.userComment = "";
    },
    CLEAR_REGISTER(state) {
      state.regist.userName = "";
      state.regist.userPassword = "";
      state.regist.userPassword2 = "";
      state.regist.userEmail = "";
    },
    SET_ERR_USER_NAME(state, msg) {
      state.regist.userNameErrMsg = msg;
    },
    SET_ERR_USER_EMAIL(state, msg) {
      state.regist.userEmailErrMsg = msg;
    },
    SET_ERR_USER_PASSWORD(state, msg) {
      state.regist.userPasswordErrMsg = msg;
    },
    SET_ERR_USER_PASSWORD2(state, msg) {
      state.regist.userPassword2ErrMsg = msg;
    },
    SET_USER_LIST(state, list){
      state.admin.list = list;
      state.admin.countCurrentList = list.length;
  },
  SET_USER_TOTAL_LIST_ITEM_COUNT(state, count) {
      state.admin.totalListItemCount = count;
      // store.state.boardStore.totalListItemCount = count;
   },

   SET_USER_MOVE_PAGE(state, pageIndex) {
      state.admin.offset = (pageIndex - 1) * state.listRowCount;
      state.admin.currentPageIndex = pageIndex;
      // store.state.boardStore.offset = (pageIndex - 1) * state.listRowCount;
      // store.state.boardStore.currentPageIndex = pageIndex;
   },
   SET_USER_DETAIL(state, payload) {
      state.admin.user.userSeq = payload.userSeq;
      state.admin.user.userName = payload.userName;
      state.admin.user.userPassword = payload.userPassword;
      state.admin.user.userEmail = payload.userEmail;
      state.admin.user.regDate = util.makeDateStr(payload.userRegisterDate.date.year, payload.userRegisterDate.date.month, payload.userRegisterDate.date.day, ".");
      state.admin.user.regTime = util.makeTimeStr(payload.userRegisterDate.time.hour, payload.userRegisterDate.time.minute, payload.userRegisterDate.time.second, ":");
      state.admin.user.userProfileImageUrl = payload.userProfileImageUrl;
      state.admin.user.userState = payload.userState;
      state.admin.user.userComment = payload.userComment;
   },
  },
  actions: {
    validateUserName({commit}, value) {
      this.state.userStore.regist.isUserNameValid = value.length > 0 ? true : false;
      if (!this.getters["userStore/isUserNameFocusAndValid"]) commit("SET_ERR_USER_NAME", "이름을 입력해주세요.");
      else commit("SET_ERR_USER_NAME", "");
    },

    validateUserEmail({commit}, value) {
      let regexp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      this.state.userStore.regist.isUserEmailValid = regexp.test(value) ? true : false;
      if (!this.getters["userStore/isUserEmailFocusAndValid"])
        commit("SET_ERR_USER_EMAIL", "올바른 이메일을 입력해 주세요.");
      else commit("SET_ERR_USER_EMAIL", "");
    },

    validateUserPassword({commit}, value) {
      let patternEngAtListOne = new RegExp(/[a-zA-Z]+/); // + for at least one
      let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/); // + for at least one
      let patternNumAtListOne = new RegExp(/[0-9]+/); // + for at least one

      this.state.userStore.regist.isUserPasswordValid =
        patternEngAtListOne.test(value) &&
        patternSpeAtListOne.test(value) &&
        patternNumAtListOne.test(value) &&
        value.length >= 8
          ? true
          : false;
      if (!this.getters["userStore/isUserPasswordFocusAndValid"])
        commit("SET_ERR_USER_PASSWORD", "1개 이상의 특수문자, 대소문자 및 숫자를 포함하고 8자리 이상이여야 합니다.");
      else commit("SET_ERR_USER_PASSWORD", "");
    },
    validateUserPassword2({commit}, value) {
      this.state.userStore.regist.isUserPassword2Valid =
        value == this.state.userStore.regist.userPassword ? true : false;
      if (!this.getters["userStore/isUserPassword2FocusAndValid"])
        commit("SET_ERR_USER_PASSWORD2", "비밀번호가 일치하지 않습니다.");
      else commit("SET_ERR_USER_PASSWORD2", "");
    },

    register({commit}) {
      if (
        !this.state.userStore.regist.isUserNameValid ||
        !this.state.userStore.regist.isUserEmailValid ||
        !this.state.userStore.regist.isUserPasswordValid
      )
        return;

      let body = {
        userName: this.state.userStore.regist.userName,
        userEmail: this.state.userStore.regist.userEmail,
        userPassword: this.state.userStore.regist.userPassword,
        userState: "001",
      };
      userAxios.userRegister(
        body,
        ({data}) => {
          console.log(data);
          if (data.result == 1) {
            let params = {
              receiveUserSeq: data.userSeq,
              contentType: "001"
            }
            store.dispatch("alermStore/alermSend",params);
            alertify
              .alert("Congratulation! Go to Login Page", function () {
                router.push("/login");
              })
              .setHeader("<em> SUCCESS! </em> ");
          } else if (data.result == "duplicate") {
            alertify.alert("Duplicated E-mail").setHeader("<em> FAIL! </em> ");
          }
        },
        error => {
          console.error(error);
          console.log(data);
          alertify.alert("Server Error").setHeader("<em> ERROR </em> ");
        },
      );
    },
    withdraw({commit}) {
      userAxios.userWithdraw(
        this.state.userStore.user.userSeq,
        ({data}) => {
          console.log(data);
          if (data.result == "success") {
            commit("SET_LOGIN", false);
            commit("CLEAR_USER");
            commit("SET_IS_VALID_TOKEN", false);
            alertify
              .alert("Withdrawed. Thank you for using", function () {
                router.push("/main");
              })
              .setHeader("<em> SUCCESS! </em> ");
          }
        },
        error => {
          console.error(error);
          alertify.alert("Server Error").setHeader("<em> ERROR </em> ");
        },
      );
    },
    modify({commit}) {
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
          if (data.result == "success") {
            alertify.success("Modification Completed");
            commit("SET_MODIFY");
          }
        },
        error => {
          console.error(error);
          alertify.alert("Server Error").setHeader("<em> ERROR </em> ");
        },
      );
    },
    toggleModify({commit}) {
      commit("SET_MODIFY");
    },
    login({commit}) {
      let body = {
        userEmail: this.state.userStore.user.userEmail,
        userPassword: this.state.userStore.user.userPassword,
      };
      console.log(body);
      userAxios.login(
        body,
        ({data}) => {
          if (data.result == "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            let dto = JSON.parse(data.userDto);
            let payload = {
              isLogin: true,
              user: {
                ...dto,
              },
            };
            commit("SET_LOGIN_INFO", payload);
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
            userAxios.setHeader();
            store.dispatch("alermStore/alermList");
            console.log(dto.userName);
            alertify
              .alert("Hello, " + dto.userName, function () {
                router.push("/main");
              })
              .setHeader("<em> SUCCESS! </em> ");
          } else {
            commit("SET_LOGIN", false);
            commit("CLEAR_USER");
            commit("SET_IS_VALID_TOKEN", false);
          }
        },
        error => {
          console.log(error.request.status);
          if (error.request.status == "404") {
            alertify.alert("Check your E-mail or Password Again!").setHeader("<em> ERROR </em> ");
          } else {
            alertify.alert("Server Error").setHeader("<em> ERROR </em> ");
          }
        },
      );
    },
    checkToken({commit, dispatch, state}, token) {
      let decodeToken = jwtDecode(token);
      if (decodeToken.userSeq === state.user.userSeq) {
        userAxios.setHeader();
        userAxios.checkToken(
          ({data}) => {
            if (data.result === "success") {
              console.log("www");
            }
          },
          error => {
            commit("SET_IS_VALID_TOKEN", false);
            dispatch("tokenRegeneration");
          },
        );
      }
    },
    tokenRegeneration({commit, state}) {
      userAxios.tokenRegeneration(
        state.user,
        ({data}) => {
          if (data.result === "success") {
            let accessToken = data["access-token"];
            console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        error => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            userAxios.logout(
              state.user.userSeq,
              ({data}) => {
                if (data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alertify
                  .alert("Login Again Please", function () {
                    router.push("/main");
                  })
                  .setHeader("<em> RefreshToken Expired!!! </em> ");
                commit("SET_IS_LOGIN", false);
                commit("CLEAR_USER");
                commit("SET_IS_VALID_TOKEN", false);
                router.push("/login");
              },
              error => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              },
            );
          }
        },
      );
    },
    logout({commit, state}) {
      userAxios.logout(state.user.userSeq, ({data}) => {
        if (data.result == "success") {
          commit("SET_LOGIN", false);
          commit("CLEAR_USER");
          commit("SET_IS_VALID_TOKEN", false);
          alertify.success("로그아웃에 성공하였습니다.");
          router.push("/main");
        }
      });
    },

    userList({commit}){
      let params = {
        limit: this.state.userStore.admin.limit,
        offset: this.state.userStore.admin.offset,
        searchWord: this.state.userStore.admin.searchWord,
      };
      userAxios.userList(
        params,
        ({data}) => {
          commit("SET_USER_LIST", data.list);
          commit("SET_USER_TOTAL_LIST_ITEM_COUNT", data.count);
        },
        (error) => {
            console.error(error);
            this._vm.$alertify.error("서버에 문제가 있습니다.");
        });
    },
    userDetail({commit}, userSeq){
      let params = {
          userSeq
       };
       userAxios.userDetail(
          params,
          ({data}) => {
              let { dto } = data;
              console.log(dto);
              commit("SET_USER_DETAIL", dto);
          },
          (error) => {
              console.log("UserMainVue: error : ");
              console.log(error);
          }
      )
  },
    userDelete({dispatch}, userSeq){
      userAxios.userDelete(
        userSeq,
        ({data}) => {
          this._vm.$alertify.success("회원삭제");
          dispatch("userList")
        },
        (error) => {
          console.log("UserMainVue: error : ");
          console.log(error);
        }
      )
    }
  },
  getters: {
    isLogin: function (state) {
      return state.user.isLogin;
    },
    isUserName: function (state) {
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
    getList(state){
      return state.admin.list;
    },
    getAdminUser(state){
      return state.admin.user;
    },
     // pagination
    getPageCount: function (state) {
      return Math.ceil(state.admin.totalListItemCount / state.admin.listRowCount);
  },
  getStartPageIndex: function (state) {
      if (state.admin.currentPageIndex % state.admin.pageLinkCount == 0) {
      //10, 20...맨마지막
      return (state.admin.currentPageIndex / state.admin.pageLinkCount - 1) * state.admin.pageLinkCount + 1;
      } else {
      return Math.floor(state.admin.currentPageIndex / state.admin.pageLinkCount) * state.admin.pageLinkCount + 1;
      }
  },
  getEndPageIndex: function (state, getters) {
      let ret = 0;
      if (state.admin.currentPageIndex % state.admin.pageLinkCount == 0) {
      //10, 20...맨마지막
      
      ret = (state.admin.currentPageIndex / state.admin.pageLinkCount - 1) * state.admin.pageLinkCount + state.admin.pageLinkCount;
      } else {
      ret = Math.floor(state.admin.currentPageIndex / state.admin.pageLinkCount) * state.admin.pageLinkCount + state.admin.pageLinkCount;
      }
      // 위 오류나는 코드를 아래와 같이 비교해서 처리
      return ret > getters.getPageCount ? getters.getPageCount : ret;
  },
  getPrev: function (state) {
      if (state.admin.currentPageIndex <= state.admin.pageLinkCount) {
      return false;
      } else {
      return true;
      }
  },
  getNext: function (state, getters) {
      if (Math.floor(getters.getPageCount / state.admin.pageLinkCount) * state.admin.pageLinkCount < state.admin.currentPageIndex) {
      return false;
      } else {
      return true;
      }
  },
  },
};
