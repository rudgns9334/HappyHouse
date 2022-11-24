import Vue from "vue";
import Router from "vue-router";
import Main from "./pages/Main.vue";
import Landing from "./pages/Landing.vue";
import Login from "./pages/Login.vue";
import Register from "./pages/Register.vue";
import Profile from "./pages/Profile.vue";
import FriendProfile from "./pages/FriendProfile.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import Apt from "./pages/AptMap.vue";
import Notice from "./pages/Notice.vue";
import Event from "./pages/Event.vue";
import Review from "./pages/Review.vue";
import Admin from "./pages/Admin.vue";
import AdminNoticeList from "./pages/components/AdminNoticeList.vue";

import store from "./store/store.js";

Vue.use(Router);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkToken = store.getters["userStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    store.dispatch("userStore/checkToken", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alertify.alert("Go to Login Page").setHeader("<em> Non-Member Access </em> ");
    store.commit("alermStore/INIT");
    store.dispatch("userStore/logout");
    next({name: "login"});
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

export default new Router({
  linkExactActiveClass: "active",
  routes: [
    {
      path: "/",
      components: {default: Main, header: MainNavbar, footer: MainFooter},
      props: {
        header: {colorOnScroll: 400},
        footer: {backgroundColor: "black"},
      },
    },
    {
      path: "/main",
      name: "main",
      components: {default: Main, header: MainNavbar, footer: MainFooter},
      props: {
        header: {colorOnScroll: 400},
        footer: {backgroundColor: "black"},
      },
    },
    {
      path: "/landing",
      name: "landing",
      components: {default: Landing, header: MainNavbar, footer: MainFooter},
      props: {
        header: {colorOnScroll: 400},
        footer: {backgroundColor: "black"},
      },
    },
    {
      path: "/login",
      name: "login",
      components: {default: Login, header: MainNavbar},
      props: {
        header: {colorOnScroll: 400},
      },
    },
    {
      path: "/register",
      name: "register",
      components: {default: Register, header: MainNavbar, footer: MainFooter},
      props: {
        header: {colorOnScroll: 400},
      },
    },
    {
      path: "/profile",
      name: "profile",
      components: {default: Profile, header: MainNavbar, footer: MainFooter},
      beforeEnter: onlyAuthUser,
      props: {
        header: {colorOnScroll: 400},
        footer: {backgroundColor: "black"},
      },
    },
    {
      path: "/friendprofile",
      name: "friendprofile",
      components: {default: FriendProfile, header: MainNavbar, footer: MainFooter},
      beforeEnter: onlyAuthUser,
      props: {
        header: {colorOnScroll: 400},
        footer: {backgroundColor: "black"},
      },
    },
    {
      path: "/apt",
      name: "apt",
      components: {default: Apt},
      beforeEnter: onlyAuthUser,
      props: {
        header: {colorOnScroll: 400},
      },
    },
    {
      path: "/notice",
      name: "notice",
      components: {default: Notice, header: MainNavbar, footer: MainFooter},
      beforeEnter: onlyAuthUser,
      props: {
        header: {colorOnScroll: 400},
        footer: {backgroundColor: "black"},
      },
    },
    {
      path: "/event",
      name: "event",
      components: {default: Event, header: MainNavbar, footer: MainFooter},
      beforeEnter: onlyAuthUser,
      props: {
        header: {colorOnScroll: 400},
        footer: {backgroundColor: "black"},
      },
    },
    {
      path: "/review",
      name: "review",
      components: {default: Review, header: MainNavbar, footer: MainFooter},
      beforeEnter: onlyAuthUser,
      props: {
        header: {colorOnScroll: 400},
        footer: {backgroundColor: "black"},
      },
    },
    {
      path: "/admin",
      name: "admin",
      components: {default: Admin, header: MainNavbar, footer: MainFooter},
      beforeEnter: onlyAuthUser,
      props: {
        header: {colorOnScroll: 400},
        footer: {backgroundColor: "black"},
      }
    },
  ],
  scrollBehavior: to => {
    if (to.hash) {
      return {selector: to.hash};
    } else {
      return {x: 0, y: 0};
    }
  },
});
