package com.lwhtarena.jpa.repository;

import com.lwhtarena.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

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
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 查询用户名称包含username字符串的用户对象
     * @param username
     * @return
     */
    List<User> findByUsernameContaining(String username);

    /**
     * 获得单个用户对象，根据username和pwd的字段匹配
     * @param username
     * @param pwd
     * @return
     */
    User getByUsernameIsAndUserpwdIs(String username,String pwd);

    /**
     * 精确匹配username的用户对象
     * @param username
     * @return
     */
    User getByUsernameIs(String username);

    @Transactional(readOnly = false)
    int deleteByUser(User user);
}
