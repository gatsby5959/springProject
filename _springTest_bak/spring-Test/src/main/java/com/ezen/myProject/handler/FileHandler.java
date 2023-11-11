package com.ezen.myProject.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.myProject.domain.FileVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;


@Slf4j
@AllArgsConstructor
@Component		//내가 만든 객체는 컴포넌트 붙임        //이미 만들어진것은 bean붙임
public class FileHandler {

	//내파일 (업로드)기본경로 설정
	private final String UP_DIR = "D:\\_myweb\\_java\\fileupload"; //업로드 되는 경로
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		//멀티파트 파일 객체를 받아서 fileVO형태로 저장 한 후
		//오늘 날짜 경로(가변형태로 자동생성(있으면 그냥 사용) /  실제 파일을 해당 경로에 저장(이건내가만듬 원본경로)
		//fileVO를 List에 추가 => return list;
		
		//오늘 날짜 경로 생성
		LocalDate date = LocalDate.now();
		log.info(">>> date : " + date);
		String today = date.toString();	//2023-10-23 String으로 변환
		// 2023\\10\\13 String 생성
		today = today.replace("-", File.separator);
		
		//오늘날짜(today)의 폴더 구성        서브폴더
		File folders = new File(UP_DIR, today);
		if(!folders.exists()) { //해당하는 폴더가 없다면
			folders.mkdirs();	//폴더 생성 명령
		}
		
		//리스트 설정    2번째 글자가 대문자를 인식을 못함
		List<FileVO> flist = new ArrayList<FileVO>();
		//여러개의 파일 중 순서대로 하나의 파일 가져오기
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSave_dir(today);  //공통 튀쪽에 오늘 날짜 경로만 set
			fvo.setFile_size(file.getSize()); //파일사이즈는 return이 Long    그냥 롱으로 설정할것 안그러면 짤리고 여튼 복잡해짐
			
			//파일 이름 설정(OriginalFilename()설정)
			//originalFileName : 파일 경로를 포함하고 있을 수 있음.
			log.info(">>>> getName "+file.getName()); // 파일 객체의 종류
			log.info(">>>> oriName "+file.getOriginalFilename());	//이름
			String originalFileName = file.getOriginalFilename();
			String onlyFileName= originalFileName.substring(
											originalFileName.lastIndexOf(File.separator)+1); //실파일명만 추출 (마지막에 뭐 있따면 잘라서)
		
			log.info(">>onlyFileName>> " + onlyFileName);
			fvo.setFile_name(onlyFileName); //파일 이름 설정
			
			//UUID 생성  
			UUID uuid = UUID.randomUUID();
			//uuid는 uuid의 객체 이므로 .toString으로 변경후 저장
			log.info(">>> uuid >> " + uuid.toString());
			fvo.setUuid(uuid.toString()); 
			//<----------------------여기까지 fvo 설정 완료 fvo setting-------------------------->   <!--이제 파일?에 저장해야함-->
			
			//이제 디스크에 저장할 파일 객체 생성하고----> 이후 저장 해야함
			//실제 저장할 떄는 uuid를 붙여서 _ 붙여서 파일 이름 구분 -->  uuid_파일네임
			String fullFileName = uuid.toString()+"_"+onlyFileName; // 예를 들어.. 9a92aa2d-41bb-4b8f-a314-a382d46d52e0_dak.jpg
			File storeFile = new File(folders, fullFileName);
			
			//저장 => 폴더가 없으면 저장이 안되기 때문에 ioException가 필수 발생함 그래서 트라이 케치항상 하기
			try {
				file.transferTo(storeFile); //원본 객체를 저장을 위한 형태로 변경 후 복사
				//파일 타입을 결정 => 이미지 파일이라면 썸네일 생성
				if(isImageFile(storeFile)) {
					fvo.setFile_type(1);
					//uuid_th_파일네임
					File thumNail = new File(folders,
										uuid.toString()+"_th_"+onlyFileName);
					Thumbnails.of(storeFile).size(75,75).toFile(thumNail);
				}	
			} catch (Exception e) {
				// TODO: handle exception
				log.info(">> file생성 오류");
				e.printStackTrace();
			}
			
			flist.add(fvo); //여기에 해당 파일이 담겼음
			
		}
		//...
		return flist;
	}
	
	
	//tika를 사용하여 파일 형식 체크 -> 이미지 파일이 맞는지 확인
	public boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile); // image/jpg, image/png 파일 추가할때 브라우저에 들어온 값을 확인
		return mimeType.startsWith("image")? true : false;
	}
	
	
}//end of class
