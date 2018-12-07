var pageSize = $("span[id^='pages']").length;
for(var i = 0; i < pageSize; i++){
    var cur = parseInt($("span[id^='pages']:eq(" + i + ")").html());
    $("span[id^='pages']:eq(" + i + ")").html(cur+1);
}

$("table").on("click", "#edit", modifyContents);

function modifyContents(e) {
    e.preventDefault();
    var tr = $(this).closest("tr");
    var id = tr.find("#id").html();
    var newContentsElement = tr.find("#contents");
    var newModifiedDateElement = tr.find("#modifiedDate");
    var newContents = prompt("수정할 contents를 입력해주세요.");
    var url = "/api/todos/" + id;
    var queryString = "id="+id+"&"+"contents="+newContents;

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
