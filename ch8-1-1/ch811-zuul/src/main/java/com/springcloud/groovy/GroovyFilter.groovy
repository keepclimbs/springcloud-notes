package com.springcloud.groovy

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import com.netflix.zuul.exception.ZuulException

import javax.servlet.http.HttpServletRequest

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE

/**
 * @author: song biao wei
 * @date: 2019/1/29 14:15
 * @description:
 */
class GroovyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE
    }

    @Override
    public int filterOrder() {
        return -19
    }

    @Override
    public boolean shouldFilter() {
        return true
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.currentContext.request as HttpServletRequest
        Iterator headerIt = request.getHeaderNames().iterator()
        while (headerIt.hasNext()) {
            String name = (String) headerIt.next()
            String value = request.getHeader(name)
            println("header: " + name + ":" + value)
        }
        println("This is Groovy Filter!")
        return null
    }
}
