$(document).ready(function () {
    // HTML 문서를 로드할 때마다 실행합니다.
    getMessages();

})

function isValidContents(content, title, name) {

    if (title == '') {
        alert('제목을 입력해주세요');
        return false;
    }
    if (title.trim().length > 60) {
        alert('공백 포함 60자 이하로 입력해주세요');
        return false;
    }
    if (content == '') {
        alert('내용을 입력해주세요');
        return false;
    }
    if (content.trim().length > 60) {
        alert('공백 포함 60자 이하로 입력해주세요');
        return false;
    }
    if (name == '') {
        alert('이름을 입력해주세요');
        return false;
    }
    if (name.trim().length > 10) {
        alert('10자 이하로 입력해주세요');
        return false;
    }
    return true;
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
                let content = message['content'];
                let name = message['name'];
                let modifiedAt = message['modifiedAt'];
                addHTML(id, name, content, title, modifiedAt);
            }
        }
    })
}

function addHTML(id,title, content, name, modifiedAt) {
    //HTML태그 만들기

let temphtml = `<tr>
                    <td class="title">
                        <a id="${id}-title" href="../../templates/private.html">${title}</a>
                    </td>
                    <td id="${id}-name" class="name">${name}</td>
                    <td id="${id}-date" class="date">[${modifiedAt}]<br></td>
                </tr>`
    $('#board-box').append(temphtml);
}

// 메모를 생성합니다.
function writePost() {
    // 1. 작성한 메모
    let content = $('#content').val().trim();
    let title = $('#title').val().trim();
    let name = $('#name').val().trim();

    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(title) == false) {
        return;}
    if (isValidContents(content) == false) {
        return;
    }
    if (isValidContents(name) == false) {
        return;
    }

    // 4. 전달할 data JSON으로 만듭니다.
    let data = {'title': title, 'contents': contents, 'name': name};

    // 5. POST /api/bloglists 에 data를 전달합니다.
    $.ajax({
        type: "POST",
        url: `/api/private/edit/${id}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 작성되었습니다.');
            window.location.reload('private.html');
        }
    });
}

// 메모를 수정합니다.
function submitEdit(id) {
    // 1. 작성 대상 메모의 name과 contents 를 확인합니다.
    let title = $(`#${id}-edtitle`).val().trim();
    let contents = $(`#${id}-textarea`).val().trim();
    let name = $(`#${id}-edname`).val().trim();
    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(title) == false) {
        return;}
    if (isValidContents(contents) == false) {
        return;
    }
    if (isValidContents(name) == false) {
        return;
    }

    // 3. 전달할 data JSON으로 만듭니다.
    let data = {'title': title, 'content': content, 'name': name};

    // 4. PUT /api/memos/{id} 에 data를 전달합니다.
    $.ajax({
        type: "PUT",
        url: `/api/private/edit/${id}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 변경되었습니다.');
            hideEdits()
            window.location.reload();
        }
    });
}

function deleteOne(id) {
    $.ajax({
        type: "DELETE",
        url: `/api/private/edit/${id}`,
        success: function (response) {
            alert('삭제 되었습니다.');
            window.location.reload();
        }
    });
}
// function searchContents(){
//     let searchType = $('#searchType').val();
//     let searchText = $('#searchText').val();
//     $('#cards-box').empty();
//
//     $.ajax({
//         type: 'GET',
//         url: '/api/contents',
//         success: function (response) {
//             for (let i = 0; i < response.length; i++) {
//                 let message = response[i];
//                 let id = message['id'];
//                 let username = message['username'];
//                 let title = message['title'];
//                 let content = message['content'];
//                 let category = message['category'];
//                 let modifiedAt = message['modifiedAt'];

//                 if (message[searchType].includes(searchText))
//                 {
//                     addHTML(id, username, title, content, category, modifiedAt);
//                 }
//             }
//         }

