document.getElementById('trigger').addEventListener('click',()=>{//이게 눌리면
    document.getElementById('file').click(); //이게 눌리는 효과를 줘라
})

//정규 표현식을 사용하여 파일 형식 제한 함수 만들기
//실행파일 막기, 이미지 파일만 체크, 50M 사이즈보다 크면 막기
const regExp = new RegExp("\.(exe|sh|bat|mis|dll|jar)$"); //쩜으로 시작하면서  이런 파일들은 안보이게.. 이건 막아야지
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif|bmp)$"); //이미지 파일 패턴 이건 OK어야함
const maxSize = 1024*1024*20; //20메가 보다 큰지 작은지 

//리턴 값은 0과 1로 하겠습니다.
function fileValidation(fileName, fileSize){
    if(regExp.test(fileName)){ //실행파일이면
        return 0 ;
    }else if(fileSize>maxSize){
        return 0;
    }else if(!regExpImg.test(fileName)){
        return 0; //이미지만 넣을거임 이미지 아니면 0리턴
    }else{
        return 1; //이미지 파일이면서 크기가 20mb이하임  1
    } //파일에 대한 벨리데이션을 만듬
}


//첨부파일에 따라서 파일이 등록 가능한지 체크 함수
document.addEventListener('change',(e)=>{
    document.getElementById('regBtn').disabled = false; //한번 트루된것은 다시 false로위해서 씀
    console.log('e.target입니다: '+e.target);
    if(e.target.id == 'file'){
        const fileObject = document.getElementById('file').files; //여러개의 파일이 배열로 들어옴
        console.log('fileObject입니다: '+ fileObject);
        console.log(fileObject);
        let div = document.getElementById('fileZone');
        div.innerHTML='';
        let ul = `<ul>`;
        let isOk = 1; //fileValidation 함수의 리턴여부를 *로 체크 하나라도 리턴을 0을 리턴하면 모든 파일은 다 못들어감 그래서 곱하기처리
        for(let file of fileObject){
            let validResult = fileValidation(file.name, file.size); //현재가 업로드 가능한지 확인
            isOk *= validResult;    //모든 파일이 누적되어 *   //모든 파일이 업로드 가능한지 확인
            ul+= `<li>`; //li<div> 업로드 가능//불가능 </div> 
            //업로드 가능 여부 표시
            ul+= `${validResult? '<div>업로드 가능' : '<div>업로드 불가능'}</div>`;
            ul+= `${file.name}`;
            // ul+= `<span>${file.size}Byte</span>`;
            ul+= `<span class="badge rounded-pill text-bg-${validResult? 'success':'danger'}">${file.size}>Byte</span></li>`;
        }
        ul += `</ul>`; //누적은 여기서 이미완료
        div.innerHTML = ul; //그래서 걍 적음
        if(isOk == 0){//첨부 불가능한 파일이 있다는 것을 의미
            document.getElementById('regBtn').disabled = true; //버튼 활성화
        }
    }
})

//ul->li 여러개 생성
//li<div> 업로드 가능//불가능 </div> 
//파일이름 
//<span>파일사이즈</span>