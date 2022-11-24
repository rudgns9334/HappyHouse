<template>
  <div>
    <admin-notice-list v-if="notice.isList"></admin-notice-list>
    <admin-notice-insert v-if="notice.isInsert"></admin-notice-insert>
    <admin-notice-detail v-if="notice.isDetail"></admin-notice-detail>
    <admin-notice-update v-if="notice.isUpdate"></admin-notice-update>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import AdminNoticeUpdate from "./AdminNoticeUpdate.vue";
import AdminNoticeDetail from "./AdminNoticeDetail.vue";
import AdminNoticeInsert from "./AdminNoticeInsert.vue";
import AdminNoticeList from "./AdminNoticeList.vue";

const adminStore = "adminStore";
export default {
  components: { AdminNoticeUpdate, AdminNoticeDetail, AdminNoticeInsert, AdminNoticeList },
  computed: {
    ...mapState(adminStore, ["notice"]),
  },
  methods: {
    ...mapMutations(adminStore, [
      "INIT_NOTICE",
      "SET_NOTICE_INSERT",
      "SET_NOTICE_DETAIL",
      "SET_NOTICE_UPDATE",
    ]),
  },
  mounted(){
    console.log("dddd");
    this.INIT_NOTICE();
  }
};
</script>

<style></style>
