<template>
  <div class="container notice-list" style="margin-top: 50px">
    <div class="container">
      <input
        type="text"
        class="form-control"
        id="inputSearchWord"
        placeholder="Search"
        v-model="$store.state.board.searchWord"
        @keydown.enter="boardList"
      />
      <button class="btn btn-round btn-success" type="button" id="btnSearchWord" @click="boardList">
        Search
      </button>
    </div>
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Owner</th>
          <th scope="col">Title</th>
          <th scope="col">Date</th>
          <th scope="col">Views</th>
        </tr>
      </thead>
      <tbody>
        <tr style="cursor: pointer" v-for="(board, index) in listGetters" :key="index">
          <td>{{ board.boardId }}</td>
          <td>{{ board.userName }}</td>
          <td>{{ board.title }}</td>
          <td>{{ board.regDt.date | makeDateStr(".") }}</td>
          <td>{{ board.readCount }}</td>
        </tr>
      </tbody>
    </table>
    <div class="navbar-fixed-bottom">
      <n-pagination type="default" v-on:call-parent="movePage"> </n-pagination>
    </div>
  </div>
</template>

<script>
import util from "@/common/util.js";
import { Pagination } from "@/components";

export default {
  components: {
    [Pagination.name]: Pagination,
  },
  name: "noticeList",
  computed: {
    listGetters() {
      return this.$store.getters.getBoardList;
    },
  },
  methods: {
    boardList() {
      this.$store.dispatch("boardList", "001");
    },
    movePage(pageIndex) {
      console.log("BoardMainVue : movePage : pageIndex : " + pageIndex);

      // store commit 으로 변경
      // this.offset = (pageIndex - 1) * this.listRowCount;
      // this.currentPageIndex = pageIndex;
      this.$store.commit("SET_BOARD_MOVE_PAGE", pageIndex);

      this.boardList();
    },
  },
  created() {
    this.boardList();
  },
  filters: {
    makeDateStr: function (date, type) {
      return util.makeDateStr(date.year, date.month, date.day, type);
    },
  },
};
</script>

<style>
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
