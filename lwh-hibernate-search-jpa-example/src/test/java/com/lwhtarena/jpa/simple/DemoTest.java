package com.lwhtarena.jpa.simple;

import com.lwhtarena.jpa.entity.Department;
import com.lwhtarena.jpa.repository.DepartmentRepository;
import org.hibernate.jpa.QueryHints;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.SQLException;
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
public class DemoTest {
    private ApplicationContext ctx = null;
    private DepartmentRepository departmentRepository;
    private EntityManagerFactory entityManagerFactory;

    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        departmentRepository = ctx.getBean(DepartmentRepository.class);
        entityManagerFactory = ctx.getBean(EntityManagerFactory.class);
    }

    @Test
    public void testJpaSecondLevelCache() {
        String jpql = "FROM Department d";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(jpql);
        List<Department> departments = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();

        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        query = entityManager.createQuery(jpql);
        departments = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
        entityManager.close();
    }

    @Test
    public void testRepositorySecondLevelCache() {
        List<Department> departments = departmentRepository.getAll();
        departments = departmentRepository.getAll();
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }
}
