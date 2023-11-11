package com.ezen.myProject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	
//	create table board(
//			bno int not null auto_increment,
//			title varchar(200),
//			content text,
//			writer varchar(100),
//			isDel varhcar(10) default 'N',
//			registerDate datetime default now(),
//			read_count int,
//			primary key(bno));

	private int bno;
	private String title;
	private String content;
	private String writer;
	private String isDel;
	private String registerDate;
	private int read_count;
	private int commentCount; //231013추가
	private int fileCount; //231013추가
	

}
