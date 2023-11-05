console.log("boardModify.js");

document.addEventListener("click", (e) => {
    console.log("boardModify.js 진입");
    console.log("e.target" + e.target);
    if (e.target.classList.contains("file-x")) {
        //file-x 가 있다면 removeFileToServer 호출
        console.log("X 모양 버튼 클릭됨 (file-x클래스)");
        let uuid = e.target.dataset.uuid;
        console.log("jsp에서 javascript로 쏴준 uuid" + uuid);

        //서버연결
        removeFileToServer(uuid).then((result) => {
            //함수 실행후 리절트 받기
            console.log("파일삭제 removeFileToServer()의 결과는 " + result);
            if (result == 1) {
                alert(
                    "파일 삭제 성공" + (parseInt(result) > 0 ? "완료" : "실패")
                );
                if (parseInt(result)) {
                    // e.target.classList("li").remove();
                    location.reload(); // 새로고침
                }
            } else if (result == 2) {
                alert("result == 2 제대로 동작 안함 파일삭제 실패");
            } else {
                alert("else문진입 파일 삭제 실패");
            }
        });
    }
});

async function removeFileToServer(uuid) {
    console.log("디비의 파일 row를 삭제하기 위한 함수 진입1");
    console.log("uuid는 " + uuid);

    try {
        console.log(
            "삭제하려는 js가 컨트롤러로  /board/file  uuid 로출발~ @DeleteMapping임"
        );
        const url = "/board/file/" + uuid; //board컨트롤러에서 file관련 컨트롤러 만들어야함
        const config = {
            method: "delete"
        };

        const resp = await fetch(url, config);
        const result = await resp.text();
        console.log(
            "async function removeFileToServer2(uuid)거의실행" + result
        );
        return result;
    } catch (err) {
        console.log(err);
    }
}
