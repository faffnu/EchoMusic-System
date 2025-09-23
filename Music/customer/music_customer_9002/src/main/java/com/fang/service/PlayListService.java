package com.fang.service;

import com.fang.entity.dto.PlayListDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.response.ResultData;

import java.util.List;

public interface PlayListService {
    /**
     * 根据播放量获取5个热门歌单
     *
     * @return List<PlayListDTO>
     */
    public List<PlayListDTO> getPlayList();

    /**
     * 根据歌单id获取歌曲和歌手信息
     *
     * @param playListId 歌单id
     * @return List<SongMusicDTO>
     */
    public List<SongMusicDTO> getPlayListBySongMusicId(String playListId);

    /**
     * 获取歌单类型
     *
     * @return List<String>
     */
    public List<String> getPlayListType();

    /**
     * 根据类型获得歌单5条数据
     * @param type 类型
     * @return List<PlayListDTO>
     */
    public List<PlayListDTO> getPlayListByType(String type);

    /**
     * 获得所有歌单
     * @return List<PlayListDTO>
     */
    public List<PlayListDTO> getPlayListAll();

    /**
     * 歌单播放量+1
     * @return ResultData<Object>
     */
    public ResultData<Object> updatePlayCountById(String playListId);
}
