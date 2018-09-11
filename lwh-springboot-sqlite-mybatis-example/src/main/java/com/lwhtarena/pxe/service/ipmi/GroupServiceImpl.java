package com.lwhtarena.pxe.service.ipmi;

import com.lwhtarena.pxe.domain.Group;
import com.lwhtarena.pxe.service.GroupService;
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

@Service("groupService")
public class GroupServiceImpl implements GroupService {

    public static final Logger infoLog = LoggerFactory.getLogger(GroupServiceImpl.class);

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
    public List<Group> selectAll() {
        return null;
    }

    @Override
    public List<Group> selectByCondition(Group group) {
        return null;
    }
}
