package com.lwhtarena.config;

import com.lwhtarena.handler.MyAuthenticationFailureHandler;
import com.lwhtarena.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * Security 配置
 */
@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private MyAuthenticationFailureHandler failureHandler;
  @Autowired
  private MyAuthenticationSuccessHandler successHandler;

   /**配置认证用户信息和权限**/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("123456").authorities("addOrder");

		// 添加admin账号
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password("123456").authorities("showOrder","addOrder","updateOrder","deleteOrder");

		// 添加userAdd账号
		auth.inMemoryAuthentication()
				.withUser("userAdd")
				.password("123456").authorities("showOrder","addOrder");

		/**如果想实现动态账号与数据库关联 在该地方改为查询数据库**/

	}

	/**配置拦截请求资源**/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/**
		 * 如何权限控制 给每一个请求路径 分配一个权限名称 让后账号只要关联该名称，就可以有访问权限
		 */
		http.authorizeRequests()
//				.antMatchers("/**").fullyAuthenticated().and().formLogin();
		/**配置查询订单权限**/
		.antMatchers("/showOrder").hasAnyAuthority("showOrder")
		.antMatchers("/addOrder").hasAnyAuthority("addOrder")
		.antMatchers("/login").permitAll()
		.antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
		.antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
		.antMatchers("/**").fullyAuthenticated()
				.and().formLogin().loginPage("/login")
				.successHandler(successHandler)
				.failureHandler(failureHandler)
				.and().csrf().disable();
	}

	/**
	 * SpringBoot2.0抛弃了原来的NoOpPasswordEncoder,要求用户保存的密码必须使用加密算法后存储，在登录验证的时
	 * 候security会将获得的密码再进行编码后再和数据库中加密后的密码进行对比
	 *
	 * @return
	 */
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

}
