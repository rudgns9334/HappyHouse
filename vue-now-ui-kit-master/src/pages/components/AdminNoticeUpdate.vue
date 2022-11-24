<template>
  <div>
    <div class="header">
               <h5 class="title">글 수정</h5>
            </div>
            <div class="body">
               <div class="mb-3">
                  <!-- v-model 을 store 와 직접 연결하기 보다 computed-get-set 을 사용(strict mode 에서는 오류 발생) -->
                  <!-- <input v-model="$store.state.board.title" type="text" class="form-control" placeholder="제목"> -->
                  <input v-model="storeTitle" type="text" class="form-control" placeholder="제목" />
               </div>
               <div class="mb-3">
                  <div id="divEditorUpdate"></div>
               </div>
               <!-- 기존 파일 내용 보여줌  -->
               <!-- 새로운 첨부파일은 data-fileList 로 -->
               <div v-if="$store.state.boardStore.board.fileList.length > 0" class="mb-3">
                  첨부파일 :
                  <span
                     ><div v-for="(file, index) in $store.state.boardStore.board.fileList" class="fileName" :key="index">{{ file.fileName }}</div></span
                  >
               </div>
               <div class="form-check mb-3">
                <n-checkbox v-model="attachFile">파일 추가</n-checkbox>
               </div>
               <div class="mb-3" v-show="attachFile" id="imgFileUploadUpdateWrapper">
                  <input @change="changeFile" type="file" id="inputFileUploadUpdate" multiple />
                  <div id="imgFileUploadUpdateThumbnail" class="thumbnail-wrapper">
                     <!-- vue way img 를 만들어서 append 하지 않고, v-for 로 처리 -->
                     <img v-for="(file, index) in fileList" v-bind:src="file" v-bind:key="index" />
                  </div>
               </div>
            </div>
            <div class="footer">
               <button @click="boardUpdate" class="btn btn-sm btn-primary btn-outline" type="button">수정</button>
            </div>
  </div>
</template>

<script>
import Vue from "vue";
import CKEditor from "@ckeditor/ckeditor5-vue2";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import VueAlertify from "vue-alertify";
import { Checkbox } from '@/components';

Vue.use(CKEditor).use(VueAlertify);

import http from "@/common/axios.js";
import { mapActions, mapMutations, mapState } from 'vuex';

const adminStore = "adminStore";
const boardStore = "boardStore";

export default {
   name: "AdminNoticeUpdate",
   components: {
    [Checkbox.name]: Checkbox,
  },
   data() {
      return {
         CKEditor: "",
         attachFile: false,
         fileList: [],
      };
   },
   // v-model -title
   computed: {
      storeTitle: {
         get() {
            return this.$store.state.boardStore.board.title;
         },
         set(title) {
            this.$store.commit("boardStore/SET_BOARD_TITLE", title);
         },
      },
   },
   methods: {
    ...mapActions(boardStore,["boardList"]),
    ...mapMutations(adminStore, ["INIT_NOTICE"]),
      // modal 초기화
      initUI() {
        console.log(this.$store.state.boardStore.board.content);
         this.CKEditor.setData(this.$store.state.boardStore.board.content);
         this.attachFile = false;
         this.fileList = [];
         document.querySelector("#inputFileUploadUpdate").value = "";
      },
      changeFile(fileEvent) {
         this.fileList = []; // thumbnail 초기화

         const fileArray = Array.from(fileEvent.target.files);
         fileArray.forEach((file) => {
            this.fileList.push(URL.createObjectURL(file)); // push : array 에 항목 추가
         });
      },
      // 굳이 actions 에 있을 필요 없다. backend async 작업이지만, 그 결과로 store 를 변경하는 내용이 없다.
      async boardUpdate() {
         // post form data
         let formData = new FormData();
         formData.append("boardId", this.$store.state.boardStore.board.boardId); // update 에 추가
         formData.append("title", this.$store.state.boardStore.board.title);
         formData.append("content", this.CKEditor.getData()); // store X !!!!

         // file upload
         let attachFiles = document.querySelector("#inputFileUploadUpdate").files;

         if (attachFiles.length > 0) {
            const fileArray = Array.from(attachFiles);
            fileArray.forEach((file) => formData.append("file", file));
         }

         let options = {
            headers: { "Content-Type": "multipart/form-data" },
         };

         // not put, REST but FileUpload
         try {
            let { data } = await http.post("/boards/" + this.$store.state.boardStore.board.boardId, formData, options);

            console.log("UpdateModalVue: data : ");
            console.log(data);
            
            this.$alertify.success("글이 수정되었습니다.");
            this.boardList("001");
          this.INIT_NOTICE();

         } catch (error) {
            console.log("UpdateModalVue: error ");
            console.log(error);
         }
      },
     
   },
   created(){
    console.log("create");
   },
   async mounted() {
    console.log("moute");
      try {
         this.CKEditor = await ClassicEditor.create(document.querySelector("#divEditorUpdate"));
         this.initUI();
      } catch (error) {
         console.error(error);
      }

   },
};
</script>

<style scoped>
.modal >>> .ck-editor__editable {
   min-height: 300px !important;
}

.modal >>> .thumbnail-wrapper {
   margin-top: 5px;
}

.modal >>> .thumbnail-wrapper img {
   width: 100px !important;
   margin-right: 5px;
   max-width: 100%;
}
</style>