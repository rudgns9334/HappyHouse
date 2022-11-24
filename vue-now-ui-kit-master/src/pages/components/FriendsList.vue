<template>
  <div>
    <h3 class="title">친구목록</h3>
    <div class="text-lg-end">
      <span class="material-symbols-outlined" @click="showModal"> person_add </span>
    </div>
    <table class="table">
      <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col"></th>
          <th scope="col"></th>
          <th scope="col">#</th>
          <th scope="col"></th>
          <th scope="col"></th>
          <th scope="col">프로필</th>
          <th scope="col"></th>
          <th scope="col">삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(friend, index) in getFriendList" :key="index">
          <td></td>
          <td></td>
          <td></td>
          <td>{{ friend.userName }} # {{ friend.userSeq }}</td>
          <td></td>
          <td></td>
          <td>
            <span
              class="material-symbols-outlined"
              style="cursor: pointer"
              @click="goFriendProfile(friend.userSeq)"
            >
              home
            </span>
          </td>
          <td></td>
          <td>
            <span
              class="material-symbols-outlined"
              style="cursor: pointer"
              @click="deleteFriend(friend)"
            >
              delete
            </span>
          </td>
        </tr>
      </tbody>
    </table>
    <friend-find-modal v-on:hide="hideModal"></friend-find-modal>
  </div>
</template>

<script>
import { Modal } from "bootstrap";
import { mapState, mapMutations, mapGetters, mapActions } from "vuex";
import FriendFindModal from "../modals/FriendFindModal.vue";
import router from "../../router";

const friendStore = "friendStore";
const userStore = "userStore";
export default {
  components: {
    FriendFindModal,
  },
  data() {
    return {
      isColor: false,
      friendFindModal: null,
    };
  },
  computed: {
    ...mapState(userStore, ["user"]),

    ...mapGetters(friendStore, ["getFriendList"]),
  },
  methods: {
    ...mapMutations(friendStore, ["INIT_SEARCH"]),
    ...mapActions(friendStore, ["friendDelete", "friendDetail"]),

    goFriendProfile(userSeq) {
      this.friendDetail(userSeq);
      router.push("/friendprofile");
    },
    deleteFriend(friend) {
      if (friend.sendUserSeq == this.user.userSeq) {
        this.friendDelete(friend.receiveUserSeq);
      } else if (friend.receiveUserSeq == this.user.userSeq) {
        this.friendDelete(friend.sendUserSeq);
      }
    },
    showModal() {
      this.INIT_SEARCH();
      this.friendFindModal.show();
    },
    hideModal() {
      this.friendFindModal.hide();
    },
  },
  mounted() {
    this.friendFindModal = new Modal(document.getElementById("friendFindModal"));
  },
};
</script>

<style>
.friend {
  width: 350px;
}
.mouse-over-bgcolor {
  background-color: rgba(226, 226, 226, 0.644);
}
</style>
