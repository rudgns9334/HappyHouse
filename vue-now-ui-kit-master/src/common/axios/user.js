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
  async checkToken(success, fail){
    http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await http.get("/check").then(success).catch(fail);
  },
  async tokenRegeneration(body, success, fail) {
    http.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
    await http.post(`/refresh`, body).then(success).catch(fail);
  },
  async logout(userSeq,success, fail){
    await http.get("/logout/"+userSeq).then(success).catch(fail);
  },
  setHeader(){
    http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    http.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token");
  }
};
