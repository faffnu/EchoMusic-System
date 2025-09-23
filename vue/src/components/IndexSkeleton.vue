<template>
  <el-skeleton style="width: 240px" :loading="loading" animated :throttle="1000">
    <template slot="template">
      <div class="skeleton-container">
        <div class="top-bar-content">
          <el-skeleton-item variant="text" style="margin-right: 16px;" class="el-skeleton-item"/>
          <el-skeleton-item variant="text" class="el-skeleton-item"/>
          <el-skeleton-item variant="text" class="el-skeleton-item"/>
          <el-skeleton-item variant="text" class="el-skeleton-item"/>
          <el-skeleton-item variant="text" class="el-skeleton-item"/>
        </div>
        <el-skeleton-item variant="image" class="swiper"></el-skeleton-item>
        <div class="playlist-top">
          <el-skeleton-item variant="text" class="playlist-top-item"/>
          <el-skeleton-item variant="text" class="playlist-top-item"/>
          <el-skeleton-item variant="text" class="playlist-top-item"/>
          <el-skeleton-item variant="text" class="playlist-top-item"/>
        </div>
        <div class="playlist-image">
          <el-skeleton-item variant="image" class="playlist-image-item"/>
          <el-skeleton-item variant="image" class="playlist-image-item"/>
          <el-skeleton-item variant="image" class="playlist-image-item"/>
          <el-skeleton-item variant="image" class="playlist-image-item"/>
          <el-skeleton-item variant="image" class="playlist-image-item"/>
        </div>
      </div>
    </template>
  </el-skeleton>
</template>

<script>
export default {
  name: 'indexSkeleton',
  data() {
    return {
      loading: true,
    }
  }
}
</script>
<style scoped>
.skeleton-container {
  width: 100vw;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;

  .top-bar-content {
    width: 100vw;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;

    .el-skeleton-item {
      width: 30%;
      height: 50px !important;
    }
  }
  .swiper {
    margin-top: 10px;
    width: 90vw;
    height: 280px;
    padding: 20px;
    display: flex;
  }
  .playlist-top{
    margin-top: 10px;
    width: 100vw;
    height: 50px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
    .playlist-top-item{
      width: 100px;
      height: 40px;
      margin: 10px;
    }
  }
  .playlist-image{
    margin-top: 10px;
    width: 100vw;
    height: 200px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    .playlist-image-item{
      width: 240px;
      height: 240px;
      margin: 20px;
    }
  }
}
</style>