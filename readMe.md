# :computer: spingcloud学习记录
- 第一学 (springcloud的 quick-start) [笔记](https://github.com/keepclimbs/springcloud-notes/tree/master/ch2-1/readMe.md)
```
功能：
    访问 http://localhost:8761 能展示注册的实例
    代码实现请看ch2-1
```
- 第二学 了解配置中心 config-server  代码实现ch3-1 [笔记](https://github.com/keepclimbs/springcloud-notes/tree/master/ch3-1/readMe.md)
- 第三学 构建Multi Eureka Server 代码实现ch3-5-2 [笔记](https://github.com/keepclimbs/springcloud-notes/tree/master/ch3-5-2/readMe.md)
```
功能：
    依次启动4个eureka-server, 2个eureka-client, 2个zuul, 然后访问 eureka-client实例的actuator/env接口
        localhost:10001/client/actuator/env
        localhost:10002/client/actuator/env
    分别会出现
        activeProfiles : [ "zone1" ]
        activeProfiles : [ "zone2" ]
    体现了 ZoneAffinity 区域亲和性    
```