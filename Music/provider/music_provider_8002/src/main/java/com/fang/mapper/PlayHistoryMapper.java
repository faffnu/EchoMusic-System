package com.fang.mapper;

import com.fang.entity.dto.PlayHistoryDTO;
import com.fang.entity.vo.PlayHistoryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 播放历史 CRUD
 *
 * @author Fang
 */
@Repository
public interface PlayHistoryMapper {

    /**
     * 根据用户id查询播放历史
     *
     * @param user_id 用户id
     * @return List<PlayHistoryDTO>
     */
    List<PlayHistoryDTO> selectPlayHistoryByUserId(@Param("user_id") String user_id);

    /**
     * 根据 PlayHistoryDTO 添加一条播放记录
     *
     * @param playHistory PlayHistoryDTO
     * @return Boolean
     */
    Boolean insertPlayHistory(PlayHistoryDTO playHistory);

    /**
     * 根据用户id和歌曲id删除一条记录
     *
     * @param playHistoryVo PlayHistoryVo
     * @return Boolean
     */
    Boolean deletePlayHistoryByPlayHistoryVo(PlayHistoryVo playHistoryVo);

    PlayHistoryDTO selectByUserAndSong(String userId, String songId);

    void updatePlayTime(PlayHistoryDTO existing);
}
