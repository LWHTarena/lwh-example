package com.lwhtarena.pxe.domain;

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
public class Net implements Serializable {
    private String ip;      //通信ip
    private String netmask; //掩码
    private String gateway;  //网关
    private String subnet;  //子网
    private String dhcpUpper; //dhcp上限 （形如：192.168.222.119）
    private String dhcpLower; //dhcp下限 （形如：192.168.222.110）

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

    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public String getDhcpUpper() {
        return dhcpUpper;
    }

    public void setDhcpUpper(String dhcpUpper) {
        this.dhcpUpper = dhcpUpper;
    }

    public String getDhcpLower() {
        return dhcpLower;
    }

    public void setDhcpLower(String dhcpLower) {
        this.dhcpLower = dhcpLower;
    }

    public Net(String ip, String netmask, String gateway, String subnet, String dhcpUpper, String dhcpLower) {
        this.ip = ip;
        this.netmask = netmask;
        this.gateway = gateway;
        this.subnet = subnet;
        this.dhcpUpper = dhcpUpper;
        this.dhcpLower = dhcpLower;
    }

    @Override
    public String toString() {
        return "Net{" +
                "ip='" + ip + '\'' +
                ", netmask='" + netmask + '\'' +
                ", gateway='" + gateway + '\'' +
                ", subnet='" + subnet + '\'' +
                ", dhcpUpper='" + dhcpUpper + '\'' +
                ", dhcpLower='" + dhcpLower + '\'' +
                '}';
    }
}
