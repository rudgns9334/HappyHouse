<template>
  <div class="modal" tabindex="-1" id="friendFindModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">친구 찾기</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <div class="container">
            <input
              type="text"
              class="form-control"
              id="inputFriendSearch"
              v-model="$store.state.friendStore.searchWord"
              placeholder="이름을 입력해주세요."
              @keyup.enter="friendListSearchWord"
            />
            <button
              class="btn btn-round btn-primary"
              type="button"
              id="#btnFriendSearch"
              @click="friendListSearchWord"
            >
              Search
            </button>
          </div>
          <div style="overflow: auto">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col" class="text-center" style="width: 30%">친구요청</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(user, index) in getUserList" :key="index">
                  <td>{{ user.userName }}#{{ user.userSeq }}</td>
                  <td class="text-center">
                    <span
                      class="material-symbols-outlined"
                      style="cursor: pointer"
                      v-if="user.friendState == '000'"
                      @click="requestFriend(user.userSeq)"
                    >
                      add
                    </span>
                    <span
                      class="material-symbols-outlined"
                      style="cursor: pointer"
                      v-if="user.friendState == '001'"
                      @click="cancelFriend(user.userSeq)"
                    >
                      minimize
                    </span>
                    <span class="material-symbols-outlined" v-if="user.friendState == '002'">
                      check
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-round btn-primary" type="button" @click="hide">닫기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";

const friendStore = "friendStore";
const alermStore = "alermStore";

export default {
  computed: {
    ...mapGetters(friendStore, ["getUserList"]),
  },
  methods: {
    ...mapMutations(friendStore, ["INIT_SEARCH"]),
    ...mapActions(friendStore, ["friendListSearchWord", "friendRequest", "friendDelete"]),
    ...mapActions(alermStore, ["alermSend", "alermDeleteWithSeq"]),

    requestFriend(userSeq) {
      this.friendRequest(userSeq);
      let params = {
        receiveUserSeq: userSeq,
        contentType: "002"
      };
      this.alermSend(params);
    },

    cancelFriend(userSeq){
      this.friendDelete(userSeq);
      this.alermDeleteWithSeq(userSeq);
    },

    hide(){
      this.$emit("hide");
    }
  },
};
</script>

<style>
#inputFriendSearch {
  float: left;
  width: 79%;
  height: 40px;
  margin-top: 10px;
}
#btnFriendSearch {
  float: left;
  height: 40px;
  margin-top: 0px;
}
</style>
