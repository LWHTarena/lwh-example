package com.lwhtarena.pxe.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.util.List;

/**
 * <p>
 * <h2>简述：部署任务组</h2>
 * <ol></ol>
 * <h2>功能描述：部署任务组</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */

@ApiModel(value="Task",description="部署任务")
@Entity
@Table(name = "t_task")
public class Task {

    @Id
    @GeneratedValue
    private String taskId;

    @ApiModelProperty(value="任务名",name="taskName",example="任务名")
    @Column(nullable = false)
    private String taskName;

    @ApiModelProperty(value="任务节点数",name="nodeNum",example="3")
    @Column(nullable = true)
    Integer nodeNum;

    @Column(nullable = true)
    int inStep =1;  //部署任务的第几步骤

    @Column
    private String createTime;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,mappedBy = "")
    private List<IpmiBean> ipmiBeans;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,mappedBy = "")
    private List<HostConf> hostConfs;



    public Task() {
    }

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

    public Integer getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(Integer nodeNum) {
        this.nodeNum = nodeNum;
    }

    public List<IpmiBean> getIpmiBeans() {
        return ipmiBeans;
    }

    public void setIpmiBeans(List<IpmiBean> ipmiBeans) {
        this.ipmiBeans = ipmiBeans;
    }

    public List<HostConf> getHostConfs() {
        return hostConfs;
    }

    public void setHostConfs(List<HostConf> hostConfs) {
        this.hostConfs = hostConfs;
    }

    public int getInStep() {
        return inStep;
    }

    public void setInStep(int inStep) {
        this.inStep = inStep;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
