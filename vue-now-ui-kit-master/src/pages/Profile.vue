<template>
  <div>
    <div class="page-header clear-filter" filter-color="orange">
      <parallax class="page-header-image" style="background-image: url('img/bg5.jpg')"> </parallax>
      <div class="container">
        <div class="photo-container">
          <img :src="user.userProfileImageUrl" alt="" />
        </div>
        <span class="material-symbols-outlined" v-if="isModify"> add </span>
        <h3 class="title whiteFont" v-if="!isModify">{{ user.userName }}</h3>
        <h5 class="description">"{{ user.userComment }}"</h5>
        <div class="content"></div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="button-container">
          <a @click="callFriendList" class="btn btn-primary btn-lg" style="color: #fff">My Friends</a>
          <a class="btn" @click="toggleModify" v-if="!isModify && !isFriendList" title="Edit Profile">
            <span class="material-symbols-outlined" style="color: #fff"> edit_square </span>
          </a>
          <a class="btn" @click="modify" v-if="isModify && !isFriendList" title="Finish Edit">
            <span class="material-symbols-outlined" style="color: #fff"> how_to_reg </span>
          </a>
          <a class="btn" @click="withdraw" v-if="!isFriendList" title="Remove Account">
            <span class="material-symbols-outlined" style="color: #fff"> person_remove </span>
          </a>
        </div>
        <friends-list v-if="isFriendList"></friends-list>
        <div v-if="!isFriendList" style="margin-top: 20px">
          <div v-if="isModify">
            <p class="category">Name</p>
            <fg-input v-model="$store.state.userStore.user.userName" style="margin: auto; width: 500px"></fg-input>
            <p class="category">Comment</p>
            <fg-input v-model="$store.state.userStore.user.userComment" style="margin: auto; width: 500px"></fg-input>
            <p class="category">Password</p>
            <fg-input
              v-model="$store.state.userStore.user.userPassword"
              style="margin: auto; width: 500px"
              type="password"
            ></fg-input>
            <p class="category">Confirm</p>
            <fg-input
              v-model="$store.state.userStore.user.userPassword"
              style="margin: auto; width: 500px"
              type="password"
            ></fg-input>
          </div>
        </div>
      </div>
    </div>
    <div style="height: 120px"></div>
    <div class="section">
      <div class="container">
        <div class="align-items-center">
          <div class="col-lg-6 align-items-center">
            <h2 class="font-weight-bold text-primary heading">Wish List</h2>
          </div>
          <div class="col-lg-6 text-lg-end"></div>
        </div>
        <div class="row">
          <div class="col-12">
            <div class="property-slider-wrap">
              <div class="property-slider">
                <div class="property-item">
                  <a href="/login" class="img">
                    <img src="/images/img_1.jpg" alt="Image" class="img-fluid" />
                  </a>

                  <div class="property-content">
                    <div class="price mb-2"><span>13,500만원</span></div>
                    <div>
                      <span class="d-block mb-2 text-black-50">서울특별시 종로구 충신동 62-2</span>
                      <span class="city d-block mb-3">충신동 CS타워</span>

                      <div class="specs d-flex mb-4">
                        <span class="d-block d-flex align-items-center me-3">
                          <span class="caption">면적: 22.17</span>
                        </span>
                      </div>

                      <a href="/login" class="btn btn-primary py-2 px-3">See details</a>
                    </div>
                  </div>
                </div>
                <!-- .item -->

                <div class="property-item">
                  <a href="/login" class="img">
                    <img src="images/img_2.jpg" alt="Image" class="img-fluid" />
                  </a>

                  <div class="property-content">
                    <div class="price mb-2"><span>26,000만원</span></div>
                    <div>
                      <span class="d-block mb-2 text-black-50">서울특별시 종로구 행촌동 사직로 21</span>
                      <span class="city d-block mb-3">행촌동 대성맨션</span>

                      <div class="specs d-flex mb-4">
                        <span class="d-block d-flex align-items-center me-3">
                          <span class="caption">면적: 19.27</span>
                        </span>
                      </div>

                      <a href="/login" class="btn btn-primary py-2 px-3">See details</a>
                    </div>
                  </div>
                </div>
                <!-- .item -->

                <div class="property-item">
                  <a href="/login" class="img">
                    <img src="images/img_3.jpg" alt="Image" class="img-fluid" />
                  </a>

                  <div class="property-content">
                    <div class="price mb-2"><span>139,900만원</span></div>
                    <div>
                      <span class="d-block mb-2 text-black-50">서울특별시 종로구 무악동 통일로18길 9</span>
                      <span class="city d-block mb-3">무악동 인왕산아이파크</span>

                      <div class="specs d-flex mb-4">
                        <span class="d-block d-flex align-items-center me-3">
                          <span class="icon-bed me-2"></span>
                          <span class="caption">면적: 84.858</span>
                        </span>
                      </div>

                      <a href="/login" class="btn btn-primary py-2 px-3">See details</a>
                    </div>
                  </div>
                </div>
                <!-- .item -->

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
  </div>
</template>
<script>
import {FormGroupInput} from "@/components";
import {mapActions, mapMutations, mapState} from "vuex";
import FriendsList from "./components/FriendsList.vue";
import custom from "@/common/custom.js";
import menu from "@/common/menu.js";

const userStore = "userStore";
const friendStore = "friendStore";

export default {
  name: "profile",
  bodyClass: "profile-page",
  components: {
    FriendsList,
    [FormGroupInput.name]: FormGroupInput,
  },
  computed: {
    ...mapState(userStore, ["isModify", "user"]),
    ...mapState(friendStore, ["isFriendList"]),
  },
  methods: {
    ...mapMutations(userStore, ["INIT_MODIFY"]),
    ...mapActions(userStore, ["withdraw", "toggleModify", "modify"]),
    ...mapMutations(friendStore, ["SET_IS_FRIEND_LIST"]),
    ...mapActions(friendStore, ["friendList"]),

    callFriendList() {
      this.SET_IS_FRIEND_LIST(!this.isFriendList);
      this.friendList(this.user.userSeq);
    },
  },
  mounted() {
    this.INIT_MODIFY();
    custom.init();
    menu.init();
  },
};
</script>
<style>
.whiteFont {
  color: #fff;
}
</style>
