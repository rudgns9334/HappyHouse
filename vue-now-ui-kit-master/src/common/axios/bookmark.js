import http from '@/common/axios.js';

export default {
    async bookMarkList(params, success, fail){
        await http.get('/bookmarks/list' + params).then(success).catch(fail);
    },
    async bookMarkInsert(body, success, fail){
        await http.post('/bookmarks/insert', JSON.stringify(body)).then(success).catch(fail);
    },
    async bookMarkDelete(params, success, fail){
        await http.delete('/bookmarks/delete' + params).then(success).catch(fail);
    }
}
