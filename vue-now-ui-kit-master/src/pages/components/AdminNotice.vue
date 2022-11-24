<template>
  <div>
    <admin-notice-list v-if="notice.isList"></admin-notice-list>
    <admin-notice-insert v-if="notice.isInsert"></admin-notice-insert>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import AdminNoticeInsert from "./AdminNoticeInsert.vue";
import AdminNoticeList from "./AdminNoticeList.vue";

const adminStore = "adminStore";
export default {
  components: { AdminNoticeInsert, AdminNoticeList },
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
};
</script>

<style></style>
