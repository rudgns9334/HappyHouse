<template>
  <div style="margin-top: 30px; margin-bottom: 100px">
    <h2 class="font-weight-bold text-primary heading" style="margin: auto; text-align: center; margin-bottom: 30px">
      Friends List
    </h2>
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
          <td style="cursor: pointer">
            <span class="material-symbols-outlined"> home </span>
          </td>
          <td></td>
          <td style="cursor: pointer">
            <span class="material-symbols-outlined"> delete </span>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import {mapState} from "vuex";

const friendStore = "friendStore";
export default {
  data() {
    return {
      isColor: false,
    };
  },
  computed: {
    ...mapState(friendStore, ["list"]),
  },
  methods: {
    colorChange(flag) {
      this.isColor = flag;
    },
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
