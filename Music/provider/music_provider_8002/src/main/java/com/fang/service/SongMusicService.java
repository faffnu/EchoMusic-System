package com.fang.service;

import com.fang.entity.dto.SingerDTO;
import com.fang.entity.dto.SongMusicDTO;

import java.util.List;

public interface SongMusicService {
    List<SongMusicDTO> selectSongMusicByIdAndSingers(String music_id);

    SongMusicDTO selectMusicById(String music_id);

    List<SongMusicDTO> searchMusicByName(String music_name);

    Boolean updatePlayCountById(String id);

    List<SongMusicDTO> selectMusicByNewMusic();

    List<SongMusicDTO> selectMusicByPlayCount();

    /**
     * 获取所有歌手信息
     * @return List<SingerDTO>
     */
    List<SingerDTO> getAllSingers();

    /**
     * 根据歌手ID获取该歌手的全部歌曲
     * @param singerId 歌手ID
     * @return List<SongMusicDTO>
     */
    List<SongMusicDTO> getSongsBySingerId(String singerId);
}
