package com.lwhtarena.security.rbac.dto;

/**
 * @author liwh
 * @Title: RoleInfo
 * @Package com.lwhtarena.security.rbac.dto
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 21:22
 */
public class RoleInfo {

    private Long id;

    private String name;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
