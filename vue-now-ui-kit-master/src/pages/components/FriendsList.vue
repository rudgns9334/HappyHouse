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
        <tr v-for="(friend, index) in list" :key="index">
          <td></td>
          <td></td>
          <td></td>
          <td>{{ friend.userName }} # {{ friend.userSeq }}</td>
          <td></td>
          <td></td>
          <td>
            <span class="material-symbols-outlined" style="cursor: pointer"> home </span>
          </td>
          <td></td>
          <td>
            <span class="material-symbols-outlined" style="cursor: pointer"> delete </span>
          </td>
        </tr>
      </tbody>
    </table>
    <friend-find-modal></friend-find-modal>
  </div>
</template>

<script>
import { Modal } from "bootstrap";
import { mapState } from "vuex";
import FriendFindModal from "../modals/FriendFindModal.vue";

const friendStore = "friendStore";
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
    ...mapState(friendStore, ["list"]),
  },
  methods: {
    showModal() {
      this.friendFindModal.show();
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
