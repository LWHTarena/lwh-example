package com.lwhtarena.PowerMock.被测试;

import org.springframework.beans.factory.annotation.Autowired;

public class Demo {

    @Autowired
    private UserService userService;

    public void dealUser() {
        String name = userService.getUserName(1);
        System.out.println("userService.getUserName(): " + name);
        userService.handle(name);
        say(name);
    }

    private void say(String name) {
        System.out.println("say() : " + name);
        sys(name);
    }

    public void work() {
        System.out.println("work() ....");
    }

    public String getString() {
        return Utils.getObject(this, "/zero");
    }

    public String sys(String name) {
        System.out.println("sys() : " + name);
        return name;
    }
}
