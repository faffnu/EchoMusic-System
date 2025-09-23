# 回声音乐系统

## 1. 项目架构

该项目采用典型的微服务架构，主要包含以下层次：

- **Gateway层(网关)**：端口80，负责统一入口和路由转发
- **Customer层(消费者)**：
  - user_customer_9001：用户服务消费者
  - music_customer_9002：音乐服务消费者
- **Provider层(提供者)**：
  - user_provider_8001：用户服务提供者
  - music_provider_8002：音乐服务提供者
- **Model层(公共模块)**：存放实体类、DTO等公共代码

## 2. 主要模块功能

### 2.1 用户模块(user)
- **功能**：
  - 用户登录/注册
  - 用户信息管理
  - 密码修改
  - 用户状态管理

- **主要接口**：
  - `/provider/user/login`：用户登录
  - `/provider/user/register`：用户注册
  - `/provider/user/update`：更新用户信息
  - `/provider/user/changePassword`：修改密码

### 2.2 音乐模块(music)
- **功能**：
  - 音乐搜索
  - 新歌推荐
  - 歌单管理
  - 播放历史
  - 收藏音乐
  - 排行榜

- **主要接口**：
  - `/provider/music/search`：搜索音乐
  - `/provider/music/newMusic`：获取新歌
  - `/provider/music/playlist`：获取歌单
  - `/provider/music/history`：播放历史相关
  - `/provider/music/insertLikeMusic`：收藏音乐

## 3. 技术栈

### 3.1 核心框架
- **Spring Boot**：应用开发框架
- **Spring Cloud**：微服务框架
- **Spring Cloud Alibaba**：阿里云微服务解决方案

### 3.2 关键组件
- **Nacos**：服务注册与配置中心
- **OpenFeign**：服务间调用
- **Gateway**：API网关
- **Sentinel**：服务熔断和限流
- **MyBatis**：ORM框架
- **Redis**：缓存
- **Druid**：数据库连接池

### 3.3 其他特性
- **统一响应**：使用ResultData封装响应结果
- **全局异常处理**：GlobalExceptionHandler统一处理异常
- **缓存支持**：使用Spring Cache注解实现缓存
- **接口文档**：集成Swagger3生成API文档
- **跨域处理**：Gateway中配置全局跨域

### 3.4 项目配置亮点
- **多环境配置**：支持dev/test/prod多环境切换
- **懒加载**：配置lazy-initialization优化启动速度
- **熔断降级**：Feign整合Sentinel实现服务熔断
- **负载均衡**：集成LoadBalancer实现负载均衡

## 4. 系统演示

### 4.1 功能演示

[视频](.\media\music.mp4)

### 4.2 Nacos

在nacos新建3个命名空间：dev、prod、test，将每个服务注册到 Nacos 的 dev 命名空间。

![nacos1](.\media\nacos1.png)

使用nacos统一配置管理，在配置管理public空间创建文件user_customer_sentinel.json，

在dev空间创建文件application-dev.yaml、druid-common.yaml、redis-common.yaml。
在文件中修改自己的mysql和redis相关配置内容。

![img](.\media\nacos2.png) 

![img](.\media\nacos3.png) 

 

###  4.3 Sentinel

![img](.\media\sentinel.png) 

￼

### 4.4 Swagger文档

http://localhost:9001/swagger-ui/index.html

![img](.\media\swagger1.png) 

http://localhost:9002/swagger-ui/index.html

![img](.\media\swagger2.png)

 

### 4.5 Skywalking链路追踪

在每个服务启动之前，添加如下虚拟机选项。

```yaml
-javaagent:文件skywalking-agent.jar的绝对路径
-Dskywalking.agent.service_name=服务名称
-Dskywalking.collector.backend_service=localhost:11800
```

![img](.\media\skywalking.png) 

