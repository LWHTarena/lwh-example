package com.lwhtarena.pxe.service;


import com.lwhtarena.pxe.domain.IpmiBean;

/**
 *
 * 执行IPMI命令
 * Created by yqy on 2017/9/13.
 */
public interface IpmiService {

     boolean testConnection(IpmiBean ipmiBean)throws Exception;

     String exec(IpmiBean ipmiBean, String cmd)throws Exception;

     boolean execOnByPxe(String taskId, String hostId)throws Exception;

     boolean execOnByDisk(String taskId, String hostId)throws Exception;

     boolean setOnByDisk(String taskId, String hostId)throws Exception;

     boolean execOff(String taskId, String hostId)throws Exception;

     boolean execListOff(String taskId)throws Exception;

     boolean reBootByPxe(String taskId, String hostId)throws Exception;

     boolean reBootByPxe(IpmiBean ipmiBean)throws Exception;

     boolean reBootByDisk(String taskId, String hostId)throws Exception;

     boolean reBootListByPxe(String taskId)throws Exception;

     boolean reBootListByDisk(String taskId)throws Exception;

     String getPowerState(String taskId, String hostId)throws Exception;
}
