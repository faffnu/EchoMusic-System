<!-- 热门推荐 -->
<template>
  <div class="hot-playlist">
    <div v-show="false">{{ index }}</div>
    <el-card class="box-card" shadow="hover" v-for="(item,index) in playList"
             @click.native="goPlayList(item)"
             :key="index">
      <div class="clearfix"
           :style="{backgroundImage: `url(${item.playListCover})`}">
        <!-- 图片底部区域 -->
        <div class="play-count">
          <div>
            <i class="el-icon-headset"></i>
            <span class="count">{{ tranNumber(item.playCount) }}</span>
          </div>
          <div>
            <i class="el-icon-video-play"></i>
          </div>
        </div>
      </div>
      <div class="text">{{item.title}}</div>
    </el-card>
  </div>
</template>
<script>
import { tranNumber } from "@/utli/utils";
export default{
  name:'recommend',
  props:{
    index:{
      type:String,
      default:'1'
    },
    playList:[]
  },
  data(){
    return{
    }
  },
  methods:{
    tranNumber,
    //跳转到歌单详情
    goPlayList(item){
      this.$router.push({
        name:'indexPlayList',
        params:{
          item:JSON.stringify(item)
        }
      })
    }
  }
}
</script>
<style scoped>
.hot-playlist{
  flex: 1;
  align-self: center;
  display: flex;
  flex-direction: row;
  margin-top: 20px;
  margin-bottom: 20px;
}
.el-card{
  margin: 5px !important;
  border-radius: 15px !important;
  cursor: pointer;
  border: none !important;
  box-shadow: none !important;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 270px !important;
  box-sizing: border-box;
  font-family: '楷体',serif;
  .clearfix{
    cursor: pointer;
    border-radius: 5px;
    height: 210px;
    width: 250px;
    margin-bottom: 20px;
    box-sizing: border-box;
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
    display: flex;
    flex-wrap: nowrap;
    align-content: flex-end;
    justify-content: center;
    align-items: flex-end;
  }
  .play-count{
    flex: 1;
    width: 100%;
    opacity: 0.6;
    text-align: center;
    height: 25px;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    line-height: 25px;
  }
  .play-count>div:nth-child(1){
    text-indent: 8px;
    .count{
      padding-left: 5px;
    }
  }
  .play-count>div:nth-child(2){
    padding-right: 10px;
    cursor: pointer;
  }
  .text{
    width: 250px;
    height:50px;
    text-overflow: ellipsis;
    overflow: hidden;
  }
}
</style>