<template>
  <div>
    <div class="page-header clear-filter" filter-color="orange">
      <parallax
        class="page-header-image"
        style="background-image:url('img/bg5.jpg')"
      >
      </parallax>
      <div class="container">
        <div class="photo-container">
          <img src="img/ryan.jpg" alt="" />
        </div>
<<<<<<< Updated upstream
        <h3 class="title whiteFont" v-if="!isModify">{{user.userName}}</h3>
        <p class="category" v-if="!isModify">{{user.userComment}}</p>
        <fg-input
         v-model="$store.state.userStore.user.userName"
          v-if="isModify"></fg-input>
        
        <fg-input
         v-model="$store.state.userStore.user.userComment"
          v-if="isModify"></fg-input>

          <fg-input
         v-model="$store.state.userStore.user.userPassword"
          v-if="isModify"></fg-input>
        <div class="content">
          <!-- <div class="social-description">
            <h2>26</h2>
            <p>Comments</p>
          </div>
          <div class="social-description">
            <h2>26</h2>
            <p>Comments</p>
          </div>
          <div class="social-description">
            <h2>48</h2>
            <p>Bookmarks</p>
          </div> -->

        </div>
=======
        <span
          class="material-symbols-outlined"
          v-if="isModify"
          title="Add Profile Image"
          style="margin-right: 43.5%; margin-top: -20px; float: right; cursor: pointer"
        >
          add
        </span>
        <h3 class="title whiteFont" v-if="!isModify">{{ user.userName }}</h3>
        <h5 class="description">"{{ user.userComment }}"</h5>
        <div class="content"></div>
>>>>>>> Stashed changes
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="button-container">
<<<<<<< Updated upstream
          <a href="#button" class="btn btn-primary btn-round btn-lg">Follow</a>
          <a
            class="btn btn-default btn-round btn-lg btn-icon"
            @click="toggleModify"
            v-if="!isModify"
          >
            <p>수정</p>
          </a>
          <a
            class="btn btn-default btn-round btn-lg btn-icon"
            @click="modify"
            v-if="isModify"
          >
            <p>완료</p>
          </a>
          <a
            class="btn btn-default btn-round btn-lg btn-icon"
            @click="withdraw"
          >
            <p>삭제</p>
          </a>
        </div>
        <h3 class="title">About me</h3>
        <h5 class="description">
          An artist of considerable range, Ryan — the name taken by
          Melbourne-raised, Brooklyn-based Nick Murphy — writes, performs and
          records all of his own music, giving it a warm, intimate feel with a
          solid groove structure. An artist of considerable range.
        </h5>
        <div class="row">
          <div class="col-md-6 ml-auto mr-auto">
            <h4 class="title text-center">My Portfolio</h4>
=======
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
            <div>
              <fg-input
                v-model="$store.state.userStore.user.userPassword"
                style="margin: auto; width: 500px"
                type="password"
              ></fg-input
              ><span
                class="material-symbols-outlined"
                style="float: right; margin-top: -53px; margin-right: 230px; cursor: pointer"
              >
                visibility
              </span>
            </div>
            <p class="category">Confirm</p>
            <div>
              <fg-input
                v-model="$store.state.userStore.user.userPassword"
                style="margin: auto; width: 500px"
                type="password"
              ></fg-input
              ><span
                class="material-symbols-outlined"
                style="float: right; margin-top: -53px; margin-right: 230px; cursor: pointer"
              >
                visibility
              </span>
            </div>
>>>>>>> Stashed changes
          </div>
        </div>
<<<<<<< Updated upstream
=======
      </div>
      <div class="section">
        <div class="container">
          <div class="align-items-center">
            <div class="col-lg-6" style="margin: auto">
              <h2 class="font-weight-bold text-primary heading" style="margin: auto; text-align: center">Wish List</h2>
            </div>
            <div class="col-lg-6 text-lg-end">
              <p></p>
            </div>
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
                      </div>
                    </div>
                  </div>
                  <!-- .item -->

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
                      </div>
                    </div>
                  </div>
                </div>

                <div id="property-nav" class="controls" tabindex="0" aria-label="Carousel Navigation">
                  <span class="prev" data-controls="prev" aria-controls="property" tabindex="-1">Prev</span>
                  <span class="next" data-controls="next" aria-controls="property" tabindex="-1">Next</span>
                </div>
              </div>
            </div>
          </div>
        </div>
>>>>>>> Stashed changes
      </div>
    </div>
  </div>
</template>
<script>
<<<<<<< Updated upstream
import { Tabs, TabPane, FormGroupInput } from '@/components';
import { mapActions, mapState } from 'vuex';
=======
import {FormGroupInput} from "@/components";
import {mapActions, mapMutations, mapState} from "vuex";
import FriendsList from "./components/FriendsList.vue";
import custom from "@/common/custom.js";
import menu from "@/common/menu.js";
>>>>>>> Stashed changes

const userStore = "userStore";

export default {
  name: 'profile',
  bodyClass: 'profile-page',
  components: {
<<<<<<< Updated upstream
    Tabs,
    TabPane,
=======
    FriendsList,
>>>>>>> Stashed changes
    [FormGroupInput.name]: FormGroupInput,
  },
  computed:{
    ...mapState(userStore, ["isModify","user"]),
  },
<<<<<<< Updated upstream
  methods:{
    ...mapActions(userStore,["withdraw", "toggleModify", "modify"]),
    
  }
=======
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
>>>>>>> Stashed changes
};
</script>
<style>
.whiteFont {
  color: #fff;
}
</style>
