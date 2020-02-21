package com.atcrowdfunding.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
@Configuration//组件注解
@EnableWebSecurity//启用web权限框架
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用全局的细粒度权限控制【支持在方法上使用springsecurity注解验证权限】
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	//授权的所有路径都是针对浏览器访问，授权的所有地址都是controller中的映射地址
	//授权
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//1.首页、登录页面和静态资源放行
		http.authorizeRequests()
			.antMatchers("/","/index","/default" , "/index.html","/login.html" ,"/static/**").permitAll()//允许所有人访问
			.anyRequest().authenticated();//其他任意请求都需要授权认证
		//2.配置登录表单
		http.formLogin()
			.loginPage("/login.html")//登录页面
			.loginProcessingUrl("/admin/doLogin")//处理登录请求的url【登录表单只要提交请求的url和配置一样，springsecurity会自动处理该请求
			.usernameParameter("loginacct")//登录账号的参数nanme属性值
			.passwordParameter("userpswd")//登录密码的参数name属性值
			.defaultSuccessUrl("/admin/main.html");//登录成功重定向的地址
		//3.配置注销【注销处理路径、注销成功的跳转地址】
		http.logout()
			.logoutUrl("/admin-logout")
			.logoutSuccessUrl("/login.html");
		//4.禁用csrf功能
		http.csrf().disable();
		//5.登陆后访问未授权页面时的异常处理
		http.exceptionHandling()
			.accessDeniedHandler(new AccessDeniedHandler() {

				@Override
				public void handle(HttpServletRequest request, HttpServletResponse response,
						AccessDeniedException accessDeniedException) throws IOException, ServletException {
					//需要区分请求是同步还是异步的
					if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
						//异步请求
						response.getWriter().write("权限不足");
					}else {
						//同步请求
						request.setAttribute("errorMsg", accessDeniedException.getMessage());
						request.getRequestDispatcher("/WEB-INF/pages/error/403.jsp").forward(request, response);
					}
				}
				
			});
	}
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	PasswordEncoder passwordEncoder;
	//创建主体
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//配置主体信息创建的userDetailsService的对象，和配置密码处理器
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	public PasswordEncoder getPswdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
