/**
 * 
 */
console.log("boardComment.js진입");
console.log("bnoVal은"+bnoVal);


document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    console.log("cmtPostBtn 눌렸어요~")
    const cmtText = document.getElementById('cmtText').value;
    const cmtwriter = document.getElementById('cmtWriter').innerText; //span안에 있으면 이너텍스트로...
    if(cmtText==""||cmtText == null){
         alert("댓글이 없습니다 입력해주세요");
         document.getElementById('cmtText').focus(); // 커서 깜빡이게...
         return false;
     } else{
         let cmtData ={
             bno :bnoVal,
             writer : cmtwriter,
             content : cmtText
         };
         console.log("boardComment.js에서 만든 let cmtData >>>>> " +cmtData);
        postCommenttoServer(cmtData).then(result=>{ //프론트 -<< 백->에서 받음 3
            console.log(result);
            // isOk확인
            if(result == 1){
                alert('댓글 등록 성공~!!');
                cmtText.value='';   
            }
              //등록 성공하면 화면에 뿌리기      
              getCommentList(bnoVal);   
        })
    }
})


async function postCommenttoServer(cmtData){
    try{
        const url = "/comment/post"; //서버? java controller 쪽으로 보내기?
        const config = {
            method : "post",
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config);      //얘가 디비에서 데이터를 받음
        const result = await resp.text(); //isOk    //얘가 디비에서 데이터를 받음
        return result;
    }catch(err){
        console.log(err);
    }
}//등록?





//얘는 댓글들 뿌리는 얘  무조건 뿌릴때는 첫페이지1로
function getCommentList(bno, page=1){
    console.log("getCommentList함수의 page는 " + page); //값이 넘어오면 위의 =1의 무시됨 3이면 3들어옴
    spreadCommentListFromServer(bno, page).then(result=>{
        console.log(result); //ph 객체 pgvo, totalCount, cmtList 
        //화면에 뿌리기
        /*
        <li class="list-group-item">
            <div class = "mb-3">
                <div class= "fw-bold">Writer</div>
                Content
            </div>
            <span class="badge rounded-pill text-bg-dark">modAt</span>
	    </li>
        */
       const ul = document.getElementById('cmtListArea');
       if(result.cmtList.length > 0){
            //다시 댓글을 뿌릴 대 기존 값 삭제 1page일 경우만.
            if(page==1){ //기존것을 날리는 듯
                ul.innerHTML="";
            }
            for(let cvo of result.cmtList){ //기존에 추가되는듯
                let li = `<li class="list-group-item" data-cno="${cvo.cno}">`; 
                li+=`<div class="mb-3">`; 
                li+=`<div class= "fw-bold">${cvo.writer}</div>`;//
                li+=`${cvo.content}`;
                li+=`</div>`;
                li+=`<span class="badge rounded-pill text-bg-dark">${cvo.modAt}</span>`;
                li+=`<button type="button" class="btn btn-outline-warning mod" data-bs-toggle="modal" data-bs-target="#myModal">%댓글수정mod%</button>`;
                li+=`<button type="button" class="btn btn-outline-danger delBtn">X댓글삭제delBtnX</button>`;
                li+=`</li>`;
                ul.innerHTML += li;
            }

            //댓글 페이징 코드
            let moreBtn = document.getElementById('moreBtn');
            //db에서 pgvo + list 같이 가져와야 값을 줄 수 있음.
            if(result.pgvo.pageNo < result.endPage || result.next){
                moreBtn.style.visibility = 'visible'; //버튼 표시
                moreBtn.dataset.page = page + 1;
            }else{
                moreBtn.style.visibility='hidden'; //버튼 숨김
            }

       }
       else{
        let li = `<li>Comment List Empty</li>`
        ul.innerHTML = li;
       }
        // let div =document.getElementById('cmtListArea');
        // div.innerHTML="";
        // for(let i = 0; i<result.length; i++){
        //     let str = `<li>`
        //     str += `<div>`
        //     str += `<div>댓글번호: ${result[i].cno}</div>`;
        //     str += `<div>댓글작성자: ${result[i].writer}</div>`;
        //     str += `댓글내용: ${result[i].content}`;
        //     str += `</div>`
        //     str += `<span>댓글날짜 ${result[i].regdate}</span>`
        //     str += `</li>`
        //     div.innerHTML+= str; // 누적해서 담기
        // }
    })
}






async function spreadCommentListFromServer(bno, page){
    try {
        //얘는 받는것
        const resp = await fetch('/comment/'+bno+'/'+page);  //bno만달고  서버(java파일컨트롤러 로)갈것임
        const result = await resp.json();
        console.log("async스프레드함수(spreadCommentListFromServer)의 결과 == 컨트롤러의 결과 >>> "+result); //
        return result;
    } catch (err) {
        console.log(err);
    }
}  





document.addEventListener('click',(e)=>{
    console.log(e.target);
        if(e.target.classList.contains('mod')){
            //수정작업
            console.log('댓글수정버튼 클릭~!!');
            let li = e.target.closest('li');
            //nextSibling() : 같은 부모의 다음 형제 객체를 반환
            let cmtText = li.querySelector('.fw-bold').nextSibling; //반환이 안되는듯함
 
            console.log('댓글수정버튼 클릭4!! '+cmtText.nodeValue); //null이넹 
            //기존내용 모달창에 반영(수정하기 편하게~)
            document.getElementById('cmtTextMod').value = cmtText.nodeValue;
            //cmtModBtn에 data-cno달기
            document.getElementById('cmtModBtn').setAttribute('data-cno',li.dataset.cno);
        }else if(e.target.id == 'cmtModBtn'){
            console.log("댓글의 모달창에서 포스트 버튼 클릭~! 수정하겠다는 뜻")
            let cmtDataMod={
                cno : e.target.dataset.cno,
                content : document.getElementById('cmtTextMod').value
            }
            console.log(cmtDataMod);
            editCommentToServer(cmtDataMod).then(result => {
                console.log("모달창 닫기 시작1");
                if(parseInt(result)){
                    
                    //모달창 닫기
                    console.log("모달창 닫기 시작2");
                    document.querySelector('.btn_close').click(); //닫기버튼 클릭하면 닫기
                    getCommentList(bnoVal);
                }
                getCommentList(bnoVal);
            })
        }

        else if(e.target.id == 'moreBtn'){
            console.log("모어버튼 클릭 moreBtn");
            console.log("bnoVal /  e.target.dataset.page " + bnoVal+" / "+ e.target.dataset.page )
            getCommentList(bnoVal, parseInt(e.target.dataset.page));
        }


        else if(e.target.classList.contains('delBtn')){
            //삭제작업
            console.log('디테일페이지에서 의 댓글삭제버튼 클릭됨~!!');
            //내가 선택한 타겟과 가장 가까운 li찾기
            let li = e.target.closest('li'); 
            let cnoVal = li.dataset.cno;
            let writerVal = li.dataset.writer;
    
            //  console.log("세션아이디"+ sesId);
            //  console.log("댓글 작성아이디"+ writerVal);
            // if(sesId == writerVal){
                //서버연결
                removeCommentToServer(cnoVal,writerVal).then(result=>{
                    console.log("댓글삭제removeCommentToServer함수 진입 리절트값은 "+ result);
                    if(result == 1){
                        alert('댓글 삭제 성공~~~~~~');
                        getCommentList(bnoVal);
                    }
                    else{
                        alert('어쩃든 댓글 삭제 실패함~!!!!!');
                    }
        // }
        })
    }
})


async function removeCommentToServer(cno, writer){
    try {
        const url = '/comment/'+cno+'/'+writer;
        const config = {
            method:'delete'
        };
        const resp = await fetch(url,config); // 또 컨트롤러레 갔다가 값을 받아오기~
        const result = await resp.text(); // 받아온값을 result에 넣기
        console.log('async function removeCommentToServer(cno)실행중~~' + result);
        return result;

    } catch (err) {
        console.log(err);
    }
}





async function editCommentToServer(cmtDataMod){
    console.log("async function editCommentToServer(cmtDataMod) 진입");
try {
    const url = '/comment/'+cmtDataMod.cno;
    const config = {
        method:'put',
        headers:{
            'Content-Type':'application/json; charset=utf-8' 
        },
        body: JSON.stringify(cmtDataMod)
    };
    const resp = await fetch(url, config);  //얘가 디비에서 데이터를 받음(보내자마자)
    const result = await resp.text();
    return result;
} catch (error) {
    console.log(err);
}


}