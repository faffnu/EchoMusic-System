package com.fang.feign.fallBack;

import com.fang.entity.dto.MusicUserDTO;
import com.fang.feign.MusicUserFeignClient;
import com.fang.system.StaticUrl;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class MusicUserFeignClientFallBack implements MusicUserFeignClient {

    private final String uri = StaticUrl.getUrl();

    @Override
    public MusicUserDTO selectUserById(String id) {
        String music_id = UUID.randomUUID().toString().replace("-", "");
        String url = uri + "/static/avatar/default.png";
        return new MusicUserDTO(music_id, "默认用户","", url, "默认用户简介", false, new Date());
    }
}
