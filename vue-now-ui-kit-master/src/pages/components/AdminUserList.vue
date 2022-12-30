<template>
  <div class="container notice-list" style="margin-top: 20px">
    <div class="container">
      <input
        type="text"
        class="form-control"
        id="inputSearchWord"
        placeholder="Search"
        v-model="$store.state.userStore.admin.searchWord"
        @keydown.enter="callUserList"
      />
      <button
        class="btn btn-round btn-success"
        type="button"
        id="btnSearchWord"
        @click="callUserList"
      >
        Search
      </button>
    </div>
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Name</th>
          <th scope="col">Email</th>
          <th scope="col">Date</th>
          <th scope="col">Auth</th>
        </tr>
      </thead>
      <tbody>
        <tr style="cursor: pointer" v-for="(user, index) in listGetters" @click="detailUser(user.userSeq)" :key="index" >
          <td>{{ user.userSeq }}</td>
          <td>{{ user.userName }}</td>
          <td>{{ user.userEmail }}</td>
          <td>{{ user.userRegisterDate.date | makeDateStr(".") }}</td>
          <td v-if="user.userState=='001'">일반회원</td>
          <td v-if="user.userState=='003'">관리자</td>
        </tr>
      </tbody>
    </table>
    <div
      style="height: 46px"
      v-for="idx in 8 - $store.state.userStore.admin.countCurrentList"
      :key="idx + 'blank'"
    ></div>
    <div class="navbar-fixed-bottom">
      <user-pagination type="default" v-on:call-parent="movePage"> </user-pagination>
    </div>
  </div>
</template>

<script>
import util from "@/common/util.js";
import { PaginationUser } from "@/components";
import { mapActions, mapGetters, mapMutations } from "vuex";

const userStore = "userStore";
const adminStore = "adminStore";
export default {
  components: {
    [PaginationUser.name]: PaginationUser,
  },
  name: "userList",
  computed: {
    ...mapGetters(userStore, ["getList"]),

    listGetters() {
      return this.getList;
    },
  },
  methods: {
    ...mapActions(userStore, ["userList", "userDetail"]),
    ...mapMutations(userStore, ["SET_USER_MOVE_PAGE"]),
    ...mapMutations(adminStore, ["INIT_USER", "SET_USER_DETAIL"]),

    detailUser(userSeq){
      this.userDetail(userSeq);
      this.SET_USER_DETAIL();
    },

    callUserList() {
      this.userList();
    },
    movePage(pageIndex) {
      console.log("UserMainVue : movePage : pageIndex : " + pageIndex);

      // store commit 으로 변경
      // this.offset = (pageIndex - 1) * this.listRowCount;
      // this.currentPageIndex = pageIndex;
      this.SET_USER_MOVE_PAGE(pageIndex);

      this.callUserList();
    },
  },
  created() {
    this.callUserList();
  },
  filters: {
    makeDateStr: function (date, type) {
      return util.makeDateStr(date.year, date.month, date.day, type);
    },
  },
};
</script>

<style scoped>
.ck-content {
  height: 400px;
}
#btnBoardUpdateForm,
#btnBoardDeleteConfirm {
  background-color: #005555;
  color: white;
}
#inputSearchWord {
  float: left;
  width: 90%;
  height: 52px;
}
#btnInsertPage,
#btnBoardInsert {
  width: 90px;
  height: 52px;
}
#btnSearchWord {
  width: 90px;
  height: 52px;
  float: left;
  margin-top: 0px;
  margin-left: 10px;
}

#btnInsertPage:hover,
#btnInsertPage:active,
#btnInsertPage:disabled,
#btnInsertPage:enabled,
#btnInsertPage:visited {
  background-color: #005555;
}
#btnSearchWord:hover,
#btnSearchWord:active,
#btnSearchWord:disabled,
#btnSearchWord:enabled,
#btnSearchWord:visited {
  background-color: #005555;
}
#btnBoardInsert:hover,
#btnBoardInsert:active,
#btnBoardInsert:disabled,
#btnBoardInsert:enabled,
#btnBoardInsert:visited {
  background-color: #005555;
}
.page-link {
  color: #005555;
  background-color: #fff;
  border-color: #fff;
}

.page-item.active .page-link {
  z-index: 1;
  color: #fff;
  font-weight: bold;
  background-color: #005555;
  border-color: #005555;
}

.page-link:focus,
.page-link:hover {
  color: #fff;
  background-color: #005555;
  border-color: #005555;
}
</style>
