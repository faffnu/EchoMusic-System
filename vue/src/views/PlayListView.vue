<template>
  <el-container>
    <el-header style="height:120px">
      <NavBar :active-index="activeIndex"></NavBar>
    </el-header>
    <!--  排行榜左右部分  -->
    <el-container class="main-container">
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
export default {
  data(){
    return{
      activeIndex:'1'
    }
  },
  created() {
  },
  methods:{

  },
  beforeMount() {
  },
  updated() {
  },
  components:{
    Bottom,
    NavBar,
    topRight
  }
}
</script>
<style scoped>
.main-container{
  width: 75%;
  margin: 0 auto;
  background: var(--white);
}
.el-aside{
  overflow: hidden !important;
  border-right: 1px solid var(--odd-color) !important;
  border-left: 1px solid var(--odd-color) !important;
  padding: 0 15px 15px 15px !important;
}
.el-header{
  padding: 0 !important;
  border-bottom: 1px solid var(--odd-color) !important;
}
</style>