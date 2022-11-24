<template>
  <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Name</th>
          <th scope="col">Start Time</th>
          <th scope="col">End Time</th>
          <th scope="col">State</th>
        </tr>
      </thead>
      <tbody>
        <tr style="cursor: pointer" v-for="(event, index) in listGetters" :key="index" >
          <td>{{ event.eventId }}</td>
          <td>{{ event.eventName }}</td>
          <td>{{ event.startDt.date | makeDateStr(".") }} {{event.startDt.time | makeTimeStr(":")}}</td>
          <td>{{ event.endDt.date | makeDateStr(".") }}  {{event.endDt.time | makeTimeStr(":")}}</td>
          <td>{{ event.eventState }}</td>
        </tr>
      </tbody>
    </table>
    
</template>

<script>
import util from "@/common/util.js";
import {mapActions, mapGetters, mapMutations, mapState} from "vuex";
const eventStore = "eventStore";
export default {
  
  name: "eventList",
  computed: {
    ...mapState(eventStore, ["list"]),
    ...mapGetters(eventStore, ["getEventList"]),
    listGetters() {
      console.log(this.getEventList);
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
    makeTimeStr: function (time, type) {
      return util.makeTimeStr(time.hour, time.minute, time.second, type);
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
