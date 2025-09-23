<template>
  <el-container style="height: 100%">
    <el-header style="height:120px">
      <NavBar :active-index="activeIndex"></NavBar>
    </el-header>
    <el-main style="width: 90%;margin-left: auto;margin-right: auto;">
      <el-row :gutter="20" style="display: flex;justify-content: center;">
        <!-- 左侧歌手列表 -->
        <el-col :span="8" style="width: 200px">
          <div class="singers-container">
            <div class="singer-list">
              <h3 class="section-title">歌手列表</h3>
              <div v-if="loadingSingers" class="loading">
                <el-skeleton :rows="10" animated />
              </div>
              <div v-else-if="singers.length === 0" class="empty">
                <el-empty description="暂无歌手数据"></el-empty>
              </div>
              <div v-else class="singer-grid">
                <div v-for="singer in singers"
                    :key="singer.singerId"
                    class="singer-item"
                    :class="{ 'active': selectedSingerId === singer.singerId }"
                    @click="selectSinger(singer)">
                  <div class="avatar">
                    <el-avatar :size="60" :src="getAvatarUrl(singer.singerName)">
                      {{ singer.singerName.charAt(0) }}
                    </el-avatar>
                  </div>
                  <div class="name">{{ singer.singerName }}</div>
                </div>
              </div>
            </div>
          </div>
        </el-col>

        <!-- 右侧歌曲列表 -->
        <el-col :span="16">
          <div class="singer-detail-container">
            <div class="header" v-if="selectedSinger">
              <h3>{{ selectedSinger.singerName }}的歌曲</h3>
            </div>
            <div v-else class="header">
              <h3>请选择歌手</h3>
            </div>

            <div class="song-list">
              <div v-if="loadingSongs" class="loading">
                <el-skeleton :rows="5" animated />
              </div>

              <div v-else-if="!selectedSinger" class="empty">
                <el-empty description="请先选择一位歌手"></el-empty>
              </div>

              <div v-else-if="songs.length === 0" class="empty">
                <el-empty description="该歌手暂无歌曲"></el-empty>
              </div>

              <div v-else>
                <div class="song-count">共{{ songs.length }}首歌曲</div>
                <el-table :data="songs" class="songs">
                  <el-table-column type="index" width="50" label="序号"></el-table-column>
                  <el-table-column prop="songName" label="歌曲名称"></el-table-column>
                  <el-table-column prop="duration" label="时长" width="100">
                  </el-table-column>
                  <el-table-column prop="playCount" label="播放次数" width="100">
                    <template slot-scope="scope">
                      {{ scope.row.playCount || 0 }}次
                    </template>
                  </el-table-column>
                  <el-table-column label="操作" width="120">
                    <template slot-scope="scope">
                      <el-button
                          size="mini"
                          @click="playSong(scope.row)"
                          icon="el-icon-video-play"
                      >播放</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import request from '@/axios/request';
import NavBar from "@/components/NavBar.vue";
import {addMusicCount, insertPlayHistory} from "@/axios/music";

export default {
  name: 'SingerAndSongView',
  components: {NavBar},
  data() {
    return {
      singers: [],
      songs: [],
      selectedSingerId: null,
      selectedSinger: null,
      loadingSingers: false,
      loadingSongs: false,
      activeIndex: '5'
    }
  },
  created() {
    this.fetchSingers();
  },
  methods: {
    async fetchSingers() {
      this.loadingSingers = true;
      try {
        const res = await request.getAllSingers();
        this.singers = res.data || [];
      } catch (error) {
        console.error('获取歌手列表失败:', error);
        this.$message.error('获取歌手列表失败');
      } finally {
        this.loadingSingers = false;
      }
    },

    async fetchSingerSongs(singerId) {
      this.loadingSongs = true;
      try {
        const res = await request.getSongsBySingerId(singerId);
        this.songs = res.data || [];
      } catch (error) {
        console.error('获取歌手歌曲失败:', error);
        this.$message.error('获取歌手歌曲失败');
      } finally {
        this.loadingSongs = false;
      }
    },

    getAvatarUrl(name) {
      // 这里可以根据需要实现歌手头像获取逻辑
      // 暂时返回空，使用文字头像
      return '';
    },

    selectSinger(singer) {
      this.selectedSingerId = singer.singerId;
      this.selectedSinger = singer;
      this.fetchSingerSongs(singer.singerId);
    },

    playSong(item) {
      if (item.isVip !== false) {
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
  }
}
</script>

<style scoped>
.singers-container {
  padding: 20px;
  background: var(--white);
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  height: calc(100vh - 200px);
  overflow-y: auto;
  flex-direction: column;
  color: var(--color);
}

.singer-detail-container {
  padding: 20px;
  background: var(--white);
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  height: calc(100vh - 200px);
  overflow-y: auto;
}

.section-title {
  margin-bottom: 20px;
  color: var(--text-color);
  border-bottom: 1px solid #eee;
  padding-bottom: 10px;
  text-align: center;
}


.singer-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
  transition: all 0.3s;
  padding: 10px;
  border-radius: 8px;
  border: 1px solid transparent;
}

.singer-item:hover {
  transform: translateY(-3px);
  background-color: var(--bg-color);
  border-color: #e4e7ed;
}

.singer-item.active {
  background-color: var(--bg-color);
  border-color: #b3d8ff;
}

.singer-item .name {
  margin-top: 8px;
  text-align: center;
  font-size: 13px;
  max-width: 80px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.loading {
  padding: 40px 0;
}

.song-count {
  margin-bottom: 15px;
  font-size: 14px;
  color: var(--text-color);
}

.header {
  margin-bottom: 20px;
}

.header h3 {
  margin: 0;
  color: var(--text-color);
}

.songs{
  color: var(--text-color);
}


:deep(.el-table thead){
  color: var(--text-color);
}

:deep(.el-table tr){
  background-color: var(--white) ;
}

:deep(.el-table th){
  background-color: var(--white) ;
}




</style>