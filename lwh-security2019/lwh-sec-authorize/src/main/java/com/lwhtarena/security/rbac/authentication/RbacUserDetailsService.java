package com.lwhtarena.security.rbac.authentication;


import com.lwhtarena.security.rbac.domain.Admin;
import com.lwhtarena.security.rbac.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liwh
 * @Title: RbacUserDetailsService
 * @Package com.lwhtarena.security.rbac.authentication
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 21:14
 */
@Component
@Transactional
public class RbacUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdminRepository adminRepository;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名:" + username);
        Admin admin = adminRepository.findByUsername(username);
        admin.getUrls();
        return admin;
    }

}
