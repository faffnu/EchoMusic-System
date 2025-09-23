package com.fang.entity;

import java.io.Serializable;

/**
 * 音乐和歌单表，多对多关系
 * @author fang
 * @version 1.0
 */
public class SongAndPlayList implements Serializable {
    private String id;// id
    private String playListId;// 播放歌单的id
    private String songMusicId;// 音乐的id
}
