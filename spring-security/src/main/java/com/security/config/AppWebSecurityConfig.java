package com.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
@Configuration
@EnableWebSecurity
public class AppWebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	//授权过程
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);默认授权配置
		//授权任何人都可以访问项目的首页和静态资源（?代表一个任意字符	*代表一级目录任意多个字符	**代表任意多级目录）
		http.authorizeRequests()//开始给所有的请求授权
			.antMatchers("/index.jsp", "/layui/**").permitAll()//antMatchers指定要授权的访问url地址，permitAll授权允许所有人访问
			.antMatchers("/level2/**").hasAnyRole("PM - 项目经理")
			.antMatchers("/level3/**").hasAuthority("user:add")
			.anyRequest().authenticated();//anyRequest是指除了上面页面的其他任意请求；authenticated需要被授权认证
		//http.formLogin();//启用默认的登录页面
		http.formLogin().loginPage("/index.jsp")//自定义无权请求的跳转页面
						.loginProcessingUrl("/doLogin")//指定springsecurity处理登录请求的url地址（需要和页面提交登录请求的地址一样）
						//.defaultSuccessUrl("/main.html");//登录成功后的重定向跳转地址
						.usernameParameter("loginacct")
						.passwordParameter("userpswd")
						.successHandler(new AuthenticationSuccessHandler() {//可以使用handler来处理登录成功后的跳转地址
							//自定义登录成功的跳转业务的方法	
							@Override
							public void onAuthenticationSuccess(HttpServletRequest request,
									HttpServletResponse response, Authentication authentication)
									throws IOException, ServletException {
								//request:转发
								//response:重定向
								response.sendRedirect(request.getContextPath() + "/main.html");//重定向由浏览器解析
							}
							
						});
		//http.csrf().disable();//禁用csrf功能
		http.logout()
			.logoutUrl("/user-logout")//自定义注销地址
			//.logoutSuccessUrl("/index.jsp");//设置注销后跳转的页面地址
			.logoutSuccessHandler(new LogoutSuccessHandler() {

				@Override
				public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
					//request.getRequestDispatcher("/index.jsp").forward(request, response);//请求转发由服务器解析
					response.sendRedirect(request.getContextPath() + "/index.html");
				}
				
			});
		//登录成功后访问未授权页面时的跳转【异常处理】
		//http.exceptionHandling().accessDeniedPage("/unauthed");
		http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {

			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				//获取异常信息
				String errorMsg = accessDeniedException.getMessage();
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("/WEB-INF/views/unauthed.jsp").forward(request, response);
			}
			
		});
		//记住我 简单版	需要在配置中使用http.rememberMe()开启记住我功能，提交登录请求的表单需要提供一个remeber-me的参数
		//服务器保存权限状态记住我使用的是全局上下文对象保存，当服务器重启后全局上下文对象中的数据会被销毁，记住我状态也会消失
		//http.rememberMe();//当用户登陆成功服务器会创建一个代表当前浏览器权限状态的cookies字符串将给浏览器持久化半个月
		//记住我 数据库版
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);//给操作存储remember的JdbcTokenRepositoryImpl对象提供数据库连接池
		http.rememberMe().tokenRepository(tokenRepositoryImpl);//指定服务器存储remember-me字符串的方式
		
	}
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	//Md5PasswordEncoder passwordEncoder;
	PasswordEncoder passwordEncoder;
	//权限验证：主体创建
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);默认主题创建配置
		//authorities(authorities):设置权限列表；roles(roles):设置角色列表
		/*auth.inMemoryAuthentication()
			.withUser("aaa").password("123456").roles("FIRST", "SECOND")
			.and()
			.withUser("bbb").password("123456").authorities("user:add", "user:delete");*/
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);//将自己编写的处理用户登录的主体创建的业务类对象交给springsecurity管理
	}
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*public Md5PasswordEncoder getMd5PasswordEncoder() {
		return new Md5PasswordEncoder();
	}*/
}
