package com.fang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 喜欢音乐实体类
 * @author fang
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LikeMusic implements Serializable {
    private String likeMusicId;//id
    private String songId;//喜欢歌曲id
    private String userId;//用户id
    private Date likeTime;// 添加到喜欢音乐的时间
}
