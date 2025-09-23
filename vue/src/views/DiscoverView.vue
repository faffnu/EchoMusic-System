<template>
  <el-container class="main-container">
    <el-header>
      <NavBar :active-index="activeIndex"></NavBar>
    </el-header>
    <el-main v-if="this.playList.length !== 0">
      <div class="playlist-container">
        <div class="header-section">
          <h2 class="section-title">全部歌单</h2>
          <div class="subtitle">发现更多精彩内容</div>
        </div>
        <div class="playlist-grid">
          <el-card
              v-for="(item,index) in playList"
              :key="index"
              class="playlist-card"
              @click.native="goPlayList(item)"
              shadow="hover"
          >
            <div class="card-image" :style="{backgroundImage: `url(${item.playListCover})`}">
              <!-- 图片顶部区域 -->
              <div class="image-overlay">
                <div class="play-count">
                  <i class="el-icon-headset"></i>
                  <span class="count">{{tranNumber(item.playCount) }}</span>
                </div>
                <div class="play-button">
                  <i class="el-icon-video-play"></i>
                </div>
              </div>
            </div>
            <div class="card-content">
              <div class="text">{{item.description}}</div>
              <div class="author">
                <span class="by-text">by</span>
                <span v-if="item.musicUser" class="username">{{item.musicUser.userName}}</span>
              </div>
            </div>
          </el-card>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import request from "@/axios/request";
import NavBar from "@/components/NavBar.vue";
import Bottom from "@/components/Bottom.vue";
import {tranNumber} from "@/utli/utils";
export default {
  data(){
    return{
      activeIndex:'3',
      playList:[]
    }
  },
  created() {
    let playList=localStorage.getItem("playListAll");
    if (playList !== null) {
      this.playList=JSON.parse(localStorage.getItem('playListAll'));
    }else{
      this.getPlayListAll()
    }

  },
  methods:{
    tranNumber,
    getPlayListAll(){
      request.getPlayListAll().then(res=>{
        if (res.data === undefined){
          this.$message.error(res.message)
          return
        }
        this.playList=res.data;
        localStorage.setItem("playListAll",JSON.stringify(this.playList));
      }).catch(err=>{
        console.log(err);
      })
    },
    goPlayList(item){
      this.$router.push({
        name:'indexPlayList',
        params:{
          item:JSON.stringify(item),
          index:this.activeIndex
        }
      })
    }
  },
  components:{
    Bottom,
    NavBar
  }
}
</script>

<style scoped>
.main-container {
  min-height: 100vh;

}

.el-header {
  height: 80px !important;
  padding: 0 !important;
}

.playlist-container {
  width: 90%;
  max-width: 1280px;
  margin: 0 auto;
  padding: 30px 20px;
  background: var(--white);
}

.header-section {
  margin-bottom: 30px;
  text-align: center;
}

.section-title {
  font-size: 28px;
  font-weight: 700;
  color: var(--text-color);
  margin: 0 0 10px 0;
  position: relative;
  display: inline-block;
}

.section-title::after {
  content: '';
  position: absolute;
  bottom: -8px;
  left: 50%;
  transform: translateX(-50%);
  width: 60px;
  height: 4px;
  background: linear-gradient(to right, #42b983, #3498db);
  border-radius: 2px;
}

.subtitle {
  font-size: 16px;
  color: var(--text-color);
  margin-top: 15px;
}

.playlist-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 25px;
  justify-content: center;
}

.playlist-card {
  border-radius: 12px !important;
  overflow: hidden;
  transition: all 0.3s ease;
  border: none !important;
  background: var(--card-bg);
  backdrop-filter: blur(10px);
}

.playlist-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 12px 20px rgba(0, 0, 0, 0.15) !important;
}

.card-image {
  height: 200px;
  border-radius: 8px;
  background-size: cover;
  background-position: center;
  position: relative;
  overflow: hidden;
}

.card-image::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(to top, rgba(0, 0, 0, 0.6) 0%, transparent 100%);
  opacity: 0;
  transition: opacity 0.3s ease;
}

.playlist-card:hover .card-image::before {
  opacity: 1;
}

.image-overlay {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: white;
  background: linear-gradient(to top, rgba(0, 0, 0, 0.7), transparent);
  opacity: 0.9;
  transition: opacity 0.3s ease;
}

.playlist-card:hover .image-overlay {
  opacity: 1;
}

.play-count {
  display: flex;
  align-items: center;
  font-size: 13px;
  background-color: transparent;
  color: white;
  border: none;
}

.play-count i {
  margin-right: 5px;
}

.play-button {
  width: 28px;
  height: 28px;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.2);
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.2s ease;
}

.play-button:hover {
  background: #42b983;
  transform: scale(1.1);
}

.play-button i {
  font-size: 14px;
  color: white;
}

.card-content {
  padding: 15px 10px 10px;
}

.text {
  font-size: 15px;
  font-weight: 500;
  color: var(--text-color);
  line-height: 1.4;
  height: 42px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  margin-bottom: 8px;
  transition: color 0.2s ease;
}

.playlist-card:hover .text {
  color: #42b983;
}

.author {
  display: flex;
  align-items: center;
  font-size: 13px;
}

.by-text {
  color: var(--text-color);
  margin-right: 5px;
}

.username {
  color: var(--text-color);
  font-size: 15px;
  font-weight: 500;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

/* 响应式设计 */
@media (max-width: 1024px) {
  .playlist-grid {
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    gap: 20px;
  }
}

@media (max-width: 768px) {
  .playlist-container {
    width: 95%;
    padding: 20px 15px;
  }

  .section-title {
    font-size: 24px;
  }

  .playlist-grid {
    grid-template-columns: repeat(auto-fill, minmax(160px, 1fr));
    gap: 15px;
  }

  .card-image {
    height: 160px;
  }
}

@media (max-width: 480px) {
  .playlist-grid {
    grid-template-columns: repeat(2, 1fr);
    gap: 12px;
  }

  .card-image {
    height: 140px;
  }

  .text {
    font-size: 14px;
  }
}
</style>