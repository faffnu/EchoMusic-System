<template>
  <el-container>
    <el-header style="height:120px">
      <NavBar :active-index="activeIndex"></NavBar>
    </el-header>
    <el-container class="main">
      <el-main>
        <!-- 顶部区域 -->
        <div class="main-content">
          <div class="main-top">
            <div class="main-content-top" v-if="user.user !== null">
              <el-image :src="user.user.avatar" lazy></el-image>
            </div>
            <div class="main-content-bottom">
              <div class="main-content-bottom-top">
                <div style="margin-bottom: 10px">{{ user.user.userName }}</div>
                <div v-if="user.user.isVip">
                  <img src="/image/isVip.png" alt="网络不佳">
                </div>
                <div v-else>
                  <img src="/image/vip.png" alt="网络不佳">
                </div>
              </div>
              <hr style="width: 800px">
              <div style="margin-top: 20px">个人简介：{{ user.user.bio }}</div>
              <div style="margin-top: 20px;">注册时间：{{ user.user.registrationDate }}</div>
            </div>
          </div>
          <!--  底部区域  -->
          <list-song class="list-song" :play-count="playCount" :song-list="songList"
                     :is-play-history="true"></list-song>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import NavBar from "@/components/NavBar.vue";
import ListSong from "@/components/ListSong.vue";

export default {
  components: {
    NavBar,
    ListSong
  },
  data() {
    return {
      activeIndex: '4',
      playCount: 0,
      songList: [],
    }
  },
  computed: {
    user() {
      return this.$store.getters.getUser;
    }
  },
  created() {
    if (this.$route.params.item === undefined || this.$route.params.item === "") {
      this.$message.error("非法请求！")
      setTimeout(this.goMe, 3000)
      return
    }
    this.playCount = JSON.parse(this.$route.params.playCount);
    this.songList = JSON.parse(this.$route.params.item)
  },
  methods: {
    goMe() {
      this.$router.push("/me")
    }
  }
}
</script>

<style scoped>
.main {
  width: 75%;
  height: 100%;
  margin: 0 auto;
  background: var(--white);
}

.main-content {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  margin-top: 40px;

  .main-top {
    display: flex;
    flex-direction: row;

    .main-content-top {
      margin-left: 40px;

      .el-image {
        border: 1px solid #d5d5d5 !important;
        padding: 3px;
      }
    }

    .main-content-bottom {
      margin-left: 40px;

      .main-content-bottom-top {
        display: flex;
        align-items: center;

        img {
          vertical-align: center;
          width: 30px;
        }

        div {
          line-height: 30px;
          font-size: 22px;
        }

        div:nth-child(2) {
          margin-left: 10px;
        }
      }
    }
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

.el-main {
  background: var(--white) !important;
}

.el-container {
  box-shadow: none !important;
}

.list-song {
  width: 100%;
  height: 100%;
  display: flex;
  background: var(--white);
  flex-direction: column;
}
</style>