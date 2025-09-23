package com.fang.service.impl;

import com.fang.entity.dto.PlayListDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.feign.MusicUserFeignClient;
import com.fang.mapper.PlayListMapper;
import com.fang.service.PlayListService;
import com.fang.system.StaticUrl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PlayListServiceImpl implements PlayListService {

    @Resource
    private PlayListMapper playListMapper;//歌单信息

    @Resource
    private MusicUserFeignClient musicUserFeignClient; // 用户信息

    private final String uri = StaticUrl.getUrl(); //url


    /**
     * 获取热门歌单信息，缓存到数据库里面
     *
     * @return List<PlayListDTO>
     */
    @CachePut(cacheNames = "playlist", key = "#root.methodName")
    @Override
    public List<PlayListDTO> selectPlayList() {
        List<PlayListDTO> playList = playListMapper.selectPlayListByPlayCount();
        //根据歌单的用户id获取歌单作者信息
        playList.forEach(item -> {
            item.setMusicUser(musicUserFeignClient.selectUserById(item.getMusicUserId()));
            item.setPlayListCover(uri + item.getPlayListCover());
        });
        return playList;
    }

    /**
     * 根据歌单id查询歌曲信息，获取对应的歌手信息
     *
     * @param playListId 歌单id
     * @return List<SongMusicDTO>
     */
    @Override
    public List<SongMusicDTO> selectSongMusicByPlayListId(String playListId) {
        List<SongMusicDTO> songMusicList = playListMapper.selectSongMusicByPlayListId(playListId);
        songMusicList.forEach(item -> {
            item.setCoverImage(uri + item.getCoverImage());
            item.setSongUrl(uri + item.getSongUrl());
        });

        return songMusicList;
    }

    /**
     * 获取歌单类型
     *
     * @return List<String>
     */
    @Override
    public List<String> selectPlayListByPlayListType() {
        return playListMapper.selectPlayListByPlayListType();
    }

    /**
     * 根据歌单类型查询歌单，返回5条数据
     *
     * @param tag 歌单类型
     * @return List<PlayListDTO>
     */
    @CachePut(value = "playlistByTag", key = "#root.methodName")
    @Override
    public List<PlayListDTO> selectPlayListByTag(String tag) {
        List<PlayListDTO> playList = playListMapper.selectPlayListByTag(tag);
        if (playList.isEmpty()) {
            return null;
        }
        playList.forEach(item -> {
            item.setMusicUser(musicUserFeignClient.selectUserById(item.getMusicUserId()));
            item.setPlayListCover(uri + item.getPlayListCover());
        });
        return playList;
    }

    /**
     * 获得所有歌单
     *
     * @return List<PlayListDTO>
     */
    @CachePut(value = "playListAll", key = "#root.methodName")
    @Override
    public List<PlayListDTO> selectPlayListAll() {
        List<PlayListDTO> playList = playListMapper.selectPlayListAll();
        //根据歌单的用户id获取歌单作者信息
        playList.forEach(item -> {
            item.setMusicUser(musicUserFeignClient.selectUserById(item.getMusicUserId()));
            item.setPlayListCover(uri + item.getPlayListCover());
        });
        return playList;
    }

    /**
     * 根据歌单id，播放量+1，更新缓存
     *
     * @param id 歌单id
     * @return Boolean
     */
    @Override
    @Transactional
    public Boolean updatePlayCountById(String id) {
        return playListMapper.updatePlayCountById(id);
    }
}
