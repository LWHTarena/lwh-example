package org.seckill.exception;

/**
 * @author liwh
 * @Title: RepeatKillException
 * @Package org.seckill.exception
 * @Description: 重复秒杀异常（运行期异常）
 * @Version 1.0.0
 * @date 2020/8/5 22:53
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}

