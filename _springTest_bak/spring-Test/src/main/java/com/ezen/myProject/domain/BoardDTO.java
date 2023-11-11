package com.ezen.myProject.domain;

import java.util.List;

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
public class BoardDTO {

	private BoardVO bvo; //서비스가 다 알아서 하고 컨트롤러는 나중에 취합해서 보내?주는 역할
	private List<FileVO> flist;

}
