package com.springcloud.service.fallback;

import com.springcloud.service.FeignService;
import org.springframework.stereotype.Component;

/**
 * @author: song biao wei
 * @date: 2019/2/18 17:14
 * @description:
 */

@Component
public class FeignFallBack implements FeignService {

    @Override
    public String getDefaultUser() {
        return "url \"getDefaultUser\" request  failed";
    }

    @Override
    public String getContextUserId() {
        return "url \"getContextUserId\" request failed";
    }
}
