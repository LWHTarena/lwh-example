package com.lwhtarena.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author liwh
 * @Title: UserDTO
 * @Package com.lwhtarena.domain
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/23 13:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private List<String> roles;
}

