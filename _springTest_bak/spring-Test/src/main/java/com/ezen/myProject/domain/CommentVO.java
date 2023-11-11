package com.ezen.myProject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {

//	CREATE TABLE comment (
//			  cno int NOT NULL AUTO_INCREMENT,
//			  bno int NOT NULL,
//			  writer varchar(100) ,
//			  content varchar(500) ,
//			  regdate datetime DEFAULT CURRENT_TIMESTAMP,
//			  PRIMARY KEY (cno)
//			); 
	
	private int cno;
	private int bno;
	private String writer;
	private String content;
	private String regdate;
	
	
	
	
}
