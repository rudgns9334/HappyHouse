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
    <wish-list></wish-list>
  </div>
</template>
<script>
import {FormGroupInput} from "@/components";
import {mapActions, mapMutations, mapState} from "vuex";
import FriendsList from "./components/FriendsList.vue";
import WishList from "./components/WishList.vue";

const userStore = "userStore";
const friendStore = "friendStore";

export default {
  name: "profile",
  bodyClass: "profile-page",
  components: {
    FriendsList,
    [FormGroupInput.name]: FormGroupInput,
    WishList,
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
  },
};
</script>
<style>
.whiteFont {
  color: #fff;
}
</style>
