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
- zuul权限验证 
``` 
zuul + OAuth2.0 + JWT  (JSON Web Token) 意义

使用OAuth协议的思想拉去认证生成的Token 使用JWT瞬时保存这个Token 在客户端与资源端进行对称或者非对称加密
使得这个规约具有定时定量的授权认证功能, 从而免去token存储所带来的安全或系统扩展问题.
``` 
![image](https://github.com/keepclimbs/springcloud-notes/blob/master/img/821-1.png)
