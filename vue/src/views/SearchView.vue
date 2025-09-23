<!-- 歌曲列表 -->
<template>
  <el-container>
    <el-header style="height:120px">
      <NavBar :active-index="activeIndex" v-on:songName="SongName"></NavBar>
    </el-header>
    <el-main>
      <div class="container">
        <div style="padding: 20px">
          <div class="search-tip">搜索“{{ this.songName }}”,找到
            <span v-if="this.songList && this.songList.length > 0">
              <span style="color: #c20c0c;">{{ this.songList.length }}</span>
              <span>首单曲</span>
            </span>
            <span v-else>
              <span style="color: #c20c0c;">0</span>
              <span>首单曲</span>
            </span>
          </div>
          <table class="bottom-content" v-if="this.songList && this.songList.length > 0">
            <thead class="table-header">
            <tr>
              <th width="80px"></th>
              <th>标题</th>
              <th width="80px" align="center">我喜欢的音乐</th>
              <th align="left" width="50px">时长</th>
              <th width="300px" align="center">歌手</th>
            </tr>
            </thead>
            <tbody class="table-content" v-for="(item,index) in songList" :key="index">
            <tr>
              <!-- 前3个显示红色 -->
              <td align="center" v-if="index+1<=3"
                  style="color: red;font-weight: bolder">{{ index + 1 }}
              </td>
              <!-- 剩下的正常显示 -->
              <td v-else align="center">{{ index + 1 }}</td>
              <!--  大于等于3显示封面 -->
              <td v-if="index+1<=3" class="top-song">
                <img :src="item.coverImage" alt="网络不佳">
                <span class="icon-play">
                    <i class="el-icon-video-play" @click="playMusic(item)"></i>
                  </span>
                <span class="show-line">{{ item.songName }}</span>
              </td>
              <!-- 小于3只显示歌曲名 -->
              <td v-else>
                  <span class="icon-play">
                     <i class="el-icon-video-play" @click="playMusic(item)"></i>
                  </span>
                <span class="show-line">{{ item.songName }}</span>
              </td>
              <td @click="addMusic(item.songMusicId)">
                <i class="el-icon-circle-plus-outline"></i>
              </td>
              <td align="center">{{ item.duration }}</td>
              <td align="center" class="show-line">
                  <span v-for="(items,index2) in item.musicAndSingerList" :key="index2">
                      <span v-for="(item3,index3) in items.singers" :key="index3">
                        {{ item3.singerName }}
                      </span>
                  </span>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </el-main>
  </el-container>
</template>
<script>
import request from '@/axios/request';
import {addMusicCount, insertLikeMusic} from '@/axios/music';
import NavBar from "@/components/NavBar.vue";
import Bottom from "@/components/Bottom.vue";

export default {
  name: "ListSong",
  components: {Bottom, NavBar},
  data() {
    return {
      activeIndex: '1',
      songName: null,
      songList: []
    }
  },
  computed: {
    token() {
      // 先判断用户信息是否存在，再获取token
      const user = this.$store.getters.getUser;
      return user ? user.token : null;
    },
    isVip(){
      const user = this.$store.getters.getUser;
      // 先判断用户信息是否存在，再获取isVip
      return user ? user.user.isVip : false;
    }
  },
  created() {
    this.songName = this.$route.params.songName;
    this.search()
  },
  methods: {
    //子组件向父组件传递数据
    SongName(songName) {
      this.songName = songName
      this.search()//查询歌曲
    },
    //根据歌曲名查询歌曲
    search() {
      request.search(this.songName).then(res => {
        this.songList = res.data;
      }).catch(err => {
        console.log(err)
        this.$message.error("请求数据错误！")
      })
    },
    //播放单曲
    playMusic(item) {
      if (item.isVip !== this.isVip && item.isVip !== false) {
        this.$message.error("该歌曲只有vip才能播放！")
        return
      }
      //歌曲播放量+1
      addMusicCount(item.songMusicId)
      this.$store.commit('setSongList', item)
    },
    // 添加到喜欢的音乐当中
    addMusic(id) {
      if (this.token === null) {
        this.$message.error("请登录！")
        return
      }
      insertLikeMusic(this.token, id)
    }
  }
}
</script>
<style scoped>
.el-header {
  height: 110px !important;
}

.el-container {
  box-shadow: none !important;
}

.container {
  background: var(--white);
  padding-left: 5px;
  width: 70%;
  margin: 0 auto;
  box-sizing: border-box;

  .search-tip {
    font-size: 12px;
  }

  .bottom-content {
    width: 100%;
    margin: 20px auto;
    /* 区域 */

    .table-header {
      height: 30px;
      line-height: 30px;
      font-size: 12px;
      color: #575151;

      th:nth-child(2), th:nth-child(3), th:nth-child(4), th:nth-child(5) {
        border-left: 1px solid #938c8c;
        padding-left: 5px;
      }

      th:nth-child(1), th:nth-child(2), th:nth-child(3), th:nth-child(4), th:nth-child(5) {
        border-bottom: 1px solid #938c8c;
        box-shadow: 1px 0 1px 1px #f7f7f7;;
      }
    }

    /* 底部区域 */

    .table-content {
      height: max-content;
      color: var(--text-color);

      tr > td {
        height: 50px;
        line-height: 50px;
      }

      tr > td:nth-child(2), tr > td:nth-child(3), tr > td:nth-child(4) {
        padding-left: 15px;
      }

      img {
        width: 50px;
        height: 50px;
        vertical-align: middle;
      }

      .top-song {
        box-sizing: border-box;
        padding-top: 30px;
        height: 60px;
        padding-bottom: 20px;
      }

      .icon-play {
        color: #837d7d;
        padding-left: 20px;
      }

      span {
        padding-left: 10px;
      }
    }

    .table-content:nth-child(odd) {
      color: #0f0f0f;
      background-color: var(--white);
    }

    .table-content:nth-child(even) {
      background-color: inherit;
    }

    .show-line:hover {
      text-decoration: underline;
    }

    .el-icon-video-play:hover {
      color: red;
    }
  }
}
</style>