package com.fang.service.impl;

import com.fang.entity.dto.SongMusicDTO;
import com.fang.mapper.NewMusicMapper;
import com.fang.service.NewMusicService;
import com.fang.system.StaticUrl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NewMusicServiceImpl implements NewMusicService {

    @Resource
    private NewMusicMapper newMusicMapper;

    private final String uri = StaticUrl.getUrl();//地址和端口号

    /**
     * 查询新歌缓存到redis当中,获取6条数据
     *
     * @return 歌曲信息和歌手
     */
    @CachePut(cacheNames = "newMusic", key = "#root.methodName")
    @Override
    public List<SongMusicDTO> selectNewMusic() {
        List<SongMusicDTO> songMusicList = newMusicMapper.selectNewMusic();
        songMusicList.forEach(item -> {
            item.setSongUrl(uri + item.getSongUrl());
            item.setCoverImage(uri + item.getCoverImage());
        });
        return songMusicList;
    }

    /**
     * 根据类型获得6个新歌
     *
     * @param type 类型
     * @return List<SongMusicDTO>
     */
    @CachePut(value = "newMusic", key = "#root.methodName")
    @Override
    public List<SongMusicDTO> selectMusicByType(String type) {
        List<SongMusicDTO> songMusicList = newMusicMapper.selectMusicByTypeAnd(type);
        if (songMusicList.isEmpty())
            return null;
        songMusicList.forEach(item -> {
            item.setSongUrl(uri + item.getSongUrl());
            item.setCoverImage(uri + item.getCoverImage());
        });
        return songMusicList;
    }

    /**
     * 获得歌曲类型
     *
     * @return List<String>
     */
    @Cacheable(value = "musicType", key = "#root.methodName")
    @Override
    public List<SongMusicDTO> selectMusicType() {
        return newMusicMapper.selectByType();
    }

    @Override
    public List<SongMusicDTO> selectByMusicId(String music_id) {
        List<SongMusicDTO> songMusicList = newMusicMapper.selectByMusicId(music_id);
        songMusicList.forEach(item -> item.setSongUrl(uri + item.getSongUrl()));
        return songMusicList;
    }

}
