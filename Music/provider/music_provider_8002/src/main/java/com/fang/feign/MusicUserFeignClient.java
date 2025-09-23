package com.fang.feign;

import com.fang.entity.dto.MusicUserDTO;
import com.fang.feign.fallBack.MusicUserFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户 feign
 *
 * @author Fang
 */
@FeignClient(value = "user-provider", fallback = MusicUserFeignClientFallBack.class)
public interface MusicUserFeignClient {
    @GetMapping("/provider/user/selectUserById")
    MusicUserDTO selectUserById(@RequestParam("id") String id);
}
