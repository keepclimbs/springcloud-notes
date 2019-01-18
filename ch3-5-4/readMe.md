# 学到了什么
##代码实现ch3-5-4
```
功能：http basic 认证
操作步骤：
    1、启动eureka-server 然后 localhost:8761访问不了   
    2、git工具命令行输入 curl -i --basic -u admin:Xk38CNHigBP5jK75 http://localhost:8761/eureka/apps 
         然后回车就可以查看服务列表 
    3、启动eureka-client 继续输入上面命令 观察服务列表 显示实例注册成功。
注意：需要配置SecurityConfig(类上有注释) 否则 client会注册不成功
```
- 了解了 xss 攻击
```
xss解释：
    XSS攻击全称跨站脚本攻击，是为不和层叠样式表(Cascading Style Sheets, CSS)的缩写混淆，故将跨站脚本攻击缩写为XSS，
    XSS是一种在web应用中的计算机安全漏洞，它允许恶意web用户将代码植入到提供给其它用户使用的页面中。
解决办法(没深究)：
    增加过滤器, 在WEB.XM 中配置 filter的时候，请将这个 filter 放在第一位.

```
- 了解了 csrf 攻击 
```
csrf解释：
    CSRF（Cross-site request forgery）跨站请求伪造，也被称为“One Click Attack”或者Session Riding，
    通常缩写为CSRF或者XSRF，是一种对网站的恶意利用。尽管听起来像跨站脚本（XSS），但它与XSS非常不同，
    XSS利用站点内的信任用户，而CSRF则通过伪装成受信任用户的请求来利用受信任的网站。与XSS攻击相比，
    CSRF攻击往往不大流行（因此对其进行防范的资源也相当稀少）和难以防范，所以被认为比XSS更具危险性

解决办法(没深究)：
    增加token验证
```
- 了解 curl命令
```
 curl -i --basic -u admin:Xk38CNHigBP5jK75 http://localhost:8761/eureka/apps
 解释：
    通过-I或者-head可以只打印出HTTP头部信息：
        [root@localhost text]# curl -I http://man.linuxde.net
        HTTP/1.1 200 OK
        Server: nginx/1.2.5
        date: Mon, 10 Dec 2012 09:24:34 GMT
        Content-Type: text/html; charset=UTF-8
        Connection: keep-alive
        Vary: Accept-Encoding
        X-Pingback: http://man.linuxde.net/xmlrpc.php
    --basic 使用HTTP基本验证    
    使用curl选项 -u 可以完成HTTP或者FTP的认证，可以指定密码，也可以不指定密码在后续操作中输入密码：    
        curl -u user:pwd http://man.linuxde.net
        curl -u user http://man.linuxde.net
扩展：    
    -X/--request <command>	指定什么命令 比如 请求的类型 POST DELETE PUT等  
    -H/--header <line>	自定义头信息传递给服务器  
学习文档 https://blog.csdn.net/huangzx3/article/details/80625080           

```
