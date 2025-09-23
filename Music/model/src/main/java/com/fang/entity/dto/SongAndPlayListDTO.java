package com.fang.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 音乐和歌单表，多对多关系
 *
 * @author fang
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SongAndPlayListDTO implements Serializable {
    private String id;// id
    private String playListId;// 播放歌单的id
    private String songMusicId;// 音乐的id
}
