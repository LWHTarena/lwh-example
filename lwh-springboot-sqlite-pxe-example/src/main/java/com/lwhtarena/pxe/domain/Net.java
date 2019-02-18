package com.lwhtarena.pxe.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
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
@ApiModel(value = "Net对象",description = "本地虚拟机WIN服务虚拟机网卡信息")
@Entity
@Table(name = "t_net")
public class Net implements Serializable {

    @Id
    @GeneratedValue
    private String id;

    @ApiModelProperty(value="通信ip",name="ip",example="192.168.222.10")
    @Column(nullable = false)
    private String ip;      //通信ip

    @ApiModelProperty(value="掩码",name="netmask",example="255.255.255.0")
    @Column(nullable = false)
    private String netmask; //掩码

    @ApiModelProperty(value="网关",name="gateway",example="192.168.222.1")
    @Column(nullable = false)
    private String gateway;  //网关

    /**
     * referencedColumnName：参考列名,默认的情况下是列表的主键
     * nullable=是否可以为空，
     * insertable：是否可以插入，
     * updatable：是否可以更新
     * columnDefinition=列定义，
     */
    @ApiModelProperty(value="任务",name="net")
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "task_id",referencedColumnName = "taskId",nullable = false)
    private Task task;

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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
