<!-- 歌曲列表 -->
<template>
  <div class="container-list-song">
    <div class="top-content">
      <div class="left-content">
        <div style="margin-left: 10px">歌曲列表</div>
        <div>{{ songList.length }}首歌</div>
      </div>
      <div class="right-content">
        <span>播放：</span>
        <span>{{ playCount }}</span>
        <span>次</span>
      </div>
    </div>
    <table class="bottom-content">
      <thead class="table-header">
      <tr>
        <th width="80px"></th>
        <th align="center">标题</th>
        <th v-if="isShowLike || isPlayHistory" align="center">删除</th>
        <th width="80px" align="center" v-if="isShowLike===false">我喜欢的音乐</th>
        <th align="center">时长</th>
        <th align="center">歌手</th>
      </tr>
      </thead>
      <tbody class="table-content" v-for="(item,index) in songList" :key="index">
      <tr>
        <td align="center">{{ index + 1 }}</td>
        <td  class="top-song">
          <img :src="item.coverImage" alt="网络不佳">
          <span class="icon-play">
               <i class="el-icon-video-play" style="font-size: 20px" @click="playMusic(item)"></i>
            </span>
          <span class="show-line">{{ item.songName }}</span>
        </td>

        <!--  删除喜欢的音乐 -->
        <td @click="deleteLikeMusic(item.songMusicId)" align="center"
            v-if="isShowLike">
          <i class="el-icon-delete"></i>
        </td>
        <!-- 删除播放历史记录 -->
        <td @click="deletePlay(item.songMusicId)" align="center"
            v-if="isPlayHistory">
          <i class="el-icon-delete"></i>
        </td>
        <td @click="addMusic(item.songMusicId)" align="center" v-if="isShowLike===false">
          <i class="el-icon-circle-plus-outline"></i>
        </td>
        <td align="center">{{ item.duration }}</td>
        <td align="center">
            <span v-for="(items,index2) in item.musicAndSingerList" :key="index2">
              <span v-for="(item3,index3) in items.singers" :key="index3">
                {{ item3.singerName }}
              </span>
            </span>
          <span align="center" class="show-line">{{ item.singer }}</span>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import request from "@/axios/request";
import {addMusicCount, insertLikeMusic, insertPlayHistory} from "@/axios/music";

export default {
  name: "ListSong",
  props: {
    //歌曲播放数量
    playCount: {
      type: Number,
      default: 1
    },
    //歌曲
    songList: {
      type: Array
    },
    //是否显示删除我喜欢的音乐
    isShowLike: {
      type: Boolean,
      default: false
    },
    //是否显示删除播放记录
    isPlayHistory: {
      type: Boolean,
      default: false
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
    },
    userId() {
      if (this.$store.getters.getUser !== null) {
        return this.$store.getters.getUser.user.musicUserId
      }
    },
  },
  mounted() {
    if (localStorage.getItem("user") !== null) {
      this.$store.commit('setUser', JSON.parse(localStorage.getItem("user")));
    }
  },
  methods: {
    //播放单曲
    playMusic(item) {
      if (item.isVip !== this.isVip && item.isVip !== false) {
        this.$message.error("该歌曲只有vip才能播放！")
        return
      }
      //歌曲播放量+1
      addMusicCount(item.songMusicId)
      if (this.$store.getters.getUser !== null) {
        insertPlayHistory(this.userId, item.songMusicId)//添加一条播放记录
      }
      this.$store.commit('setSongList', [])
      this.$store.commit('setSongList', item)
    },
    //添加喜欢的音乐
    addMusic(id) {
      if (this.token === null) {
        this.$message.error("请登录！")
        return
      }
      insertLikeMusic(this.token, id)
    },
    //查询喜欢的音乐
    selectLikeMusic() {
      request.selectUserByToken(this.token).then(res => {
        if (res.message === "暂无喜欢的歌曲") {
          this.$message.error("暂无喜欢的音乐！")
          setTimeout(this.goMe, 3000)
          return
        }
        this.playCount = 0
        res.data.forEach((item) => {
          item.songMusicList.forEach(song => {
            if (song !== undefined) {
              this.playCount += song.playCount;
              this.songList.push(song);
            }
          })
        })
      })
    },
    // 删除喜欢的音乐
    deleteLikeMusic(id) {
      this.$confirm('是否删除我喜欢的音乐, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.deleteMusicByTokenAndMusicId(this.token, id).then(res => {
          this.$message.success(res.message)
          // 删除成功后再查一下
          this.songList = []
          this.selectLikeMusic()
        }).catch(err => {
          console.log(err)
          this.$message.error(err.message)
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    // 删除播放历史记录
    deletePlay(id) {
      let data;
      this.$confirm('是否删除播放历史记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.songList = []
        request.deletePlayHistory(this.userId, id).then(res => {
          this.$message.success(res.message)
          request.getPlayHistory(this.token).then(res => {
            data = res.data
            if (data.length <= 0) {
              this.$message.error("暂无播放历史！")
              setTimeout(this.goMe, 3000)
              return
            }
            let count = 0
            res.data.forEach(item => {
              if (item !== undefined) {
                count += item.playCount;
                this.songList.push(item);
              }
            })
            this.playCount = count
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    goMe() {
      this.$router.push("/me")
    }
  }
}
</script>
<style scoped>
/* 底部区域 */
.container-list-song {
  padding-left: 5px;
  width: 100%;
  height: 100%;
  margin-bottom: 70px;
  box-sizing: border-box;
  /* 头部区域 */

  .top-content {
    margin-top: 50px;
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-between;

    .left-content {
      display: flex;
      flex-direction: row;
      align-items: center;
    }

    .left-content > div:nth-child(1) {
      font-size: 20px;
    }

    .left-content > div:nth-child(2) {
      padding-left: 20px;
      text-align: center;
      font-size: 14px;
    }

    .right-content {
      font-size: 14px;
      padding-right: 10px;
    }

    .right-content > span:nth-child(2) {
      color: red;
    }
  }

  .bottom-content {
    margin: 15px 5px 5px;
    width: 100%;
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

      th:nth-child(1), th:nth-child(2), th:nth-child(3), th:nth-child(4), th:nth-child(5), th:nth-child(6) {
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

      .like-song {
        text-align: center;

        img {
          width: 20px;
          height: 20px;
        }
      }

      .icon-play {
        color: #837d7d;
        padding-left: 20px;
      }

      .el-icon-video-play:hover {
        color: red;
      }

      span {
        padding-left: 10px;
      }
    }

    .table-content{
    }

    .show-line:hover {
      text-decoration: underline;
    }
  }
}
</style>