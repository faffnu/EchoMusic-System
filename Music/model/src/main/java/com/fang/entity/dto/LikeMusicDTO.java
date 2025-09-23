package com.fang.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 喜欢音乐实体类
 *
 * @author fang
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LikeMusicDTO implements Serializable {
    private String likeMusicId;//id
    private String userId;//用户id
    private String songId;//用户喜欢歌曲id
    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
    private Date likeTime;//喜欢歌曲的时间

    private List<SongMusicDTO> songMusicList;//音乐
}
