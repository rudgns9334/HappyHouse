import http from "@/common/axios.js";

export default {
  async userRegister(body, success, fail) {
    await http.post("/register", JSON.stringify(body)).then(success).catch(fail);
  },
};
