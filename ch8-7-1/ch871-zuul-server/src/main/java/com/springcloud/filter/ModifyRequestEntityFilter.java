package com.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author: song biao wei
 * @date: 2019/1/30 17:33
 * @description:
 */

@Configuration
public class ModifyRequestEntityFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    // 放在zuul的类型为pre的Filter的最后面
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER + 1;// =6
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("ModifyRequestEntityFilter----------------------------");
        try {
            RequestContext context = RequestContext.getCurrentContext();
            String charset = context.getRequest().getCharacterEncoding();
            InputStream in = (InputStream) context.get("requestEntity");
            if (in == null)
                in = context.getRequest().getInputStream();
            String body = StreamUtils.copyToString(in, Charset.forName(charset));
            // 这里是新增的参数
            body += "weight=140";
            byte[] bytes = body.getBytes(charset);
            context.setRequest(new HttpServletRequestWrapper(context.getRequest()) {

                @Override
                public ServletInputStream getInputStream() throws IOException {
                    return new ServletInputStreamWrapper(bytes);
                }

                @Override
                public int getContentLength() {
                    return bytes.length;
                }

                @Override
                public long getContentLengthLong() {
                    return bytes.length;
                }
            });
        } catch (IOException e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}




