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
          <a class="nav-link" @click="goLogout">
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
          <a
            class="nav-link"
            href="https://www.creative-tim.com/product/vue-now-ui-kit"
            target="_blank"
          >
            <p>Event</p>
          </a>
        </li>
        <li class="nav-item" v-show="isLogin">
          <span @click="callAlerm" class="material-symbols-outlined" :class="[{newIcon: isNew},{noNewIcon: !isNew}]" style="cursor: pointer; padding-top: 7px;">
            circle_notifications
          </span>
        </li>
      </template>
    </navbar>
    <alerm v-if="isAlerm"></alerm>
  </div>
</template>

<script>
import { Navbar } from "@/components";
import { Popover } from "element-ui";
import { mapActions, mapMutations, mapState } from "vuex";
import Alerm from "../pages/components/Alerm.vue";

const userStore = "userStore";
const alermStore = "alermStore";

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
    ...mapState(alermStore,["isAlerm", "isNew"]),
  },
  methods: {
    ...mapActions(userStore, ["logout"]),
    ...mapActions(alermStore,["alermList", "alermReadAll"]),
    ...mapMutations(alermStore,["SET_IS_ALERM","INIT"]),
    callAlerm(){
      this.SET_IS_ALERM(!this.isAlerm);
      if(!this.isAlerm){
        this.alermReadAll();
      }
    },
    goLogout(){
      this.INIT();
      this.logout();
    },
    checkAlarm(){
      if(this.isLogin){
        this.alermList();
      }
      setInterval(()=>{
        if(this.isLogin){
          this.alermList();
        }
      }, 10000);
    }
  },
  created(){
    this.checkAlarm();
  }
};
</script>

<style>
.material-symbols-outlined {
  font-variation-settings: "FILL" 0, "wght" 400, "GRAD" 0, "opsz" 48;
}

.noNewIcon{
  color: #fff;
}
.newIcon {
  color: red;
}
</style>
