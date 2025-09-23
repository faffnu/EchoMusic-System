<template>
  <div class="container">
    <!-- 顶部区域 -->
    <div class="top-container">
      <div class="left-content">
        <div class="image-wrapper">
          <img :src="top.image" alt="排行榜封面" class="top-image">
          <div class="image-overlay">
            <i class="el-icon-video-play"></i>
          </div>
        </div>
      </div>
      <div class="right-content">
        <h2 class="right-text">{{ top.text }}</h2>
        <div class="right-avatar">
          <div class="user-time">
            <i class="el-icon-time"></i>
            <span>最近更新：</span>
            <span class="update-date">{{ formattedDate }}</span>
            <span class="update-frequency">（每天更新）</span>
          </div>
        </div>
        <div class="right-btn">
          <el-button type="primary" size="medium" class="play-btn" @click="playList">
            <i class="el-icon-video-play"></i>
            <span>播放全部</span>
          </el-button>
          <el-button size="medium" class="icon-btn">
            <i class="el-icon-folder-add"></i>
            <span class="count">(12345)</span>
          </el-button>
          <el-button size="medium" class="icon-btn">
            <i class="el-icon-share"></i>
            <span class="count">(13194)</span>
          </el-button>
          <el-button size="medium" class="icon-btn">
            <i class="el-icon-bottom"></i>
            <span>下载</span>
          </el-button>
          <el-button size="medium" class="icon-btn">
            <i class="el-icon-chat-dot-square"></i>
            <span class="count">(43987)</span>
          </el-button>
        </div>
      </div>
    </div>
    <!-- 底部区域 -->
    <list-song class="list-song" :play-count="item.playCount" :song-list="songList"></list-song>
  </div>
</template>

<script>
// 组件脚本部分保持不变
import bus from "@/eventBus/bus";
import ListSong from "@/components/ListSong.vue";
import request from "@/axios/request";
import {addPlayListCount} from "@/axios/music";

export default {
  name: "TopRight",
  data() {
    return {
      item: [],
      songList: [],//歌曲
      top: {
        image: 'https://p2.music.126.net/rIi7Qzy2i2Y_1QD7cd0MYA==/109951170048506929.jpg',
        text: '飙升榜',
      }
    }
  },
  created() {
    this.getTop()
  },
  computed: {
    formattedDate() {
      let date = new Date();
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      return `${year}年${month}月${day}日`;
    }
  },
  methods: {
    //top榜
    getTop() {
      request.getTop().then((res) => {
        this.item = res.data;
        this.songList = res.data.songMusicList;
      }).catch(err => {
        console.error(err)
        this.$message.error('请求错误')
      })
    },
    //新歌榜
    getNewMusicBoard() {
      request.getNewMusic().then((res) => {
        if (res.data === undefined) {
          this.$message.error("系统异常")
          return
        }
        this.songList = res.data;
      }).catch(err => {
        console.log(err)
        this.$message.error('请求错误')
      })
    },
    //热歌榜
    hotMusicBoard() {
      request.getHotMusic().then((res) => {
        if (res.data === undefined) {
          this.$message.error("系统异常")
          return
        }
        this.songList = res.data;
      }).catch(err => {
        console.log(err)
        this.$message.error('请求错误')
      })
    },
    //播放全部
    playList() {
      addPlayListCount(this.item.playListId) // 歌单播放量+1
      this.$store.commit('setSongList', this.songList)
    }
  },
  mounted() {
    bus.$on("brotherForObject", res => {
      this.top = res;
      switch (this.top.id) {
        case 1:
          this.getTop();
          break;
        case 2:
          this.getNewMusicBoard();
          break;
        case 3:
          this.hotMusicBoard();
          break;
      }
    });

  },
  components: {
    ListSong
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  display: flex;
  font-family: "PingFang SC", "Microsoft YaHei", sans-serif;
  background: var(--white);
  flex-direction: column;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  overflow: hidden;
}

/* 顶部区域 */
.top-container {
  padding: 25px 30px;
  display: flex;
  flex-direction: row;
  align-items: stretch;
  justify-content: flex-start;
  background: var(--white);
  border-bottom: 1px solid #eee;
}

.left-content {
  flex-shrink: 0;
}

.image-wrapper {
  position: relative;
  width: 180px;
  height: 180px;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
}

.image-wrapper:hover {
  transform: scale(1.03);
}

.image-wrapper:hover .image-overlay {
  opacity: 1;
}

.top-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}

.image-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.4);
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0;
  transition: opacity 0.3s ease;
}

.image-overlay i {
  color: white;
  font-size: 40px;
  cursor: pointer;
}

.right-content {
  flex: 1;
  display: flex;
  text-align: left;
  justify-content: center;
  flex-direction: column;
  padding-left: 30px;
}

.right-text {
  font-size: 24px;
  font-weight: 600;
  color: var(--text-color);
  margin: 0 0 10px 0;
}

.right-avatar {
  margin: 15px 0;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
}

.user-time {
  font-size: 14px;
  color: var(--text-color);
  display: flex;
  align-items: center;
}

.user-time i {
  font-size: 16px;
  margin-right: 6px;
  color: var(--text-color);
}

.update-date {
  font-weight: 500;
  color: var(--text-color);
}

.update-frequency {
  color: var(--text-color);
  margin-left: 5px;
}

/* 按钮区域 */
.right-btn {
  margin-top: 20px;
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
}

.play-btn {
  background: linear-gradient(to right, #3A89D4, #2b6cb0) !important;
  border: none;
  border-radius: 6px;
  padding: 10px 20px;
  font-weight: 500;
  box-shadow: 0 2px 6px rgba(58, 137, 212, 0.3);
  transition: all 0.2s ease;
}

.play-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 10px rgba(58, 137, 212, 0.4);
}

.icon-btn {
  background: var(--bg-color);
  color: var(--text-color);
  border: 1px solid #dcdfe6 !important;
  border-radius: 6px;
  padding: 9px 15px;
  transition: all 0.2s ease;
}

.icon-btn:hover {
  background: var(--bg-color);
  color: var(--text-color);
  border-color: #c6e2ff !important;
}

.icon-btn i {
  margin-right: 5px;
}

.count {
  font-size: 13px;
}

.list-song {
  width: 100%;
  flex: 1;
  background: var(--white);
}

/* 响应式设计 */
@media (max-width: 768px) {
  .top-container {
    flex-direction: column;
    padding: 20px;
    align-items: center;
    text-align: center;
  }

  .image-wrapper {
    width: 150px;
    height: 150px;
    margin-bottom: 20px;
  }

  .right-content {
    padding-left: 0;
    align-items: center;
  }

  .right-btn {
    justify-content: center;
  }

  .user-time {
    justify-content: center;
  }
}
</style>