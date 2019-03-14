## @ResponseBody @RequestBody 注解解释
```
@ResponseBody：
    作用在方法上的，@ResponseBody 表示该方法的返回结果直接写入 HTTP response body 中，
    一般在异步获取数据时使用【也就是AJAX】，在使用 @RequestMapping后，返回值通常解析为跳转路径，
    但是加上 @ResponseBody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中。 
    比如异步获取 json 数据，加上 @ResponseBody 后，会直接返回 json 数据。
    @RequestBody 将 HTTP 请求正文插入方法中，使用适合的 HttpMessageConverter 将请求体写入某个对象
@RequestBody：
     @RequestBody是作用在形参列表上，用于将前台发送过来固定格式的数据【xml 格式或者 json等】封装为对应的 JavaBean 对象
重点:
    post提交的json数据 无法用 request.getParamer()来获取
```
## pom中有些依赖下载不下来 可以给 依赖配置粘贴到别的项目中下载下来 然后在当前项目中去掉这个依赖 重新粘贴下载就可以了
- maven安装jar到本地仓库 mvn install:install-file -DgroupId=com.baidu -DartifactId=ueditor -Dversion=1.0.0 -Dpackaging=jar -Dfile=D:\ueditor-1.1.2.jar