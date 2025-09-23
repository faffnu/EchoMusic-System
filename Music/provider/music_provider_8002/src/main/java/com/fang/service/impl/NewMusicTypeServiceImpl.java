package com.fang.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.mapper.NewMusicTypeMapper;
import com.fang.service.NewMusicTypeService;
import com.fang.system.StaticUrl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NewMusicTypeServiceImpl implements NewMusicTypeService {

    @Resource
    private NewMusicTypeMapper newMusicTypeMapper;

    private final String uri = StaticUrl.getUrl();

    /**
     * 根据类型获取歌曲，返回6条数据
     *
     * @param type 类型
     * @return list集合
     */
    @CachePut(value = "typeList", key = "#root.methodName")
    @Override
    public List<SongMusicDTO> selectByType(String type) {
        List<SongMusicDTO> songMusicList = newMusicTypeMapper.selectByType(type);
        if (ObjectUtil.isNull(songMusicList)) {
            return null;
        }
        songMusicList.forEach(musicItem -> {
            musicItem.setCoverImage(uri + musicItem.getCoverImage());
            musicItem.setSongUrl(uri + musicItem.getSongUrl());
        });
        return songMusicList;
    }
}
