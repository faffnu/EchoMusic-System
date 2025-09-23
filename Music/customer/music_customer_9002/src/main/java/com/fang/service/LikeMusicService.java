package com.fang.service;

import com.fang.entity.dto.LikeMusicDTO;
import com.fang.response.ResultData;

import java.util.List;
import java.util.Map;

public interface LikeMusicService {
    public ResultData<Map<String, Object>> insertLikeMusic(String user_id, String song_id);

    /**
     * 根据用户id查询用户喜欢的音乐
     *
     * @param token 用户token
     * @return list
     */
    public List<LikeMusicDTO> selectLikeMusicByUserId(String token);

    /**
     * 根据用户id和歌曲id删除一条记录
     *
     * @param token 用户token
     * @param songId 歌曲id
     * @return ResultData
     */
    public ResultData<Map<String, Object>> deleteLikeMusic(String token, String songId);
}
