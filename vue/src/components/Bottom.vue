<template>
  <div class="footer" v-if="songList.length !== 0">
      <aplayer ref="player" :music="item" :list="playList" :autoplay="shouldAutoplay"
               :mutex="false" listMaxHeight="300" theme="#409EFF" class="aplayer"
               @ended="handleSongEnded"></aplayer>
  </div>
</template>

<script>
import aplayer from "vue-aplayer";
export default {
  name: 'Bottom',
  components: {
    aplayer
  },
  data() {
    return {
      itemMusic: [],
      item: {},
      playList: [],
      currentIndex: 0, // 当前播放歌曲的索引
      shouldAutoplay: true // 控制是否自动播放
    };
  },
  computed: {
    songList() {
      return this.$store.getters.getSongList;
    }
  },
  watch: {
    songList(newList) {
      // 歌单
      if (this.songList.length > 1) {
        this.item = {}; // 初始化 item 为一个空对象
        this.playList = []; // 清空播放列表，避免重复添加
        if (newList.length !== 0) {
          newList.forEach(items => {
            let singerTo = '';
            items.musicAndSingerList.forEach(item => {
              let {singers} = item;
              singerTo = singerTo + singers[0].singerName + " ";
            });
            this.playList.push({
              title: items.songName,
              src: items.songUrl, // 使用 src 替代 url
              pic: items.coverImage,
              artist: singerTo
            });
          });
        }
      } else {
        // 歌曲
        if (this.songList.length !== 0) {
          let singerTo = '';
          newList.musicAndSingerList.forEach(item => {
            let {singers} = item;
            singerTo = singerTo + singers[0].singerName + " ";
          });
          this.playList.push({
            title: newList.songName,
            src: newList.songUrl,
            pic: newList.coverImage,
            artist: singerTo
          });
        }
      }
      if (this.playList.length > 0) {
        this.item = this.playList[0];
      }
    }
  },
  methods: {
    // 播放完之后
    handleSongEnded() {
      if (this.playList.length > 0) {
        // 计算下一首歌曲的索引
        this.currentIndex = (this.currentIndex + 1) % this.playList.length;
        this.item = this.playList[this.currentIndex];
        //this.$forceUpdate();
      }
    }
  }
};
</script>

<style scoped>
.footer {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  box-sizing: border-box;
  opacity: 0;
  visibility: hidden;
  transform: translateY(100%);
  transition: opacity 0.5s ease-out, transform 0.5s ease-out, visibility 0.5s ease-out;
  z-index: 999;
}

.el-footer .footer {
  opacity: 1;
  visibility: visible;
  transform: translateY(0);
}

.aplayer{
  background-color: var(--bg-dialog-color);
  color: var(--color);
}


.aplayer :deep(.aplayer-list ol li:hover) {
  background-color: var(--bg-dialog-color);
}

.aplayer :deep(.aplayer-list ol li.aplayer-list-light) {
  background-color: var(--bg-color);
}
</style>