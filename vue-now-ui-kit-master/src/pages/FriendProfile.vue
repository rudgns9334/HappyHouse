<template>
  <div>
    <div class="page-header clear-filter" filter-color="orange">
      <parallax class="page-header-image" style="background-image: url('img/bg5.jpg')"> </parallax>
      <div class="container">
        <div class="photo-container">
          <img :src="user.userProfileImageUrl" alt="" />
        </div>
        <h3 class="title whiteFont">{{ user.userName }}</h3>
        <h5 class="description">"{{ user.userComment }}"</h5>
        <div class="content"></div>
      </div>
    </div>
    <friend-wish-list></friend-wish-list>
  </div>
</template>
<script>
import { mapState } from "vuex";
import FriendWishList from "./components/FriendWishList.vue";

const friendStore = "friendStore";

export default {
  name: "friendprofile",
  bodyClass: "profile-page",
  components: {
    FriendWishList,
  },
  computed: {
    ...mapState(friendStore, ["user"]),
  },
  methods: {},
  mounted() {},
};
</script>
<style>
.whiteFont {
  color: #fff;
}
</style>
