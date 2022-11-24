import http from "@/common/axios.js";

export default {
  async reviewRegister(body, success, fail) {
    console.log("REVIEW AXIOS");
    console.log(JSON.stringify(body));
    await http.post("/review", JSON.stringify(body)).then(success).catch(fail);
  },
};
