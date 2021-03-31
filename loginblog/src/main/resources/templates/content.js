//포스팅전 글자수와 빈칸체크하기
function isValidContents(contents) {
    if (contents == '') {
        alert('내용을 입력해주세요');
        return false;
    }
    if (contents.trim().length > 60) {
        alert('공백 포함 60자 이하로 입력해주세요');
        return false;
    }
    return true;
}


//글 전체 조회하기
function getContent(){
    $('').empty();
    $.ajax({
        type:'GET',
        url:'/api/lists',
        success: function(response){

        }
    })

}

//글 작성하기
function writecontent(id){
    //1. 작성한 메모
    // let title=$('#${id}-title').val.trim();
    // let content=$('#${id}-content').val.trim();
    // let name = $('#${id}-name').val.trim();

    //2. 메모가 잘 작성되어있는지 함수를 통해 확인
    if (isValidContents(title) == false) {
        return;}
    if (isValidContents(content) == false) {
        return;
    }
    if (isValidContents(name) == false) {
        return;
    }

    //2. 전달할 데이터 JSON으로 만들기
    let data = {'title' : title, 'content':content, 'name': name};

    //3. POST /api/lists에 data를 전달
    $.ajax({
        type:"POST",
        url:`/api/private/edit/${id}`,
        contentType:"application/json",
        data:JSON.stringify(data),
        success: function(response){
            alert('입력하신 글이 작성되었습니다.');
            window.location.reload();
    }
    })
}

//글 수정하기
function editcontent(id){
    //글 수정하기
    let title = $(`#${id}-edtitle`).val().trim();
    let content = $(`#${id}-edcontent`).val().trim();
    let name = $(`#${id}-edname`).val().trim();

    //2. 메모가 잘 작성되어있는지 함수를 통해 확인
    if (isValidContents(title) == false) {
        return;}
    if (isValidContents(content) == false) {
        return;
    }
    if (isValidContents(name) == false) {
        return;
    }

    //전달할 데이터를 JSON으로 바꾸기
    let data = {'title': title, "content": content, 'name': name}

    //5. PUT /api/private/edit/{id}에 data를 전달합니다.
    $.ajax({
        type:"PUT",
        url:`/api/private/edit/${id}`,
        contentType:"application/json",
        data:JSON.stringify(data),
        success: function(response){
            alert("님의 글이 수정되었습니다.")
            window.location.reload("Private.html");
        }
    })

    function removeCheck() {
        if (confirm("정말 삭제하시겠습니까??") == true){    //확인
            document.removefrm.submit();
            //html삭제 확인 구현할때 name='removefrm'넣기
        }else{   //취소
            return false;
        }
    }

    function deletecontent(id) {
        $.ajax({
            type:"DELETE",
            url:`/api/private/edit/${id}`,
            success: function(response) {
                alert('삭제되었습니다.')
                window.location.reload();
            }
        })


    }

}