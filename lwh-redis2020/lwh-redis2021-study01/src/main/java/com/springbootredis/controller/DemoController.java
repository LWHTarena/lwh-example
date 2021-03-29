package com.springbootredis.controller;

import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/29 07:06:54
 * @description
 */
@RestController
public class DemoController {
    @Autowired
    RedissonClient redisson;

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        //1.获取一把锁，只要锁的名字一样，就是同一锁
        RLock lock = redisson.getLock("my-lock");

        //2.加锁
        lock.lock();
        try {
            System.out.println("加锁成功，执行业务..."+Thread.currentThread().getId());
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放锁..."+Thread.currentThread().getId());
            lock.unlock();
        }
        return "hello";
    }

    @GetMapping("lockDoor")
    @ResponseBody
    public String lockDoor() throws InterruptedException {
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.trySetCount(5);
        door.await();//等待锁关闭
        return "放假了...";
    }

    @GetMapping("/gogogo/{id}")
    public String gogogo(@PathVariable("id") long id){
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.countDown();//计数减一
        return id +"班的人都走了...";
    }

    /***
     * 描述 车库停车
     *   3车位
     *   park.tryAcquire() 用于分布式限流
     * @param
     * @return  
     * @author liwh
     * @date 2021/3/29 20:41
     * @version 1.0
     **/
    @GetMapping("/park")
    public String park() throws InterruptedException {
        RSemaphore park = redisson.getSemaphore("park");
//        park.acquire();//获取一个信号，获取一个值-->占一个车位
        boolean b = park.tryAcquire();//
        return "ok==>"+b;
    }

    @RequestMapping("/park/go")
    public String parkGo() throws InterruptedException {
        RSemaphore park = redisson.getSemaphore("park");
        park.release();//释放一个车位
        return "ok";
    }
}
