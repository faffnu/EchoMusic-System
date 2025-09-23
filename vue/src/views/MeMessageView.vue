<template>
  <el-container>
    <el-header>
      <NavBar :active-index="activeIndex"></NavBar>
    </el-header>
    <el-main>
      <div class="container-message">
        <!-- 消息分类标签 -->
        <el-tabs v-model="activeTab" type="card">
          <el-tab-pane label="系统消息" name="system">
            <div class="message-list">
              <div class="message-item">
                <img src="/image/music.png" alt="网络不佳" class="message-avatar">
                <div class="message-content">
                  <div class="message-title">{{ systemMessages.title }}</div>
                  <div class="message-text">{{ systemMessages.content }}</div>
                  <div class="message-time">{{ user.user.registrationDate}}</div>
                </div>
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-main>
    <el-footer>
      <Bottom></Bottom>
    </el-footer>
  </el-container>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import Bottom from "@/components/Bottom.vue";

export default {
  name: "MeMessageView",
  components: {
    Bottom,
    NavBar
  },
  computed:{
    user(){
      return this.$store.state.user;
    }
  },
  data() {
    return {
      activeIndex: '1',
      activeTab: 'system',
      systemMessages: {
        title: "系统消息",
        content: "欢迎登录！"
      }
    }
  }
};
</script>

<style scoped>
.el-aside {
  padding: 20px;
  height: 100%;
  box-sizing: border-box;
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: flex-start;
}

.el-main {
  height: 100%;
  background: var(--white) !important;
}
.el-container {
  box-shadow: none !important;
}
.container-message {
  width: 75%;
  height: 100%;
  margin: 0 auto 20px;
  background: var(--white);
}

.message-list {
  padding: 20px;
}

.message-item {
  display: flex;
  align-items: center;
  padding: 10px 0;
  border-bottom: 1px solid #eee;
}

.message-avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 15px;
}

.message-content {
  flex: 1;
}

.message-title {
  font-weight: bold;
  margin-bottom: 5px;
}

.message-text {
  color: #666;
  margin-bottom: 5px;
}

.message-time {
  font-size: 12px;
  color: #999;
}
</style>