# 这一章学到了什么
##代码实现ch2-1 
```
功能：
    (springcloud的 quick-start)
操作步骤：
    访问 http://localhost:8761 能展示注册的实例
```
## 关于maven
- 学习到了maven配置dependencies和dependencyManagement的区别
    
```
- dependencyManagement
    作用： 让子工程继承配置, 但是不下载依赖jar包, 详细如下
        1：子模块如果需要依赖, 不用输入版本号和依赖范围等, 有groupId、artifactId就可以, 继承了版本号,依赖范围等.
        2：子模块依赖的时候, 才会去下载
        3：好处是方便管理依赖版本号 （相比如dependencies更灵活）
        
- dependencies
    作用：导入依赖, 和当前项目子模块共有此依赖
        1：子模块自动继承 ( 相比如dependencyManagement 不用配置groupId、artifactId )
        2：缺点：有些子模块不需要父工程中某些依赖, 但是还是会被动继承。（不够灵活）
```
    
- 理解了maven配置scope：(常用的几项)
    - compiler ：默认依赖范围, 测试编译运行都需要该依赖
    - test     ：测试依赖范围, 编译和运行测试代码的时候才需要   例：junit
    - runtime  ：测试和运行时候有效                           例：jdbc驱动实现
    - system   ：系统依赖范围                                 例：钉钉相关jar
- 理解了maven配置packageing
    - jar
    - war
    - pom : 项目里没有java代码，也不执行任何代码，只是为了聚合工程或传递依赖用的
- 理解了maven version版本号
    - 例子 0.0.1 第一个0是大版本号 第二个0是分支版本号  第三个0是小版本号
    - snapshot 快照
    - alpha    内部公测
    - beta     公测
    - Release  稳定
    - GA       发布
## 关于springcloud
```
- spring-boot-starter-parent
    ：springcloud基于springboot所以需要导入此依赖

- spring-cloud-dependencies
    ：提供了springcloud需要的依赖, 例如spring-cloud-netflix, spring-cloud-config等
     eureka就是属于spring-cloud-netflix项目
     
- application.yml文件配置解释
eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false # 服务本身是否注册到eureka
    fetchRegistry: false # 指示此客户端是否应该从EURKA服务器获取EURKA注册表信息。
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
  server:
    waitTimeInMsWhenSyncEmpty: 0 # 在Eureka服务器获取不到集群里对等服务器上的实例时，需要等待的时间，单位为毫秒
    enableSelfPreservation: false # 是否开启自我保护
```
