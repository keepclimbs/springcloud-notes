# 学到了什么
##  [第七章 zuul 基础篇](https://github.com/keepclimbs/springcloud-notes/tree/master/ch8-1-1/readMe-7.md)
## 第八章内容如下

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

## 灰度发布
- 疑惑： 灰度发布要先写好Filter 而且还要手动配置请求的header
- 增加了三个类 在 eureka-client里面 分别启动不同的配置文件 比命令行跟方便 
- zuul 项目 增加了一个 Filter 实现了 灰度发布
```
操作步骤 
    分别启动eureka-server,  zuul , 三个节点的eureka-client
    利用postman 访问http://localhost:5555/client/mul?a=100&b=300  header增加条件 gray_mark = enable 就可以访问到7073端口
```

## 文件上传 
- 新加了一个配置文件和一个controller 
- 注意点1: springboot的版本不同 文件上传的配置也有些许区别 需要注意 (请看application-uploadFile.yml文件)
- 注意点2: springcloud F版本之前 上传中文名字的文件会乱码  在请求前面加上 zuul就会好 (官方解释)  http://localhost:5555/zuul/upload 

```
zuul:
  host:
    socket-timeout-millis: 600000 
    connect-timeout-millis: 600000
    max-per-route-connections: 1024 
    max-total-connections: 1024 
  SendErrorFilter:    # 禁用过滤器 下面有解释
    error:
      disable: false
      
解释： 
  ApacheHttpClient配置route最大连接数(如果是okhttp无效)
       max-per-route-connections： 默认20个 
  服务的http客户端连接池最大连接.适用于ApacheHttpClient，如果是okhttp无效。
       max-total-connections: 默认200个 
    
  如果路由方式是url的方式，则zuul.host开头的生效:
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