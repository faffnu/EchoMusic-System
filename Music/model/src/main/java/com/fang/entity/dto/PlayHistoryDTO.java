package com.fang.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 播放历史实体类
 *
 * @author fang
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PlayHistoryDTO implements Serializable {
    private String id;//id
    private String songId;//歌曲id
    private String userId;//用户id
    private Date playTime;// 添加到播放历史的时间

    private List<SongMusicDTO> songMusicList;//歌曲
}
