package com.lwhtarena.pxe.util.ebs.exception;

/**
 * @author： liwh
 * @Date: 2017/3/17.
 * @Description：<p>返回的异常信息</P>
 */
public class EBSExceptionJson {
    /**
     * 异常代码
     */
    private String exceptionCode;
    /**
     * 异常描述
     */
    private String exceptionMessage;
    /**
     * 堆栈信息
     */
    private String stackTrace;

    public String getExceptionCode() {
        return exceptionCode;
    }
    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
    public String getExceptionMessage() {
        return exceptionMessage;
    }
    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
    public String getStackTrace() {
        return stackTrace;
    }
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
