package com.lwhtarena.springcloud.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liwh
 * @Title: Dept
 * @Package com.lwhtarena.springcloud.entities
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 07:42
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Dept implements Serializable {
    private Long deptno;
    private String dname;
    private String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }
}
