var pageSize = $("span[id^='pages']").length;
for(var i = 0; i < pageSize; i++){
    var cur = parseInt($("span[id^='pages']:eq(" + i + ")").html());
    $("span[id^='pages']:eq(" + i + ")").html(cur+1);
}

$("#add").on("click", addTodo);
$("table").on("click", "#edit", modifyTodo);
$("table").on("click", "#del", deleteTodo);

function deleteTodo(e){
    e.preventDefault();
    console.log(e);
    var deleteButton = $(this);
//    var url = $("#delete").attr("action");
    var url = deleteButton.parent().attr("action");
    console.log(url);

    $.ajax({
        type : 'delete',
        url : url,
        data : '',
        dataType : 'json',
        error: function(xhr) {
            console.log("error xhr");
            console.log(xhr);
            alert("error!")
        },
        success : function(data) {
            console.log("success");
            console.log(data);
            var valid = data.valid;
            if(valid){
            //ajax 지만 front 개발 시간 부족으로, reload 하였음.
                window.location.reload();
            }
            else {
                alert(data.errorMessage);
            }
        }
    });
}

function addTodo(e){
    e.preventDefault();
    console.log(e);
    var queryString = $("#todo").serialize();
    console.log(queryString);
    var url = $("#todo").attr("action");
    console.log(url);

    $.ajax({
        type : 'post',
        url : url,
        data : queryString,
        dataType : 'json',
        error: function(xhr) {
            console.log("error xhr");
            console.log(xhr);
            alert("error!")
        },
        success : function(data) {
            console.log("success");
            console.log(data);
            var valid = data.valid;
            if(valid){
            //ajax 지만 front 개발 시간 부족으로, reload 하였음.
                window.location.reload();
            }
            else {
                alert(data.errorMessage);
            }
        }
    });

}

function modifyTodo(e) {
    e.preventDefault();
    var tr = $(this).closest("tr");
    var id = tr.find("#id").html();
    var newContentsElement = tr.find("#contents");
    var newModifiedDateElement = tr.find("#modified");
    var newContents = prompt("수정할 contents를 입력해주세요.");
    var url = "/api/todos/" + id;
    var queryString = "id="+id+"&"+"contents="+newContents;
    if(newContents !== null && newContents !== ""){
        $.ajax({
            type : 'put',
            url : url,
            dataType : 'json',
            data : queryString,
            error : function(){
                console.log("hello error");
            },
            success : function(data){
                console.log("success");
                console.log(data);

                var valid = data.valid;
                if(valid){
                    var todo = data.data;
                    newContentsElement.html(todo.contents);
                    newModifiedDateElement.html(todo.formattedModifiedDate)
                }
                else {
                    alert(data.errorMessage);
                }
            }
        });
    }
}
