# 学到了什么
##代码实现ch3-5-5
- keytool jdk自带的 如果想知道怎么使用 直接用cmd命令行 输入 keytool -help就可以了解。
```
功能：
操作步骤：
- 1、生成server端证书, 使用Teriminal切换到server的resource目录（例:G:\git_code\springcloud-notes\ch3-5-5\ch355-eureka-server）
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
    - 给ch355-eureka-server目录下的 serverSsl.p12, serverSsl.crt, clientSsl.crt分别拷贝到resource目录中
    - 给ch355-eureka-server目录下的 clienteSsl.p12, clientSsl.crt, serverSsl.crt分别拷贝到resource目录中
```


 
