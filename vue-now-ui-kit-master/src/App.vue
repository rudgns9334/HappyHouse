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
  computed: {},
  methods: {
    init() {
      this.$nextTick(() => {
        custom.init();
        menu.init();
      });
    },
  },
  created() {
    this.init();
  },
  updated() {
    this.init();
  },
};
</script>
