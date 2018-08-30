package com.lwhtarena.zero.web.dao.mapper;

import com.lwhtarena.zero.web.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：用户数据映射</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public interface UserMapper {

    @Select(value="select username,password,enabled from users where username = #{username}")
    User loadUserByUsername(@Param("username") String username);

    @Insert(value="insert into users (username, password, enabled, create_date) value(#{username},#{password},#{enabled},#{createDate})")
    void saveUser(User user);
}
