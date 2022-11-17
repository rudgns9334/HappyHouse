import http from '@/common/axios.js';

export default {
    async getSido(success, fail){
        await http.get('/apt/sido').then(success).catch(fail);
    }
}
