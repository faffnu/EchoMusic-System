package com.fang.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 歌单实体类
 *
 * @author fang
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PlayListDTO implements Serializable {
    private String playListId;//歌单id
    private String musicUserId;//歌单作者id
    private String title;// 歌单标题
    private String description;// 歌单描述
    private String playListCover;//歌单图片

    @JsonInclude(JsonInclude.Include.NON_NULL) //如果属性值不为空序列化
    private Type playListType;//歌单类型

    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
    private Date createTime;// 创建时间
    private Integer playCount;// 播放次数

    private MusicUserDTO musicUser;//歌单作者信息
    @JsonInclude(JsonInclude.Include.NON_NULL) //如果属性值不为空序列化
    private List<SongMusicDTO> songMusicList;//歌曲信息

    @Getter
    public enum Type {
        HY("华语"), LX("流行"), MY("民谣");
        private final String playListType;

        Type(String tag) {
            this.playListType = tag;
        }
    }
}
