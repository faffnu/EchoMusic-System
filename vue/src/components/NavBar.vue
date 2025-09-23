<template>
  <div class="navbar-container">
    <el-menu :default-active="activeIndex" class="el-menu-demo"
             text-color="#999999" active-text-color="red"
             mode="horizontal" router>
      <div class="logo">
        <picture>
          <source
              srcset="/image/music.png"
              media="(orientation: portrait)"
              height="50px"/>
          <img src="/image/music.png" height="50" alt="music"/>
        </picture>
      </div>
      <el-menu-item index="1" route="/">
        首页
      </el-menu-item>
      <el-menu-item index="2" route="/top">
        排行榜
      </el-menu-item>
      <el-menu-item index="5" route="/singers">
        歌手
      </el-menu-item>
      <el-menu-item index="3" route="/discover/playlist">
        歌单
      </el-menu-item>
      <el-menu-item index="4" route="/me">
        我的音乐
      </el-menu-item>
      <div class="right">
        <el-autocomplete
            v-model="songName"
            :fetch-suggestions="querySearchAsync"
            @change="changeData"
            placeholder="请输入歌曲名称"
            :maxlength="30">
        </el-autocomplete>
        <el-button class="icon-search" icon="el-icon-search" @click="search(true)"></el-button>
        <el-dropdown v-show="isAvatar !== null">
          <span class="el-dropdown-link">
            <el-badge :value="1" class="item" v-if="isDot">
              <el-avatar :src="avatar" v-if="avatar"></el-avatar>
            </el-badge>
            <span v-else>
              <el-avatar :src="avatar" v-if="avatar"></el-avatar>
            </span>
          </span>
          <el-dropdown-menu slot="dropdown">
            <!--  当点击以后不会出现小圆点  -->
            <el-badge :value="1" class="item" v-if="isDot">
              <el-dropdown-item @click.native="goMessage">我的消息</el-dropdown-item>
            </el-badge>
            <el-dropdown-item v-else @click.native="goMessage">我的消息</el-dropdown-item>
            <el-dropdown-item @click.native="showChangePasswordDialog">修改密码</el-dropdown-item>
            <el-dropdown-item class="item" @click.native="quitLogin">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <div class="el-button icon-search" v-if="!isAvatar" @click="isLogin = true">登录</div>
        <el-switch
            style="margin-left: 10px"
            v-model="themes"
            @change="changeTheme">
        </el-switch>
      </div>
    </el-menu>

    <!-- 登录对话框 -->
    <el-dialog
        :visible.sync="isLogin"
        width="400px"
        class="login-dialog"
        :before-close="handleLoginClose">
      <div class="dialog-tabs">
        <div class="tabs-header">
          <span
              :class="['tab-item', activeTab === 'login' ? 'active' : '']"
              @click="activeTab = 'login'">登录</span>
          <span
              :class="['tab-item', activeTab === 'register' ? 'active' : '']"
              @click="activeTab = 'register'">注册</span>
        </div>

        <!-- 登录表单 -->
        <div v-if="activeTab === 'login'" class="form-container">
          <div class="login-logo">
            <picture>
              <source
                  srcset="/image/music.png"
                  media="(orientation: portrait)"
                  height="50px"/>
              <img src="/image/music.png" height="50" alt="music"/>
            </picture>
          </div>
          <div class="login-logo-center">
            <el-input
                placeholder="请输入账号"
                prefix-icon="el-icon-user"
                v-model="username">
            </el-input>
            <el-input
                type="password"
                placeholder="请输入密码"
                prefix-icon="el-icon-view"
                v-model="password"
                @keyup.enter.native="login">
            </el-input>
          </div>
          <div class="login-center">
            <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="login">登 录</el-button>
            </span>
          </div>
        </div>

        <!-- 注册表单 -->
        <div v-else class="form-container">
          <div class="login-logo">
            <picture>
              <source
                  srcset="/image/music.png"
                  media="(orientation: portrait)"
                  height="50px"/>
              <img src="/image/music.png" height="50" alt="music"/>
            </picture>
          </div>
          <div class="login-logo-center">
            <el-input
                placeholder="请输入用户名"
                prefix-icon="el-icon-user"
                v-model="registerForm.username"
                :maxlength="20">
            </el-input>
            <el-input
                type="password"
                placeholder="请输入密码"
                prefix-icon="el-icon-view"
                v-model="registerForm.password"
                :maxlength="30">
            </el-input>
            <el-input
                type="password"
                placeholder="请确认密码"
                prefix-icon="el-icon-view"
                v-model="registerForm.confirmPassword"
                :maxlength="30"
                @keyup.enter.native="register">
            </el-input>
            <el-input
                placeholder="请输入个人简介(可选)"
                prefix-icon="el-icon-edit"
                v-model="registerForm.bio"
                :maxlength="100">
            </el-input>
          </div>
          <div class="login-center">
            <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="register">注 册</el-button>
            </span>
          </div>
        </div>
      </div>
    </el-dialog>

    <!-- 修改密码对话框 -->
    <el-dialog
        title="修改密码"
        :visible.sync="changePasswordDialogVisible"
        width="400px"
        class="change-password-dialog">
      <div class="form-container">
        <el-form :model="passwordForm" :rules="passwordRules" ref="passwordForm" label-width="100px">
          <el-form-item label="原密码" prop="oldPassword">
            <el-input
                type="password"
                v-model="passwordForm.oldPassword"
                autocomplete="off"
                placeholder="请输入原密码">
            </el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newPassword">
            <el-input
                type="password"
                v-model="passwordForm.newPassword"
                autocomplete="off"
                placeholder="请输入新密码">
            </el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="confirmPassword">
            <el-input
                type="password"
                v-model="passwordForm.confirmPassword"
                autocomplete="off"
                placeholder="请再次输入新密码"
                @keyup.enter.native="changePassword">
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="changePasswordDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="changePassword">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from '@/axios/request';

export default {
  name: 'NavBar',
  props: {
    activeIndex: {
      type: String,
      default: '1'
    }
  },
  data() {
    // 密码验证规则
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else if (value.length < 6) {
        callback(new Error('密码长度不能少于6位'));
      } else {
        if (this.passwordForm.confirmPassword !== '') {
          this.$refs.passwordForm.validateField('confirmPassword');
        }
        callback();
      }
    };

    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.passwordForm.newPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };

    return {
      themes: false,
      restaurants: [],
      songName: null,
      timeout: null,
      isLogin: false,
      isAvatar: false,
      username: '',
      password: '',
      isSearchButtonClicked: false,
      activeTab: 'login', // 登录/注册标签页
      changePasswordDialogVisible: false, // 修改密码对话框显示状态

      // 注册表单数据
      registerForm: {
        username: '',
        password: '',
        confirmPassword: '',
        bio: ''
      },

      // 修改密码表单数据
      passwordForm: {
        oldPassword: '',
        newPassword: '',
        confirmPassword: ''
      },

      // 密码验证规则
      passwordRules: {
        oldPassword: [
          { required: true, message: '请输入原密码', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    isDot() {
      return this.$store.getters.getIsDot
    },
    avatar() {
      let user = this.$store.getters.getUser;
      if (user !== null) {
        return this.$store.getters.getUser.user.avatar
      }
    },
    // 获取当前用户的token
    userToken() {
      let user = this.$store.getters.getUser;
      return user ? user.token : null;
    }
  },
  created() {
    if (localStorage.getItem('themes') !== null) {
      this.themes = JSON.parse(localStorage.getItem('themes'));
      this.changeTheme();
    }
    if (localStorage.getItem('user') !== null) {
      this.setUser(JSON.parse(localStorage.getItem('user')));
    }
  },
  watch: {
    themes(newValue) {
      localStorage.setItem('themes', JSON.stringify(newValue));
    }
  },
  methods: {
    // 过滤特殊字符
    filterSpecialCharacters(input) {
      return input.replace(/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/gi, '');
    },
    changeData() {
      this.songName = this.filterSpecialCharacters(this.songName);
      this.loadAll()
    },
    loadAll() {
      this.searchTip()
      return this.restaurants
    },
    querySearchAsync(queryString, cb) {
      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        this.songName = this.filterSpecialCharacters(queryString);
        this.isSearchButtonClicked = false;
        this.searchTip().then(() => {
          let results = queryString ? this.restaurants.filter(this.createsongNameFilter(queryString)) : this.restaurants;
          cb(results);
        });
      }, 300);
    },
    createsongNameFilter(queryString) {
      return (songName) => {
        return (songName.value.toUpperCase().indexOf(queryString.toUpperCase()) > -1);
      };
    },
    goMessage() {
      this.$store.commit('setIsDot', false)
      if (this.$route.path !== '/message') {
        this.$router.push({
          path: '/message'
        })
      }
    },
    quitLogin() {
      localStorage.clear()
      if (this.$route.path !== '/') {
        this.$router.push('/')
      }
      window.location.reload()
    },
    search(isClicked) {
      this.isSearchButtonClicked = isClicked;
      if (this.songName === null || this.songName.trim() === '') {
        this.$message.error("请输入内容！")
        return;
      }
      this.$emit('songName', this.songName)
      if (this.$route.path !== '/search') {
        this.$router.push({
          name: 'search',
          params: {
            songName: this.songName
          }
        })
      }
    },
    async searchTip() {
      this.restaurants = [];
      if (this.songName === null || this.songName.trim() === '') {
        return
      }
      try {
        const res = await request.search(this.songName);
        if (res.data !== undefined && res.data.length > 0) {
          res.data.forEach(item => {
            this.restaurants.push({
              "value": item.songName
            });
          });
        } else {
          if (this.isSearchButtonClicked) {
            this.$message.error("没有找到相关的搜索建议，请重新输入！");
            this.songName = null;
          }
        }
      } catch (error) {
        console.log(error);
        if (this.isSearchButtonClicked) {
          this.$message.error("请求出错！");
        }
      }
    },
    createLink(href) {
      const existingLink = document.getElementById('theme-style');
      if (existingLink) {
        document.head.removeChild(existingLink);
      }
      const link = document.createElement('link');
      link.id = 'theme-style';
      link.rel = 'stylesheet';
      link.href = href;
      document.head.appendChild(link);
    },
    changeTheme() {
      if (this.themes) {
        this.createLink('/theme/dark.css')
      } else {
        this.createLink('/theme/light.css')
      }
    },
    login() {
      if (this.username === '' || this.password === '') {
        this.$message.error("请输入账号或密码")
      } else {
        request.login(this.username, this.password).then(res => {
          if (res.data === undefined) {
            this.$message.error(res.message)
            return
          }
          this.setUser(res.data)
          this.$store.commit('setIsDot', true)
          this.$message.success(res.message)
          localStorage.setItem('user', JSON.stringify(res.data));
          this.isLogin = false;
        }).catch(err => {
          console.log(err)
          this.$message.error("请求出错")
        })
      }
    },
    // 用户注册
    register() {
      if (this.registerForm.username === '' || this.registerForm.password === '') {
        this.$message.error("请输入用户名和密码");
        return;
      }

      if (this.registerForm.password !== this.registerForm.confirmPassword) {
        this.$message.error("两次输入的密码不一致");
        return;
      }

      request.register(this.registerForm.username, this.registerForm.password, this.registerForm.bio)
          .then(res => {
            if (res.code == 200) {
              this.$message.success("注册成功，请登录");
              this.activeTab = 'login';
              this.username = this.registerForm.username;
              // 清空注册表单
              this.registerForm = {
                username: '',
                password: '',
                confirmPassword: '',
                bio: ''
              };
            } else {
              this.$message.error(res.message || "注册失败");
            }
          })
          .catch(error => {
            console.error(error);
            this.$message.error("注册请求失败");
          });
    },
    // 显示修改密码对话框
    showChangePasswordDialog() {
      if (!this.userToken) {
        this.$message.warning("请先登录");
        return;
      }
      this.passwordForm = {
        oldPassword: '',
        newPassword: '',
        confirmPassword: ''
      };
      this.changePasswordDialogVisible = true;
    },
    // 修改密码
    changePassword() {
      this.$refs.passwordForm.validate((valid) => {
        if (valid) {
          request.changePassword(this.userToken,
              this.passwordForm.oldPassword,
              this.passwordForm.newPassword,
              this.passwordForm.confirmPassword)
              .then(res => {
                if (res.code == 200) {
                  this.$message.success("密码修改成功");
                  this.changePasswordDialogVisible = false;
                  // 清空表单
                  this.passwordForm = {
                    oldPassword: '',
                    newPassword: '',
                    confirmPassword: ''
                  };
                } else {
                  this.$message.error(res.message || "密码修改失败");
                }
              })
              .catch(error => {
                console.error(error);
                this.$message.error("密码修改请求失败");
              });
        } else {
          return false;
        }
      });
    },
    setUser(item) {
      this.$store.commit('setUser', item)
      this.isLogin = false
      this.isAvatar = !this.isAvatar
    },
    // 处理登录对话框关闭
    handleLoginClose(done) {
      // 清空表单
      this.username = '';
      this.password = '';
      this.registerForm = {
        username: '',
        password: '',
        confirmPassword: '',
        bio: ''
      };
      done();
    }
  },
  mounted() {

  }
}
</script>

<style scoped>
.navbar-container {
  width: 100% !important;
}

.logo {
  margin-right: 100px;
}

.el-menu-demo {
  height: 100px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: center;
}

a {
  text-decoration: none;
}

.icon-search {
  border: none !important;
  background: none !important;
}

.icon-search:hover {
  border: none;
  background: none;
}

.right {
  margin-left: 100px;
  display: flex;
  align-items: center;
  justify-content: space-around;
}

.item {
  margin: 5px;
}

/* 登录对话框样式 */
.dialog-tabs {
  width: 100%;
}

.tabs-header {
  display: flex;
  border-bottom: 1px solid #e6e6e6;
  margin-bottom: 20px;
}

.tab-item {
  flex: 1;
  text-align: center;
  padding: 10px 0;
  cursor: pointer;
  font-size: 16px;
  color: #606266;
}

.tab-item.active {
  color: #409EFF;
  border-bottom: 2px solid #409EFF;
}

.form-container {
  padding: 0 20px;
}

.login-logo {
  text-align: center;
  margin-bottom: 20px;
}

.login-logo-center {
  margin-bottom: 20px;
}

.login-logo-center .el-input {
  margin-bottom: 15px;
}

.login-center {
  text-align: center;
  margin-bottom: 20px;
}

.login-center > .dialog-footer > .el-button {
  width: 100% !important;
  background-color: #FF641E;
  border: 1px solid #FF641E;
}

/* 修改密码对话框样式 */
.change-password-dialog .el-form-item {
  margin-bottom: 22px;
}

.change-password-dialog .dialog-footer {
  text-align: right;
}

/* 输入框样式优化 */
.el-autocomplete {
  width: 200px;
  margin-right: 10px;
}
</style>