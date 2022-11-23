import http from '@/common/axios.js';

export default {
    async alermList(params, success, fail){
        await http.get('/alerms/list',{params}).then(success).catch(fail);
    },
    async alermSend(body, success, fail){
        await http.post('/alerms/send', JSON.stringify(body)).then(success).catch(fail);
    },
    async alermReadAll(body, success, fail){
        await http.put('/alerms/allRead', JSON.stringify(body)).then(success).catch(fail);
    },
    async alermDelete(params, success, fail){
        await http.delete('/alerms/delete',{params}).then(success).catch(fail);
    },
    async alermDeleteWithSeq(params, success, fail){
        await http.delete('/alerms/delete2',{params}).then(success).catch(fail);
    },
    async alermDeleteAll(params, success, fail){
        await http.delete('/alerms/deleteAll',{params}).then(success).catch(fail);
    }
}
