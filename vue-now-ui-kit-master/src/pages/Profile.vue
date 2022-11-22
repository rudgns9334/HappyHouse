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
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="button-container">
          <a href="#button" class="btn btn-primary btn-round btn-lg">Follow</a>
          <a
            class="btn btn-default btn-round btn-lg btn-icon"
            @click="toggleModify"
            v-if="!isModify"
          >
            <p>수정</p>
          </a>
          <a class="btn btn-default btn-round btn-lg btn-icon" @click="modify" v-if="isModify">
            <p>완료</p>
          </a>
          <a class="btn btn-default btn-round btn-lg btn-icon" @click="withdraw">
            <p>삭제</p>
          </a>
        </div>
        <div v-if="!isModify">
          <h3 class="title">한줄평</h3>
          <h5 class="description">
            {{ user.userComment }}
          </h5>
        </div>
        <div v-if="isModify">
          <p class="category">이름</p>
          <fg-input v-model="$store.state.userStore.user.userName"></fg-input>
          <p class="category">한줄평</p>
          <fg-input v-model="$store.state.userStore.user.userComment"></fg-input>
          <p class="category">비밀번호</p>
          <fg-input v-model="$store.state.userStore.user.userPassword"></fg-input>
        </div>

        <div class="row">
          <div class="col-md-6 ml-auto mr-auto">
            <h4 class="title text-center">My Portfolio</h4>
          </div>
          <tabs
            pills
            class="nav-align-center"
            tab-content-classes="gallery"
            tab-nav-classes="nav-pills-just-icons"
            type="primary"
          >
            <tab-pane title="Profile">
              <i slot="label" class="now-ui-icons design_image"></i>

              <div class="col-md-10 ml-auto mr-auto">
                <div class="row collections">
                  <div class="col-md-6">
                    <img src="img/bg6.jpg" class="img-raised" />
                    <img src="img/bg11.jpg" alt="" class="img-raised" />
                  </div>
                  <div class="col-md-6">
                    <img src="img/bg7.jpg" alt="" class="img-raised" />
                    <img src="img/bg8.jpg" alt="" class="img-raised" />
                  </div>
                </div>
              </div>
            </tab-pane>

            <tab-pane title="Home">
              <i slot="label" class="now-ui-icons location_world"></i>

              <div class="col-md-10 ml-auto mr-auto">
                <div class="row collections">
                  <div class="col-md-6">
                    <img src="img/bg1.jpg" alt="" class="img-raised" />
                    <img src="img/bg3.jpg" alt="" class="img-raised" />
                  </div>
                  <div class="col-md-6">
                    <img src="img/bg8.jpg" alt="" class="img-raised" />
                    <img src="img/bg7.jpg" alt="" class="img-raised" />
                  </div>
                </div>
              </div>
            </tab-pane>

            <tab-pane title="Messages">
              <i slot="label" class="now-ui-icons sport_user-run"></i>

              <div class="col-md-10 ml-auto mr-auto">
                <div class="row collections">
                  <div class="col-md-6">
                    <img src="img/bg3.jpg" alt="" class="img-raised" />
                    <img src="img/bg8.jpg" alt="" class="img-raised" />
                  </div>
                  <div class="col-md-6">
                    <img src="img/bg7.jpg" alt="" class="img-raised" />
                    <img src="img/bg6.jpg" class="img-raised" />
                  </div>
                </div>
              </div>
            </tab-pane>
          </tabs>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Tabs, TabPane, FormGroupInput } from "@/components";
import { mapActions, mapMutations, mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "profile",
  bodyClass: "profile-page",
  components: {
    Tabs,
    TabPane,
    [FormGroupInput.name]: FormGroupInput,
  },
  computed: {
    ...mapState(userStore, ["isModify", "user"]),
  },
  methods: {
    ...mapMutations(userStore, ["INIT_MODIFY"]),
    ...mapActions(userStore, ["withdraw", "toggleModify", "modify"]),
  },
  mounted() {
    this.INIT_MODIFY();
  },
};
</script>
<style>
.whiteFont {
  color: #fff;
}
</style>
