package com.fang.service.impl;

import com.fang.entity.dto.SingerDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.mapper.SongMusicMapper;
import com.fang.service.SongMusicService;
import com.fang.system.StaticUrl;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SongMusicServiceImpl implements SongMusicService {

    @Resource
    private SongMusicMapper songMusicMapper;

    private final String uri = StaticUrl.getUrl();

    /**
     * 根据id查询音乐及对应的歌手
     *
     * @param music_id id
     * @return songMusic
     */
    @Override
    public List<SongMusicDTO> selectSongMusicByIdAndSingers(String music_id) {
        List<SongMusicDTO> songMusicList = songMusicMapper.selectSongMusicById(music_id);
        songMusicList.forEach(item -> {
            item.setSongUrl(uri + item.getSongUrl());
            item.setCoverImage(uri + item.getCoverImage());
        });
        return songMusicList;
    }

    /**
     * 根据id查询音乐
     *
     * @param music_id id
     * @return SongMusicDTO
     */
    @Override
    public SongMusicDTO selectMusicById(String music_id) {
        SongMusicDTO songMusicDTO = songMusicMapper.selectMusicById(music_id);
        if (songMusicDTO == null) {
            return null;
        }
        songMusicDTO.setSongUrl(uri + songMusicDTO.getSongUrl());
        songMusicDTO.setCoverImage(uri + songMusicDTO.getCoverImage());
        return songMusicDTO;
    }

    /**
     * 根据歌曲名进行搜索
     *
     * @param music_name 歌曲名字
     * @return List<SongMusicDTO>
     */
    @CachePut(value = "searchMusic", key = "#root.methodName")
    @Override
    public List<SongMusicDTO> searchMusicByName(String music_name) {
        List<SongMusicDTO> searchMusicList = songMusicMapper.searchMusicByName(music_name);
        searchMusicList.forEach(item -> {
            item.setSongUrl(uri + item.getSongUrl());
            item.setCoverImage(uri + item.getCoverImage());
        });
        return searchMusicList;
    }

    @Override
    @Transactional
    public Boolean updatePlayCountById(String id) {
        return songMusicMapper.updatePlayCountById(id);
    }

    /**
     * 新歌榜
     *
     * @return List<SongMusicDTO>
     */
    @CachePut(value = "newMusicBoard", key = "#root.methodName")
    @Override
    public List<SongMusicDTO> selectMusicByNewMusic() {
        List<SongMusicDTO> songMusicDTOList = songMusicMapper.selectMusicByNewMusic();
        songMusicDTOList.forEach(item -> {
            item.setSongUrl(uri + item.getSongUrl());
            item.setCoverImage(uri + item.getCoverImage());
        });
        return songMusicDTOList;
    }

    /**
     * 热歌榜
     *
     * @return List<SongMusic>
     */
    @CachePut(value = "hotMusicTop", key = "#root.methodName")
    @Override
    public List<SongMusicDTO> selectMusicByPlayCount() {
        List<SongMusicDTO> songMusicDTOList = songMusicMapper.selectMusicByPlayCount();
        songMusicDTOList.forEach(item -> {
            item.setSongUrl(uri + item.getSongUrl());
            item.setCoverImage(uri + item.getCoverImage());
        });
        return songMusicDTOList;
    }

    @Override
    @Cacheable(value = "allSingers", key = "#root.methodName")
    public List<SingerDTO> getAllSingers() {
        return songMusicMapper.selectAllSingers();
    }

    @Override
    @Cacheable(value = "singerSongs", key = "#singerId")
    public List<SongMusicDTO> getSongsBySingerId(String singerId) {
        List<SongMusicDTO> songList = songMusicMapper.selectSongsBySingerId(singerId);
        songList.forEach(item -> {
            item.setSongUrl(uri + item.getSongUrl());
            item.setCoverImage(uri + item.getCoverImage());
        });
        return songList;
    }
}
