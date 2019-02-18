package com.springcloud.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.springcloud.utils.JsonUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * @author: song biao wei
     * @description: 其实GET里面代码也适合POST 感觉更好 --------重点哦-----------
     * @date: 2019/2/18 11:56
     */
    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            String methodType = context.getRequest().getMethod();
            if ("POST".equals(methodType)) {
                // 请求编码
                String charset = context.getRequest().getCharacterEncoding();
                // 获取请求内容
                InputStream in = (InputStream) context.get("requestEntity");
                if (in == null)
                    in = context.getRequest().getInputStream();
                String requestJsonString = StreamUtils.copyToString(in, Charset.forName(charset));
                // 给请求内容转换成map 然后增加或者修改参数
                Map<String, Object> map = new HashMap<>();
                if (!"".equals(requestJsonString)) {
                    map = JsonUtil.jsonFromObject(requestJsonString, new TypeReference<Map<String, Object>>() {});
                }
                map.put("weight", 140);
                // 给处理完的对象转换成字符串
                String writeValueAsString = JsonUtil.objectFromJson(map);
                byte[] bytes = writeValueAsString.getBytes(charset);
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
            } else if ("GET".equals(methodType)) {
                Map<String, List<String>> requestQueryParams = context.getRequestQueryParams();
                if (requestQueryParams == null) {
                    requestQueryParams = new HashMap<>();
                }
                //将要新增的参数添加进去,被调用的微服务可以直接 去取,就想普通的一样,框架会直接注入进去
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("1");
                requestQueryParams.put("weight", arrayList);
                context.setRequestQueryParams(requestQueryParams);
            }

        } catch (IOException e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}




