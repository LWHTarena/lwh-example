package com.lwhtarena.config;

import com.lwhtarena.entity.Permission;
import com.lwhtarena.mapper.PermissionMapper;
import com.lwhtarena.security.MyUserDetailService;
import com.lwhtarena.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;


/**
 * Security 配置
 */
@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private MyUserDetailService myUserDetailService;

   /**配置认证用户信息和权限**/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailService).passwordEncoder(new PasswordEncoder() {

			/**加密的密码与数据库密码进行比对**/
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				String encode = MD5Util.encode((String) rawPassword);
				encodedPassword=encodedPassword.replace("\r\n", "");
				boolean result = encodedPassword.equals(encode);
				return result;
			}

			/**对表单密码进行加密**/
			@Override
			public String encode(CharSequence rawPassword) {
				return MD5Util.encode((String) rawPassword);
			}
		});
	}

	/**配置拦截请求资源**/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<Permission> listPermission = permissionMapper.findAllPermission();
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests
				= http.authorizeRequests();
		for (Permission permission : listPermission) {
			authorizeRequests.antMatchers(permission.getUrl()).hasAnyAuthority(permission.getPermTag());
		}
		authorizeRequests.antMatchers("/login").permitAll()
				.antMatchers("/**").fullyAuthenticated().and().formLogin()
				.loginPage("/login").and().csrf().disable();
	}

	/**
	 * SpringBoot2.0抛弃了原来的NoOpPasswordEncoder,要求用户保存的密码必须使用加密算法后存储，在登录验证的时
	 * 候security会将获得的密码再进行编码后再和数据库中加密后的密码进行对比
	 *
	 * @return
	 */
//	@Bean
//	public static NoOpPasswordEncoder passwordEncoder() {
//		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}

}
