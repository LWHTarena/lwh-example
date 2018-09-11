package com.lwhtarena.pxe.util.ebs.exception;

/**
 * @author： liwh
 * @Date: 2017/3/17.
 * @Description：<p></P>
 */
public class EBSException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    //异常代码
    private String exceptionCode;
    //异常描述
    private String exceptionMessage;
    //堆栈信息
    private String stackTrace;

    public EBSException(String exceptionCode, String exceptionMessage, String stackTrace) {
        super(exceptionMessage);
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
        this.stackTrace = stackTrace;
    }

    public EBSException(String exceptionCode, String exceptionMessage, String stackTrace, Throwable cause) {
        super(exceptionMessage, cause);
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
        this.stackTrace = stackTrace;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    @Override
    public String toString() {
        return "EBSException [exceptionCode=" + exceptionCode
                + ", exceptionMessage=" + exceptionMessage + ", stackTrace="
                + stackTrace + "]";
    }
}
