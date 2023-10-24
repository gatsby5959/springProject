console.log(bnoVal);

async function CommentPostToServer(cmtData) {
     try {
          const url = "/comment/post";
          const config = {
               method: "post",
               headers: {
                    "content-type": "application/json; charset=utf-8"
               },
               body: JSON.stringify(cmtData)
          };
          const resp = await fetch(url, config);
          const result = await resp.text();
          return result;
     } catch (error) {
          console.log(error);
     }
}

document.getElementById('cmtPostBtn').addEventListener('click', () => {
     const cmtWriter = document.getElementById('cmtWriter').innerText;
     const cmtText = document.getElementById('cmtText').value;

     if (cmtText == "" || cmtText == null) {
          alert('댓글을 입력해주세요.');
          document.getElementById('cmtText').focus();
          return false;
     } else {
          let cmtData = {
               bno: bnoVal,
               writer: cmtWriter,
               content: cmtText
          };
          console.log(cmtData);
          CommentPostToServer(cmtData).then(result => {
               if (result == 1) {
                    alert('댓글 등록 성공!');
                    document.getElementById('cmtText').value = '';
                    CommentList(bnoVal);
               } else {
                    alert('댓글 등록 실패!');
               }
          })
     }
})

async function spreadCommentList(bno, page) {
     try {
          const resp = await fetch("/comment/" + bno + "/" + page);
          const result = await resp.json();
          return result;
     } catch (error) {
          console.log(error);
     }
}

// 무조건 처음 뿌릴때는 첫 페이지 값을 뿌려야 함.
function CommentList(bno, page = 1) {
     spreadCommentList(bno, page).then(result => {
          console.log(result); // ph 객체
          if (result.cmtList.length > 0) {
               let ul = document.getElementById('cmtListArea');
               // 다시 댓글을 뿌릴때 기존 값 삭제 1page일 경우만
               if (page == 1) {
                    ul.innerHTML = "";
               }
               for (let cvo of result.cmtList) {
                    let li = `<li class="list-group-item" data-cno=${cvo.cno}>`;
                    li += `<div class="mb-3">`;
                    li += `<div class="fw-bold">${cvo.writer}</div>`;
                    li += `${cvo.content}</div>`;
                    li += `<span class="badge rounded-pill text-bg-info">${cvo.modAt}</span><br>`;
                    li += `<button type="button" class="btn btn-warning modBtn" data-bs-toggle="modal" data-bs-target="#myModal">Mod</button>`;
                    li += `<button type="button" class="btn btn-danger delBtn">Del</button>`;
                    li += `</li>`;
                    ul.innerHTML += li;
               }
               // 댓글 페이징 코드
               let moreBtn = document.getElementById('moreBtn');
               console.log(moreBtn);
               // DB에서 pgvo + list 같이 가져와야 값을 줄 수 있음.
               if (result.pgvo.pageNo < result.endPage || result.next) {
                    moreBtn.style.visibility = 'visible';
                    moreBtn.dataset.page = page + 1;
               } else {
                    moreBtn.style.visibility = 'hidden';
               }
          } else {
               let li = `<li class="list-group-item">Comment Empty</li>`;
               document.getElementById('cmtListArea').innerHTML = li;
          }


     })
}

// function moreComment() {
//      let page = parseInt(document.getElementById('moreBtn').dataset.page);
//      CommentList(bnoVal, page);
// }

async function removeComment(cno) {
     const url = "/comment/" + cno;
     const config = {
          method: "delete"
     };
     const resp = await fetch(url, config);
     const result = await resp.text();
     return result;
}

async function editCommentToServer(cmtDataMod) {
     try {
          const url = "/comment/" + cmtDataMod.cno;
          const config = {
               method: "put",
               headers: {
                    "content-type": "application/json; charset=utf-8"
               },
               body: JSON.stringify(cmtDataMod)
          };
          const resp = await fetch(url, config);
          const result = await resp.text();
          return result;
     } catch (error) {
          console.log(error);
     }
}

document.addEventListener('click', (e) => {
     console.log(e.target);
     if (e.target.classList.contains('delBtn')) {
          let li = e.target.closest('li');
          let cnoVal = li.dataset.cno;
          removeComment(cnoVal).then(result => {
               if (result == 1) {
                    alert('댓글 삭제 성공!');
               } else {
                    alert('댓글 삭제 실패!');
               }
               CommentList(bnoVal);
          })
     } else if (e.target.classList.contains('modBtn')) {
          let li = e.target.closest('li');
          // nextSibling(); : 같은 부모의 다음 형제 객체를 반환
          let cmtText = li.querySelector('.fw-bold').nextSibling;

          // 기존 내용 모달창에 반영(수정하기 편하게)
          document.getElementById('cmtTextMod').value = cmtText.nodeValue;
          // cmtModBtn에 data-cno 달기
          document.getElementById('cmtModBtn').setAttribute('data-cno', li.dataset.cno);
     } else if (e.target.id == 'cmtModBtn') {
          let cmtDataMod = {
               cno: e.target.dataset.cno,
               content: document.getElementById('cmtTextMod').value
          };
          console.log(cmtDataMod);
          editCommentToServer(cmtDataMod).then(result => {
               if (parseInt(result)) {
                    // 모달창 닫기
                    document.querySelector('.btn-close').click();
               }
               CommentList(bnoVal);
          })
     } else if (e.target.id == 'moreBtn') {
          CommentList(bnoVal, parseInt(e.target.dataset.page))
     }
})