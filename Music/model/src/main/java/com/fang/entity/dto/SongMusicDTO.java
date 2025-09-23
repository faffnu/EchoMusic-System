package com.fang.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 歌曲实体类
 *
 * @author fang
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SongMusicDTO implements Serializable {
    private String songMusicId;//id
    private String songName;//歌曲名字
    private String coverImage;//歌曲封面图片
    private String songUrl;//歌曲路径
    private String duration;//歌曲时长
    private Boolean isVip; // 该歌曲是否需要vip才能播放
    private Integer playCount;//播放数量
    @JsonIgnore
    private Boolean isNewMuSic;//是否是新歌
    private List<MusicAndSingerDTO> musicAndSingerList;//歌手
    private Type type;

    @Getter
    public enum Type {
        ZH("华语"), EA("欧美"), KR("韩国");
        private final String chinese;

        Type(String chinese) {
            this.chinese = chinese;
        }
    }
}
