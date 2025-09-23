import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        songList: [],//歌曲
        isDot:false, //是否显示圆点
        user:null
    },
    getters: {
        getSongList: (state) => state.songList,
        getIsDot: (state) => state.isDot,
        getUser: (state) => state.user,
    },
    mutations: {
        setSongList(state, song) {
            state.songList = null
            state.songList = song;
        },
        setIsDot: (state, isDot) => {
            state.isDot = isDot;
        },
        setUser: (state, user) => {
            state.user = user;
        }
    },
    actions: {}
});