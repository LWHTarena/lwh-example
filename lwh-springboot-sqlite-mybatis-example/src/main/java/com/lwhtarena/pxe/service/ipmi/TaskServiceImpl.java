package com.lwhtarena.pxe.service.ipmi;

import com.lwhtarena.pxe.domain.InstallTask;
import com.lwhtarena.pxe.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {

    public static final Logger infoLog = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Override
    public int add() {
        return 0;
    }

    @Override
    public int del() {
        return 0;
    }

    @Override
    public int modify() {
        return 0;
    }

    @Override
    public List<InstallTask> selectAll() {
        return null;
    }

    @Override
    public List<InstallTask> selectByCondition(InstallTask it) {
        return null;
    }

    @Override
    public InstallTask selectByEntity(InstallTask it) {
        return null;
    }
}
