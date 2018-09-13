package com.lwhtarena.pxe.domain;

import com.sun.javaws.progress.Progress;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 * <h2>简述：超融合自动化部署配置</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */

@ApiModel(value="HostConf",description="超融合物理机配置")
@Entity
@Table(name = "t_host")
public class HostConf implements Serializable,Comparable<HostConf>{

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

    /**
     * 本地ISO库大小（单位GB）
     */
    private String isoSize;

    /**
     * dom0根目录大小（单位GB）
     */
    private String dom0RootSize;

    /**
     * dom0日志目录大小（单位GB）
     */
    private String dom0LogSize;

    /**
     *  是否忽略
     *  默认不忽略
     */
    private int ignoreState = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;


    public int getIgnoreState() {
        return ignoreState;
    }

    public void setIgnoreState(int ignoreState) {
        this.ignoreState = ignoreState;
    }

    /***
     * 然后实现 compareTo（）方法，该方法的返回值0代表相等，1表示大于，-1表示小于
     * @param o
     * @return
     */
    @Override
    public int compareTo(HostConf o) {
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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getIsoSize() {
        return isoSize;
    }

    public void setIsoSize(String isoSize) {
        this.isoSize = isoSize;
    }

    public String getDom0RootSize() {
        return dom0RootSize;
    }

    public void setDom0RootSize(String dom0RootSize) {
        this.dom0RootSize = dom0RootSize;
    }

    public String getDom0LogSize() {
        return dom0LogSize;
    }

    public void setDom0LogSize(String dom0LogSize) {
        this.dom0LogSize = dom0LogSize;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
