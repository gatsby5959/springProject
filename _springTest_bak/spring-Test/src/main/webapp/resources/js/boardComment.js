
console.log(bnoVal);
console.log("jsp에서 javascript로 쏴준 작성자명 "+  writerVal);
console.log("sesId는  " + sesId );
// console.log("commentwriterVal(댓글작성자)는 "+  commentwriterVal );
// commentwriterVal = `<c:out value = "${cvo.writer}"/>`;
// console.log("댓글작성자)는 "+  ${cvo.writer} );

async function postCommenttoServer(cmtData){
    try{
        const url = "/comment/post"; //보내기?
        const config = {
            method : "post",
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config);
        const result = await resp.text(); //isOk
        return result;
    }catch(err){
        console.log(err);
    }
}//등록?





document.getElementById('cmtPostBtn').addEventListener('click',()=>{
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
        console.log(cmtData);
        postCommenttoServer(cmtData).then(result=>{
            console.log(result);
            //isOk확인
            if(result == 1){
                alert('댓글 등록 성공~!!');
                
                //화면에 뿌리기      
                getCommentList(bnoVal);         
            }
        })
    }
})




async function spreadCommentListFromServer(bno){
    try {
        //얘는 받는것
        const resp = await fetch('/comment/'+bno);  //bno만달고 갈것임
        const result = await resp.json();
        console.log("스프레드의 결과입니당 "+result);
        return result;
    } catch (err) {
        console.log(err);
    }
}  



//얘는 뿌리는얘
function getCommentList(bno){
    spreadCommentListFromServer(bno).then(result=>{
        console.log(result);

        //화면에 뿌리기
        /*
        <li>
        <div>
            <div>Writer</div>
            Content
        </div>
        <span>reg_date</span>
        </li>
        */
       const ul = document.getElementById('cmtListArea');
       if(result.length>0){
            ul.innerHTML="";
            for(let cvo of result){
                let li = `<li data-cno=${cvo.cno} data-writer=${cvo.writer}><div>`//
                li+=`<div>${cvo.writer}</div>`;//
                li+=`<input type="text" id="cmtTextMod" value="${cvo.content}"><div>`;
                li+=`<span>${cvo.regdate}</span>`;
                if(sesId == cvo.writer){
                li+=`<button type="button" class="modBtn">%</button>`;
                li+=`<button type="button" class="delBtn">X</button>`;
                }
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




async function editCommentToServer(cmtModData){
    try {
        const url = '/comment/'+cmtModData.cno;//달고가기        //포스트처럼 에디트 포스트 달고 가도 됩니다
        const config = {
            method : 'put', //풋은 수정할떄 달고가는 메서드
            headers: {
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtModData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text(); //isOk
        return result;
    
    } catch (err) {
        console.log(err);
    }
}



async function removeCommentToServer(cno, writer){
    try {
        const url = '/comment/'+cno+'/'+writer;
        const config = {
            method:'delete'
        };
        const resp = await fetch(url,config);
        const result = await resp.text();
        console.log('async function removeCommentToServer(cno)거의실행' + result);
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
        //내가 선택한 타겟과 가장 가까운 li찾기
        let li = e.target.closest('li');
        let cnoVal = li.dataset.cno;
        let writerVal = li.dataset.writer;
        let textContent = li.querySelector('#cmtTextMod').value;
        console.log("cnt / content:   " + cnoVal +"  /  "+textContent);

        let cmtModData={
            cno : cnoVal, //얘가 가는 것
            content :  textContent,
            writer : writerVal
        };
        let cmtDeleteData={
            cno : cnoVal, //얘가 가는 것
            content :  textContent,
            writer : writerVal
        };
        console.log(cmtModData);
        //서버연결
        editCommentToServer(cmtModData).then(result=>{
            if(result == 1){
                alert('댓글 수정 성공~!!');
            }else if(result == 2 ){
                alert('사용자가 일치하지 않습니다.~!!');
            }
            getCommentList(bnoVal);
        })

    }else if(e.target.classList.contains('delBtn')){
        //삭제작업
        console.log('삭제버튼 클릭됨~!!');
         //내가 선택한 타겟과 가장 가까운 li찾기
         let li = e.target.closest('li'); 
         let cnoVal = li.dataset.cno;
         let writerVal = li.dataset.writer;
   
         console.log("세션아이디"+ sesId);
         console.log("댓글 작성아이디"+ writerVal);
        if(sesId == writerVal){
            //서버연결
            removeCommentToServer(cnoVal,writerVal).then(result=>{
                console.log("댓글삭제removeCommentToServer함수 진입 리절트값은 "+ result);
                if(result == 1){
                    alert('댓글 삭제 성공~!!');
                }else if(result == 2 ){
                    alert('사용자가 일치하지 않습니다.~!!');
                }
                getCommentList(bnoVal);
        })}else{
                alert('작성자만 삭제 가능합니다. 댓글 삭제 실패~!!');
        }
    }
})



//안씀-----S
// async function deleteCommentToServer(cmtDeleteData){
//     try {
//         const url = '/comment/delete'+cmtDeleteData.cno;//달고가기        //포스트처럼 에디트 포스트 달고 가도 됩니다
//         const config = {
//             method : 'put', //풋은 수정할떄 달고가는 메서드인데.... 뭐 그냥 삭제할떄도 달고갔네
//             headers: {
//                 'content-type':'application/json; charset=utf-8'
//             },
//             body:JSON.stringify(cmtDeleteData)
//         };
//         const resp = await fetch(url, config);
//         const result = await resp.text(); //isOk
//         return result; 
//     } catch (err) {
//         console.log(err);
//     }
// }
//test중
//안씀-----E













