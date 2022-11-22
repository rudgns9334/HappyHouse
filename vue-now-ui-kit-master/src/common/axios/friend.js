import http from '@/common/axios.js';

export default {
    async friendList(params, success, fail){
        await http.get('/friends/list' + params).then(success).catch(fail);
    },
    async friendDetail(params, success, fail){
        await http.get('/friends/detail' + params).then(success).catch(fail);
    },
    async friendRegister(body, success, fail){
        await http.post('/friends/register', JSON.stringify(body)).then(success).catch(fail);
    },
    async friendDelete(params, success, fail){
        await http.delete('/friends/delete' + params).then(success).catch(fail);
    }
}
