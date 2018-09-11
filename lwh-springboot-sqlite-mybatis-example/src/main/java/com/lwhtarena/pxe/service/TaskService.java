package com.lwhtarena.pxe.service;

import com.lwhtarena.pxe.domain.Group;
import com.lwhtarena.pxe.domain.InstallTask;

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
public interface TaskService {

    int add();

    int del();

    int modify();

    List<InstallTask> selectAll();

    List<InstallTask> selectByCondition(InstallTask it);

    InstallTask selectByEntity(InstallTask it);

}
