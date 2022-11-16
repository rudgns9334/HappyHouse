import Vue from "vue";
import Router from "vue-router";
import Main from "./pages/Main.vue";
import Landing from "./pages/Landing.vue";
import Login from "./pages/Login.vue";
import Register from "./pages/Register.vue";
import Profile from "./pages/Profile.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import Apt from "./pages/AptDetail.vue";
import Notice from "./pages/Notice.vue";

Vue.use(Router);

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
      components: {default: Register, header: MainNavbar},
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
      components: {default: Apt, header: MainNavbar},
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
