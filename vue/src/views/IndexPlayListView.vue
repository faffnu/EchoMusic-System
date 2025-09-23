<template>
  <el-container>
    <el-header style="height:120px">
      <NavBar :active-index="activeIndex"></NavBar>
    </el-header>
    <el-container class="main-container">
      <el-main>
        <!-- 顶部区域 -->
        <div class="container">
          <div class="top-container">
            <div class="left-content">
              <img :src="item.playListCover" alt="网络不佳" class="top-image">
            </div>
            <div class="right-content">
              <!-- 添加一个容器包裹描述和按钮 -->
              <div class="description-wrapper">
                <!-- 给描述部分添加一个类名，方便控制样式 -->
                <div class="description" :class="{ 'collapsed': isCollapsed }">
                  {{ item.description }}
                </div>
                <!-- 根据状态显示展开或收起按钮 -->
                <div v-if="shouldShowButton" class="toggle-button" @click="toggleDescription">
                  {{ isCollapsed ? '展开' : '收起' }}
                </div>
              </div>
              <div class="right-avatar">
                <div>
                  <el-avatar :size="40" src="/image/default.png"></el-avatar>
                </div>
                <div v-if="item.musicUser">{{ item.musicUser.userName }}</div>
                <div class="user-time">
                  <span>{{ item.createTime }}</span>
                  <span style="padding-left: 5px">创建</span>
                </div>
              </div>
              <div style="padding-top: 20px">
                <span>用户简介：</span>
                <span style="padding-left: 5px" v-if="item.musicUser">{{ item.musicUser.bio }}</span>
              </div>
              <div class="right-btn">
                <el-button type="primary" size="medium" @click="playList">
                  <i class="el-icon-video-play"></i>
                  <span>播放</span>
                </el-button>
                <el-button type="primary" size="medium" disabled class="el-disabled">
                  <i class="el-icon-folder-add"></i>
                  <span>(12345)</span>
                </el-button>
                <el-button type="primary" size="medium" class="btn">
                  <i class="el-icon-share"></i>
                  <span>(13194)</span>
                </el-button>
                <el-button type="primary" size="medium" class="btn">
                  <i class="el-icon-bottom"></i>
                  <span>下载</span>
                </el-button>
                <el-button type="primary" size="medium" class="btn">
                  <i class="el-icon-chat-dot-square"></i>
                  <span>(43987)</span>
                </el-button>
              </div>
            </div>
          </div>
          <list-song class="list-song" :play-count="item.playCount" :song-list="songList"></list-song>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import request from "@/axios/request";
import {addMusicCount,insertLikeMusic,addPlayListCount} from "@/axios/music";
import ListSong from "@/components/ListSong.vue";
export default {
  components: {ListSong, NavBar},
  data() {
    return {
      activeIndex: '1',
      item: [],
      songList: [], // 歌曲
      isCollapsed: true, // 控制描述是否收起
      shouldShowButton: false // 控制是否显示展开/收起按钮
    }
  },
  computed: {
    token(){
      return this.$store.getters.getUser.token;
    },
    isVip(){
      if (this.$store.getters.getUser !== null) {
        return this.$store.getters.getUser.user.isVip
      }
    }
  },
  created() {
    // 如果没有 item 会跳转到首页
    if (this.$route.params.item === undefined || this.$route.params.item === null) {
      this.$router.push("/")
    } else {
      //获取item
      this.item = JSON.parse(this.$route.params.item)
      this.getList(this.item.playListId)
    }
    if (this.$route.params.index !== undefined && this.$route.params.index !== null) {
      this.activeIndex = this.$route.params.index
    }
  },
  mounted() {
    // 在组件挂载后检查描述内容是否超出指定高度
    this.$nextTick(() => {
      const descriptionElement = this.$el.querySelector('.description');
      if (descriptionElement) {
        this.shouldShowButton = descriptionElement.scrollHeight > descriptionElement.clientHeight;
      }
    });
  },
  methods: {
    // 切换描述的展开/收起状态
    toggleDescription() {
      this.isCollapsed = !this.isCollapsed;
    },
    // 播放全部歌曲
    playList() {
      addPlayListCount(this.item.playListId); // 歌单播放量+1
      if (!this.isVip){
        // 过滤非 VIP 歌曲
        const nonVipSongList = this.songList.filter(song => !song.isVip);
        this.$store.commit('setSongList', nonVipSongList);
        return
      }
      this.$store.commit('setSongList',this.songList)
    },
    // 根据歌单id获得歌曲
    getList(id) {
      request.playListById(id).then((res) => {
        if (res.code === '200') {
          this.songList = res.data
        } else {
          this.$message.error(res.message)
        }
      }).catch(err => {
        this.$message.error("请求错误！")
        console.error(err)
      })
    }
  }
}
</script>

<style scoped>
.main-container {
  width: 75%;
  margin: 0 auto;
  background: var(--white);
}

.el-aside {
  overflow: hidden !important;
  border-right: 1px solid var(--odd-color) !important;
  border-left: 1px solid var(--odd-color) !important;
  padding: 0 15px 15px 15px !important;
}

.el-header {
  padding: 0 !important;
  border-bottom: 1px solid var(--odd-color) !important;
}

.container {
  width: 100%;
  height: 100%;
  display: flex;
  font-family: 楷体, serif;
  background: var(--white);
  flex-direction: column;
  /* 顶部区域 */
  .top-container {
    margin-top: 15px;
    display: flex;
    flex-direction: row;
    align-items: stretch;
    justify-content: flex-start;
    padding-left: 50px;
    .top-image {
      display: inline;
      width: 150px;
      height: 150px;
      border-radius: 3px;
      border: 1px solid rgb(204, 204, 204);
      padding: 3px;
    }

    .right-content {
      flex: 1;
      display: flex;
      flex-wrap: nowrap;
      text-align: left;
      align-content: center;
      justify-content: flex-start;
      align-items: stretch;
      flex-direction: column;
      padding-left: 50px;
    }

    /* 描述容器样式 */
    .description-wrapper {
      position: relative;
    }

    /* 描述样式 */
    .description {
      font-size: 20px;
      font-weight: normal;
      overflow: hidden;
      transition: max-height 0.3s ease;
    }

    /* 收起状态样式 */
    .collapsed {
      max-height: 40px;
    }

    /* 展开/收起按钮样式 */
    .toggle-button {
      color: #0c73c2;
      cursor: pointer;
      margin-top: 5px;
    }

    .right-avatar {
      margin-top: 10px;
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: flex-start;

      div:nth-child(2) {
        color: #0c73c2;
        padding-left: 10px;
        font-size: 14px;
        padding-right: 20px;
      }

      div:nth-child(2):hover {
        text-decoration: underline;
        cursor: pointer;
      }

      div:nth-child(3) {
        font-size: 14px;
        color: #837d7d;
      }
    }

    /* 按钮 */
    .right-btn {
      margin-top: 50px;
      text-align: left;
      font-family: '宋体', serif;

      .el-button {
        background-color: #3A89D4 !important;
      }

      i {
        font-size: 15px;
        color: whitesmoke;
        vertical-align: middle;
        margin-right: 5px;
      }

      .el-disabled {
        background-color: rgb(168, 161, 161) !important;
        color: rgb(51, 51, 51);
        border: none !important;
        cursor: default;

        i {
          color: rgb(51, 51, 51);
        }
      }

      .btn {
        color: rgb(51, 51, 51);
        background-color: #c1bcbc !important;
        border: none !important;

        i {
          color: rgb(51, 51, 51);
        }
      }

      .btn:hover {
        background-color: #8a8686 !important;
      }
    }
  }
  /* 底部区域 */
  .list-song {
    width: 100%;
    height: 100%;
    display: flex;
    background: var(--white);
    flex-direction: column;
  }
}
.el-container {
  box-shadow: none !important;
}
</style>