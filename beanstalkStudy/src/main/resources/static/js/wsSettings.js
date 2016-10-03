$(function(){
    var ws = new WebSocket("ws://localhost:8080/sendUserMessage");
    ws.onopen = function(){
        ws.send(
            "<div class='message_box'><p class='name_cs' id='user_name'>コンシェルジュ</p><div class='message_cs' id='user_message'>connpassで勉強会を検索します</div>"
        );
        $("#message").val("");
    };

    ws.onclose = function(){};

    ws.onmessage = function(message){
        $("#result").append(message.data).append("<br />");
    };

    ws.onerror = function(event){
        alert("接続大失敗。がびーん。");
    };

    $("#form").submit(function(){
        var messageBox = "<div class='message_box'>"
                        + "<p class='name_cs' id='user_name'>" + $("#name").val() + "</p>"
                        + "<div class='message_cs' id='user_message'>" + $("#message").val() + "</div>"

        ws.send(messageBox);
        $("#message").val("");
        return false;
    });

});