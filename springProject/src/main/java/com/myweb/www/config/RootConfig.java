package com.myweb.www.config;


import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration //난 컴피그 입니다 표시 (근데 웹 컨피그는 안붙임)
@MapperScan(basePackages = {"com.myweb.www.repository"})   //어디서 스켄할까요? 여기서 스켄해가지고 올께요
@ComponentScan(basePackages= {"com.myweb.www.service"})
public class RootConfig {
	//DB설정 부분
	//이전과 달라진 부분 log4jdbc-log4j2 사용
	//hikariCP 사용
	
	@Autowired
	ApplicationContext applicationContext; 

	@Bean
	public DataSource dataSource(){
		HikariConfig hikariConfig = new HikariConfig(); //히카리
		//log4jdbc-log4j2의 드라이버 클래스 url 사용
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springdb");//DB랑 유저 등
		hikariConfig.setUsername("springUser");
		hikariConfig.setPassword("mysql");
		
		hikariConfig.setMaximumPoolSize(5); //최대 유휴 커넥션 개수 (항상 이 위아래 2개는 같이 설정 그래야 성능 최적화)
		hikariConfig.setMinimumIdle(5);	//최소 유휴 커넥션 개수???  (항상 이 위아래 2개는 같이 설정 그래야 성능 최적화) 최소로 여유 5개
	
		hikariConfig.setConnectionTestQuery("SELECT now()"); //테스트쿼리 test
		hikariConfig.setPoolName("springHikariCP");
		
		//config의 추가 설정
		//cache 사용여부 설정 (true 속도상승)
		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		//mysql 드라이버가 연결당 cache statement의 수에 관한 설정 디폴트는25   근데    250 ~ 500 사이 권장
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
		//mysql connection당 캐싱할 preparedStatement의 개수 지정 옵션 default256   트루하면 256잡힘
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "true" );
		//mysql 서버에서 최신 이슈가 있을 경우 지원을 받는 설정 true
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts","true");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		return hikariDataSource;
		
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		sqlFactoryBean.setDataSource(dataSource());
		
		sqlFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/*.xml"));
		
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/MybatisConfig.xml"));
		
		return (SqlSessionFactory)sqlFactoryBean.getObject();
	}

}
