package com.fang.mapper;

import com.fang.entity.dto.LikeMusicDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 喜欢音乐 Mapper
 * @author Fang
 */
@Repository
public interface LikeMusicMapper {

    /**
     * 添加喜欢的音乐
     *
     * @param likeMusic likeMusic对象
     * @return 是否添加成功
     */
    Boolean insertLikeMusic(LikeMusicDTO likeMusic);

    /**
     * 根据用户id和音乐id查询该歌曲是否已经被添加
     *
     * @param user_id 用户id
     * @param song_id 歌曲id
     * @return 是否已经添加
     */
    LikeMusicDTO selectLikeMusicByUserIdAndSongId(@Param("user_id") String user_id,
                                                  @Param("song_id") String song_id);

    /**
     * 根据用户id查询喜欢的音乐，和歌手
     *
     * @param user_id 用户id
     * @return 喜欢音乐的信息
     */
    List<LikeMusicDTO> selectLikeMusicByUserId(@Param("user_id") String user_id);

    /**
     * 根据用户id和歌曲id删除自己喜欢的音乐
     *
     * @param user_id 用户id
     * @param song_id 歌曲id
     * @return 是否删除成功
     */
    Boolean deleteLikeMusicByUserId(@Param("userId") String user_id,
                                    @Param("songId") String song_id);
}
