package com.myweb.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.myweb.www.security.CustomAuthMemberService;
import com.myweb.www.security.LoginFailureHandler;
import com.myweb.www.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//security package를 생성하여 사용자 핸들러 생성
	
	//비밀번호 암호화 객체 빈 생성
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//SuccessHandler 빈 객체 생성 => 사용자가 생성
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new LoginSuccessHandler();
	}
	
	//failureHandler 빈 객체 생성 => 사용자가 생성
	@Bean
	public AuthenticationFailureHandler authFailureHandler() {
		return new LoginFailureHandler();
	}
	
	//userDetail 빈 객체 생성 => 사용자가 생성
	@Bean
	public UserDetailsService customUserService() {
		return new CustomAuthMemberService();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(auth);
		//로그인 한 값을 암호화하여 처리할것임
		auth.userDetailsService(customUserService())
		.passwordEncoder(bcPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(http);
		http.csrf().disable();  //요건 나중에 설명해줄꼐용 쌤曰
		//승인요청
		http.authorizeRequests()
		.antMatchers("/member/list").hasRole("ADMIN")                                                                          //아래는 그냥 아무나 접근 가능한 페이지들 일단 보드 레지스터 풀어둠 나중에 막을것임
		.antMatchers("/","/board/list","/board/detail","/resources/**","/upload/**","/comment/**","/member/register","/member/login" ,"/board/register").permitAll()
		.anyRequest().authenticated(); //나머지 리퀘스트는 인증된 사용자만 사용할수 있게           //비회원도 되는것이 PermitAll()   //나중에 comment/**할때는 퍼미션으로 가면 됨 post modify등
		// '맴버의 리스트'는 ADMIN만 보이게...
		
		
		//유저네임을 이메일로 할께~
		//커스텀 로그인 페이지를 구성   (로그인은 어떻게 할건지 설정)
		// Controller의  주소요청 맵핑도 같이 꼭 적어줘야함(.loginPage("/member/login"))
		http.formLogin()
		.usernameParameter("email")
		.passwordParameter("pwd")
		.loginPage("/member/login")
		.successHandler(authSuccessHandler())
		.failureHandler(authFailureHandler());
		
		//로그아웃 페이지(로그아웃은 어떻게 할건지 설정)       반드시 method="post"
		http.logout()
			.logoutUrl("/member/logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/");
		
	}
	
}










