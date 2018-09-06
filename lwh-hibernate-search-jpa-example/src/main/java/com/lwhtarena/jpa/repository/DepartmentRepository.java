package com.lwhtarena.jpa.repository;

import com.lwhtarena.jpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
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
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @QueryHints({@QueryHint(name= org.hibernate.jpa.QueryHints.HINT_CACHEABLE,value="true")})
    @Query("FROM Department d")
    List<Department> getAll();

}