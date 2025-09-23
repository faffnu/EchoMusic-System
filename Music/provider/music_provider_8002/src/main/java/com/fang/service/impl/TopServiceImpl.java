package com.fang.service.impl;

import com.fang.entity.dto.MusicUserDTO;
import com.fang.entity.dto.PlayListDTO;
import com.fang.entity.dto.SongAndPlayListDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.feign.MusicUserFeignClient;
import com.fang.mapper.TopMapper;
import com.fang.service.TopService;
import com.fang.system.StaticUrl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TopServiceImpl implements TopService {
    @Resource
    private TopMapper topMapper;

    private final String uri = StaticUrl.getUrl();


    @Resource
    private SongAndPlayListServiceImpl songAndPlayListServiceImpl;//歌单音乐中间

    @Resource
    private SongMusicServiceImpl songMusicServiceImpl;//音乐

    @Resource
    private MusicUserFeignClient musicUserFeignClient;//用户

    /**
     * 查询热门榜单数据
     *
     * @return 返回10条数据
     */
    @CachePut(value = "topList", key = "#root.methodName")
    @Override
    public PlayListDTO selectTop() {
        PlayListDTO playListDTO = topMapper.selectTop();//查询播放量最高的歌单
        List<SongAndPlayListDTO> songAndPlayList = songAndPlayListServiceImpl
                .selectByMusicId(playListDTO.getPlayListId());//根据歌单id查询歌曲
        List<SongMusicDTO> musicDTOS = new ArrayList<>();
        //根据歌曲id查询歌曲和歌手信息
        songAndPlayList.forEach(item -> {
            List<SongMusicDTO> songMusicDTOS = songMusicServiceImpl
                    .selectSongMusicByIdAndSingers(item.getSongMusicId());
            musicDTOS.addAll(songMusicDTOS);
        });
        playListDTO.setSongMusicList(musicDTOS);
        //查询用户
        MusicUserDTO musicUser = musicUserFeignClient.selectUserById(playListDTO.getMusicUserId());
        musicUser.setAvatar(uri + musicUser.getAvatar());
        playListDTO.setMusicUser(musicUser);
        playListDTO.setPlayListCover(uri + playListDTO.getPlayListCover());
        return playListDTO;
    }
}
