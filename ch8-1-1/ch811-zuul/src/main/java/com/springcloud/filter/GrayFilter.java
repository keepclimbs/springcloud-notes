package com.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * @author: song biao wei
 * @description: 根据自定义元数据  灰度发布 的例子
 * @date: 2019/1/30 14:34
 * @params:
 * @return:
 */

// @Component
public class GrayFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(GrayFilter.class);

	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return PRE_DECORATION_FILTER_ORDER - 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return !ctx.containsKey(FORWARD_TO_KEY) && !ctx.containsKey(SERVICE_ID_KEY);
	}

	@Override
	public Object run() throws ZuulException {
		logger.info("this is 灰度发布 filter");
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		String mark = request.getHeader("gray_mark");
		if (!StringUtils.isEmpty(mark) && "enable".equals(mark)) {
			RibbonFilterContextHolder.getCurrentContext()
				.add("host-mark", "gray-host");
		} else {
			RibbonFilterContextHolder.getCurrentContext()
				.add("host-mark", "running-host");
		}
		return null;
	}
}
