package com.springcloud.filter;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义Zuul回退机制处理器
 *
 * 我们在项目中使用Spring cloud zuul的时候，有一种这样的需求，
 * 就是当我们的zuul进行路由分发时，如果后端服务没有启动，或者调用超时，这时候我们希望Zuul提供一种降级功能，而不是将异常暴露出来。
 *
 */
@Component
public class ZuulFallback implements FallbackProvider{

    /**
     * @author: song biao wei
     * @description: 返回值表示需要针对此微服务做回退处理（该名称一定要是注册进入 eureka 微服务中的那个 serviceId 名称）
     * 					api服务id，如果需要所有调用都支持回退，则return "*"或return null
     * @date: 2019/2/1 16:35
     * @params: []
     * @return: java.lang.String
     */
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            /**
             * 网关向api服务请求是失败了，但是消费者客户端向网关发起的请求是OK的，
             * 不应该把api的404,500等问题抛给客户端
             * 网关和api服务集群对于客户端来说是黑盒子
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            /**
             * 当 springms-provider-user 微服务出现宕机后，客户端再请求时候就会返回 fallback 等字样的字符串提示；
             *
             * 但对于复杂一点的微服务，我们这里就得好好琢磨该怎么友好提示给用户了；
             *
             * 如果请求用户服务失败，返回什么信息给消费者客户端
             * @return
             * @throws IOException
             */
            @Override
            public InputStream getBody() throws IOException {
                //定义自己的错误信息

                return new ByteArrayInputStream("asdfadsf".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                // 和body中的内容编码一致，否则容易乱码
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                // TODO Auto-generated method stub
                return HttpStatus.INTERNAL_SERVER_ERROR.value();
            }
        };

    }

}
