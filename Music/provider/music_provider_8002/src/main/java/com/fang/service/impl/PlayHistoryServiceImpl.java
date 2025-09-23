package com.fang.service.impl;

import com.fang.entity.dto.PlayHistoryDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.entity.vo.PlayHistoryVo;
import com.fang.mapper.PlayHistoryMapper;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.service.PlayHistoryService;
import com.fang.service.SongMusicService;
import com.fang.utils.ParseToken;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PlayHistoryServiceImpl implements PlayHistoryService {

    @Resource
    private PlayHistoryMapper playHistoryMapper;

    @Resource
    private SongMusicService songMusicService;

    @CachePut(value = "userPlayHistory", key = "#root.methodName")
    @Override
    public ResultData<List<SongMusicDTO>> selectPlayHistoryByUserId(String token) {
        String user_id = ParseToken.parseToken(token);
        if (user_id == null) {
            return ResultData.fail(ResultDataEnum.RC500.getCode(), "非法token");
        }
        List<PlayHistoryDTO> playHistoryList = playHistoryMapper.selectPlayHistoryByUserId(user_id);
        List<SongMusicDTO> itemSongMusicList = new ArrayList<>();
        //根据音乐id查询音乐
        playHistoryList.forEach(item -> itemSongMusicList.addAll(songMusicService
                .selectSongMusicByIdAndSingers(item.getSongId())));
        return ResultData.success(itemSongMusicList);
    }

    @Transactional
    @Override
    public ResultData<String> insertPlayHistory(PlayHistoryDTO playHistory) {
        // 先查询是否已有记录
        PlayHistoryDTO existing = playHistoryMapper.selectByUserAndSong(
                playHistory.getUserId(), playHistory.getSongId());

        if (existing != null) {
            // 已有记录，更新播放时间
            existing.setPlayTime(new Date()); // 添加播放时间字段
            playHistoryMapper.updatePlayTime(existing);
            return ResultData.success("更新成功!");
        } else {
            // 新记录，插入
            playHistory.setId(UUID.randomUUID().toString().replace("-", ""));
            playHistory.setPlayTime(new Date());
            Boolean result = playHistoryMapper.insertPlayHistory(playHistory);
            if (!result) {
                return ResultData.fail(ResultDataEnum.RC500.getCode(), "添加失败");
            }
            return ResultData.success("添加成功!");
        }
    }


    @Transactional
    @Override
    public ResultData<String> deletePlayHistoryByPlayHistoryVo(PlayHistoryVo playHistoryVo) {
        Boolean result = playHistoryMapper.deletePlayHistoryByPlayHistoryVo(playHistoryVo);
        if (!result) {
            return ResultData.fail(ResultDataEnum.RC500.getCode(), "删除失败");
        }
        return ResultData.success("删除成功");
    }
}
