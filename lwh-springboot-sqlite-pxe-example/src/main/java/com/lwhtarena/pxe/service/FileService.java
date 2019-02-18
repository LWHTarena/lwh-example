package com.lwhtarena.pxe.service;


//import com.lwhtarena.pxe.domain.Config;
import com.lwhtarena.pxe.domain.MsgVo;
import com.lwhtarena.pxe.domain.Net;

import java.util.List;

public interface FileService {

    /***
     * 传入参数创建并修改文件，开始安装winserver
     * @param configs 传入配置文件list集合
     * @return
     */
//    MsgVo createAndModify(List<Config> configs) throws Exception;

    /**
     * 创建文件
     * @param taskId
     * @param hostId
     * @return
     * @throws Exception
     */
//    MsgVo createFile(String taskId, String hostId) throws Exception;

    /**
     * 删除文件
     * @param taskId
     * @param hostId
     * @return
     * @throws Exception
     */
//    MsgVo delFile(String taskId, String hostId) throws Exception;

    /**
     * 修改dhcpd.conf
     * @param net
     * @return
     */
    boolean modifyLocalServce(Net net);

    public Net gatherInfo();
}
