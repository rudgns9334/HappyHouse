<template>
  <div>
    <navbar
      position="fixed"
      type="primary"
      :transparent="transparent"
      :color-on-scroll="colorOnScroll"
      menu-classes="ml-auto"
    >
      <template>
        <router-link v-popover:popover1 class="navbar-brand" to="/">
          <img src="img/main-logo.png" alt="" style="width: 100px" />
        </router-link>
        <el-popover
          ref="popover1"
          popper-class="popover"
          placement="bottom"
          width="200"
          trigger="hover"
        >
          <div class="popover-body">Designed by Invision. Coded by Creative Tim</div>
        </el-popover>
        <div
          id="header"
          v-show="isLogin"
          style="display: inline-block; padding-top: 13px; padding-left: 10px; color: #fff"
        >
          Hello, {{ user.userName }}
        </div>
      </template>
      <template slot="navbar-menu">
        <li class="nav-item" v-show="!isLogin">
          <router-link class="nav-link" to="/login">
            <p>Login</p>
          </router-link>
        </li>
        <li class="nav-item" v-show="isLogin">
          <a class="nav-link" @click="logout">
            <p>Logout</p>
          </a>
        </li>
        <li class="nav-item" v-show="!isLogin">
          <router-link class="nav-link" to="/register">
            <p>Register</p>
          </router-link>
        </li>
        <li class="nav-item" v-show="isLogin">
          <router-link class="nav-link" to="/profile">
            <p>MyPage</p>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/notice">
            <p>Notice</p>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/event">
            <p>Event</p>
          </router-link>
        </li>
        <li class="nav-item" v-show="isLogin">
          <span class="material-symbols-outlined" style="padding-top: 7px; color: #fff">
            circle_notifications
          </span>
        </li>
      </template>
    </navbar>
    <alerm></alerm>
  </div>
</template>

<script>
import { Navbar } from "@/components";
import { Popover } from "element-ui";
import { mapActions, mapState } from "vuex";
import Alerm from "../pages/components/Alerm.vue";

const userStore = "userStore";

export default {
  name: "main-navbar",
  props: {
    transparent: Boolean,
    colorOnScroll: Number,
  },
  components: {
    Navbar,
    Alerm,
    [Popover.name]: Popover,
  },
  computed: {
    ...mapState(userStore, ["isLogin", "user"]),
  },
  methods: {
    ...mapActions(userStore, ["logout"]),
  },
};
</script>

<style>
.material-symbols-outlined {
  font-variation-settings: "FILL" 0, "wght" 400, "GRAD" 0, "opsz" 48;
}
</style>
