# 关于springcloud
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
    - 1、这个配置类 我不明白为何要重新new一个对象, 因为这个对象有很多属性 (com\springcloud\config\RegionConfig.java)
    - 2、如图
![image](https://github.com/keepclimbs/springcloud-notes/blob/master/img/353-2.png)    
    
    




