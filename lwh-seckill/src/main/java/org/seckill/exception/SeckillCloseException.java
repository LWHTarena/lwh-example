package org.seckill.exception;

/**
 * @author liwh
 * @Title: SeckillCloseException
 * @Package org.seckill.exception
 * @Description: 秒杀关闭异常
 * @Version 1.0.0
 * @date 2020/8/5 22:54
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
