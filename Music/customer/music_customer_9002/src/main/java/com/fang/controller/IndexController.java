package com.fang.controller;

import com.fang.entity.dto.SongMusicDTO;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.service.Impl.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/music")
@Tag(name = "首页模块")
public class IndexController {

    @Resource
    private SwiperServiceImpl swiperServiceImpl;

    @Resource
    private PlayListServiceImpl playListServiceImpl;

    @Resource
    private NewMusicServiceImpl newMusicServiceImpl;

    /**
     * 首页数据
     *
     * @return ResultData<Map < String, Object>>
     */
    @GetMapping("/index")
    @Operation(summary = "首页的数据", description = "返回轮播图、歌单、新歌推荐")
    public ResultData<Map<String, Object>> index() {
        Map<String, Object> indexMap = new HashMap<String, Object>();
        indexMap.put("swiper", swiperServiceImpl.getSwiper());//获取轮播图
        indexMap.put("playListTags", playListServiceImpl.getPlayListType());//获取歌单类型
        indexMap.put("playList", playListServiceImpl.getPlayList());//获取歌单
        indexMap.put("newMusicTags", playListServiceImpl.getPlayListType());//获得新歌的的类型
        indexMap.put("music", newMusicServiceImpl.newMusic());//获取新歌，返回6条数据
        return ResultData.success(indexMap);
    }

    @Resource
    private SearchMusicServiceImpl searchMusicServiceImpl;

    /**
     * 搜索
     *
     * @param musicName 歌曲名字
     * @return 歌曲和歌手
     */
    @PostMapping("/search")
    @Operation(summary = "搜索", description = "需要传入歌曲名字")
    public ResultData<List<SongMusicDTO>> search(@RequestParam("music_name") String musicName) {
        return searchMusicServiceImpl.search(musicName);
    }

    @Resource
    private NewMusicTypeServiceImpl newMusicTypeServiceImpl;


    /**
     * 根据歌曲类型获得歌曲
     *
     * @param type 类型
     * @return ResultData<List < NewMusicTypeDTO>>
     */
    @GetMapping("/selectMusicByType")
    @Operation(summary = "根据歌曲类型获得新歌", description = "需要传入歌曲类型ZH：华语，ER：欧美，KR：韩国")
    public ResultData<List<SongMusicDTO>> getMusicByType(@RequestParam("type") String type) {
        List<SongMusicDTO> musicByTag = newMusicTypeServiceImpl.getMusicByTag(type);
        if (!musicByTag.isEmpty()) {
            return ResultData.success(musicByTag);
        }
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
    }
}
