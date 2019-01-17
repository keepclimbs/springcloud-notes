# :computer: spingcloud学习记录
- 第一学 代码实现ch2-1  [笔记](https://github.com/keepclimbs/springcloud-notes/tree/master/ch2-1/readMe.md)
```
功能：
    (springcloud的 quick-start)
操作步骤：
    访问 http://localhost:8761 能展示注册的实例
```
- 第二学 了解配置中心 config-server  代码实现ch3-1 [笔记](https://github.com/keepclimbs/springcloud-notes/tree/master/ch3-1/readMe.md)
- 第三学 构建Multi Eureka Server 代码实现ch3-5-2 [笔记](https://github.com/keepclimbs/springcloud-notes/tree/master/ch3-5-2/readMe.md)
```
功能：
    体现了 ZoneAffinity 区域亲和性 
操作步骤：
    依次启动4个eureka-server, 2个eureka-client, 2个zuul, 然后访问 eureka-client实例的actuator/env接口
        localhost:10001/client/actuator/env
        localhost:10002/client/actuator/env
        client 是 eureka-client中 spring.application.name的值
    分别会出现
        activeProfiles : [ "zone1" ]
        activeProfiles : [ "zone2" ]
```
- 第四学  代码实现ch3-5-3 [笔记(有两个疑问)](https://github.com/keepclimbs/springcloud-notes/tree/master/ch3-5-3/readMe.md)
```
功能：springcloud异地多活 当local region 不可用之后 自动转移请求 到 remote region
操作步骤：
    1、先启动 4个 eureka-server , 然后启动 4个client 和 2 个 zuul
    2、然后访问 localhost:10001/client/actuator/env  会显示 activeProfiles : [ "zone1" ] 
    3、然后停止zone1 zone2的服务,继续访问    localhost:10001/client/actuator/env 
       会有几次失败, 然后轮询展示  activeProfiles : [ "zone3" ]  activeProfiles : [ "zone4" ] 
结论：证明了异地多活。
```