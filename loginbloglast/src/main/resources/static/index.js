$(document).ready(function () {
    // HTML 문서를 로드할 때마다 실행합니다.
    getMessages();

})
// 수정 버튼을 눌렀을 때, 기존 작성 내용을 textarea 에 전달합니다.
// 숨길 버튼을 숨기고, 나타낼 버튼을 나타냅니다.
function editPost(id) {
    showEdits(id);
    let title = $(`#${id}-title`).text().trim();
    let contents = $(`#${id}-contents`).text().trim();
    let name = $(`#${id}-name`).text().trim();
    $(`#${id}-edtitle`).val(title);
    $(`#${id}-textarea`).val(contents);
    $(`#${id}-edname`).val(name);
}

function showEdits(id) {
    $(`#${id}-editarea`).show();
    $(`#${id}-submit`).show();
    $(`#${id}-delete`).show();

    $(`#${id}-edtitle`).show();
    $(`#${id}-textarea`).show();
    $(`#${id}-edname`).show();
    $(`#${id}-edit`).hide();
    $(`#${id}-modifiedAt`).hide();
}

function hideEdits(id) {
    $(`#${id}-editarea`).hide();
    $(`#${id}-submit`).hide();
    $(`#${id}-delete`).hide();

    $(`#${id}-edtitle`).hide();
    $(`#${id}-textarea`).hide();
    $(`#${id}-edname`).hide();
    $(`#${id}-edit`).show();
    $(`#${id}-modifiedAt`).show();
}



// 메모를 불러와서 보여줍니다.
function getMessages() {
    // 1. 기존 메모 내용을 지웁니다.
    $('#board-box').empty();
    // 2. 메모 목록을 불러와서 HTML로 붙입니다.
    $.ajax({
        type: 'GET',
        url: '/api/lists',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let message = response[i];
                let id = message['id'];
                let title = message['title'];
                let userid = message['userid'];
                let modifiedAt = message['modifiedAt'];
                addHTML(id, title, userid, modifiedAt);
            }
        }
    })
}
//
// function addHTML( title, userid, modifiedAt) {
//     //HTML태그 만들기
//
// let temphtml = `<tr>
//                     <td class="title">
//                         <a id="${id}-title" href="../../templates/private.html">${title}</a>
//                     </td>
//                     <td id="${id}-userid" class="userid">${userid}</td>
//                     <td id="${id}-date" class="date">[${modifiedAt}]<br></td>
//                 </tr>`
//     $('#board-box').append(temphtml);
// }
//
// // 메모를 생성합니다.
// function writePost() {
//     // 1. 작성한 메모
//     let title = $('#tite').val().trim();
//     let content = $('#content').val().trim();
//
//     // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
//     if (isValidContents(title) == false) {
//         return;}
//     if (isValidContents(content) == false) {
//         return;
//     }
//
//     // 4. 전달할 data JSON으로 만듭니다.
//     let data = {'title': title, 'content': content, 'name': name};
//
//     // 5. POST /api/bloglists 에 data를 전달합니다.
//     $.ajax({
//         type: "POST",
//         url: `/api/lists/post/{id}`,
//         contentType: "application/json",
//         data: JSON.stringify(data),
//         success: function (response) {
//             alert('메시지가 작성되었습니다.');
//             close_pop()
//             window.location.reload();
//         }
//     });
// }
//
// function close_pop() {
//     $('#index').hide();
// }
//
// // 메모를 수정합니다.
// function submitEdit(id) {
//     // 1. 작성 대상 메모의 name과 contents 를 확인합니다.
//     let title = $(`#${id}-edtitle`).val().trim();
//     let contents = $(`#${id}-textarea`).val().trim();
//     // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
//     if (isValidContents(title) == false) {
//         return;}
//     if (isValidContents(content) == false) {
//         return;
//     }
//
//     // 3. 전달할 data JSON으로 만듭니다.
//     let data = {'title': title, 'content': content, 'name': name};
//
//     // 4. PUT /api/memos/{id} 에 data를 전달합니다.
//     $.ajax({
//         type: "PUT",
//         url: `/api/private/delete/{id}`,
//         contentType: "application/json",
//         data: JSON.stringify(data),
//         success: function (response) {
//             alert('메시지가 변경되었습니다.');
//             hideEdits()
//             window.location.reload();
//         }
//     });
// }
//
// function deleteOne(id) {
//     $.ajax({
//         type: "DELETE",
//         url: `/api/private/edit/${id}`,
//         success: function (response) {
//             alert('삭제 되었습니다.');
//             window.location.reload();
//         }
//     });
// }
// // function searchContents(){
// //     let searchType = $('#searchType').val();
// //     let searchText = $('#searchText').val();
// //     $('#cards-box').empty();
// //
// //     $.ajax({
// //         type: 'GET',
// //         url: '/api/contents',
// //         success: function (response) {
// //             for (let i = 0; i < response.length; i++) {
// //                 let message = response[i];
// //                 let id = message['id'];
// //                 let username = message['username'];
// //                 let title = message['title'];
// //                 let content = message['content'];
// //                 let category = message['category'];
// //                 let modifiedAt = message['modifiedAt'];
//
// //                 if (message[searchType].includes(searchText))
// //                 {
// //                     addHTML(id, username, title, content, category, modifiedAt);
// //                 }
// //             }
// //         }
//
