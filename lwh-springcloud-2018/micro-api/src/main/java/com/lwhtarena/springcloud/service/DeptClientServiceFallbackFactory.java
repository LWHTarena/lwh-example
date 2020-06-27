package com.lwhtarena.springcloud.service;

import com.lwhtarena.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liwh
 * @Title: DeptClientServiceFallbackFactory
 * @Package com.lwhtarena.springcloud.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 07:46
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept get(long id) {
                return new Dept()
                        .setDeptno(id)
                        .setDname("该"+id+"没有对应的信息，consumer客户端提供的降级信息，此服务Provider已经关闭")
                        .setDb_source("no this database in mysql");
            }

            @Override
            public List<Dept> list() {
                return null;
            }

            @Override
            public boolean add(Dept dept) {
                return false;
            }
        };
    }
}
