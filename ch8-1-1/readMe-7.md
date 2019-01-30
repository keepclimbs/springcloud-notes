# 第七章的重要知识点
```
一：路由配置
    路由通配符
        /** 匹配任意数量的路径和字符
        /*  匹配任意数量的字符
        /?  匹配单个字符
    1) 单实例 serviceId 映射
        zuul: 
            routes:
                client-a:
                    path: /client/**
                    serviceId: client-a # 
    2) 单实例 url 映射  (路由到物理地址）
        zuul: 
            routes:
                client-a:
                    path: /client/**
                    url: http://localhost:7070
                    
    3) 多实例路由
       默认情况下, zuul会使用Eureka中集成的基本负载均衡功能, 如果想要使用Ribbon的负载均衡功能, 就需要指定
       一个serviceId, 此操作需要禁止Ribbon使用Eureka
       
       很疑惑这里的服务是写死的, 为什么要禁止eureka 真的好么
       
        zuul: 
           routes:
               client-a:
                   path: /client/**
                   serviceId: client-a
        ribbon:
            eureka:
                enabled: false
        
        client-a:
            ribbon:
                NIWSServerListClassName: com.netflix.loadbalancer.ConfigurationBasedServerList
                listOfServers: localhost:7070, localhost:7071
    
    4) Zuul中做逻辑处理, 然后forward 本地跳转
        
        @GetMapping("/client")
        public String add(Integer a, Integer b) {
            int c = a + b;
            // 逻辑处理之后本地跳转
            return "forward: /add?num=" + c; // 这里是一个请求的url
        }
    
        zuul:
            routes:
                client-a:
                    path: /client/**
                    url: forward: /client  
                
二：功能配置 
    1) 路由前缀
        zuul:
            prefix: /pre # 路由前缀
            routes:
                client-a:
                    path: /client/**
                    url: forward: /client  
    
    2) 服务屏蔽和路径屏蔽
        zuul:
            ignored-services: client-b    #忽略的服务, 防止服务侵入
            ignored-patterns: /**/div/**  #忽略的请求
            routes:
                client-a:
                    path: /client/**

    3) 敏感头信息 (Zuul可以指定敏感头, 切断它和下层服务之间的交互)
        zuul:
            routes:
                client-a:
                    path: /client/**
                    sensitiveHeaders: Cookie, Set-Cookie, Authorization

    4) 重定向问题 (请求zuul, 然后跳转到一个欢迎页, 网页的host是服务的host 而不是 zuul的host, 暴露了服务的ip)
        zuul:
            add-host-header: true
            routers:
                client-a: /client/**
    
    5) 重试机制 (zuul 配合 ribbon 来做重试) 慎重使用 有一些接口要保证幂等性, 一定要做好相关工作 
        zuul:
            retryable: true
            
        ribbon:
            MaxAutoRetries: 1
            MaxAutoRetriesNextServer: 1
            
```