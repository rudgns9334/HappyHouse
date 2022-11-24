<template>
  <div class="row">
    <div class="col-md-6 ml-auto mr-auto">
      <p></p>
    </div>
    <tabs
      pills
      class="nav-align-center"
      tab-content-classes="gallery"
      tab-nav-classes="nav-pills-just-icons"
      type="primary"
    >
      <tab-pane title="Profile">
        <span slot="label" class="material-symbols-outlined"> campaign </span>

        <div class="col-md-10 ml-auto mr-auto">
          <admin-notice></admin-notice>
        </div>
      </tab-pane>

      <tab-pane title="Home">
        <span slot="label" class="material-symbols-outlined"> manage_accounts </span>

        <div class="col-md-10 ml-auto mr-auto">
          <admin-user></admin-user>
        </div>
      </tab-pane>

      <tab-pane title="Messages">
        <span slot="label" class="material-symbols-outlined"> event </span>

        <div class="col-md-10 ml-auto mr-auto">
          <admin-event-list></admin-event-list>
        </div>
      </tab-pane>
    </tabs>
  </div>
</template>

<script>
import { Tabs, TabPane } from "@/components";
import AdminNotice from "./AdminNotice.vue";
import AdminUser from "./AdminUser.vue";
import AdminEventList from "./AdminEventList.vue";
export default {
  name: "adminclass",
  bodyClass: "admin-class",
  components: {
    Tabs,
    TabPane,
    AdminNotice,
    AdminUser,
    AdminEventList,
  },
};
</script>

<style scoped>
.material-symbols-outlined {
  font-variation-settings: "FILL" 0, "wght" 400, "GRAD" 0, "opsz" 48;
  margin-top: 25px;
}
</style>
