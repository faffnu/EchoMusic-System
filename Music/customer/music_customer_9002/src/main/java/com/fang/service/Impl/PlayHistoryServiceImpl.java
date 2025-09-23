package com.fang.service.Impl;

import com.fang.entity.dto.PlayHistoryDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.entity.vo.PlayHistoryVo;
import com.fang.feign.MusicFeignClients;
import com.fang.response.ResultData;
import com.fang.service.PlayHistoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayHistoryServiceImpl implements PlayHistoryService {

    @Resource
    private MusicFeignClients musicFeignClients;

    /**
     * 根据token查询所有的播放历史
     *
     * @param token token
     * @return ResultData<List < SongMusicDTO>>
     */
    @Override
    public ResultData<List<SongMusicDTO>> selectPlayHistoryByUserId(String token) {
        return musicFeignClients.selectPlayHistoryByUserId(token);
    }

    /**
     * 添加一条播放历史记录
     *
     * @param playHistoryDTO PlayHistoryDTO
     * @return ResultData<String>
     */
    @Override
    public ResultData<String> insertPlayHistory(PlayHistoryDTO playHistoryDTO) {
        return musicFeignClients.insertPlayHistory(playHistoryDTO);
    }

    /**
     * 删除一条播放历史记录
     *
     * @param playHistoryVo PlayHistoryVo
     * @return ResultData<String>
     */
    @Override
    public ResultData<String> deletePlayHistory(PlayHistoryVo playHistoryVo) {
        return musicFeignClients.deletePlayHistory(playHistoryVo);
    }
}
