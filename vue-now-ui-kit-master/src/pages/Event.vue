<template>
  <div>
    <mobile-menu></mobile-menu>
    <div class="page-header page-header-more-small page-inner">
      <parallax class="page-header-image" style="background-image: url('img/hero_bg_3.jpg')"> </parallax>
      <div class="container">
        <div class="row justify-content-center align-items-center">
          <div class="col-lg-9 text-center" style="margin-bottom: 250px">
            <h1 class="heading">Event</h1>
          </div>
        </div>
      </div>
    </div>
    <event-list></event-list>
  </div>
</template>

<script>
import {Parallax} from "@/components";
import MobileMenu from "./components/MobileMenu.vue";
import EventList from "./components/EventList.vue";
export default {
  name: "event",
  components: {
    Parallax,
    MobileMenu,
    EventList,
  },
};
</script>

<style>
.heading {
  color: #fff;
  font-size: clamp(1.5rem, 2.5vw, 2.5rem);
  margin-bottom: 20px;
  margin-top: 50px;
  font-weight: 600;
}
</style>
