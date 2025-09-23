import axios from "axios";

const music = "/music";
const user = "/user";
const playHistory = "/playHistory";
// 创建配置
const instance = axios.create({
    baseURL: "http://localhost:80/api",//默认请求地址
    timeout: 5000,//请求超时时间，单位毫秒
})
const headers={
    'Content-Type': 'application/json'
}
const musicApi = {
    //TODO 获取首页数据
    index() {
        return instance.get(music + "/index")
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },
    //TODO 根据歌曲类型获取新歌
    musicByTag(type) {
        return instance.post(music + "/getMusicByType?type=" + type)
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },
    //TODO 根据歌单类型获得歌单
    playlistByTag(type) {
        return instance.post(music + "/selectPlayListByTag?tag=" + type)
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },
    //TODO 根据歌单id获得歌曲
    playListById(id) {
        return instance.get(music + "/selectPlayListByMusicId/" + id)
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },
    //TODO 根据歌曲名称进行搜索
    search(songName) {
        return instance.post(music + "/search?music_name=" + songName)
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },

    //TODO 获得所有歌单
    getPlayListAll() {
        return instance.get(music + "/getPlayListAll")
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },

    //TODO 榜单播放量+1
    addPlayListCount(playListId) {
        return instance.get(music + "/updatePlayListByPlayCount?play_list_id=" + playListId)
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },

    //TODO 歌曲播放量+1
    addMusicCount(musicId) {
        return instance.get(music + "/updatePlayCount?id=" + musicId)
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },

    getAllSingers(){
        return instance.get(music + "/allSingers")
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },

    getSongsBySingerId(singerId){
        return instance.get(music + "/songs?singerId=" + singerId)
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },


    //TODO 用户登录
    login(username, password) {
        let data = {
            userName: username,
            passwordHash: password
        }
        return instance.post(user + "/login", JSON.stringify(data), {
            headers:headers
        }).then(res => res.data).catch(error => {
                throw error;
        })
    },

    //用户注册
    register(username, password, bio) {
        let data = {
            userName: username,
            passwordHash: password,
            bio: bio,
        }
        return instance.post(user + "/register", JSON.stringify(data), {
            headers:headers
        }).then(res => res.data).catch(error => {
            throw error;
        })
    },

    //修改用户信息
    update(token,bio, isVip) {
        let data = {
            bio: bio,
            isVip: isVip
        }
        return instance.put(user + "/update?token="+token, JSON.stringify(data), {
            headers:headers
        }).then(res => res.data).catch(error => {
            throw error;
        })
    },

    //修改密码
    changePassword(token,oldPassword, newPassword,confirmPassword) {
        let data = {
            oldPassword: oldPassword,
            newPassword: newPassword,
            confirmPassword: confirmPassword
        }
        return instance.put(user + "/changePassword?token="+token, JSON.stringify(data), {
            headers:headers
        }).then(res => res.data).catch(error => {
            throw error;
        })
    },

    //TODO 添加喜欢的音乐
    insertLikeMusic(token,musicId) {
        return instance.post(music + "/insert?token="+token+"&song_id="+musicId)
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },

    //TODO 根据用户token查询用户喜欢的音乐
    selectUserByToken(token) {
        return instance.post(music + "/selectLikeMusicByUserId?token=" + token)
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },

    //TODO 根据用户token和歌曲id删除我喜欢的音乐
    deleteMusicByTokenAndMusicId(token,musicId) {
        return instance.delete(music + "/deleteMusic?token=" + token+"&song_id="+musicId)
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },

    //TODO top榜
    getTop(){
        return instance.get(music + "/getTop")
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },

    //TODO 新歌榜
    getNewMusic(){
        return instance.get(music + "/new_music_board")
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },

    //TODO 热歌榜
    getHotMusic(){
        return instance.get(music + "/hot_music_board")
            .then(res => res.data)
            .catch(error => {
                throw error;
            })
    },

    //TODO 查询所有的历史播放记录
    getPlayHistory(token){
        return instance.post(music+playHistory+"/selectByToken?token=" + token)
            .then(res => res.data)
            .catch(error=>{
                throw error;
            })
    },

    //TODO 添加一条播放记录
    insertPlayHistory(userId,musicId) {
        let data = {
            songId: musicId,
            userId: userId
        }
        return instance.post(music+playHistory+"/insert", JSON.stringify(data),{
            headers:headers
        }).then(res => res.data).catch(error => {
            throw error;
        })
    },

    //TODO 删除一条播放记录
    deletePlayHistory(userId,musicId) {
        let data = {
            userId: userId,
            songId: musicId
        }
        return instance.post(music+playHistory+"/delete", JSON.stringify(data),{
            headers:headers
        }).then(res => res.data).catch(error => {
            throw error;
        })
    },
}

export default musicApi;