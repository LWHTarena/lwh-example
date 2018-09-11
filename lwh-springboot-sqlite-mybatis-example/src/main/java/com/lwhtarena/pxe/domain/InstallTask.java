package com.lwhtarena.pxe.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * <h2>简述：安装任务</h2>
 * <ol></ol>
 * <h2>功能描述：安装任务</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class InstallTask implements Serializable {

    /**安装任务**/
    private String taskId;

    /**安装任务**/
    private String taskName;

    /**安装步骤**/
    private int inStep; //1,2,3,4,5 在第几步

    /**一次任务的N个物理机安装配置信息**/
    private List<Config> hosts; // key: hostId  value 配置

    /**启动安装时的时间**/
    private  String createTime; //启动安装时的时间

    /**获取当前所有分布式存储的进度**/
    private Thread winStoreStatus = null;

    private int countdownSecond = -1;

    List<Config> configs =new ArrayList<Config>();

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getInStep() {
        return inStep;
    }

    public void setInStep(int inStep) {
        this.inStep = inStep;
    }

    public List<Config> getHosts() {
        return hosts;
    }

    public void setHosts(List<Config> hosts) {
        this.hosts = hosts;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Thread getWinStoreStatus() {
        return winStoreStatus;
    }

    public void setWinStoreStatus(Thread winStoreStatus) {
        this.winStoreStatus = winStoreStatus;
    }

    public int getCountdownSecond() {
        return countdownSecond;
    }

    public void setCountdownSecond(int countdownSecond) {
        this.countdownSecond = countdownSecond;
    }

    public List<Config> getConfigs() {
        return configs;
    }

    public void setConfigs(List<Config> configs) {
        this.configs = configs;
    }
}
