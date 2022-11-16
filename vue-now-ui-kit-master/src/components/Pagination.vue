<template>
  <ul class="pagination justify-content-center" :class="paginationClass">
    <li class="page-item prev-page" v-if="prev">
      <a class="page-link" aria-label="Previous" @click="paginationChanged(startPageIndex - 1)">
        <slot name="prev">»</slot>
      </a>
    </li>
    <li
      v-for="index in endPageIndex - startPageIndex + 1"
      :key="index"
      v-bind:class="{ active: startPageIndex + index - 1 == $store.state.board.currentPageIndex }"
      class="page-item"
    >
      <a @click="paginationChanged(startPageIndex + index - 1)" class="page-link" href="#">{{
        startPageIndex + index - 1
      }}</a>
      <!-- href 는 그대로, 커서 모양 유지-->
    </li>
    <li class="page-item page-pre next-page" v-if="next">
      <a class="page-link" aria-label="Next" @click="paginationChanged(endPageIndex + 1)">
        <slot name="next">»</slot>
      </a>
    </li>
  </ul>
</template>
<script>
export default {
  name: "n-pagination",
  props: {
    type: {
      type: String,
      default: "primary",
      validator: (value) => {
        return ["default", "primary", "danger", "success", "warning", "info"].includes(value);
      },
    },
  },
  computed: {
    paginationClass() {
      return `pagination-${this.type}`;
    },
    pageCount: function () {
      return this.$store.getters.getPageCount;
    },
    startPageIndex: function () {
      return this.$store.getters.getStartPageIndex;
    },
    endPageIndex: function () {
      return this.$store.getters.getEndPageIndex;
    },
    prev: function () {
      return this.$store.getters.getPrev;
    },
    next: function () {
      return this.$store.getters.getNext;
    },
  },
  methods: {
    // 부모에게 event 전달
    paginationChanged(pageIndex) {
      console.log("paginationVue : paginationChanged : pageIndex : " + pageIndex);
      this.$emit("call-parent", pageIndex);
    },
  },
};
</script>
