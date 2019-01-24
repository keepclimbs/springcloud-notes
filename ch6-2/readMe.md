# 代码实现ch5-1-2

```
功能：
操作步骤：
    1、分别启动eureka-server 和 eureka-client-provider, eureka-client-consumer
    2、利用postman多次访问 localhost:8888/getUser?username=spring 和 localhost:8888/getUser?username=spring1
结论：出现异常就会跳转熔断方法

```
# 学到了什么
- 单纯的使用 hystrix 需要在service实现类上增加 @HystrixCommand(fallbackMethod="defaultUser") 此注解 并增加回调方法
- 使用feign 和 hystrix 来调用远程服务, 有异常就会自动跳转到熔断方法
- 了解hystrix和ribbon的超时时间 和重试次数配置
- 复习了饥饿加载
- 复习了暴露所有endpoints(端点)
```
management:
  endpoints:
    web:
      exposure:
        include: '*'
```