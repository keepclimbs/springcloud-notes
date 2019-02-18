# 学到了什么
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

- 注册中心用 8-1-1章节的
- zuul权限验证 什么都没学到
- zuul限流 什么都没学到