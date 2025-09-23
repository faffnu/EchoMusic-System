package com.fang.service;

import com.fang.entity.dto.SingerDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SingerService {
    List<SingerDTO> selectById(@Param("singerId") String id);
}
