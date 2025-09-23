package com.fang.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 歌手实体类
 * @author fang
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SingerDTO implements Serializable {
    private String singerId; // 歌手id
    private String singerName;// 歌手名称
}
