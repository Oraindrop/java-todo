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
    var deleteButton = $(this);
    var url = deleteButton.parent().attr("action");

    $.ajax({
        type : 'delete',
        url : url,
        data : '',
        dataType : 'json',
        error: function(xhr) {
            console.log("error xhr");
            console.log(xhr);
            alert(xhr.responseText);
        },
        success : function(data) {
            console.log("success");
            console.log(data);
            window.location.reload();
        }
    });
}

function addTodo(e){
    e.preventDefault();
    var queryString = $("#todo").serialize();
    var url = $("#todo").attr("action");

    $.ajax({
        type : 'post',
        url : url,
        data : queryString,
        dataType : 'json',
        error: function(xhr) {
            console.log("error xhr");
            console.log(xhr);
            alert(xhr.responseText);
        },
        success : function(data) {
            console.log("success");
            console.log(data);
            window.location.reload();
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
                alert(xhr.responseText);
            },
            success : function(data){
                console.log("success");
                console.log(data);
                newContentsElement.html(data.contents);
                newModifiedDateElement.html(data.formattedModifiedDate)
            }
        });
    }
}
