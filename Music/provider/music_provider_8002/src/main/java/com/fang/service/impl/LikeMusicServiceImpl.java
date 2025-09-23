package com.fang.service.impl;

import com.fang.entity.dto.LikeMusicDTO;
import com.fang.entity.dto.MusicUserDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.feign.MusicUserFeignClient;
import com.fang.mapper.LikeMusicMapper;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.service.LikeMusicService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class LikeMusicServiceImpl implements LikeMusicService {
    @Resource
    private LikeMusicMapper likeMusicMapper;

    @Resource
    private MusicUserFeignClient musicUserFeignClient;

    @Resource
    private SongMusicServiceImpl songMusicServiceImpl;

    /**
     * 先查询根据用户id查询用户信息，看看有没有该用户，添加喜欢的音乐,缓存到redis中
     *
     * @param user_id 用户id
     * @param song_id 歌曲id
     * @return 是否添加成功
     */
    @Override
    @Transactional
    public ResultData<Map<String, Object>> insertLikeMusic(String user_id, String song_id) {
        //如果没有查到的话会抛出异常，代表没有user_id或者song_id
        try {
            MusicUserDTO musicUserDTO = musicUserFeignClient.selectUserById(user_id);
            SongMusicDTO songMusicDTO = songMusicServiceImpl.selectMusicById(song_id);
            if (musicUserDTO == null || songMusicDTO.getSongUrl() == null) {
                return ResultData.fail(ResultDataEnum.RC500.getCode()
                        , "请输入正确的用户id或歌曲id！");
            }
        } catch (Exception e) {
            return ResultData.fail(ResultDataEnum.RC4004.getCode(), e.getMessage());
        }
        //查询该歌曲是否已经添加过
        LikeMusicDTO likeMusicDTO = likeMusicMapper.selectLikeMusicByUserIdAndSongId(user_id, song_id);
        if (!Objects.isNull(likeMusicDTO)) {
            return ResultData.fail(ResultDataEnum.RC4004.getCode(), "已经添加到自己喜欢的音乐当中了！");
        }
        //添加歌曲
        LikeMusicDTO likeMusic = new LikeMusicDTO();
        likeMusic.setLikeMusicId(UUID.randomUUID().toString().replace("-", ""));
        likeMusic.setUserId(user_id);
        likeMusic.setSongId(song_id);
        likeMusic.setLikeTime(new Date());
        if (likeMusicMapper.insertLikeMusic(likeMusic)) {
            return ResultData.success(ResultDataEnum.RC200.getCode(), ResultDataEnum.RC200.getMessage());
        }
        return ResultData.fail(ResultDataEnum.RC4004.getCode(), ResultDataEnum.RC4004.getMessage());
    }

    /**
     * 根据用户id和音乐id查询该歌曲是否已经被添加
     *
     * @param user_id 用户id
     * @param song_id 歌曲id
     * @return 是否已经添加
     */
    @Override
    public LikeMusicDTO selectLikeMusicByUserIdAndSongId(String user_id, String song_id) {
        return likeMusicMapper.selectLikeMusicByUserIdAndSongId(user_id, song_id);
    }

    /**
     * 根据用户id查询用户喜欢的音乐信息及歌手，更新到redis中
     *
     * @param user_id 用户id
     * @return List<LikeMusicDTO>
     */
    @CachePut(value = "likeMusicList", key = "#root.methodName")
    @Override
    public List<LikeMusicDTO> selectLikeMusicByUserId(String user_id) {
        List<LikeMusicDTO> likeMusicList = likeMusicMapper.selectLikeMusicByUserId(user_id);
        likeMusicList.forEach(item -> {
            List<SongMusicDTO> songMusicList = songMusicServiceImpl.selectSongMusicByIdAndSingers(item.getSongId());
            item.setSongMusicList(songMusicList);
        });
        return likeMusicList;
    }

    /**
     * 根据用户id和歌曲id删除我喜欢的歌曲
     *
     * @param user_id 用户id
     * @param song_id 歌曲id
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteLikeMusicByUserId(String user_id, String song_id) {
        return likeMusicMapper.deleteLikeMusicByUserId(user_id, song_id);
    }
}
