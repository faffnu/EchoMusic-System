package com.fang.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 前端参考文档
 * @author fang
 * @version 1.0
 */
//配置Swagger跨域请求
@OpenAPIDefinition(
        servers = @Server(url = "${gateway-url}")
)
@Configuration(proxyBeanMethods = false)
public class Swagger3Config {
    @Bean
    public OpenAPI docs() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Cloud Alibaba 音乐服务模块")
                        .description("音乐模块描述")
                        .version("1.0"))
                        .externalDocs(new ExternalDocumentation()
                        .description("具体请参考文档！")
                        .url("https://mac2git.github.io/docs-demo"));
    }
}
