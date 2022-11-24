import http from '@/common/axios.js';

export default {
    async boardList(params, success, fail){
        await http.get('/boards', {params}).then(success).catch(fail);
    },
    async boardDetail(params, success, fail){
        await http.get('/boards/detail', {params}).then(success).catch(fail);
    },
    async boardDelete(params, success, fail){
        await http.delete('/boards/' + params).then(success).catch(fail);
    }
}
