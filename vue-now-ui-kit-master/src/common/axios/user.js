import http from "@/common/axios.js";

export default {
  async userRegister(body, success, fail) {
    await http.post("/register", JSON.stringify(body)).then(success).catch(fail);
  },
  async userWithdraw(id, success, fail) {
    await http.delete("/withdraw/"+id).then(success).catch(fail);
  },
  async userModify(body, success, fail) {
    await http.post("/modify", JSON.stringify(body)).then(success).catch(fail);
  },
  async login(body, success, fail){
    await http.post("/login", JSON.stringify(body)).then(success).catch(fail);
  },
  async logout(success, fail){
    await http.get("/logout").then(success).catch(fail);
  },
};
