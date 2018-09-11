package com.lwhtarena.pxe.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * <h2>简述：任务组</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Group {

    String id;

    String name;

    List<InstallTask> taskList =new ArrayList<InstallTask>();

}
