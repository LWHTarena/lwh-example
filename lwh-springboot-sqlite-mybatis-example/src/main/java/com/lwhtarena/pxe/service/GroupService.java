package com.lwhtarena.pxe.service;

import com.lwhtarena.pxe.domain.Group;

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
public interface GroupService {

    int add();

    int del();

    int modify();

    List<Group> selectAll();

    List<Group> selectByCondition(Group group);

}
