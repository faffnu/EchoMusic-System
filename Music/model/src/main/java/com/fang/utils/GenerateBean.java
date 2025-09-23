package com.fang.utils;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.fang.entity.Swiper;
import com.fang.entity.dto.*;
import com.fang.system.StaticUrl;
import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 生成bean数据的实体类
 */
public class GenerateBean {

    @Resource
    private final String uri = StaticUrl.getUrl();

    private final String uuid = UUID.randomUUID().toString().replace("-", "");

    public final LikeMusicDTO generateLikeMusic() {
        return new LikeMusicDTO(uuid, uuid, uuid, new Date(), List.of(generateSongMusic()));
    }

    public final MusicAndSingerDTO generateMusicAndSinger() {
        return new MusicAndSingerDTO(uuid, uuid, uuid, List.of(generateSinger()));
    }

    public final MusicUserDTO generateMusicUser() {
        String avatar = uri + "/static/avatar/default.png";
        return new MusicUserDTO(uuid, "默认用户名称", "",avatar, "暂无简介！", false, new Date());
    }

    public final PlayHistoryDTO generatePlayHistory() {
        return new PlayHistoryDTO(uuid, uuid, uuid, new Date(),List.of(generateSongMusic()));
    }

    public final PlayListDTO generatePlayList() {
        String url = uri + "/static/coverImage/default_album.jpg";
        return new PlayListDTO(uuid, uuid, "默认标题", "暂无描述！", url,
                PlayListDTO.Type.HY, new Date(), 0, generateMusicUser(), List.of(generateSongMusic()));
    }

    public final SingerDTO generateSinger() {
        return new SingerDTO(uuid, "默认歌手");
    }

    public final SongAndPlayListDTO songAndPlayList() {
        return new SongAndPlayListDTO(uuid, uuid, uuid);
    }

    public final SongMusicDTO generateSongMusic() {
        String url = uri;
        String coverImage = url + "/static/coverImage/default_album.jpg";
        String songUrl = url + "/static/music/1.mp3";
        return new SongMusicDTO(uuid, "默认歌曲名字！", coverImage, songUrl, "00:00",
                false, 0, false, List.of(generateMusicAndSinger()), SongMusicDTO.Type.ZH);
    }

    public final List<Swiper> generateSwiper() {
        String url = uri;
        List<Swiper> swiperList = new ArrayList<>();
        swiperList.add(new Swiper(uuid, url + "/static/swiper/default/1.jpg"));
        swiperList.add(new Swiper(uuid, url + "/static/swiper/default/2.jpg"));
        swiperList.add(new Swiper(uuid, url + "/static/swiper/default/2.jpg"));
        return swiperList;
    }
}
