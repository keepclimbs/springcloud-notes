package com.springcloud.controller;


import com.springcloud.service.HelloCommand;
import com.springcloud.service.IHelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/**
 * @author: song biao wei
 * @date: 2019/1/27 17:37
 * @description: Hystrix缓存在一次请求内有效, 所以需要初始化Hystrix上下文
 */

@RestController
public class CacheController {
    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private IHelloService helloService;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @author: song biao wei
     * @description: 继承HystrixCommand类开启缓存 (不建议) 需要重写getCacheKey方法缓存请求
     *         日志会输出一个 flase 和 一个 true 证明了第二次请求来源于缓存
     * @date: 2019/1/27 17:01
     * @params: [id]
     * @return: java.lang.String
     */
    @RequestMapping(value = "/getUserIdByExtendCommand/{id}", method = RequestMethod.GET)
    public String getUserIdByExtendCommand(@PathVariable("id") Integer id) {
        HelloCommand one = new HelloCommand(restTemplate,id);
        one.execute();
        logger.info("from cache:   " + one.isResponseFromCache());
        HelloCommand two = new HelloCommand(restTemplate,id);
        two.execute();
        logger.info("from cache:   " + two.isResponseFromCache());
        return "getUserIdByExtendCommand success";
    }

    /**
     * @author: song biao wei
     * @description: 使用注解开启缓存
     * 输出信息：
     *         {"username":"Toy","password":"123456","age":10}
     *         {"username":"Toy","password":"123456","age":10}
     *         -----------
     *         {"username":"Toy","password":"123456","age":10}
     * 观察输出结果可明白：
     *         @CacheKey 如果IHelloService接口方法没有此注解 就会默认方法所有参数作为key来缓存请求
     *         @CacheResult 使用此注解开启缓存请求
     * @date: 2019/1/27 17:05
     * @params: [id]
     * @return: java.lang.String
     */
    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public String getUserId(@PathVariable("id") Integer id) {
        helloService.hello(id, 1);
        helloService.hello(id, 2);
        System.out.println("-----------");
        helloService.hello(id, 3);
        helloService.hello(id, 3);
        return "getUser success";
    }

    /**
     * @author: song biao wei
     * @description: 参数为5的时候 会更新缓存
     * @date: 2019/1/27 17:05
     * @params: [id]
     * @return: java.lang.String
     */
    @RequestMapping(value = "/getUserIdUpdate/{id}", method = RequestMethod.GET)
    public String getUserIdUpdate(@PathVariable("id") Integer id) {
        helloService.hello(id, 1);
        helloService.hello(id, 1);
        helloService.hello(5, 2);
        return "getUserIdUpdate success";
    }



    /**
     * @author: song biao wei
     * @description:
     *         运行之后输出是这样子的
     *            {"username":"Toy","password":"123456","age":10}
     *         删除getUser缓存
     *         {"username":"Toy","password":"123456","age":10}
     *   结论： 因为配置了@CacheKey属性,因此按照id来缓存请求,不然会输出多条
     *          输出结果证明缓存删除成功
     *   注意： 删除缓存需要给缓存取个名称, 具体如下
     *               @CacheRemove(commandKey="getUser")
     *               @HystrixCommand(commandKey = "getUser")
     * @date: 2019/1/27 17:05
     * @params: [id]
     * @return: java.lang.String
     */
    @RequestMapping(value = "/getAndUpdateUser/{id}", method = RequestMethod.GET)
    public String getAndUpdateUser(@PathVariable("id") Integer id) {
        //调用接口并缓存数据
        helloService.getUserToCommandKey(id, 1);
        helloService.getUserToCommandKey(id, 2);
        //清除缓存
        helloService.updateUser(id, 1);
        //再调用接口
        helloService.getUserToCommandKey(id, 1);
        helloService.getUserToCommandKey(id, 3);
        return "getAndUpdateUser success";
    }

}


