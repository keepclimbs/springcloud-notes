# 学到了什么
##代码实现ch3-5-5
- keytool jdk自带的 如果想知道怎么使用 直接用cmd命令行 输入 keytool -help就可以了解。
```
功能：启用https
操作步骤： 需要配置类 EurekaHttpsClientConfig
- 1、生成server端证书, 使用Teriminal切换到项目目录 下列命令都在同一目录执行
    - 执行命令：
        1) 生成证书： keytool -genkeypair -alias serverSsl -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore serverSsl.p12 -validity 3650
        2) 导出证书： keytool -export -alias serverSsl -file serverSsl.crt --keystore serverSsl.p12
- 2、生成client端证书, 使用Teriminal切换到client目录
    - 执行命令：
        1) 生成证书： keytool -genkeypair -alias clientSsl -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore clientSsl.p12 -validity 3650
        2) 导出证书： keytool -export -alias clientSsl -file clientSsl.crt --keystore clientSsl.p12

- 3 导入证书
    - server端执行命令：keytool -import -alias clientSsl -file clientSsl.crt -keystore serverSsl.p12 (使server信任client)
    - client端执行命令：keytool -import -alias serverSsl -file serverSsl.crt -keystore clientSsl.p12 (使client信任server)
- 4 拷贝到resource目录中
    - 给serverSsl.p12分别拷贝到ch355-eureka-server的resource目录中
    - 给clienteSsl.p12分别拷贝到ch355-eureka-client的resource目录中
- 5 然后分别启动 eureka-server  eureka-client 成功后访问https://localhost:8761可以看到 实例注册成功  
```


 

