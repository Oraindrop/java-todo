var pageSize = $("span[id^='pages']").length;
for(var i = 0; i < pageSize; i++){
    var cur = parseInt($("span[id^='pages']:eq(" + i + ")").html());
    $("span[id^='pages']:eq(" + i + ")").html(cur+1);
}