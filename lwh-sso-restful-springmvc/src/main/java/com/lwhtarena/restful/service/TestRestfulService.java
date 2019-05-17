package com.lwhtarena.restful.service;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public interface TestRestfulService {

    String queryUserById(Long id);

    void saveUser(String user);

    void updateUser(String user);

    void deleteUserById(Long id);

}
