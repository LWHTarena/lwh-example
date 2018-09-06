package com.lwhtarena.jpa.service;

import com.lwhtarena.jpa.entity.Employee;
import com.lwhtarena.jpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void delete(Integer id){
        employeeRepository.delete(id);
    }

    @Transactional(readOnly=true)
    public Employee get(Integer id){
        return employeeRepository.findById(id).get();
    }

    @Transactional
    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setCreateTime(new Date());
        }
        employeeRepository.saveAndFlush(employee);
    }

    @Transactional(readOnly=true)
    public Employee getByLastName(String lastName){
        return employeeRepository.getByLastName(lastName);
    }

    @Transactional(readOnly=true)
    public Page<Employee> getPage(int pageNo, int pageSize){
        PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
        return employeeRepository.findAll(pageable);
    }
}
