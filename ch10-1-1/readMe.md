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

## 实现 config-server第一种方式  使用git配合actuator刷新


## 实现 config-server第二种方式  通过git配合springcloud bus刷新