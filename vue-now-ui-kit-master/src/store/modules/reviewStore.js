import reviewAxios from "@/common/axios/review.js";
import router from "../../router";
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
  namespaced: true,
  actions: {
    reviewRegister({commit}, body) {
      console.log(body);
      reviewAxios.reviewRegister(
        body,
        ({data}) => {
          if (data.result == 1) {
            alertify
              .alert("Thank you For Join Our Event!!")
              .setHeader("<em> SUCCESS! </em> ")
              .set("onok", function () {
                router.push("/main");
              });
          }
        },
        error => {
          console.log("등록 실패");
          alertify.alert("Server Error").setHeader("<em> ERROR </em> ");
        },
      );
    },
  },
};
