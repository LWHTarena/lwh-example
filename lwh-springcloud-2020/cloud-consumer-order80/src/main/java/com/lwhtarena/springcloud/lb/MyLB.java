package com.lwhtarena.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liwh
 * @Title: MyLB
 * @Package com.lwhtarena.lb
 * @Description: 自定义均衡器
 * @Version 1.0.0
 * @date 2020/5/7 09:08
 */
@Component
public class MyLB implements LoadBlancer {

    private AtomicInteger atomicInteger =new AtomicInteger();

    /**
     * 坐标
     * @return
     */
    private final int getAndIncrement(){
        int current;
        int next;
        do {
            current =this.atomicInteger.get();
            next =current>=2147483647?0:current+1;
        }while(!this.atomicInteger.compareAndSet(current,next)); //从第一个参数是期望值，第二个参数是修改值是

        System.out.println("***********第几次访问，次数next:"+next);
        return next;
    }


    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) { //得到机器列表
        int index =getAndIncrement()%serviceInstances.size(); //得到服务器的下标位置
        return serviceInstances.get(index);
    }
}
