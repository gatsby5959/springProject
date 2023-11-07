package com.myweb.www.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class AuthVO {

//	create table auth_member(
//	email varchar(100) not null,
//	auth varchar(50) not null,
//	primary key(email));

	private String email;
	private String auth;
	
	
}
