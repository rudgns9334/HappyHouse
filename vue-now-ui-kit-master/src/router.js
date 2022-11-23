import Vue from "vue";
import Router from "vue-router";
import Main from "./pages/Main.vue";
import Landing from "./pages/Landing.vue";
import Login from "./pages/Login.vue";
import Register from "./pages/Register.vue";
import Profile from "./pages/Profile.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import Apt from "./pages/AptMap.vue";
import Notice from "./pages/Notice.vue";

Vue.use(Router);

<<<<<<< Updated upstream
=======
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
    alertify.confirm("로그인 후 이용하실 수 있습니다.").setHeader("<em> 비회원 로그인 </em> ");
    store.dispatch("userStore/logout");
    next({name: "login"});
    // this.push("/login");
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

>>>>>>> Stashed changes
export default new Router({
  linkExactActiveClass: "active",
  routes: [
    {
      path: "/",
      name: "main",
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
      props: {
        header: {colorOnScroll: 400},
        footer: {backgroundColor: "black"},
      },
    },
    {
      path: "/apt",
      name: "apt",
      components: {default: Apt},
      props: {
        header: {colorOnScroll: 400},
      },
    },
    {
      path: "/notice",
      name: "notice",
      components: {default: Notice, header: MainNavbar, footer: MainFooter},
      props: {
        header: {colorOnScroll: 400},
        footer: {backgroundColor: "black"},
      },
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
