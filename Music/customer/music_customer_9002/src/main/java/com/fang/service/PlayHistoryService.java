package com.fang.service;

import com.fang.entity.dto.PlayHistoryDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.entity.vo.PlayHistoryVo;
import com.fang.response.ResultData;

import java.util.List;

public interface PlayHistoryService {
    public ResultData<List<SongMusicDTO>> selectPlayHistoryByUserId(String token);

    public ResultData<String> insertPlayHistory(PlayHistoryDTO playHistoryDTO);

    public ResultData<String> deletePlayHistory(PlayHistoryVo playHistoryVo);
}
