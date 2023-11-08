package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHandler {
	//로거 객체 선언
	private static final Logger log = LoggerFactory.getLogger(FileHandler.class);
		
	//파일 이름과 경로를 받아서 파일을 삭제 하는 메서드
	//리턴 타입 int(isOk) => 잘 삭제했는지 체크하기 위한 변수
	public int deleteFile(String imageFileName, String savePath) {
		boolean isDel = true; //삭제가 잘 이루어지는지 체크하는 변수
		log.info("imageFileName >> "+ imageFileName);
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+File.separator+imageFileName);
		File removeThFile = new File(fileDir+File.separator+"_th_"+imageFileName);
		
		log.info("fileDir은 : " + fileDir.toString());
		log.info("removeFile은 : " + removeFile.toString());
		log.info("removeThFile은 : " + removeThFile.toString());
		
		//현재 시점에서 삭제하고자 하는 파일이 있어야(존재해야) 삭제
		if(removeFile.exists() || removeThFile.exists()) {
			isDel = removeFile.delete(); //boolean 형태로 리턴
			log.info(">>>>del: "+ (isDel?"OK":"Fail"));
			if(isDel) {
				isDel = removeThFile.delete();
				log.info(">>>> thFile del: "+ (isDel?"OK":"Fail"));
			}
		}
		log.info("remove File Ok");
		return isDel ? 1 : 0 ; //isDel은 처음에는 그냥 트루이다. 없으면 트루로 그대로 유지될듯
	}	
				
}


