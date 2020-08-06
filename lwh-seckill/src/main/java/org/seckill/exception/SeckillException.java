package org.seckill.exception;

/**
 * @author liwh
 * @Title: SeckillException
 * @Package org.seckill.exception
 * @Description: 秒杀相关业务异常
 * @Version 1.0.0
 * @date 2020/8/5 22:53
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
