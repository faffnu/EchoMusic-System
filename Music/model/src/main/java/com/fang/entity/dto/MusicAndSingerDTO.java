package com.fang.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 歌曲和歌手中间表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MusicAndSingerDTO {
    @JsonIgnore //忽略返回值，不给前端看
    private String id;
    @JsonIgnore
    private String musicId;
    @JsonIgnore
    private String singerId;
    private List<SingerDTO> singers;
}
