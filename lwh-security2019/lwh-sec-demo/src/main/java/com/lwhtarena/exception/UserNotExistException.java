package com.lwhtarena.exception;

/**
 * @author liwh
 * @Title: UserNotExistException
 * @Package com.lwhtarena.exception
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:26
 */
public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = -6112780192479692859L;

    private String id;

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
