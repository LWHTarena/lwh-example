package com.lwhtarena.pxe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * <p>
 * <h2>简述：ipmi 配置信息</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
@Entity
public class IpmiBean implements Serializable {

    @Id
    @GeneratedValue
    private String id; //标识符

    @Column(nullable = false)
    private String ipmiIP; //ipmi远程连接IP

    @Column(nullable = false)
    private String username; //ipmi 远程登录用户

    @Column(nullable = false)
    private String pwd; //ipmi 远程登录密码

    public IpmiBean() {
    }

    public IpmiBean(String id, String ipmiIP, String username, String pwd) {
        this.id = id;
        this.ipmiIP = ipmiIP;
        this.username = username;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpmiIP() {
        return ipmiIP;
    }

    public void setIpmiIP(String ipmiIP) {
        this.ipmiIP = ipmiIP;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
