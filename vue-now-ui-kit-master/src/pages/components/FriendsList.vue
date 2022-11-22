<template>
  <div>
    <h3 class="title">친구목록</h3>
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
          <td>{{ friend.userName }} # {{friend.userSeq}}</td>
          <td></td>
          <td></td>
          <td style="cursor: pointer">
            <span class="material-symbols-outlined">
                home
            </span>
          </td>
          <td></td>
          <td style="cursor: pointer">
            <span class="material-symbols-outlined">
                delete
            </span>
        </td>
        
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { mapState } from 'vuex';

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
}
</script>

<style>
.friend {
  width: 350px;
}
.mouse-over-bgcolor {
  background-color: rgba(226, 226, 226, 0.644);
}
</style>