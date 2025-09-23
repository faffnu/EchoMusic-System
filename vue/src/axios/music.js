import request from "@/axios/request";
import { Message } from 'element-ui';
// 添加喜欢的音乐
export function insertLikeMusic(token,id) {
    request.insertLikeMusic(token,id).then((res) => {
        if (res.message === 'success') {
            Message.success('添加成功！')
            return
        }
        Message.error(res.message)
    }).catch(err => {
        console.log(err)
        Message.error("请求出错")
    })
}

// 音乐播放量+1
export function addMusicCount(id){
    request.addMusicCount(id).then().catch(err => {
        console.log(err);
    });
}

//歌单播放量+1
export function addPlayListCount(playListId) {
    request.addPlayListCount(playListId).then().catch(err => {
        console.log(err)
    })
}

//添加一条播放记录
export function insertPlayHistory(userId,musicId) {
    request.insertPlayHistory(userId,musicId).then(res=>{
        console.log(res)
    }).catch(err => {
        console.log(err);
    })
}