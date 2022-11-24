<template>
  <div>
    <div class="page-header clear-filter" filter-color="orange">
      <parallax class="page-header-image" style="background-image: url('img/hero_bg_3.jpg')"> </parallax>
      <div class="container">
        <div class="content-center brand">
          <h1 class="heading" data-aos="fade-up">The Easiest Way To Find My Home</h1>
          <!-- <search-bar></search-bar> -->
        </div>
      </div>
    </div>
    <popular-apt></popular-apt>
    <review-list></review-list>
    <img src="img/bg11.jpg" alt="" />
  </div>
</template>
<script>
import {Parallax} from "@/components";
// import SearchBar from "./components/SearchBar.vue";
import PopularApt from "./components/PopularApt.vue";
import ReviewList from "./components/ReviewList.vue";

export default {
  name: "index",
  bodyClass: "index-page",
  components: {
    Parallax,
    // SearchBar,
    PopularApt,
    ReviewList,
  },
};
</script>
<style>
.heading {
  color: #fff;
  font-size: clamp(1.5rem, 2.5vw, 2.5rem);
  margin-bottom: 30px;
  font-weight: 600;
}
</style>
