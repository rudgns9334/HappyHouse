<template>
  <div id="app">
    <mobile-menu></mobile-menu>
    <router-view name="header" />
    <div class="wrapper">
      <router-view />
    </div>
    <router-view name="footer" />
    <pre-loader></pre-loader>
  </div>
</template>
<script>
import custom from "@/common/custom.js";
import menu from "@/common/menu.js";
import PreLoader from "./pages/components/PreLoader.vue";
import MobileMenu from "./pages/components/MobileMenu.vue";

export default {
  components: {
    PreLoader,
    MobileMenu,
  },
  mounted() {
    custom.init();
    menu.init();
  },
  updated() {
    custom.init();
    menu.init();
  },
};
</script>
