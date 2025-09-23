<template>
  <div>
    <div class="app-container" v-if="isLoading">
      <index-skeleton></index-skeleton>
    </div>
    <el-container v-else>
      <el-header>
        <NavBar :active-index="activeIndex"></NavBar>
      </el-header>
      <el-main>
        <!-- 轮播图区域  -->
        <div class="swiper" :style="{background: `linear-gradient(white, ${dominantColorHex})`}">
          <el-carousel ref="mySwiper" height="280px" class="swiper-container" indicator-position="outside"
                       @change="handleSlideChange" arrow="always">
            <el-carousel-item v-for="(item,index) in carouselImage" :key="index">
              <img
                  :src="item.swiperUrl"
                  :data-index="index"
                  crossorigin="anonymous"
                  ref="carouselImages"
                  class="carousel-image"
                  alt="加载失败"
                  @error="handleImageError"
                  @load="handleImageLoad"/>
            </el-carousel-item>
          </el-carousel>
        </div>
        <!-- 热门推荐区域 -->
        <div class="hot-container">
          <el-image :src="require('../../public/image/hot.png')" style="width: 50px; height: 50px"></el-image>
          <h1 class="new-top">热门推荐</h1>
          <el-menu default-active="1" class="el-menu-demo"
                   text-color="#999999" active-text-color="red"
                   mode="horizontal"
                   @select="selectMenu">
            <el-menu-item index="1">
              推荐
            </el-menu-item>
            <el-menu-item index="2">
              华语
            </el-menu-item>
            <el-menu-item index="3">
              流行
            </el-menu-item>
            <el-menu-item index="4">
              民谣
            </el-menu-item>
          </el-menu>
          <!-- 热门推荐 -->
          <recommend :index="recommendIndex" :play-list="playList"></recommend>
        </div>
        <!-- 新碟上架区域 -->
        <div class="new-container">
          <el-image :src="require('../../public/image/new.png')" style="width: 50px; height: 50px"></el-image>
          <h1 class="new-top">新碟上架</h1>
          <el-menu default-active="1" class="el-menu-demo"
                   text-color="#999999" active-text-color="red"
                   mode="horizontal"
                   @select="selectNews">
            <el-menu-item index="1">
              全部
            </el-menu-item>
            <el-menu-item index="2">
              华语
            </el-menu-item>
            <el-menu-item index="3">
              欧美
            </el-menu-item>
            <el-menu-item index="4">
              韩国
            </el-menu-item>
          </el-menu>
          <news :index="newIndex" class="new" :all="newSong"></news>
        </div>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import request from "@/axios/request";
import ColorThief from "colorthief";
import NavBar from "@/components/NavBar.vue";//头部Bar
import indexSkeleton from "@/components/IndexSkeleton.vue";//首页加载动画
import Recommend from "@/components/Recommend.vue"; //热门推荐
import News from "@/components/News.vue"; //新碟上架
import Bottom from "@/components/Bottom.vue"; //底部区域
export default {
  data() {
    return {
      isLoading: true,//是否加载完成
      activeIndex: '1',//当前菜单索引
      carouselImage: [],//轮播图
      recommendIndex: '1',//热门推荐索引
      itemPlayList: [],//热门歌单
      playList: [],//热门推荐
      itemNewSong: [],//临时新歌推荐
      newSong: [],//新歌推荐
      dominantColor: null,//提取的颜色
      currentSlideIndex: 0,//当前轮播图的索引，从0开始
      newIndex: '1',//新碟上架
    }
  },
  created() {
    this.saveIndex()
  },
  computed: {
    // 将 RGB 数组转为十六进制颜色代码
    dominantColorHex() {
      if (!this.dominantColor) return "#C6C5D3";//默认颜色
      return this.rgbToHex(...this.dominantColor);
    }
  },
  mounted() {
    if (!this.isLoading) {
      // 页面加载时提取首张图片的颜色
      const firstImg = this.$refs.carouselImages[0];
      if (firstImg) {
        if (firstImg.complete) {
          const colorThief = new ColorThief();
          this.dominantColor = colorThief.getColor(firstImg);
        } else {
          firstImg.addEventListener("load", () => {
            const colorThief = new ColorThief();
            this.dominantColor = colorThief.getColor(firstImg);
          });
        }
      }
    }
  },
  methods: {
    // 保存数据
    saveIndex() {
      let item = localStorage.getItem("index");
      if (item !== null) {
        let data = JSON.parse(localStorage.getItem("index"));
        this.carouselImage = data.swiper // 获得轮播图
        this.itemPlayList = data.playList
        this.playList = data.playList
        this.newSong = data.music
        this.itemNewSong = data.music
        this.isLoading = false
      } else {
        this.index()
      }
    },
    // 请求首页数据
    index() {
      request.index().then(res => {
        let {data} = res
        if (res.code !== '200') {
          this.$message.error(res.message);
        }
        let item = localStorage.getItem("index");
        if (item === null || item === undefined) {
          localStorage.setItem("index", JSON.stringify(data));
          console.log(data)
        }
        this.saveIndex()
      }).catch(err => {
        console.log(err);
      })
    },
    handleImageError(event) {
      console.error('Image failed to load:', event.target.src);
    },
    // 图片加载完成后提取颜色
    handleImageLoad(event) {
      const img = event.target;
      const colorThief = new ColorThief();
      // 检查图片是否有有效的宽度和高度
      if (img.width > 0 && img.height > 0) {
        this.extractColor(img, colorThief);
      } else {
        // 若图片宽度或高度为 0，等待一段时间后再次检查
        const checkInterval = setInterval(() => {
          if (img.width > 0 && img.height > 0) {
            clearInterval(checkInterval);
            this.extractColor(img, colorThief);
          }
        }, 100);
      }
    },
    // 提取主色
    extractColor(img, colorThief) {
      try {
        const index = parseInt(img.getAttribute("data-index"));
        if (index === this.currentSlideIndex) {
          this.dominantColor = colorThief.getColor(img);
        }
      } catch (error) {
        console.error("颜色提取失败:", error);
      }
    },
    // 轮播图切换时更新颜色
    handleSlideChange(currentIndex) {
      this.currentSlideIndex = currentIndex;
      const currentImg = this.$refs.carouselImages[currentIndex];
      if (currentImg) {
        if (currentImg.complete) {
          const colorThief = new ColorThief();
          this.dominantColor = colorThief.getColor(currentImg);
        } else {
          currentImg.addEventListener("load", () => {
            const colorThief = new ColorThief();
            this.dominantColor = colorThief.getColor(currentImg);
          });
        }
      }
    },
    // RGB 转十六进制
    rgbToHex(r, g, b) {
      return '#' + [r, g, b].map((x) => x.toString(16).padStart(2, "0")).join("");
    },
    // 根据歌单类型获得歌单
    playListByTag(type) {
      request.playlistByTag(type).then(res => {
        if (res.code === '200') {
          this.playList = res.data
        } else {
          this.$message.error(res.message)
        }
      }).catch(err => {
        this.$message.error("请求错误！")
        console.error(err)
      })
    },
    // 热门推荐选择
    selectMenu(key) {
      this.recommendIndex = key
      switch (key) {
        case '1':
          this.playList = this.itemPlayList
          break
        case '2':
          this.playListByTag('HY')
          break
        case '3':
          this.playListByTag('LX')
          break
        case '4':
          this.playListByTag('MY')
          break
      }
    },
    //根据类型获得新歌
    newMusicByTag(type) {
      request.musicByTag(type).then((res) => {
        if (res.code === '200') {
          this.newSong = res.data
        } else {
          this.$message.error(res.message)
        }
      }).catch(err => {
        this.$message.error("请求错误！")
        console.error(err)
      })
    },
    //新碟上架类型选择
    selectNews(key) {
      this.newIndex = key
      switch (key) {
        case '1':
          this.newSong = this.itemNewSong
          break
        case '2':
          this.newMusicByTag('ZH')
          break
        case '3':
          this.newMusicByTag('ER')
          break
        case '4':
          this.newMusicByTag('KR')
          break
      }
    }
  },
  components: {
    NavBar,
    indexSkeleton,
    Recommend,
    News,
    Bottom
  }
}
</script>
<style>
/* 中间区域 */
.el-main {
  padding: 0 !important;
  align-items: center;
  overflow-y: hidden;
}

/* 轮播图区域 */
.swiper {
  box-sizing: border-box;
  position: relative;
  backdrop-filter: blur(10px);
  top: 50%;
  left: 0;
  margin-bottom: 20px;
}

.el-carousel__indicators {
  position: absolute !important;
  width: 100%;
  top: 90% !important;
  left: 0 !important;
}

.el-carousel--horizontal {
  overflow: hidden;
}

.swiper-container {
  text-align: center;
  box-sizing: border-box;
}

.carousel-image {
  height: 280px;
  object-fit: cover;
  box-sizing: border-box;
}

/* 左轮播图指示点 */
.el-carousel__arrow--left {
  left: 20% !important;
}

/* 右轮播图指示点 */
.el-carousel__arrow--right {
  right: 20% !important;
}

.el-carousel__button {
  background-color: orangered !important;
}

/* 热门推荐区域 */
.hot-container {
  border-radius: 10px;
  width: 80%;
  height: 520px;
  box-sizing: border-box;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: var(--bg-dialog-color);
}

.el-menu-demo{
  border-radius: 10px;
  width: 100%;
  align-items: center;
  display: flex;
  flex-direction: row;
  justify-content: center;

  .el-menu-item{
    font-size: 17px;
  }
  .el-menu-item.is-active{
    font-size: 25px;
  }
}


/* 新碟上架区域 */
.new-container {
  cursor: pointer;
  margin: 10px auto;
  width: 80%;
  flex-wrap: wrap;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.new {
  cursor: pointer;
  margin: 10px auto;
  width: 90%;
  flex-wrap: wrap;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

/* 底部区域 */
.el-footer {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 0 !important;
  position: fixed !important;
  bottom: 0 !important;
  left: 0 !important;
}
</style>