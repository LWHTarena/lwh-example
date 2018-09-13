package com.lwhtarena.pxe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * <p>
 * <h2>简述：本地虚拟机WIN服务的ip</h2>
 * <ol></ol>
 * <h2>功能描述：本地虚拟机WIN服务的ip</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */

@Entity
public class Net implements Serializable {

    @Id
    @GeneratedValue
    private String id;

    @Column(nullable = false)
    private String ip;      //通信ip

    @Column(nullable = false)
    private String netmask; //掩码

    @Column(nullable = false)
    private String gateway;  //网关

    public Net() {

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
