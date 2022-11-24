<template>
    <div>
      <admin-event-list v-if="event.isList"></admin-event-list>
      <admin-event-insert v-if="event.isInsert"></admin-event-insert>
    </div>
  </template>
  
  <script>
  import { mapMutations, mapState } from "vuex";

  import AdminEventInsert from "./AdminEventInsert.vue";
  import AdminEventList from "./AdminEventList.vue";
  
  const adminStore = "adminStore";
  export default {
    components: { AdminEventInsert, AdminEventList },
    computed: {
      ...mapState(adminStore, ["event"]),
    },
    methods: {
      ...mapMutations(adminStore, [
        "INIT_EVENT",
        "SET_EVENT_INSERT",
        "SET_EVENT_DETAIL",
        "SET_EVENT_UPDATE",
      ]),
    },
    mounted(){
      this.INIT_EVENT();
    }
  };
  </script>
  
  <style></style>
  