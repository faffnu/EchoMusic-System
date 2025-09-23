<template>
  <el-container style="margin-bottom: 20px">
    <el-header style="height:120px">
      <NavBar :active-index="activeIndex"></NavBar>
    </el-header>
    <!--  排行榜左右部分  -->
    <el-container class="main-container">
      <el-aside width="230px">
        <div>
          <div class="playlist-text">特色榜</div>
          <div class="topNarBar-container"
               v-if="topList!==null"
               v-for="item in topList"
               :key="item.id"
               @click="brotherForObject(item)"
               :style="{ backgroundColor: selectedId === item.id? 'var(--bg-color)' : 'transparent' }">
            <div class="item-image">
              <img :src="item.image" alt="网络不佳">
            </div>
            <div class="item-content">
              <div>{{ item.text }}</div>
              <div>{{ item.status }}</div>
            </div>
          </div>
        </div>
      </el-aside>
      <el-main>
        <top-right></top-right>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import NavBar from "@/components/NavBar.vue";
import topRight from "@/components/TopRight.vue";
import Bottom from "@/components/Bottom.vue";
import bus from "@/eventBus/bus";

export default {
  data() {
    return {
      activeIndex: '2',
      selectedId: null,// 存储当前选中项的 id
      // top 榜
      topList: [{
        id:1,
        image:'https://p2.music.126.net/rIi7Qzy2i2Y_1QD7cd0MYA==/109951170048506929.jpg',
        text:'飙升榜',
        status:'刚刚更新'
      },{
        id:2,
        image:'https://p2.music.126.net/5guhqPBTcIrrhLBotgaT6w==/109951170048511751.jpg',
        text:'新歌榜',
        status:'刚刚更新'
      },{
        id:3,
        image: 'https://p2.music.126.net/0SUEG8yDACfx0Bw2MYFv4Q==/109951170048519512.jpg',
        text: '热歌榜',
        status: '每天更新'
      }]
    }
  },
  created() {
    // 如果有默认选中第一个
    if (this.topList && this.topList.length > 0) {
      this.selectedId = this.topList[0].id;
    }
  },
  methods: {
    //将数据传递给兄弟组件
    brotherForObject(item) {
      this.selectedId = item.id;
      bus.$emit('brotherForObject', item)
    }
  },
  components: {
    Bottom,
    NavBar,
    topRight
  }
}
</script>
<style scoped>
.main-container {
  width: 75%;
  margin: 0 auto;
  background: var(--white);

  .playlist-text {
    font-family: 新宋体, serif;
    font-weight: bold;
    font-size: 1.1rem;
    padding-top: 20px;
    padding-left: 10px;
    padding-bottom: 20px;
  }
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

.el-container {
  box-shadow: none !important;
}

.topNarBar-container {
  padding: 20px;
  height: 100%;
  box-sizing: border-box;
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: flex-start;

  .item-image > img {
    width: 60px;
    height: 60px;
  }

  .item-content {
    flex: 1;
    width: 100%;
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
    font-size: 12px;
  }

  .item-content > div:nth-child(1) {
    padding-left: 10px;
    padding-top: 6px;
    color: var(--color);
  }

  .item-content > div:nth-child(2) {
    padding-left: 10px;
    padding-top: 6px;
    padding-bottom: 5px;
    color: var(--color);
  }
}
</style>