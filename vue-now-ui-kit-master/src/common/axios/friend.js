import http from '@/common/axios.js';

export default {
    async friendListSearchWord(params, success, fail){
        await http.get('/friends/list',{params}).then(success).catch(fail);
    },
    async friendList(params, success, fail){
        await http.get('/friends/list' + params).then(success).catch(fail);
    },
    async friendDetail(params, success, fail){
        await http.get('/friends/detail' + params).then(success).catch(fail);
    },
    async friendRequest(body, success, fail){
        await http.post('/friends/request', JSON.stringify(body)).then(success).catch(fail);
    },
    async friendDelete(params, success, fail){
        await http.delete('/friends/delete' + params).then(success).catch(fail);
    }
}
