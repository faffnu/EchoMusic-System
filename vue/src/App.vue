<template>
  <div id="app">
    <router-view/>
    <el-footer>
      <bottom></bottom>
    </el-footer>
  </div>
</template>
<script>
import Bottom from "@/components/Bottom.vue";
import NavBar from "@/components/NavBar.vue";
export default {
  components: {NavBar, Bottom},
  created () {
    const link = document.createElement('link');
    link.id = 'theme-style';
    link.rel = 'stylesheet';
    link.href = `/theme/light.css`
    document.head.appendChild(link);
    sessionStorage.setItem('theme', 'true');
  }
}
</script>
<style lang="less">

</style>