package com.lwhtarena.esapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: User
 * @Package com.lwhtarena.esapi.pojo
 * @Description:
 * @Version 1.0.0
 * @date 2020/8/22 21:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
    String name;
    int age;
    String address;
}
