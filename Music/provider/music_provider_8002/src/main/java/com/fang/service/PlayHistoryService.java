package com.fang.service;

import com.fang.entity.dto.PlayHistoryDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.entity.vo.PlayHistoryVo;
import com.fang.response.ResultData;

import java.util.List;

public interface PlayHistoryService {

    ResultData<List<SongMusicDTO>> selectPlayHistoryByUserId(String user_id);

    ResultData<String> insertPlayHistory(PlayHistoryDTO playHistory);

    ResultData<String> deletePlayHistoryByPlayHistoryVo(PlayHistoryVo playHistoryVo);
}
