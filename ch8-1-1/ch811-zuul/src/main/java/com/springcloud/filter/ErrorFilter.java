package com.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;

/**
 * @author: song biao wei
 * @description: 异常后跳转的Filter
 * @date: 2019/1/30 14:33
 * @params:
 * @return:
 */
public class ErrorFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);
    
    @Override
    public String filterType() {
        return ERROR_TYPE;
    }
    
    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("this is errorFilter");
        RequestContext ctx = RequestContext.getCurrentContext();
        return null;
    }

}
