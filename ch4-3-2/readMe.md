# 代码实现ch4-3-2
## 注意：工程的pom可能会有些jar下载不下来需要多弄几次, 需注意
```
功能1：展示当前服务的接口
    操作步骤：
        1、新建一个eureka-server 当作注册中心
        2、新建一个服务提供者
        3、新建一个服务消费者
            1) 整合 swagger 方便测试, 具体配置见：类Swagger2Config和ApplicationExceptionAdapter和 pom文件
            2) 需要导入eureka-client的依赖, 不然无法注册到eureka-server
        4、依次启动eureka-server, 服务提供者, 服务消费者, 然后访问 http://localhost:8011/swagger-ui.html 即可进行测试
    结论：
功能2：展示zuul网关 路由的所有服务的接口
    详情请看ch8-7-1  zuul需要配置SwaggerResourcesProvider这个bean
    重点： 这里使用swagger版本要求为2.7 用2.5传递参数失败

```
# 学到了什么
- 如何配置swagger
- 学会了使用feign的请求拦截器RequestIntercepter来处理feign的get请求传POJO
    - 具体实现请看 FeignRequestInterceptor (递归jsonNode感觉很高级)
- 学会了使用feign的请求拦截器FeignTokenInterceptor来处理token的传递
- 熟悉了服务间如何通过feign进行调用
- springboot的logback日志配置
    - spring-boot-starter-web依赖包含了 日志logback的依赖,所以不用导入日志依赖 (请看eureka-server)
    - 具体日志配置细节请看logback.xml文件 (spring-note项目的log4j2日志笔记) [笔记](https://github.com/keepclimbs/spring-note/tree/master/src/main/resources/log4j2.xml)

## feign文件上传
- 请看增加的pom依赖和 代码
    - 增加了 FeignMultipartSupportConfig 配置类 (类里面有注释)
