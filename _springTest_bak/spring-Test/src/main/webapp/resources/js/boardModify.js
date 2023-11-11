

async function removeFileToServer2(uuid){
    try {
        const url = '/board/file/'+uuid;
        const config = {
            method:'delete'
        };
        const resp = await fetch(url,config);
        const result = await resp.text();
        console.log('async function removeFileToServer2(uuid)거의실행' + result);
        return result;

    } catch (err) {
        console.log(err);
    }
}



document.addEventListener('click',(e)=>{
    console.log(e.target);
    if(e.target.classList.contains('file-x')){ //있다면
        //삭제작업
        console.log('파일 삭제버튼 클릭됨~!!');
         let uuid = e.target.dataset.uuid;
         console.log("jsp에서 javascript로 쏴준2222 uuid222 "+  uuid);

            //서버연결
            removeFileToServer2(uuid).then(result=>{
                console.log("파일삭제removeCommentToServer2함수 진입 리절트값은 "+ result);
                if(result == 1){
                    alert('파일 삭제 성공~!!');
                    e.target.closest('li').remove(); //가장 가까운 li삭제
                    location.reload();// 새로고침
                }else if(result == 2 ){
                    alert('먼가 제대로 동작 안함~!!2 파일삭제 실패~!!2');
                }else{
                    alert("먼가 잘 안됨 파일삭제 실패 3")
                }
               
            })

    }
})
















