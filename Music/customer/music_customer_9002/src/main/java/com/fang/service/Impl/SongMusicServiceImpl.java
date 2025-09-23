package com.fang.service.Impl;

import com.fang.entity.dto.SingerDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.feign.MusicFeignClients;
import com.fang.response.ResultData;
import com.fang.service.SongMusicService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongMusicServiceImpl implements SongMusicService {

    @Resource
    private MusicFeignClients musicFeignClients;


    /**
     * 根据音乐id播放量+1
     * @param id 音乐id
     * @return
     */
    @Override
    public ResultData<Object> updateSongMusicById(String id) {
        return musicFeignClients.updateSongMusicById(id);
    }

    /**
     * 新歌榜
     * @return ResultData<List<SongMusicDTO>>
     */
    @Override
    public ResultData<List<SongMusicDTO>> newMusicBoard() {
        return musicFeignClients.newMusicBoard();
    }

    /**
     * 热歌榜
     * @return ResultData<List<SongMusicDTO>>
     */
    @Override
    public ResultData<List<SongMusicDTO>> hotMusicBoard() {
        return musicFeignClients.hotMusicBoard();
    }


    @Override
    public ResultData<List<SingerDTO>> getAllSingers() {
        return musicFeignClients.getAllSingers();
    }

    @Override
    public ResultData<List<SongMusicDTO>> getSongsBySingerId(String singerId) {
        return musicFeignClients.getSongsBySingerId(singerId);
    }
}
