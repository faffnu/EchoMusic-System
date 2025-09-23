package com.fang;

import com.fang.entity.dto.LikeMusicDTO;
import com.fang.service.impl.LikeMusicServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LikeMusicServiceImplTest {


    @Resource
    private LikeMusicServiceImpl likeMusicServiceImpl;

    @Test
    void test() {
        List<LikeMusicDTO> likeMusicDTOList = likeMusicServiceImpl.selectLikeMusicByUserId("af2460354f224f79ad6dc55de92da6b6");
        likeMusicDTOList.forEach(System.out::println);
    }
}
