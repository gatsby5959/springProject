package com.ezen.myProject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//create table file(
//uuid varchar(256) not null,
//save_dir varchar(256) not null,
//file_name varchar(256) not null,
//file_type tinyint(1) default 0,
//bno int,
//file_size int,
//reg_date datetime default now(),
//primary key(uuid));

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class FileVO {
	private String uuid;
	private String save_dir;
	private String file_name;
	private int file_type;
	private int bno;
	private long file_size; //bigint
	private String reg_date;
	
}
