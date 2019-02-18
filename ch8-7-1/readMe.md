- zuul推荐设置
``` 
zuul:
    ribbonIsolationStrategy: THREAD # 默认zuul使用信号量
    threadPool:
        useSeparateThreadPools: true 
        threadPoolKeyPrefix: zuulgatway # 线程前缀
    host:
        max-per-route-connections: 50  # 每个路由最大连接数 默认20
        max-total-connections: 300     # 目标主机最大连接数
        socket-timeout-millis: 5000    # zuul路由方式是url的方式 才生效
        connect-timeout-millis: 5000   # zuul路由方式是url的方式 才生效
```
# 请求体修改 (feign的get传递POJO也是修改传递参数和这里有什么不同呢 )
- 通过拦截器 往header里面添加东西  (详情请看类HeaderDeliverFilter)
- 修改请求体详情请看 ModifyRequestEntityFilter.java
# 重试机制 (想想之前的ribbon重试 和这里有啥不同)
- zuul的重试机制 需要配合spring retry这个依赖
# 复习了 swagger 的使用 (详情请看ch4-3-2章节的readMe.md)

# springcloud各个组件之间通信协议是http, http客户端使用的是apache的httpclient (feign默认使用的是 jdk自带的urlConnection)
```
区别： feign的替换 httpclient或okhttp 是因为 feign默认是使用jdk的urlconnection发送http请求 所以可以替换两个
       zuul替换okhttp                 是因为 zuul默认使用的httpclient发送http请求         所以只能替换一个
```