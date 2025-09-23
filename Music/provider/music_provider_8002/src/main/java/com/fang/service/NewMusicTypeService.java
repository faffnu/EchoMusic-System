package com.fang.service;

import com.fang.entity.dto.SongMusicDTO;

import java.util.List;

public interface NewMusicTypeService {
    /**
     * 根据新歌类型查询对应的歌曲和歌手,获得6条数据
     *
     * @param type 类型
     * @return 返回音乐数据和歌手数据
     */
    List<SongMusicDTO> selectByType(String type);
}
