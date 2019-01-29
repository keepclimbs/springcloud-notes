# 学到了什么
##  [第七章 zuul 基础篇](https://github.com/keepclimbs/springcloud-notes/tree/master/ch8-1-1/readMe-7.md)
## 第八章内容如下
```
zuul:
  host:
    socket-timeout-millis: 600000 
    connect-timeout-millis: 600000
    max-per-route-connections: 1024 
    max-total-connections: 1024 
  SendErrorFilter: 
    error:
      disable: false
      
  解释： 
  max-per-route-connections： 默认20个 适用于ApacheHttpClient，如果是okhttp无效。每个route可用的最大连接数
  max-total-connections: 默认200个 适用于ApacheHttpClient，如果是okhttp无效。每个服务的http客户端连接池最大连接.
  如果是url的方式，则zuul.host开头的生效:
        zuul.host.connect-timeout-millis
        zuul.host.socket-timeout-millis 
  如果路由方式是serviceId的方式，那么ribbon的超时时间生效
    
         
  禁用过滤器：
     配置规则是 zuul.<SimpleClassName>.<FilterType>.disable=true
     SimpleClassName例子：SendErrorFilter 可以是自定义的Filter
     FilterType
        - pre
        - route
        - post
        - error
```

- 自定义Filter必须继承ZuulFilter类
    - 重写方法：filterType 通过返回值设置Filter类型
    - filterOrder()        通过返回值设置Filter执行顺序 越小越靠前
    - shouldFilter         通过返回值判断该filter是否执行, 可作为开关使用
    - run                  核心逻辑

- http://localhost:5555/actuator/filters 可以展示所有注册的Filter
- http://localhost:5555/actuator/routes/details 可以展示所有的端点明细
- groovyFilter 
    - 不用编译 不用打包 放在服务器的任意位置即可 
    - 在工程启动主类中添加Groovy加载器
    - 然后回20秒扫面一次,修改拦截器内容后 20秒后就生效. 太牛逼
- Zuul生命周期组合流程图
![image](https://github.com/keepclimbs/springcloud-notes/blob/master/img/811-1.png)