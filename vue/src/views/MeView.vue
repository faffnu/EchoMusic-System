<template>
  <el-container >
    <el-header style="height:120px">
      <NavBar :active-index="activeIndex"></NavBar>
    </el-header>
    <div v-if="user === null" class="main">
      <el-empty :image-size="500" image="/image/login.png" description="请登录！"></el-empty>
    </div>
    <el-container class="main" v-else>
      <el-main>
        <div class="main-content">
          <div class="main-top">
            <div class="avatar-container">
              <el-image :src="user.user.avatar" lazy class="user-avatar"></el-image>
            </div>
            <div class="user-info">
              <div class="username-section">
                <h1>{{ user.user.userName }}</h1>
                <div class="vip-badge">
                  <img v-if="user.user.isVip" src="/image/isVip.png" alt="VIP会员">
                  <img v-else src="/image/vip.png" alt="普通用户">
                </div>
              </div>
              <div class="bio-section">
                <div class="bio-header">
                  <span>个人简介：</span>
                  <el-button
                      type="text"
                      icon="el-icon-edit"
                      @click="editBio"
                      class="edit-btn"
                  >编辑</el-button>
                </div>
                <div class="bio-content">
                  {{ user.user.bio || '暂无简介' }}
                </div>
              </div>
            </div>
          </div>

          <!-- 我喜欢的音乐 -->
          <div class="main-bottom" v-if="likeMusic && likeMusic.length > 0 && likeMusic[0].songMusicList && likeMusic[0].songMusicList.length > 0">
            <div class="section-title">我喜欢的音乐</div>
            <hr class="section-divider">
            <div class="music-card"
                 @click="goLikeMusicList"
                 :style="{backgroundImage: `url(${likeMusic[0].songMusicList[0].coverImage})`}">
              <div class="card-overlay">
                <div class="play-count">
                  <i class="el-icon-headset"></i>
                  <span class="count">{{ likeMusic.length }}</span>
                </div>
                <div class="play-button">
                  <i class="el-icon-video-play"></i>
                </div>
              </div>
            </div>
            <div class="card-title" v-if="likeMusic && likeMusic.length > 0">{{user.user.userName }}喜欢的音乐</div>
          </div>

          <!-- 播放历史 -->
          <div class="main-bottom" style="margin-bottom: 40px" v-if="playHistory && playHistory.length > 0 && playHistory[0].songMusicList && playHistory[0].songMusicList.length > 0">
            <div class="section-title">播放历史</div>
            <hr class="section-divider">
            <div class="music-card"
                 @click="goLikePlayHistory"
                 :style="{backgroundImage: `url(${playHistory[0].songMusicList[0].coverImage})`}">
              <div class="card-overlay">
                <div class="play-count">
                  <i class="el-icon-headset"></i>
                  <span class="count">{{ playHistory[0].songMusicList.length }}</span>
                </div>
                <div class="play-button">
                  <i class="el-icon-video-play"></i>
                </div>
              </div>
            </div>
            <div class="card-title" v-if="playHistory && playHistory.length > 0">{{ user.user.userName }}播放历史</div>
          </div>
        </div>
      </el-main>
    </el-container>

    <!-- 编辑简介的对话框 -->
    <el-dialog
        title="编辑个人简介"
        :visible.sync="bioDialogVisible"
        width="500px"
        :before-close="handleClose"
    >
      <el-input
          type="textarea"
          :rows="4"
          placeholder="请输入个人简介"
          v-model="editBioText"
          maxlength="200"
          show-word-limit
      >
      </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="bioDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveBio">确 定</el-button>
      </span>
    </el-dialog>
  </el-container>
</template>

<script>
import request from "@/axios/request";
import NavBar from "@/components/NavBar.vue";
import Bottom from "@/components/Bottom.vue";

export default {
  data() {
    return {
      activeIndex: '4',
      likeMusic: [],
      playHistory: [],
      bioDialogVisible: false,
      editBioText: '',
      isSaving: false
    }
  },
  computed: {
    user() {
      return this.$store.getters.getUser;
    }
  },
  components: {
    Bottom,
    NavBar
  },
  created() {
    if (localStorage.getItem("user")) {
      this.$store.commit("setUser", JSON.parse(localStorage.getItem("user")));
    }
    if(this.user !== null) {
      this.getUserMusic()
      this.selectPlayHistory()
    }
  },
  methods: {
    //根据用户的token查询用户喜欢的音乐
    getUserMusic() {
      if (!this.user) {
        return
      }
      request.selectUserByToken(this.user.token).then(res => {
        if (res.data) {
          this.likeMusic = res.data
        } else {
          this.likeMusic = []
        }
      }).catch(err => {
        console.log(err);
        this.$message.error("请求出错")
        this.likeMusic = []
      })
    },
    //播放历史
    selectPlayHistory() {
      if (!this.user) {
        return
      }
      request.getPlayHistory(this.user.token).then(res => {
        if (res.data) {
          this.playHistory = [{
            "songMusicList": res.data
          }]
        } else {
          this.playHistory = []
        }

      }).catch(err => {
        console.log(err)
        this.$message.error('请求出错')
        this.playHistory = []
      })
    },
    //去播放历史页面
    goLikePlayHistory() {
      let playCount = 0;
      let music = []
      if (this.playHistory && this.playHistory.length > 0) {
        this.playHistory.forEach((item) => {
          if (item.songMusicList) {
            item.songMusicList.forEach(song => {
              playCount += song.playCount;
              music.push(song);
            })
          }
        })
      }
      this.$router.push({
        name: "play_history",
        params: {
          item: JSON.stringify(music),
          playCount: playCount
        }
      })
    },
    //去我喜欢的音乐
    goLikeMusicList() {
      if (this.likeMusic) {
        this.$router.push({
          name: "me_playList",
          params: {
            item: JSON.stringify(this.likeMusic)
          }
        })
      }
    },
    // 编辑简介
    editBio() {
      this.editBioText = this.user.user.bio || '';
      this.bioDialogVisible = true;
    },
    // 关闭对话框
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    // 保存简介
    saveBio() {
      if (this.isSaving) return;

      this.isSaving = true;

      // 调用API更新用户信息
      request.update(this.user.token, this.editBioText, this.user.user.isVip)
          .then(res => {
            if (res.code == 200) {
              // 更新本地存储的用户信息
              const updatedUser = {
                ...this.user,
                user: {
                  ...this.user.user,
                  bio: this.editBioText
                }
              };

              this.$store.commit("setUser", updatedUser);
              localStorage.setItem("user", JSON.stringify(updatedUser));

              this.$message.success('个人简介更新成功');
              this.bioDialogVisible = false;
            } else {
              this.$message.error(res.msg || '更新失败');
            }
          })
          .catch(error => {
            console.error('更新用户信息失败:', error);
            this.$message.error('更新失败，请重试');
          })
          .finally(() => {
            this.isSaving = false;
          });
    }
  }
}
</script>

<style scoped>
.main {
  width: 75%;
  height: 100%;
  background: var(--white);
  margin: 0 auto 20px;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.main-content {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  padding: 40px;

  .main-top {
    display: flex;
    margin-bottom: 40px;

    .avatar-container {
      flex-shrink: 0;
      margin-right: 30px;

      .user-avatar {
        width: 150px;
        height: 150px;
        border-radius: 50%;
        border: 3px solid #f5f5f5;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
      }
    }

    .user-info {
      flex: 1;

      .username-section {
        display: flex;
        align-items: center;
        margin-bottom: 20px;

        h1 {
          margin: 0;
          font-size: 28px;
          color: var(--text-color);
          font-weight: bold;
        }

        .vip-badge {
          margin-left: 15px;

          img {
            height: 28px;
            vertical-align: middle;
          }
        }
      }

      .bio-section {
        .bio-header {
          display: flex;
          align-items: center;
          margin-bottom: 10px;

          span {
            font-size: 16px;
            font-weight: bold;
            color: var(--text-color);
          }

          .edit-btn {
            margin-left: 10px;
            padding: 4px 8px;
            font-size: 14px;
          }
        }

        .bio-content {
          font-size: 15px;
          line-height: 1.6;
          color: var(--color);
          padding: 12px;
          background-color: var(--card-bg);
          border-radius: 6px;
          min-height: 24px;
        }
      }
    }
  }

  .main-bottom {
    margin-bottom: 40px;

    .section-title {
      font-size: 20px;
      font-weight: bold;
      color: var(--text-color);
      margin-bottom: 12px;
    }

    .section-divider {
      border: 2px solid #c20c0c;
      margin-bottom: 20px;
    }

    .music-card {
      position: relative;
      cursor: pointer;
      border-radius: 6px;
      height: 160px;
      width: 160px;
      background-repeat: no-repeat;
      background-position: center;
      background-size: cover;
      overflow: hidden;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
      transition: transform 0.3s;

      &:hover {
        transform: scale(1.05);

        .card-overlay {
          opacity: 1;
        }
      }

      .card-overlay {
        position: absolute;
        bottom: 0;
        left: 0;
        right: 0;
        background: linear-gradient(transparent, rgba(0, 0, 0, 0.7));
        padding: 10px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        color: white;
        opacity: 0.9;
        transition: opacity 0.3s;
      }
    }

    .play-count{
      background-color: transparent;
      border: none;
    }

    .card-title {
      margin-top: 12px;
      font-size: 14px;
      color: var(--color);
      cursor: pointer;

      &:hover {
        color: #c20c0c;
      }
    }
  }
}

.el-header {
  padding: 0 !important;
  border-bottom: 1px solid var(--odd-color) !important;
}

.el-main {
  background: var(--white) !important;
}
</style>