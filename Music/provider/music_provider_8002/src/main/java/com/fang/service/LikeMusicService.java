package com.fang.service;

import com.fang.entity.dto.LikeMusicDTO;
import com.fang.response.ResultData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LikeMusicService {
    ResultData<Map<String, Object>> insertLikeMusic(String user_id, String song_id);

    LikeMusicDTO selectLikeMusicByUserIdAndSongId(String user_id, String song_id);

    List<LikeMusicDTO> selectLikeMusicByUserId(String user_id);

    Boolean deleteLikeMusicByUserId(@Param("user_id") String user_id,
                            @Param("song_id") String song_id);
}
