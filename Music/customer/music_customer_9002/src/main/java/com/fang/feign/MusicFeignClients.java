package com.fang.feign;

import com.fang.entity.Swiper;
import com.fang.entity.dto.*;
import com.fang.entity.vo.PlayHistoryVo;
import com.fang.feign.fallback.MusicFeignClientsFallBack;
import com.fang.response.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 添加喜欢的音乐，查询喜欢的音乐
 *
 * @author Fang
 */
@FeignClient(value = "music-provider", fallback = MusicFeignClientsFallBack.class)
public interface MusicFeignClients {

    /**
     * 添加喜欢的音乐
     *
     * @param token   用户token
     * @param song_id 歌曲id
     * @return 是否添加成功
     */
    @PostMapping("/provider/music/insertLikeMusic")
    public ResultData<Map<String, Object>> insertLikeMusic(
            @RequestParam(name = "token") String token,
            @RequestParam(name = "song_id") String song_id);

    /**
     * 根据用户id查询用户喜欢的音乐
     *
     * @param token 用户token
     * @return list
     */
    @PostMapping("/provider/music/selectLikeMusicByUserId")
    public List<LikeMusicDTO> selectLikeMusicByUserId(@RequestParam(name = "token") String token);

    /**
     * 根据用户id和歌曲id删除一条记录
     *
     * @param token  用户token
     * @param songId 歌曲id
     * @return ResultData
     */
    @DeleteMapping("/provider/music/deleteMusic")
    public ResultData<Map<String, Object>> deleteLikeMusic(@RequestParam("token") String token,
                                                           @RequestParam("song_id") String songId);


    /**
     * 获取新歌,获取6条数据
     *
     * @return list
     */
    @GetMapping("/provider/music/newMusic")
    public List<SongMusicDTO> newMusic();

    /**
     * 根据类型获取歌曲
     *
     * @param type ZH：华语、EA：欧美、KR：韩国
     * @return List<SongMusicDTO>
     */
    @PostMapping("/provider/music/newMusicByType")
    public List<SongMusicDTO> newMusicByType(@RequestParam("type") String type);

    /**
     * 获取歌曲类型
     *
     * @return List<String>
     */
    @GetMapping("/provider/music/getType")
    public List<SongMusicDTO> getType();

    /**
     * 根据歌曲类型获得歌曲
     *
     * @param tag "需要传入歌曲类型，ZH：华语，ER：欧美，KR：韩国
     * @return List<NewMusicTypeDTO>
     */
    @PostMapping("/provider/music/getMusicByTag")
    public List<SongMusicDTO> getMusicByTag(@RequestParam("tag") String tag);

    /**
     * 根据播放量获取5个热门歌单
     *
     * @return List<PlayListDTO>
     */
    @GetMapping("/provider/music/playlist")
    public List<PlayListDTO> getPlayList();

    /**
     * 根据歌单id获取歌曲和歌手信息
     *
     * @param playListId 歌单id
     * @return List<SongMusicDTO>
     */
    @PostMapping("/provider/music/selectPlayListBySongMusicId")
    public List<SongMusicDTO> getPlayListBySongMusicId(@RequestParam("play_list_id") String playListId);

    /**
     * 获得所有歌单
     *
     * @return List<PlayListDTO>
     */
    @GetMapping("/provider/music/playListAll")
    public List<PlayListDTO> getAllPlayList();

    /**
     * 获取歌单类型
     *
     * @return List<String>
     */
    @GetMapping("/provider/music/getPlayListType")
    public List<String> getPlayListType();

    @PostMapping("/provider/music/playListByTag")
    public List<PlayListDTO> getPlayListByTag(@RequestParam("tag") String tag);

    /**
     * 歌单播放量+1
     *
     * @param playListId 歌单id
     * @return ResultData<Object>
     */
    @GetMapping("/provider/music/updatePlayCountById")
    public ResultData<Object> updatePlayCountById(@RequestParam("play_list_id") String playListId);

    /**
     * 传入歌曲名称搜索歌曲
     *
     * @param music_name 歌曲名称
     * @return ResultData<List < SongMusicDTO>>
     */
    @PostMapping("/provider/music/search")
    @Operation(summary = "搜索歌曲", description = "传入歌曲名称")
    public ResultData<List<SongMusicDTO>> search(@RequestParam("music_name") String music_name);


    /**
     * 获取轮播图
     *
     * @return List<Swiper>
     */
    @GetMapping("/provider/music/getSwiper")
    public List<Swiper> getSwiper();

    /**
     * 获取top榜10条数据
     *
     * @return PlayListDTO
     */
    @GetMapping("/provider/music/selectTop")
    public PlayListDTO selectTop();

    /**
     * 音乐播放量+1
     *
     * @param id 音乐id
     * @return ResultData<Object>
     */
    @GetMapping("/provider/music/updateSongMusicById")
    public ResultData<Object> updateSongMusicById(@RequestParam("id") String id);

    /**
     * 新歌榜
     *
     * @return ResultData<List < SongMusicDTO>>
     */
    @GetMapping("/provider/music/newMusicBoard")
    public ResultData<List<SongMusicDTO>> newMusicBoard();

    /**
     * 热歌榜
     *
     * @return ResultData<List < SongMusicDTO>>
     */
    @GetMapping("/provider/music/hotMusicBoard")
    public ResultData<List<SongMusicDTO>> hotMusicBoard();

    /**
     * 根据用户传入的token查询播放历史记录
     *
     * @param token token
     * @return ResultData<List < SongMusicDTO>>
     */
    @PostMapping("/provider/music/history/selectPlayHistoryByUserToken")
    public ResultData<List<SongMusicDTO>> selectPlayHistoryByUserId(@RequestParam String token);

    /**
     * 添加一条播放历史记录
     *
     * @param playHistoryDTO PlayHistoryDTO
     * @return ResultData<String>
     */
    @PostMapping("/provider/music/history/insert")
    public ResultData<String> insertPlayHistory(@RequestBody PlayHistoryDTO playHistoryDTO);

    /**
     * 删除一条播放历史记录
     *
     * @param playHistoryVo PlayHistoryVo
     * @return ResultData<String>
     */
    @PostMapping("/provider/music/history/delete")
    public ResultData<String> deletePlayHistory(@RequestBody PlayHistoryVo playHistoryVo);

    @GetMapping("/provider/music/allSingers")
    @Operation(summary = "获取所有歌手", description = "获取系统中所有歌手信息")
    public ResultData<List<SingerDTO>> getAllSingers();

    @GetMapping("/provider/music/songs")
    @Operation(summary = "获取歌手歌曲", description = "根据歌手ID获取该歌手的全部歌曲")
    public ResultData<List<SongMusicDTO>> getSongsBySingerId(@RequestParam("singerId") String singerId);
}
