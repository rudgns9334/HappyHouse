<template>
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
        v-show="$store.getters.isLogin"
        style="display: inline-block; padding-top: 13px; padding-left: 10px; color: #fff"
      >
        Hello,{{ $store.getters.isUserName }}
      </div>
    </template>
    <template slot="navbar-menu">
      <li class="nav-item">
        <router-link class="nav-link" to="/login">
          <p>Login</p>
        </router-link>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          href="https://www.creative-tim.com/product/vue-now-ui-kit"
          target="_blank"
        >
          <p>Logout</p>
        </a>
      </li>
      <li class="nav-item">
        <router-link class="nav-link" to="/register">
          <p>Register</p>
        </router-link>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          href="https://www.creative-tim.com/product/vue-now-ui-kit"
          target="_blank"
        >
          <p>MyPage</p>
        </a>
      </li>
      <li class="nav-item">
        <router-link class="nav-link" to="/notice">
          <p>Notice</p>
        </router-link>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          href="https://www.creative-tim.com/product/vue-now-ui-kit"
          target="_blank"
        >
          <p>Event</p>
        </a>
      </li>
    </template>
  </navbar>
</template>

<script>
import { Navbar } from "@/components";
import { Popover } from "element-ui";
export default {
  name: "main-navbar",
  props: {
    transparent: Boolean,
    colorOnScroll: Number,
  },
  components: {
    Navbar,
    [Popover.name]: Popover,
  },
};
</script>

<style scoped></style>
