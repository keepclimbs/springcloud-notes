# 关于springcloud
##代码实现ch3-5-3
```
功能：springcloud异地多活 当local region 不可用之后 自动转移请求 到 remote region
操作步骤：
    1、先启动 4个 eureka-server , 然后启动 4个client 和 2 个 zuul
    2、然后访问 localhost:10001/client/actuator/env  会显示 activeProfiles : [ "zone1" ] 
    3、然后停止zone1 zone2的服务,继续访问    localhost:10001/client/actuator/env 
       会有几次失败, 然后轮询展示  activeProfiles : [ "zone3" ]  activeProfiles : [ "zone4" ] 
结论：证明了异地多活。
```
- application.yml是公用的，所有环境 
    `例子：mvn spring-boot:run -Dspring.profile.active=zone1 运行之后 application-zone1.yml 和 application.yml同时生效`
- bootstrap.yml 和 application.yml区别
```
1、
    bootstrap.yml是spring cloud读取的配置文件，和spring boot没关系 spring cloud在启动的时候,
    会首先初始化一个applicationContext, 这个context作为spring boot的applicationContext的parent
    而spring cloud的context读取的就是bootstrap.yml
2、
    这个配置文件的名称是可以配置的，通过spring.config.name来配置
```
- 项目模块图谱
![image](https://github.com/keepclimbs/springcloud-notes/blob/master/img/353-1.png)

- 疑问?
    - 1、我不明白为何要重新new一个EurekaServerConfigBean, 因为这个对象有很多属性使用new 就会有很多属性没有set 或者说在什么时候注入的 (配置类路径：com\springcloud\config\RegionConfig.java)
    - 2、如图
![image](https://github.com/keepclimbs/springcloud-notes/blob/master/img/353-2.png)    
    - 3、region有啥用  我感觉用zone就可以了呀   全世界各地都有机房  干嘛还需要多个region
    




