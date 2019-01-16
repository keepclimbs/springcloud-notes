# 这一章学到了什么
## 关于maven
- 学会了去掉parent标签内容后, pom文件该怎么修改
```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.3.RELEASE</version>
</parent>

去掉parent标签内容后的操作：
    1：复制spring-boot-starter-parent项目里面的properties标签内容到当前项目的 properties中
        <properties>
            <java.version>1.8</java.version>
            <maven.compiler.source>${java.version}</maven.compiler.source>
            <maven.compiler.target>${java.version}</maven.compiler.target>
            <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        </properties>
    2：给 spring-boot-starter-parent 配置到 dependencyManagement 中 
        <dependencyManagement>
            <dependencies>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-parent</artifactId>
                    <version>${spring-boot.version}</version>
                    <type>pom</type>
                    <scope>import</scope>
                </dependency>
            </dependencies>
        </dependencyManagement>
    3：配置pluginManagement 方便子项目依赖插件, 作用和dependencyManagement一样 ( 非必要 )    
        <build>
            <pluginManagement>
                <plugins>
                    <!-- 作用：构建springboot项目  -->
                    <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                        <version>2.0.3.RELEASE</version>
                    </plugin>
                    <!-- 作用：编译项目源代码  -->
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-compiler-plugin</artifactId>
                        <version>${maven.compiler.version}</version>
                    </plugin>
                </plugins>
            </pluginManagement>
        </build>
```

- 学会了maven编译插件如何配置jdk
```
<build>
    <plugins>
        <!-- 作用：构建springboot项目  -->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>2.0.3.RELEASE</version>
        </plugin>
        
        <!-- 
            作用：maven编译源代码
                如果不配置, maven编译项目会使用的默认的jdk(版本1.3或者1.5)
                如果需要修改 就配置 
                    <configuration>
                        <source>8</source>
                        <target>8</target>
                    </configuration>
                或者 在 properties 标签里面增加下面四个属性
                    <properties>
                        <java.version>1.8</java.version>
                        <maven.compiler.source>${java.version}</maven.compiler.source>
                        <maven.compiler.target>${java.version}</maven.compiler.target>
                        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                    </properties>
         -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <!-- 配置编译插件 jdk版本为1.8 -->
            <configuration>
                <source>8</source>
                <target>8</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```
- maven三大生命周期 clean default site
    - clean     清理上次编译后的文件夹
    - default   真正构建时候的所有步骤 ( validate -> deploy )
    - site      建立和发布项目站点

- ch3-1 重新用回了 parent标签 因为比较简洁  
## 关于springcloud
```
依赖介绍：
    config server : spring-cloud-config-server
    config client : spring-cloud-starter-config
   
    监控系统健康情况的工具 ：spring-boot-starter-actuator
```