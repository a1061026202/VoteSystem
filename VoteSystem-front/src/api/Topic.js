import request from '@/utils/requests.js'

export default{
    getTopic(){
        return request({
            url: '/api/vote/getVoteTopicList',
            method: 'get',
        })
    },
    addTopic(form){
        return request({
            url: '/api/vote/createVote',
            method: 'post',
            data: form
        })
    },
    getoptions(id){
        return request({
            url: `/api/vote/getVoteOptionList/${id}`,
            method: 'get'
        })
    },
    toVoteById(id){
        return request({
            url: `/api/vote/voteOption/${id}`,
            method: 'put'
        })
    },
    getVoteResult(id){
        return request({
            url:`/api/vote/getVoteResult/${id}`,
            method:'get'
        })
}
}