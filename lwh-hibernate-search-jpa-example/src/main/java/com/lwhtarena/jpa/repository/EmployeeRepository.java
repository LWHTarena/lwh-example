package com.lwhtarena.jpa.repository;

import com.lwhtarena.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {

    Employee getByLastName(String lastName);

    void delete(Integer id);
}
