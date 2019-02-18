#代码实现ch412-feign-helloword
- feign默认是使用jdk原生的URLConnection发送http请求
```
功能：feign的helloword 和 gzip 启动方式相同
操作步骤：
    1、启动Ch412OpenFeignApplication类 的main方法   
    2、浏览器输入 http://localhost:8010/search/github?str=spring-cloud-dubbo
    3、结果和 https://api.github.com/search/repositories?q=spring-cloud-dubbo 的返回结果一样
结论：feign可以调用其他服务

```
# 学到了什么
- ch412-feign-helloword 项目介绍
    - open feign是什么： 声明式的webService客户端, 提供了HTP请求的模板并整合了Ribbon的负载均衡和Hystrix
    
    - @FeignClient(name = "github-client", url = "https://api.github.com", configuration = HelloFeignServiceConfig.class) 注解需要配合接口使用
        - name = "github-client"：微服务的名称, 如果用到了Ribbon name会作为微服务的名称用于服务发现
        - url = "https://api.github.com"： 目标服务的域名; 与接口里面方法的 @RequestMapping配合使用
            - 如果是get请求 还需要和 @RequestParam配合使用 , 例如get请求通过id查询
        - configuration = HelloFeignServiceConfig.class： feign配置类 （Encoder, Decoder, Level, Contract等）
            - feign的专属Logger, 记录所有请求与响应的明细，包括头信息、请求体、元数据
        - fallback： 定义容错处理类。调用远程接口失败时调用。
        - fallbackFactory：工厂类 用于生成fallback类实例,通过这个属性为接口提供通用的容错逻辑, 减少重复代码。 

- ch412-feign-gzip 项目介绍
    - 启用gzip压缩需要配置
    - 使用apache的httpclient 替换feign默认的 httpclient (好处是增加了连接池等优化属性) 
```
启用gzip压缩需要配置：
    feign:
        compression:
            request:
                enabled: true                                             # 配置请求GZIP压缩
                mime-types: text/xml,application/xml,application/json     # 配置压缩支持的MIME TYPE
                min-request-size: 2048                                    # 配置压缩数据大小的下限
            response:
                enabled: true                                             # 配置响应GZIP压缩
                
    且： 
        返回值需要改成ResponseEntity<byte[]> 才能正常显示, 否则会导致服务之间调用乱码
---------------------------------------------------------------------------------------------
使用apache的httpclient 替换feign默认的 httpclient (好处是增加了连接池等优化属性) ：
   pom里面增加
       <dependencies>
           <dependency>
               <groupId>org.apache.httpcomponents</groupId>
               <artifactId>httpclient</artifactId>
           </dependency>
           <dependency>
               <groupId>com.netflix.feign</groupId>
               <artifactId>feign-httpclient</artifactId>
               <version>8.17.0</version>
           </dependency>
       </dependencies>
       
   然后application.yml中开启 (开启 apache 替换 默认的http-client) :
       feign.httpclient.enabled=true
```
# 问题 
- ch412-feign-gzip的application.yml中设置了 超时时间 但是实际运行, 没有生效
- 使用apache的httpclient 替换feign默认的 httpclient 
```
使用apache的httpclient替换默认的httpclient 
    具体配置如下
    需要增加 apache-httpclient和feign-httpclient依赖
        详情请看pom文件
    配置文件中开启httpclient
        feign:
            httpclient: 
              enabled: false
          
如果使用okhttp 需要导入okhttp的以来并且禁用httpclient
    feign:
        httpclient: 
          enabled: false
        okhttp:
          enabled: true        
```

- 设置feign公用配置 优先级高于配置类里面的配置 比如日志配置类里面是FULL 这里是Baisc 最后的结果就是Basic
```
feign:
    client:
      config:                # 配置feign属性 和 @FeignClient 注解功能相同
        default:
          connecTimeout: 1 # 连接超时
          readTimeout: 1   # 读超时
          loggerLevel: basic
```
- 开启feign日志
```
application.yml中增加 （如果不加 就没有日志输出）
logging:
     level:
       com.springcloud.service.HelloFeignService: debug
并且 增加配置类 HelloFeignServiceConfig

```
## 使用apache-httpclient 怎么配置连接池大小呢？