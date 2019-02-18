# 代码实现ch2-2

```
功能：展示hystrix的基础使用
操作步骤：
    1、分别启动eureka-server 和 eureka-client-provider, eureka-client-consumer
    2、利用postman多次访问 localhost:8888/getUser?username=spring 和 localhost:8888/getUser?username=spring1
结论：出现异常就会跳转熔断方法

```
# 学到了什么
- 单纯的使用 hystrix 例子：
```
    @GetMapping("/getFallbackMethodTest")
    @HystrixCommand(fallbackMethod="defaultUser")
    public String getFallbackMethodTest(String id){
    	throw new RuntimeException("getFallbackMethodTest failed");
    }
    
    public String defaultUser(String id, Throwable throwable) {
    	log.error(throwable.getMessage());
        return "this is fallback message";
    }
```
- 使用feign 和 hystrix 来调用远程服务, 有异常就会自动跳转到熔断方法
- 了解hystrix和ribbon的超时时间 和重试次数配置
- 复习了ribbon饥饿加载
- 复习了暴露所有endpoints(端点)
```
management:
  endpoints:
    web:
      exposure:
        include: '*'
```