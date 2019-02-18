# 代码实现ch6-2-3

```
功能：hystrix-thread-context 线程传递及并发策略
操作步骤：
    1、分别启动 eureka-server、hystrix-thread-context
    2、访问 http://localhost:3333/getUser/5555 注意控制台输出 
       注意： 必须要写上config文件夹下的几个类 解决了线程传递及并发策略      
结论：
```

# 学到了什么
- feign.hystrix.enabled 默认是false 所以需要在配置文件中开启
- hystrix-thread-context 这一章有点难 例子不是很懂 特别是配置类
- RequestContextHolder 请求上下文容器 
    - 如果没有配置线程传递及并发策略这里会取值失败
- Hystrix默认是 线程池隔离
- hystrix配置说明
![image](https://github.com/keepclimbs/springcloud-notes/blob/master/img/623-4.png)
- hystrix线程池 大小计算  和 ribbon超时时间的设置
![image](https://github.com/keepclimbs/springcloud-notes/blob/master/img/623-5.png)
- 预留了250ms,然后加上重试一次的中位数100ms的解释
    - 30个并发  默认情况下可以给每个线程分配350ms的时候, 这个时间可以作为ribbon的超时配置时间
    - ConnectionTimeOut:250ms
    - ReadTimeOut:100ms
```
配置隔离策略为 信号量 默认是线程池
@HystrixCommand(fallbackMethod = "stubMyService",
    commandProperties = {
      @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
    }
)
```    
- Hystrix命令详解
![image](https://github.com/keepclimbs/springcloud-notes/blob/master/img/623-6.png)