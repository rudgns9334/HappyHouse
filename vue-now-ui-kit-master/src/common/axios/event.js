import http from '@/common/axios.js';

export default {
    async boardList(params, success, fail){
        await http.get('/events', {params}).then(success).catch(fail);
    }
}
