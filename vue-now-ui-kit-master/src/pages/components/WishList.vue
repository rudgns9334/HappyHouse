<template>
  <div class="section">
    <div class="container">
      <div class="align-items-center">
        <div class="col-lg-6" style="margin: auto">
          <h2 class="font-weight-bold text-primary heading" style="text-align: center; margin-bottom: 80px; margin">
            WISH LIST
          </h2>
        </div>
        <div class="col-lg-6 text-lg-end">
          <p></p>
        </div>
      </div>
      <div class="row">
        <div v-show="isEmpty" class="bgDiv">
          <div style="text-align: center">
            <h1 style="color: #fff"><strong>No WishList</strong></h1>
          </div>
        </div>
        <div class="col-12" v-show="!isEmpty">
          <div class="property-slider-wrap">
            <div class="property-slider">
              <div class="property-item" v-for="(apt, index) in list" :key="index">
                <p>{{ apt.no }}</p>
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
                    <a class="btn btn-primary" style="color: #fff" @click="deleteWishItem(apt.no)">DELETE</a>
                  </div>
                </div>
              </div>
              <!-- .item -->
            </div>

            <div id="property-nav" class="controls" tabindex="0" aria-label="Carousel Navigation">
              <span class="prev" data-controls="prev" aria-controls="property" tabindex="-1">Prev</span>
              <span class="next" data-controls="next" aria-controls="property" tabindex="-1">Next</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState, mapActions, mapGetters} from "vuex";

const userStore = "userStore";
const bookMarkStore = "bookMarkStore";
export default {
  name: "wishList",
  computed: {
    ...mapState(userStore, ["user"]),
    ...mapState(bookMarkStore, ["list", "isEmpty"]),
    ...mapGetters(bookMarkStore, ["getWishList"]),
    listGetters() {
      return this.getWishList;
    },
  },
  created() {
    this.callBookMarkList();
  },
  mounted() {
    this.callBookMarkList();
    console.log("@@@@@", this.list);
  },
  methods: {
    ...mapActions(bookMarkStore, ["bookMarkList", "bookMarkDelete"]),

    callBookMarkList() {
      console.log("INIT!!!");
      console.log(this.user);
      this.bookMarkList(this.user.userSeq);
    },
    deleteWishItem(no) {
      console.log(no);
      this.bookMarkDelete(no);
    },
  },
};
</script>

<style>
.bgDiv {
  background-color: #005555;
  height: 300px;
}
.bgDiv h1 {
  margin-top: 120px;
}
</style>
