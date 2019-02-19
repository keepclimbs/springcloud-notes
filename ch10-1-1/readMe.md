# ch10-1-1 综合所有模块练习 
## ch1011-common模块
- feign截器来传递用户信息         ------- RequestInterceptor
```
    feign给前台传递的header 重新set进apply方法的template参数 然后进入服务提供方的拦截器
    拦截器会从request里面获取东西,从而验证权限
```
- restTemplate截器来传递用户信息  ------- ClientHttpRequestInterceptor 
```
    restTemplate给前台传递的header 重新set进intercept方法的request参数 
    然后进入服务提供方的拦截器 拦截器会从request里面获取东西,从而验证权限
```
- 线程传递属性                    -------  CommonConfiguration 

- 实现类zuul的 fallback           -------  FallbackProvider
```
    - 区别下hystrix的fallback哦 当路由服务没有启动的时候 会给予友好提示
```

## 实现 config-server第一种方式  使用git配合actuator刷新 -- 是 config-client端刷新
```
1、在git仓库新建一个容器 
2、在容器里新建一个文件夹 然后新建三个配置文件名称分别为config-info-dev.yml, config-info-test.yml, config-info-prod.yml
3、启动config-server 启动之后config-server会在本地临时目录克隆远程仓库配置信息
4、访问 http://localhost:9090/config-info/dev/master 可以展示相关信息

配置信息和url的映射关系 application是应用名 config-info就是应用名
    /{application}/{profile}[/{lable}]
    /{application}-{profile}.yml              # 展示配置文件
    /{lable}/{application}-{profile}.yml      # 展示配置文件
```
- 手动刷新 是client端刷新
```
1、访问 http://localhost:9091/getConfigValue 观察内容
2、然后 修改config-info-dev.yml 文件内容 
3、访问 http://localhost:9091/getConfigValue 观察内容 发现内容不变
4、利用postman post方式请求 http://localhost:9091/actuator/refresh 来手动刷新 -- 是 config-client端刷新
5、再次访问 访问 http://localhost:9091/getConfigValue  发现配置已经修改
```

## 实现 config-server第二种方式  通过git配合springcloud bus刷新 -- 是 config-server端刷新