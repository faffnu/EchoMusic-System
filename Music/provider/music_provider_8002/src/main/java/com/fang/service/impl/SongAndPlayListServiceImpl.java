package com.fang.service.impl;

import com.fang.entity.dto.SongAndPlayListDTO;
import com.fang.mapper.SongAndPlayListMapper;
import com.fang.service.SongAndPlayListService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongAndPlayListServiceImpl implements SongAndPlayListService {

    @Resource
    private SongAndPlayListMapper songAndPlayListMapper;

    @Override
    public List<SongAndPlayListDTO> selectByMusicId(String id) {
        return songAndPlayListMapper.selectByMusicId(id);
    }
}
