<template>
  <div class="container notice-list" style="margin-top: 50px">
    <div class="event-list-div" v-for="(event, index) in list" :key="index">
      <router-link to="/review">
        <img class="event-img" :src="event.imgUrl" alt="" />
      </router-link>
    </div>
    <div style="height: 46px" v-for="idx in 8 - $store.state.eventStore.countCurrentList" :key="idx + 'blank'"></div>
    <div class="navbar-fixed-bottom">
      <n-pagination type="default" v-on:call-parent="movePage"> </n-pagination>
    </div>
  </div>
</template>

<script>
import util from "@/common/util.js";
import {Pagination} from "@/components";
import {mapActions, mapGetters, mapMutations, mapState} from "vuex";
const eventStore = "eventStore";
export default {
  components: {
    [Pagination.name]: Pagination,
  },
  name: "eventList",
  computed: {
    ...mapState(eventStore, ["list"]),
    ...mapGetters(eventStore, ["getEventList"]),
    listGetters() {
      return this.getEventList;
    },
  },
  methods: {
    ...mapActions(eventStore, ["eventList"]),
    ...mapMutations(eventStore, ["SET_EVENT_MOVE_PAGE"]),
    callEventList() {
      this.eventList();
    },
    movePage(pageIndex) {
      this.SET_EVENT_MOVE_PAGE(pageIndex);
      this.callEventList();
    },
  },
  created() {
    this.callEventList();
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
  width: 80%;
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
.event-list-div {
  text-align: center;
  margin: 30px;
}
.event-img {
  cursor: pointer;
}
</style>
