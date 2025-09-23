package com.fang.service.Impl;

import com.fang.entity.dto.PlayListDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.feign.MusicFeignClients;
import com.fang.response.ResultData;
import com.fang.service.PlayListService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListServiceImpl implements PlayListService {
    @Resource
    private MusicFeignClients playListFeignClients;

    @Override
    public List<PlayListDTO> getPlayList() {
        return playListFeignClients.getPlayList();
    }

    @Override
    public List<SongMusicDTO> getPlayListBySongMusicId(String playListId) {
        return playListFeignClients.getPlayListBySongMusicId(playListId);
    }

    @Override
    public List<String> getPlayListType() {
        return playListFeignClients.getPlayListType();
    }

    @Override
    public List<PlayListDTO> getPlayListByType(String type) {
        return playListFeignClients.getPlayListByTag(type);
    }

    @Override
    public List<PlayListDTO> getPlayListAll() {
        return playListFeignClients.getAllPlayList();
    }

    @Override
    public ResultData<Object> updatePlayCountById(String playListId) {
        return playListFeignClients.updatePlayCountById(playListId);
    }
}
