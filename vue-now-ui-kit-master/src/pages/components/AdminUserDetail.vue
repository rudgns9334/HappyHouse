<template>
  <div>
    <div class="header">
        <button @click="INIT_USER" class="btn btn-sm btn-primary btn-outline" data-dismiss="modal" type="button">목록 가기</button>
               <h5 class="title">회원 정보</h5>
            </div>
            <div class="body">
               <table class="table">
                  <tbody>
                     <tr>
                        <td>고유 번호</td>
                        <td>{{ getAdminUser.userSeq }}</td>
                     </tr>
                     <tr>
                        <td>이름</td>
                        <td>{{ getAdminUser.userName }}</td>
                     </tr>
                     <tr>
                        <td>비밀번호</td>
                        <td v-html="getAdminUser.userPassword"></td>
                     </tr>
                     <tr>
                        <td>이메일</td>
                        <td>{{ getAdminUser.userEmail }}</td>
                     </tr>
                     <!-- 아래 코드는 오류 발생 초기 생성 시점에 regDt = {} -->
                     <!-- <tr><td>작성일시</td><td>{{ makeDateStr(regDt.date.year, regDt.date.month, regDt.date.day, '.') }}</td></tr> -->
                     <tr>
                        <td>작성일시</td>
                        <td>{{ getAdminUser.regDate }} {{ getAdminUser.regTime }}</td>
                     </tr>
                     <tr>
                        <td>한줄평</td>
                        <td>{{ getAdminUser.userComment }}</td>
                     </tr>
                     <tr>
                        <td>프로필</td>
                        <td>{{ getAdminUser.userProfileImageUrl }}</td>
                     </tr>
                     
                  </tbody>
               </table>
            </div>
            <div class="footer">
               <button @click="update" class="btn btn-sm btn-primary btn-outline" data-dismiss="modal" type="button">글 수정하기</button>
               <button @click="deleteUser(getAdminUser.userSeq)" class="btn btn-sm btn-warning btn-outline" data-dismiss="modal" type="button">회원 삭제하기</button>
            </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from 'vuex';

const userStore = "userStore";
const adminStore = "adminStore";

export default {
    computed:{
        ...mapGetters(userStore, ["getAdminUser"]),
    },
    methods:{
        ...mapActions(userStore, ["userDelete"]),
        ...mapMutations(adminStore, ["SET_USER_UPDATE", "INIT_USER"]),
        
        update(){
            
        },

        deleteUser(userSeq){
            this.userDelete(userSeq);
            this.INIT_USER();
        }
    }
}
</script>

<style>

</style>