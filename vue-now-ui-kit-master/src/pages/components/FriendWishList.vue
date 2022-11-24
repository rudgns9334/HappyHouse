<template>
  <div class="section">
    <div class="container">
      <div class="align-items-center">
        <div class="col-lg-6">
          <h2 class="font-weight-bold text-primary heading" style="text-align: center">
            Wish List
          </h2>
        </div>
        <div class="col-lg-6 text-lg-end">
          <p></p>
        </div>
      </div>
      <div class="row">
        <div class="col-12">
          <div class="property-slider-wrap">
            <div class="property-slider">
              <div class="property-item" v-for="(apt, index) in list" :key="index">
                <a href="/properties?propNum=1" class="img">
                  <img src="/images/img_1.jpg" alt="Image" class="img-fluid" />
                </a>

                <div class="property-content">
                  <div class="price mb-2">
                    <span>{{ apt.dealAmount }}</span>
                  </div>
                  <div>
                    <span class="d-block mb-2 text-black-50">{{ apt.houseAddress }}</span>
                    <span class="city d-block mb-3">{{ apt.aptName }} {{ apt.floor }}층</span>

                    <div class="specs d-flex mb-4">
                      <span class="d-block d-flex align-items-center me-3">
                        <span class="caption">전용 면적 : {{ apt.area }}m<sup>2</sup></span>
                      </span>
                    </div>
                    <a class="btn btn-primary" style="color: #fff">DELETE</a>
                  </div>
                </div>
              </div>
              <!-- .item -->
            </div>

            <div id="property-nav" class="controls" tabindex="0" aria-label="Carousel Navigation">
              <span class="prev" data-controls="prev" aria-controls="property" tabindex="-1"
                >Prev</span
              >
              <span class="next" data-controls="next" aria-controls="property" tabindex="-1"
                >Next</span
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";

import custom from "@/common/custom.js";
import menu from "@/common/menu.js";

const friendStore = "friendStore";
const bookMarkStore = "bookMarkStore";
export default {
  name: "friendwishList",
  computed: {
    ...mapState(friendStore, ["user"]),
    ...mapState(bookMarkStore, ["list"]),
    ...mapGetters(bookMarkStore, ["getWishList"]),

    listGetters() {
      return this.getWishList;
    },
  },
  created() {
    this.callBookMarkList();
  },
  mounted() {
    console.log(this.list);
  },
  updated() {},
  methods: {
    ...mapActions(bookMarkStore, ["bookMarkList"]),

    callBookMarkList() {
      console.log(this.user);
      this.bookMarkList(this.user.userSeq);
    },
  },
  watch: {
    getWishList(newList) {
      console.log("watch");
      console.log(newList);
      this.$nextTick(() => {
        custom.init();
        menu.init();
      });
    },
  },
};
</script>

<style></style>
