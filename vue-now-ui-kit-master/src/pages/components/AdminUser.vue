<template>
    <div>
      <admin-user-list v-if="user.isList"></admin-user-list>
      <admin-user-detail v-if="user.isDetail"></admin-user-detail>
    </div>
  </template>
  
  <script>
  import { mapMutations, mapState } from "vuex";
  
  import AdminUserList from "./AdminUserList.vue";
  import AdminUserDetail from "./AdminUserDetail.vue";
  
  const adminStore = "adminStore";
  export default {
    components: { AdminUserList, AdminUserDetail },
    computed: {
      ...mapState(adminStore, ["user"]),
    },
    methods: {
      ...mapMutations(adminStore, [
        "INIT_USER",
      ]),
    },
    mounted(){
      this.INIT_USER();
    }
  };
  </script>
  
  <style></style>
  