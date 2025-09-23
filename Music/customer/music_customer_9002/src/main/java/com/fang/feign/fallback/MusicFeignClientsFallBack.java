package com.fang.feign.fallback;

import com.fang.entity.Swiper;
import com.fang.entity.dto.*;
import com.fang.entity.vo.PlayHistoryVo;
import com.fang.feign.MusicFeignClients;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.utils.GenerateBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 兜底回调
 *
 * @author Fang
 */
@Component
public class MusicFeignClientsFallBack implements MusicFeignClients {

    private final GenerateBean generateBean = new GenerateBean();

    @Override
    public ResultData<Map<String, Object>> insertLikeMusic(String token, String song_id) {
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
    }

    @Override
    public List<LikeMusicDTO> selectLikeMusicByUserId(String token) {
        return List.of(generateBean.generateLikeMusic());
    }

    @Override
    public ResultData<Map<String, Object>> deleteLikeMusic(String token, String songId) {
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
    }

    @Override
    public List<SongMusicDTO> newMusic() {
        return List.of(generateBean.generateSongMusic());
    }

    @Override
    public List<SongMusicDTO> newMusicByType(String type) {
        return List.of(generateBean.generateSongMusic());
    }

    @Override
    public List<SongMusicDTO> getType() {
        return List.of(generateBean.generateSongMusic());
    }

    @Override
    public List<SongMusicDTO> getMusicByTag(String tag) {
        return List.of(generateBean.generateSongMusic());
    }

    @Override
    public List<PlayListDTO> getPlayList() {
        return List.of(generateBean.generatePlayList());
    }

    @Override
    public List<SongMusicDTO> getPlayListBySongMusicId(String playListId) {
        return List.of(generateBean.generateSongMusic());
    }

    @Override
    public ResultData<Object> updatePlayCountById(String playListId) {
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
    }

    @Override
    public List<PlayListDTO> getAllPlayList() {
        return List.of(generateBean.generatePlayList());
    }

    @Override
    public List<String> getPlayListType() {
        return List.of("华语");
    }

    @Override
    public List<PlayListDTO> getPlayListByTag(String tag) {
        return List.of(generateBean.generatePlayList());
    }

    @Override
    public ResultData<List<SongMusicDTO>> search(String music_name) {
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
    }

    @Override
    public List<Swiper> getSwiper() {
        return generateBean.generateSwiper();
    }

    @Override
    public PlayListDTO selectTop() {
        return generateBean.generatePlayList();
    }

    @Override
    public ResultData<Object> updateSongMusicById(String id) {
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
    }

    @Override
    public ResultData<List<SongMusicDTO>> newMusicBoard() {
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
    }

    @Override
    public ResultData<List<SongMusicDTO>> hotMusicBoard() {
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
    }

    @Override
    public ResultData<List<SongMusicDTO>> selectPlayHistoryByUserId(String token) {
        return ResultData.fail(ResultDataEnum.RC50000.getCode(), ResultDataEnum.RC50000.getMessage());
    }

    @Override
    public ResultData<String> insertPlayHistory(PlayHistoryDTO playHistoryDTO) {
        return ResultData.fail(ResultDataEnum.RC50000.getCode(), ResultDataEnum.RC50000.getMessage());
    }

    @Override
    public ResultData<String> deletePlayHistory(PlayHistoryVo playHistoryVo) {
        return ResultData.fail(ResultDataEnum.RC50000.getCode(), ResultDataEnum.RC50000.getMessage());
    }

    @Override
    public ResultData<List<SingerDTO>> getAllSingers() {
        return ResultData.fail(ResultDataEnum.RC50000.getCode(), ResultDataEnum.RC50000.getMessage());
    }

    @Override
    public ResultData<List<SongMusicDTO>> getSongsBySingerId(String singerId) {
        return ResultData.fail(ResultDataEnum.RC50000.getCode(), ResultDataEnum.RC50000.getMessage());
    }
}
