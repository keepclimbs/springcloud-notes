# 这一章学到了什么
## 代码实现ch3-5-2
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
## 关于maven启动springboot项目
```
mvn spring-boot:run  命令后面可以拼接 后面这三个属性
    -Dspring.profiles.active=zone1a 
    --settings G:\javaTools\apache-maven-3.3.9\conf\settings-aliyun.xml
    -Dspring-boot.run.jvmArguments="-Xms256m -Xmx256m"
```
## 关于springcloud 区域亲和性
- eureka-client 和 zuul 在同一个 zone 里面, 访问网关zuul的时候, 会优先调用当前zone的 eureka-client
如图：
![image](https://github.com/keepclimbs/springcloud-notes/blob/master/img/352-1.png)
    
