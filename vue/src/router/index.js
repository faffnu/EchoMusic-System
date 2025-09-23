import Vue from 'vue'
import VueRouter from 'vue-router'
import IndexView from '@/views/IndexView.vue'
import TopView from "@/views/TopView.vue";
import DiscoverView from "@/views/DiscoverView.vue";
import MeView from "@/views/MeView.vue";
import PlayList from "@/views/PlayListView.vue";
import SearchView from "@/views/SearchView.vue";
import MeMessageView from "@/views/MeMessageView.vue";
import IndexPlayListView from "@/views/IndexPlayListView.vue";
import MeLikeMusic from "@/views/MeLikeMusic.vue";
import PlayHistoryView from "@/views/PlayHistoryView.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'index',
        component: IndexView
    }, {
        path: '/indexPlayList',
        name: 'indexPlayList',
        component: IndexPlayListView
    }, {
        path: '/top',
        name: 'top',
        component: TopView
    },
    {
        path: '/discover/playlist',
        name: 'discover',
        component: DiscoverView
    },
    {
        path: '/me',
        name: 'me',
        component: MeView
    },
    {
        path: '/playList',
        name: 'playList',
        component: PlayList
    },
    {
        path: '/search',
        name: 'search',
        component: SearchView
    },
    {
        path: '/message',
        name: 'message',
        component: MeMessageView
    },
    {
        path: '/me_playList',
        name: 'me_playList',
        component: MeLikeMusic
    },
    {
        path: '/play_history',
        name: 'play_history',
        component: PlayHistoryView
    },
    {
        path: '/singers',
        name: 'singers',
        component: () => import('@/views/SingersView.vue')
    },
]

const router = new VueRouter({
    mode: 'history',
    routes
})
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => {
        if (err.name !== 'NavigationDuplicated') throw err;
    });
};

const originalReplace = VueRouter.prototype.replace;
VueRouter.prototype.replace = function replace(location) {
    return originalReplace.call(this, location).catch(err => {
        if (err.name !== 'NavigationDuplicated') throw err;
    });
};
export default router;
