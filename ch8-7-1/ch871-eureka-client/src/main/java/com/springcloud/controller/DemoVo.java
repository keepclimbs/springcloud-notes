package com.springcloud.controller;

/**
 * @author: song biao wei
 * @date: 2019/2/18 11:28
 * @description: 测试修改请求体的实体类
 */
public class DemoVo {
    private Integer weight;
    private Integer id;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DemoVo{" +
                "weight=" + weight +
                ", id=" + id +
                '}';
    }
}
