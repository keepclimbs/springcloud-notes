# 代码实现ch6-2-3

```
功能：通过dashborad来监控请求
操作步骤：
    一：dashboard演示(单个服务监控) // 使用较少 一般是集群监控
        1、分别启动eureka-server 和 eureka-client-provider, eureka-client-consumer, hystrix-dashborad
        2、然后访问 http://localhost:9000/hystrix
        3、在输入框里面输入http://localhost:9091/actuator/hystrix.stream 然后点击Monitor stream
        4、跳转后页面解释 请看图2
    二：turbine演示(集群服务的监控)
        1、分别启动eureka-server 和 eureka-client-provider, eureka-client-consumer, turbine
        2、然后访问 http://localhost:9088/hystrix
        3、看图片1 然后点击Monitor stream
        4、浏览器多次访问 http://localhost:9091/getProviderData 和http://localhost:8099/getHelloService
        5、跳转后页面解释 请看图1
    三：
        1、分别启动 eureka-server、eureka-client-provider、hystrix-exception-service 
        2、分别访问hystrix-exception-service 了解HystrixCommand注解 和 ErrorDecoder 配置
    四：
        1、分别启动 eureka-server、eureka-client-provider、hystrix-cache 
        2、分别访问 CacheController了解 hystrix-cache
结论：hystrix-dashborad监控成功
```
- 图1
![image](https://github.com/keepclimbs/springcloud-notes/blob/master/img/623-2.png)
- 图2
![image](https://github.com/keepclimbs/springcloud-notes/blob/master/img/623-1.png)
# 学到了什么
- 感觉就学会了怎么展示hystrix-dashborad 和 turbine 并不知道用这个解决什么问题
- bad-request 不会 触发 fallback 例子如下

```
   @FeignClient(name = "ch62-eureka-client-provider", fallback = UserServiceFallback.class)
   public interface IUserService {
    
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
       // 如果这里没有@RequestParam注解 就会请求不到 也不会fallback
       String getUser(@RequestParam("username") String username); 
   } 
```
- 通过hystrix-exception-service了解了 HystrixCommand注解
    - 继承 HystrixCommand 类 也可以实现 和注解同样的功能
    
- 复习了 feign 属性文件配置
![image](https://github.com/keepclimbs/springcloud-notes/blob/master/img/623-3.png)

- ErrorDecoder 配置作用
    - 调用服务端对异常信息进行编码  当前服务就不知道错误原因 就需要解码 （yes） 

- 了解了 Hsytrix-cache 
    - 重点:需要配置 Hystrix请求上下文 因为Hystrix缓存仅仅在一次请求内有效 
    - 开启Hystrix缓存 继承HystrixCommand的话 需要重写getCacheKey方法缓存请求
    - 使用注解的话需要配置 @CacheResult 来开启缓存请求

- 复习上一节：单纯的使用 hystrix 需要在service实现类上增加 @HystrixCommand(fallbackMethod="defaultUser") 此注解 并增加回调方法