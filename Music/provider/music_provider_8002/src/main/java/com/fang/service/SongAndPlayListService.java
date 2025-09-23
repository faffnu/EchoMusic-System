package com.fang.service;

import com.fang.entity.dto.SongAndPlayListDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SongAndPlayListService {

    List<SongAndPlayListDTO> selectByMusicId(@Param("id") String id);
}
