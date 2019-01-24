# 代码实现ch4-3-2
## 注意：工程的pom可能会有些jar下载不下来需要多弄几次, 需注意
```
功能：
操作步骤：
    1、新建一个eureka-server 当作注册中心
    2、新建一个服务提供者
    3、新建一个服务消费者
        1) 整合 swagger 方便测试, 具体配置见：类Swagger2Config和ApplicationExceptionAdapter和 pom文件
        2) 需要导入eureka-client的依赖, 不然无法注册到eureka-server
    4、依次启动eureka-server, 服务提供者, 服务消费者, 然后访问 http://localhost:8011/swagger-ui.html 即可进行测试
结论：


```
# 学到了什么
- 如何配置swagger
- 学会了使用feign的请求拦截器RequestIntercepter来处理get请求传POJO
- 熟悉了服务间如何通过feign进行调用
- springboot的logback日志配置
    - spring-boot-starter-web依赖包含了 日志logback的依赖,所以不用导入日志依赖
    - 具体日志配置细节请看logback.xml文件 (spring-note项目的log4j2日志笔记) [笔记](https://github.com/keepclimbs/spring-note/tree/master/src/main/resources/log4j2.xml)

## feign文件上传
- 请看增加的pom依赖和 代码
- 熟悉了服务间如何通过feign传递token 
