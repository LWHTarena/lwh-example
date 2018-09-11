package com.lwhtarena.pxe.service;

import com.lwhtarena.pxe.domain.InstallTask;
import com.lwhtarena.pxe.domain.MsgVo;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/18.
 */
public interface HostService {

    public void startWinStoreStatus(final InstallTask task);

    public Map<String,Integer> getWinStoreStatus(InstallTask task);

    public void startPoolHostsStatus(final InstallTask task, final String hostId, final boolean hasInit);

    public void initPoolHosts(final InstallTask task, final String hostId);

    public void modifyToByDisk(String taskId);

    void reInit(InstallTask task, MsgVo msgVo);
}
