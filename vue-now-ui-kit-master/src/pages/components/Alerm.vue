<template>
  <div class="alerm close">
    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col" class="text-center" style="width: 20%"></th>
          <th scope="col" class="text-center" style="width: 20%">알림함</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(alerm, index) in getList" :key="index">
          <td v-if="alerm.contentType == '001'">회원가입 축하드립니다!</td>
          <td v-if="alerm.contentType == '001'"></td>
          <td v-if="alerm.contentType == '001'" class="text-center">
            <span
              class="material-symbols-outlined"
              style="cursor: pointer; color: red"
              @click="alermDelete(alerm.alermId)"
            >
              delete
            </span>
          </td>
          <td v-if="alerm.contentType == '002'">
            {{ alerm.userName }}님으로 부터 친구 요청이 왔어요!
          </td>
          <td v-if="alerm.contentType == '002'" class="text-center">
            <span
              class="material-symbols-outlined"
              style="cursor: pointer; color: green"
              @click="accept(alerm.sendUserSeq, alerm.alermId)"
            >
              check
            </span>
          </td>
          <td v-if="alerm.contentType == '002'" class="text-center">
            <span
              class="material-symbols-outlined"
              style="cursor: pointer; color: red"
              @click="reject(alerm.sendUserSeq, alerm.alermId)"
            >
              delete
            </span>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";

const alermStore = "alermStore";
const friendStore = "friendStore";

export default {
  computed: {
    ...mapState(alermStore, ["list", "isAlerm", "isNew", "count"]),
    ...mapGetters(alermStore, ["getList"]),
  },
  methods: {
    ...mapActions(alermStore, ["alermList", "alermReadAll", "alermDelete", "alermDeleteAll"]),
    ...mapActions(friendStore, ["friendAccept", "friendDelete"]),

    accept(sendSeq, alermId) {
      this.friendAccept(sendSeq);
      this.alermDelete(alermId);
    },
    reject(sendSeq, alermId) {
      this.friendDelete(sendSeq);
      this.alermDelete(alermId);
    },
  },
};
</script>

<style>
.alerm {
  position: fixed;
  top: 75px;
  right: 130px;
  float: right;
  width: 350px;
  height: 500px;
  z-index: 10;
  overflow: auto;
  background: #fff;
  transition-duration: 1000ms;
}

.alerm.close {
  transform: translateY(-1000px);
  background-color: transparent;
}
</style>
