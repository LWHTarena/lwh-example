package com.lwhtarena.rpc.core.registry;

import java.util.Objects;

/**
 * @author liwh
 * @Title: RegistryInfo
 * @Package com.lwhtarena.rpc.core.registry
 * @Description: 注册信息
 * @Version 1.0.0
 * @date 2020/9/19 22:30
 */
public class RegistryInfo {

    private String hostname;
    private String ip;
    private Integer port;

    public RegistryInfo(String hostname, String ip, Integer port) {
        this.hostname = hostname;
        this.ip = ip;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegistryInfo that = (RegistryInfo) o;
        return Objects.equals(hostname, that.hostname) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(port, that.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostname, ip, port);
    }

    @Override
    public String toString() {
        return "RegistryInfo{" +
                "hostname='" + hostname + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
