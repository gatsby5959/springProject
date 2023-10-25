document.addEventListener('click',(e)=>{
    console.log("boardModify.js 진입");
    console.log(e.target);
    if(e.target.classList.contains('file-x')){//있다면
        console.log('X버튼 클릭됨');
        let uuid = e.target.dataset.uuid;
        console.log("jsp에서 javascript로 쏴준 uuid" + uuid);
        
        //서버연결
        removeFileToServer(uuid).then(result=>{
            console.log("파일삭제removeCommentToServer함수의 result값은 "+ result);
            if(result == 1){
                alert('파일 삭제 성공~!!');
                e.target.closest('li').remove(); //가장 가까운 li삭제
                //location.reload();// 새로고침
            }else if(result == 2 ){
                alert('먼가 제대로 동작 안함~!!2 파일삭제 실패~!!2');
            }else{
                alert("먼가 잘 안됨 파일삭제 실패 3")
            }
        })                        

    }
})

async function removeFileToServer(uuid){
    console.log("디비의 파일 row을 삭제 하기 위한 부분 진입1");
    console.log("uuid는 " +uuid);
    try {
        console.log('삭제하러 js는 디비로 출발~~');

        const url = '/board/file/'+uuid; //board컨트롤러에서 file관련 컨트롤러 만들어야함
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