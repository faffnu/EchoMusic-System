package com.fang.service.impl;

import com.fang.entity.dto.SingerDTO;
import com.fang.mapper.SingerMapper;
import com.fang.service.SingerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌手
 */
@Service
public class SingerServiceImpl implements SingerService {

    @Resource
    private SingerMapper singerMapper;

    /**
     * 根据id查询歌手
     * @param id 歌手id
     * @return List<SingerDTO>
     */
    @Override
    public List<SingerDTO> selectById(String id) {
        return singerMapper.selectById(id);
    }
}
