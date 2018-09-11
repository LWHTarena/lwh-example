package com.lwhtarena.pxe.domain;

import com.sun.javaws.progress.Progress;

import java.io.Serializable;

/**
 * <p>
 * <h2>简述：超融合平台自动化部署配置</h2>
 * <ol></ol>
 * <h2>功能描述：超融合平台自动化部署配置</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Config implements Serializable,Comparable<Config>{

    private String id; //标识

    private String hostId;
    /**
     * 资源池名称
     */
    private String poolName;

    /**
     * 物理机ip
     */
    private String hostIp;

    /***
     * 物理机名称--主机名称
     */
    private String hostName;

    /**
     * 物理机root用户的密码
     * 说明：默认所有物理机的管理用户root
     */
    private String hostPwd;

    /**
     * 网关
     */
    private String gateway;

    /**
     * 掩码
     */
    private String netmask;

    /****
     * 物理机ipmi地址
     */
    private String ipmiIp;

    /***
     * ipmi账号
     */
    private String ipmiUname;

    /***
     * ipmi密码
     */
    private String ipmiPwd;

    /**
     * 管理网卡名称
     */
    private String manageNetwork;

    /**
     * 存储网卡名称集合
     * 形如：[eth2,eth3,eth4]
     */
    private String storeNetwork;

    /**
     * 网卡mac地址
     */
    private String macAddr;


    /***
     * 系统盘符
     */
    private String systemVolume;

    /***
     * 接受参数时使用
     */
    private String taskId;

    private Progress progress;

    /**
     * dom0 内存（单位GB）
     */
    private String ram;

    /***
     * 然后实现 compareTo（）方法，该方法的返回值0代表相等，1表示大于，-1表示小于
     * @param o
     * @return
     */
    @Override
    public int compareTo(Config o) {
        String ip =this.getHostIp(); //192.168.222.23
        String[] ip_01 =this.getHostIp().split("\\.");
        String[] ip_02 =o.getHostIp().split("\\.");
        int i =Integer.parseInt(ip_01[3].toString())-Integer.parseInt(ip_02[3].toString());
        if(i==0){
            int j =Integer.parseInt(ip_01[2].toString())-Integer.parseInt(ip_02[2].toString());
            if(j==0){
                int k =Integer.parseInt(ip_01[1].toString())-Integer.parseInt(ip_02[1].toString());
                if(k==0){
                    return Integer.parseInt(ip_01[0].toString())-Integer.parseInt(ip_02[0].toString());
                }
                return k;
            }
            return j;
        }

        return i;
    }

    public Config() {
    }

    public Config(String hostId, String poolName, String hostIp, String hostName, String hostPwd, String gateway, String netmask, String ipmiIp, String ipmiUname, String ipmiPwd, String manageNetwork, String storeNetwork, String macAddr, String systemVolume, String taskId, Progress progress, String ram) {
        this.hostId = hostId;
        this.poolName = poolName;
        this.hostIp = hostIp;
        this.hostName = hostName;
        this.hostPwd = hostPwd;
        this.gateway = gateway;
        this.netmask = netmask;
        this.ipmiIp = ipmiIp;
        this.ipmiUname = ipmiUname;
        this.ipmiPwd = ipmiPwd;
        this.manageNetwork = manageNetwork;
        this.storeNetwork = storeNetwork;
        this.macAddr = macAddr;
        this.systemVolume = systemVolume;
        this.taskId = taskId;
        this.progress = progress;
        this.ram = ram;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostPwd() {
        return hostPwd;
    }

    public void setHostPwd(String hostPwd) {
        this.hostPwd = hostPwd;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getIpmiIp() {
        return ipmiIp;
    }

    public void setIpmiIp(String ipmiIp) {
        this.ipmiIp = ipmiIp;
    }

    public String getIpmiUname() {
        return ipmiUname;
    }

    public void setIpmiUname(String ipmiUname) {
        this.ipmiUname = ipmiUname;
    }

    public String getIpmiPwd() {
        return ipmiPwd;
    }

    public void setIpmiPwd(String ipmiPwd) {
        this.ipmiPwd = ipmiPwd;
    }

    public String getManageNetwork() {
        return manageNetwork;
    }

    public void setManageNetwork(String manageNetwork) {
        this.manageNetwork = manageNetwork;
    }

    public String getStoreNetwork() {
        return storeNetwork;
    }

    public void setStoreNetwork(String storeNetwork) {
        this.storeNetwork = storeNetwork;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public String getSystemVolume() {
        return systemVolume;
    }

    public void setSystemVolume(String systemVolume) {
        this.systemVolume = systemVolume;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public String getRam() {
        return ram;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    @Override
    public String toString() {
        return "Config{" +
                "id='" + id + '\'' +
                ", hostId='" + hostId + '\'' +
                ", poolName='" + poolName + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", hostName='" + hostName + '\'' +
                ", hostPwd='" + hostPwd + '\'' +
                ", gateway='" + gateway + '\'' +
                ", netmask='" + netmask + '\'' +
                ", ipmiIp='" + ipmiIp + '\'' +
                ", ipmiUname='" + ipmiUname + '\'' +
                ", ipmiPwd='" + ipmiPwd + '\'' +
                ", manageNetwork='" + manageNetwork + '\'' +
                ", storeNetwork='" + storeNetwork + '\'' +
                ", macAddr='" + macAddr + '\'' +
                ", systemVolume='" + systemVolume + '\'' +
                ", taskId='" + taskId + '\'' +
                ", progress=" + progress +
                ", ram='" + ram + '\'' +
                '}';
    }
}
