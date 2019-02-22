package com.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
/**
 * @author: song biao wei
 * @description: 最简单的Filter例子
 * @date: 2019/1/30 14:33
 * @params:
 * @return:
 */
public class FirstPreFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(FirstPreFilter.class);
    
    @Override
    public String filterType() {
        return PRE_TYPE;
    }
    
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("this is firstPreFilter");
        return null;
    }
}
