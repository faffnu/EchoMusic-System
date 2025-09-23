package com.fang.service;

import com.fang.entity.dto.PlayListDTO;
import com.fang.entity.dto.SongMusicDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayListService {
    List<PlayListDTO> selectPlayList();//查询热门歌单5个

    List<SongMusicDTO> selectSongMusicByPlayListId(String playListId);

    List<String> selectPlayListByPlayListType();//获取歌单类型

    //根据歌单类型查询歌单，返回5条数据
    List<PlayListDTO> selectPlayListByTag(@Param("tag") String tag);

    List<PlayListDTO> selectPlayListAll();

    Boolean updatePlayCountById(String id);
}
