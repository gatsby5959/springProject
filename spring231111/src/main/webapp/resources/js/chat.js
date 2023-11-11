console.log("chat.js진입");

// function autoClosingAlert(selector, delay){
//     var alert =$(selector).alert();
//     alert.show();
//     window.setTimeout(function(){alert.hide()},delay);
// }
// function submitFunction(){
//     var fromID = '<%= userID %>';
//     var toID = '<%= toID %>';
//     var chatContent = $('#chatContent').val();
//     $.ajax({
//         type : "POST",
//         url : ".chatSubmitServlet",
//         data:{
//             from: encodeURIComponent(fromID),
//             toID: encodeURIComponent(toID),
//             chatContent: encodeURIComponent(chatContent)
//         },
//         success: function(result){
//             if(result ==1  ){
//                 autoClosingAlert('#successMessage',2000);
//             }else if (result ==0){
//                 autoClosingAlert('#dangerMessage',2000);
//             }else{
//                 autoClosingAlert('#warningMessage', 2000);
//             }
//         } 
//     });
//     $('#chatContent').val('');
// }





//호출해서 등록
document.getElementById("chatSubmitBtn").addEventListener('click', () => {
    console.log("chatSubmitBtn 리스너 진입");
    const chatContent = document.getElementById("chatContent").value;
    console.log("1 " + document.getElementById("chatContent").value);
    const chatName = document.getElementById("chatName").value;
    console.log("2 " + document.getElementById("chatName").value);
    let chatData = {
        // bno: bnoVal,
        fromID: chatName,
        chatContent: chatContent  //여기 왼쪽 단어가 중요함 디비 컬럼이랑 맞춰야함
    }
    console.log("3 " , chatData);
    postComment(chatData).then(result => {
        console.log("8 " , result);
        if (result > 0) {
            alert('채팅글 insert 완료');


        } else {
            alert('댓글 insert 실패');
        }
        //전체 댓글 출력
        // printCommentList(bnoVal);
        document.getElementById("chatContent").value = '';
        document.getElementById("chatContent").focus();
    })

})

// 보내는 함수
async function postComment(chatData) {
    try {
        console.log("4 " , chatData);
        const url = "/chaturl/chat";
        const config = {
            method: "post",
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(chatData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text(); //isOk
        return result;

    } catch (error) {
        console.log(error)
    }

}
