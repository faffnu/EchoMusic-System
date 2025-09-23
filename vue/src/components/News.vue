<!-- 新歌推荐 -->
<template>
  <div class="new-songs-container">
    <div v-show="false">{{ index }}</div>
    <div class="songs-scroll-wrapper">
      <div class="songs-scroll-container" ref="scrollContainer">
        <div
            v-for="(item,index) in all"
            :key="index"
            class="song-item"
            @click="playSong(item)"
        >
          <div class="song-image">
            <img :src="item.coverImage" alt="无法加载该图片">
            <div class="play-icon">
              <i class="el-icon-video-play"></i>
            </div>
          </div>
          <div class="song-info">
            <div class="song-name">{{ item.songName }}</div>
            <div class="singer-name">
              <span v-for="(items,index2) in item.musicAndSingerList" :key="index2">
                <span v-for="(item3,index3) in items.singers" :key="index3">
                  {{ item3.singerName }}
                </span>
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="scroll-controls">
      <button class="scroll-btn prev" @click="scrollLeft">
        <i class="el-icon-arrow-left"></i>
      </button>
      <button class="scroll-btn next" @click="scrollRight">
        <i class="el-icon-arrow-right"></i>
      </button>
    </div>
  </div>
</template>

<script>
import {addMusicCount, insertPlayHistory} from "@/axios/music";
import request from "@/axios/request";
export default {
  name: 'News',
  props: {
    index: {
      type: String,
      default: '1'
    },
    all: []
  },
  data() {
    return {
      scrollPosition: 0
    }
  },
  computed: {
    userId() {
      if (this.$store.getters.getUser !== null) {
        return this.$store.getters.getUser.user.musicUserId
      }
    },
    isVip(){
      if (this.$store.getters.getUser !== null) {
        return this.$store.getters.getUser.user.isVip
      }
    }
  },
  mounted() {
    this.initScroll();
  },
  methods: {
    initScroll() {
      // 添加鼠标滚轮支持
      const container = this.$refs.scrollContainer;
      if (container) {
        container.addEventListener('wheel', this.handleWheel);
      }
    },
    handleWheel(event) {
      event.preventDefault();
      const container = this.$refs.scrollContainer;
      if (container) {
        container.scrollLeft += event.deltaY;
      }
    },
    scrollLeft() {
      const container = this.$refs.scrollContainer;
      if (container) {
        const itemWidth = 180; // 每个项目的宽度 + 间距
        container.scrollBy({ left: -itemWidth * 3, behavior: 'smooth' });
      }
    },
    scrollRight() {
      const container = this.$refs.scrollContainer;
      if (container) {
        const itemWidth = 180; // 每个项目的宽度 + 间距
        container.scrollBy({ left: itemWidth * 3, behavior: 'smooth' });
      }
    },
    // 歌曲播放量+1
    musicAddCount(id) {
      request.addMusicCount(id).then().catch(err => {
        console.log(err);
      });
    },
    playSong(item) {
      console.log(this.isVip,item.isVip)
      if (item.isVip !== this.isVip && item.isVip === true) {
        this.$message.error("该歌曲只有vip才能播放！")
        return
      }
      //歌曲播放量+1
      addMusicCount(item.songMusicId)
      if (this.$store.getters.getUser !== null) {
        insertPlayHistory(this.userId, item.songMusicId)//添加一条播放记录
      }
      this.musicAddCount(item.songMusicId)
      this.$store.commit('setSongList', item)
    }
  },
  beforeDestroy() {
    // 移除事件监听
    const container = this.$refs.scrollContainer;
    if (container) {
      container.removeEventListener('wheel', this.handleWheel);
    }
  }
}
</script>

<style scoped>
.new-songs-container {
  position: relative;
  width: 100%;
  margin: 20px 0;
}

.songs-scroll-wrapper {
  overflow: hidden;
  position: relative;
  width: 60%;
}

.songs-scroll-container {
  display: flex;
  overflow-x: auto;
  scroll-behavior: smooth;
  scrollbar-width: none; /* Firefox */
  -ms-overflow-style: none; /* IE and Edge */
  padding: 10px 0;
  gap: 20px;
}

.songs-scroll-container::-webkit-scrollbar {
  display: none; /* Chrome, Safari and Opera */
}

.song-item {
  flex: 0 0 auto;
  width: 200px;
  cursor: pointer;
  transition: all 0.3s ease;
  border-radius: 8px;
  overflow: hidden;
}

.song-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.song-item:hover .play-icon {
  opacity: 1;
}

.song-image {
  position: relative;
  width: 100%;
  height: 200px;
  border-radius: 8px;
  overflow: hidden;
  margin-bottom: 12px;
}

.song-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s ease;
}

.song-item:hover .song-image img {
  transform: scale(1.05);
}

.play-icon {
  position: absolute;
  bottom: 8px;
  right: 8px;
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.9);
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0;
  transition: opacity 0.3s ease;
}

.play-icon i {
  color: #ec4141;
  font-size: 16px;
  margin-left: 2px;
}

.song-info {
  padding: 0 4px;
}

.song-name {
  font-size: 14px;
  font-weight: 500;
  color: #333;
  margin-bottom: 4px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-family: 'PingFang SC', 'Microsoft YaHei', sans-serif;
}

.singer-name {
  font-size: 12px;
  color: #999;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-family: 'PingFang SC', 'Microsoft YaHei', sans-serif;
}

.scroll-controls {
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  transform: translateY(-50%);
  display: flex;
  justify-content: space-between;
  pointer-events: none;
}

.scroll-btn {
  pointer-events: auto;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: none;
  background: white;
  box-shadow: 0px 0px 10px 5px rgba(0,0,0,0.15);
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.3s ease;
  opacity: 0.8;
}

.scroll-btn:hover {
  opacity: 1;
  background: #f5f5f5;
}

.scroll-btn i {
  font-size: 16px;
  color: #333;
}

.prev {
  margin-left: 10%;
}

.next {
  margin-right: 10%;
}

@media (max-width: 768px) {
  .song-item {
    width: 140px;
  }

  .song-image {
    height: 140px;
  }

  .scroll-btn {
    width: 32px;
    height: 32px;
  }

  .prev {
    margin-left: -16px;
  }

  .next {
    margin-right: -16px;
  }
}
</style>