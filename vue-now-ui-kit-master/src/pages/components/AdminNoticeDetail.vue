<template>
  <div>
    <div class="header">
        <button @click="INIT_NOTICE" class="btn btn-sm btn-primary btn-outline" data-dismiss="modal" type="button">목록 가기</button>
               <h5 class="title">글 상세</h5>
            </div>
            <div class="body">
               <table class="table">
                  <tbody>
                     <tr>
                        <td>글번호</td>
                        <td>{{ getBoard.boardId }}</td>
                     </tr>
                     <tr>
                        <td>제목</td>
                        <td>{{ getBoard.title }}</td>
                     </tr>
                     <tr>
                        <td>내용</td>
                        <td v-html="getBoard.content"></td>
                     </tr>
                     <tr>
                        <td>작성자</td>
                        <td>{{ getBoard.userName }}</td>
                     </tr>
                     <!-- 아래 코드는 오류 발생 초기 생성 시점에 regDt = {} -->
                     <!-- <tr><td>작성일시</td><td>{{ makeDateStr(regDt.date.year, regDt.date.month, regDt.date.day, '.') }}</td></tr> -->
                     <tr>
                        <td>작성일시</td>
                        <td>{{ getBoard.regDate }} {{ getBoard.regTime }}</td>
                     </tr>
                     <tr>
                        <td>조회수</td>
                        <td>{{ getBoard.readCount }}</td>
                     </tr>
                     <!-- New for FileUpload -->
                     <tr>
                        <td colspan="2">첨부파일</td>
                     </tr>
                     <tr v-if="getBoard.fileList.length > 0">
                        <td colspan="2">
                           <div v-for="(file, index) in getBoard.fileList" :key="index">
                              <span class="fileName">{{ file.fileName }}</span>
                              &nbsp;&nbsp;
                              <a type="button" class="btn btn-outline btn-default btn-xs" v-bind:href="file.fileUrl" v-bind:download="file.fileName">내려받기</a>
                           </div>
                        </td>
                     </tr>
                     <!-- / New for FileUpload -->
                  </tbody>
               </table>
            </div>
            <div class="footer">
               <button @click="update" class="btn btn-sm btn-primary btn-outline" data-dismiss="modal" type="button">글 수정하기</button>
               <button @click="deleteBoard(getBoard.boardId)" class="btn btn-sm btn-warning btn-outline" data-dismiss="modal" type="button">글 삭제하기</button>
            </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from 'vuex';

const boardStore = "boardStore";
const adminStore = "adminStore";

export default {
    computed:{
        ...mapGetters(boardStore, ["getBoard"]),
    },
    methods:{
        ...mapActions(boardStore, ["boardDelete"]),
        ...mapMutations(adminStore, ["SET_NOTICE_UPDATE", "INIT_NOTICE"]),
        
        update(){
            this.SET_NOTICE_UPDATE();
        },

        deleteBoard(boardId){
            this.boardDelete(boardId);
            this.INIT_NOTICE();
        }
    }
}
</script>

<style>

</style>