# 回声音乐系统

- [技术栈](#技术栈)
  - [后端技术栈](#后端技术栈)
  - [前端技术栈](#前端技术栈)
- [微服务架构](#微服务架构)
  - [后端项目结构](#后端项目结构)
  - [前端项目结构](#前端项目结构)
  - [服务说明](#服务说明)
  - [核心功能模块](#核心功能模块)
  - [配置中心](#配置中心)
- [项目特点](#项目特点)
- [快速启动](#快速启动)
  - [环境要求](#环境要求)
  - [启动步骤](#启动步骤)
- [系统演示](#系统演示)
  - [功能演示](#功能演示)
  - [Nacos](#nacos)
  - [Sentinel](#sentinel)
  - [Swagger文档](#swagger文档)
  - [Skywalking链路追踪](#skywalking链路追踪)


# 回声音乐系统

本项目是一个仿网易云的音乐平台，采用微服务架构设计，使用Spring Cloud Alibaba技术栈实现。系统包含用户认证、音乐管理、播放历史、收藏管理等核心功能。

## 技术栈

### 后端技术栈
- **核心框架**: Spring Boot 3.x, Spring Cloud Alibaba
- **注册中心**: Nacos
- **网关**: Spring Cloud Gateway
- **服务调用**: OpenFeign
- **负载均衡**: Spring Cloud LoadBalancer
- **熔断降级**: Sentinel
- **数据库**: MySQL 8.x
- **连接池**: Druid
- **缓存**: Redis
- **ORM框架**: MyBatis
- **API文档**: Swagger3
- **工具类**: Hutool, Lombok
- **安全**: JWT, BCrypt
- **构建工具**: Maven

### 前端技术栈
- **核心框架**: Vue 2.x
- **UI框架**: Element UI
- **状态管理**: Vuex
- **路由**: Vue Router
- **HTTP客户端**: Axios
- **音频播放**: APlayer
- **构建工具**: Vue CLI

## 微服务架构

### 后端项目结构

```
Music/
├── customer/              # 客户端服务（Controller层）
│   ├── music_customer_9002/ # 音乐服务消费者
│   │   ├── src/main/java/com/fang/
│   │   │   ├── config/     # OpenFeign配置
│   │   │   ├── controller/ # 控制器层
│   │   │   ├── feign/      # Feign客户端接口
│   │   │   └── service/    # 服务层
│   │   └── pom.xml         # Maven配置文件
│   └── user_customer_9001/ # 用户服务消费者
│       ├── src/main/java/com/fang/
│       │   ├── config/     # OpenFeign配置
│       │   ├── controller/ # 控制器层
│       │   ├── feign/      # Feign客户端接口
│       │   ├── handler/    # 异常处理
│       │   └── service/    # 服务层
│       └── pom.xml         # Maven配置文件
├── gateway/               # 网关服务
│   ├── src/main/java/com/fang/
│   │   ├── config/         # 网关配置
│   │   └── GateWayApplication80.java # 网关启动类
│   └── src/main/resources/
│       └── application.yaml # 网关配置文件
├── model/                 # 公共实体类和工具类
│   ├── src/main/java/com/fang/
│   │   ├── config/         # 配置类（Druid、Redis、Swagger）
│   │   ├── entity/         # 实体类
│   │   │   ├── dto/        # 数据传输对象
│   │   │   └── vo/         # 视图对象
│   │   ├── enums/          # 枚举类
│   │   ├── exception/      # 异常处理
│   │   ├── response/       # 统一响应
│   │   ├── system/         # 系统配置
│   │   └── utils/          # 工具类
│   └── pom.xml             # Maven配置文件
├── provider/              # 服务提供者（Service层）
│   ├── music_provider_8002/ # 音乐服务提供者
│   │   ├── src/main/java/com/fang/
│   │   │   ├── config/     # 配置类
│   │   │   ├── controller/ # 控制器层
│   │   │   ├── feign/      # Feign客户端
│   │   │   ├── mapper/     # 数据访问层
│   │   │   └── service/    # 业务逻辑层
│   │   └── src/main/resources/
│   │       ├── mapper/     # MyBatis映射文件
│   │       └── application.yaml # 应用配置
│   └── user_provider_8001/ # 用户服务提供者
│       ├── src/main/java/com/fang/
│       │   ├── controller/ # 控制器层
│       │   ├── mapper/     # 数据访问层
│       │   └── service/    # 业务逻辑层
│       └── src/main/resources/
│           ├── mapper/     # MyBatis映射文件
│           └── application.yaml # 应用配置
└── pom.xml                # 根项目Maven配置
```

### 前端项目结构

```
vue/
├── public/                 # 静态资源
│   ├── theme/              # 主题文件
│   └── index.html          # 入口HTML
├── src/
│   ├── axios/              # HTTP请求封装
│   ├── components/         # 公共组件
│   ├── eventBus/           # 事件总线
│   ├── router/             # 路由配置
│   ├── store/              # Vuex状态管理
│   ├── views/              # 页面组件
│   ├── App.vue             # 根组件
│   └── main.js             # 入口文件
└── package.json            # 依赖管理
```

### 服务说明

| 服务名称 | 端口 | 说明 |
|---------|------|------|
| gateway | 80 | 网关服务 |
| user-provider | 8001 | 用户服务提供者 |
| music-provider | 8002 | 音乐服务提供者 |
| user-customer | 9001 | 用户服务消费者 |
| music-customer | 9002 | 音乐服务消费者 |

### 核心功能模块

1. **用户服务 (user-provider/user-customer)**
   - 用户注册/登录
   - 用户信息管理
   - 密码修改
   - JWT Token认证

2. **音乐服务 (music-provider/music-customer)**
   - 音乐播放
   - 歌单管理
   - 收藏管理
   - 播放历史
   - 音乐搜索
   - 排行榜
   - 歌手管理

3. **网关服务 (gateway)**
   - 路由转发
   - 负载均衡
   - 跨域处理
   - 统一文档


### 配置中心

使用Nacos作为配置中心，支持多环境配置：

- **dev**: 开发环境
- **test**: 测试环境
- **prod**: 生产环境


## 项目特点

1. **微服务架构**: 高内聚低耦合，便于维护和扩展
2. **配置中心**: 统一配置管理，支持动态刷新
3. **服务治理**: Nacos服务注册发现，Sentinel熔断降级
4. **缓存优化**: Redis缓存热点数据，提升访问性能
5. **安全机制**: JWT认证，BCrypt加密
6. **前后端分离**: Vue前端，RESTful API
7. **文档完善**: Swagger API文档，代码注释完整

## 快速启动

### 环境要求
- JDK 17+
- MySQL 8.x
- Redis
- Nacos 2.x
- Sentinel Dashboard (可选)
- Node.js 16+ (前端)

### 启动步骤

1. **启动基础设施**
   ```bash
   # 启动Nacos
   startup.cmd -m standalone
   
   # 启动Redis
   redis-server
   ```

2. **初始化数据库**
   - 创建music数据库
   - 执行数据库初始化脚本

3. **启动后端服务**
   ```bash
   # 按顺序启动服务
   cd gateway && mvn spring-boot:run
   cd provider/user_provider_8001 && mvn spring-boot:run
   cd provider/music_provider_8002 && mvn spring-boot:run
   cd customer/user_customer_9001 && mvn spring-boot:run
   cd customer/music_customer_9002 && mvn spring-boot:run
   ```

4. **启动前端**
   ```bash
   cd vue
   npm install
   npm run serve
   ```

## 系统演示

### 功能演示

https://github.com/user-attachments/assets/415af009-5e1b-4baf-b1e4-88f9feb2e6dc

### Nacos

在nacos新建3个命名空间：dev、prod、test，将每个服务注册到 Nacos 的 dev 命名空间。

![nacos1](/media/nacos1.png)

使用nacos统一配置管理，在配置管理public空间创建文件user_customer_sentinel.json，

在dev空间创建文件application-dev.yaml、druid-common.yaml、redis-common.yaml。
在文件中修改自己的mysql和redis相关配置内容。

![img](/media/nacos2.png) 

![img](/media/nacos3.png) 

 

###  Sentinel

![img](/media/sentinel.png) 



### Swagger文档

http://localhost:9001/swagger-ui/index.html

![img](/media/swagger1.png) 

http://localhost:9002/swagger-ui/index.html

![img](/media/swagger2.png)

 

### Skywalking链路追踪

在每个服务启动之前，添加如下虚拟机选项。

```yaml
-javaagent:文件skywalking-agent.jar的绝对路径
-Dskywalking.agent.service_name=服务名称
-Dskywalking.collector.backend_service=localhost:11800
```

![img](/media/skywalking.png) 

