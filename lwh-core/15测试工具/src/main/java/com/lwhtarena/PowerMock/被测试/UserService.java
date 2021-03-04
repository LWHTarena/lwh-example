package com.lwhtarena.PowerMock.被测试;

public class UserService {
    public String getUserName(int id) {
        return "hello";
    }

    public void handle(String s) {
        System.out.println("handle(): ..." + s);
    }
}
