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
                
                //등록 성공하면 화면에 뿌리기      
                getCommentList(bnoVal);         
            }
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





//얘는 댓글들 뿌리는 얘
function getCommentList(bno){
    spreadCommentListFromServer(bno).then(result=>{
        console.log(result);

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
       if(result.length>0){
            ul.innerHTML="";
            for(let cvo of result){
                let li = `<li class="list-group-item" data-cno=${cvo.cno} data-writer=${cvo.writer}><div  class = "mb-3">`//
                li+=`<div class= "fw-bold">${cvo.writer}</div>`;//
                li+=`<div>${cvo.content}<div>`;
                li+=`<span class="badge rounded-pill text-bg-dark">${cvo.modAt}</span>`;
                // if(sesId == cvo.writer){
                li+=`<button type="button" class="modBtn" data-bs-toggle="modal" data-bs-target="#myModal">%댓글수정%</button>`;
                li+=`<button type="button" class="delBtn">X댓글삭제X</button>`;
                // }
                li+=`</li>`;
                ul.innerHTML += li;
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






async function spreadCommentListFromServer(bno){
    try {
        //얘는 받는것
        const resp = await fetch('/comment/'+bno);  //bno만달고  서버(java파일컨트롤러 로)갈것임
        const result = await resp.json();
        console.log("async스프레드함수(spreadCommentListFromServer)의 결과 == 컨트롤러의 결과 >>> "+result); //
        return result;
    } catch (err) {
        console.log(err);
    }
}  





document.addEventListener('click',(e)=>{
    console.log(e.target);
        if(e.target.classList.contains('modBtn')){
            //수정작업
            console.log('수정버튼 클릭~!!');


        }else if(e.target.classList.contains('delBtn')){
            //삭제작업
            console.log('삭제버튼 클릭됨~!!');
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
                    }else{
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