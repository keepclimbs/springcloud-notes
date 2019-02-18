# 代码实现ch5-1-2

```
功能：
操作步骤：
    1、分别启动eureka-server 和 eureka-client-ribbon, 启动两个eureka-client 有两个配置文件 端口不一样
    2、利用postman多次访问 http://localhost:7777/add?a=1000&b=3000 观看控制台 可发现轮询输出eureka-client的端口
结论：了解了ribbon的负载均衡

```
# 学到了什么
- 使用ribbon的话 需要结合 restTemplate来调用服务 因此需要配置 restTemplate 并加上注解@LoadBalanced
- 验证feign整合了ribbon
```
在feign代码 4-3-2例子增加配置文件, 操作步骤差不多, 发现feign也拥有负载均衡功能,策略是轮询
```
- 自定义Ribbon客户端
```
指定IRule实现类
    默认策略是：轮询 , 现在修改默认策略为随机, 需要增加下列配置
    ch512-eureka-client：# 这个是 源服务的spring.application.name
        ribbon:
            NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
    配置增加完成之后 多次访问 http://localhost:7777/add?a=1000&b=3000 观看控制台,可证明配置生效
...
还可以指定
    ILoadBalancer           # 对应配置 <clientName>.ribbon.NFLoadBalancerClassName
    IPing                   # 对应配置 <clientName>.ribbon.NFLoadBalancerPingClassName
    ServerList              # 对应配置 <clientName>.ribbon.NIWSServerListClassName
    ServerListFilter        # 对应配置 <clientName>.ribbon.NIWSServerListFilterClassName
这些类的实现类
```
- ribbon饥饿加载 和 重试机制
```
ribbon默认是 在请求时才去创建服务的上下文, 这个特性可能会导致第一次调用的时候超时, 解决办法是开启 饥饿加载
具体配置如下:
ribbon:
    eager-load:
        enabled: true
        clients: client-a, client-b, client-c
        
# 重试机制配置  F版本默认开启了重试机制所以需要配置相关信息     
ribbon:
  ConnectTimeout: 3000  # http客户端为httpclient才生效  默认5秒
  ReadTimeout: 60000    # http客户端为httpclient才生效  默认5秒
  MaxAutoRetries: 1 #对第一次请求的服务的重试次数        推荐设置0
  MaxAutoRetriesNextServer: 1 #要重试的下一个服务的最大数量（不包括第一个服务） 推荐设置0
  OkToRetryOnAllOperations: true  推荐false
```
- ribbon脱离eureka
```
ribbon:
    eureka:
        enabled: false
增加源服务地址列表
client:
    ribbon:
        listOfServers: http://localhost:8080, http://localhost:9090
```