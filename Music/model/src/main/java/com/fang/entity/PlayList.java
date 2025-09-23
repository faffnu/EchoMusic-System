package com.fang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 歌单实体类
 * @author fang
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PlayList implements Serializable {
    private String playListId;// 歌单id
    private String musicUserId;//歌单作者id
    private String title;// 歌单标题
    private String description;// 歌单描述
    private String playListCover;//歌单图片
    private String playListType;//歌单类型
    private Date createTime;// 创建时间
    private Integer playCount;// 播放次数
}
