(function($){
    console.log("将要连接服务器。");
    var wsUri ="ws://localhost:8080/";
    var socketOpen = false;
    var messageArray = [];
    websocket = new WebSocket(wsUri);
    websocket.onopen = function(res) {
        console.log("连接服务器成功。");
        $("#inputMessage").attr("placeholder","连接服务器成功，请输入姓名。");
        socketOpen = true;
    };
    websocket.onclose = function(res) {
        console.log("断开连接。");

    };
    websocket.onmessage = function(res) {
        console.log('收到服务器内容：' + res.data);
        var data = res.data;
        var dataArray = data.split("_");
        var newMessage = {
            type:dataArray[0],
            name:dataArray[1],
            time:dataArray[2],
            message:dataArray[3]
        };
        var newArray = messageArray.concat(newMessage);
        messageArray = newArray;
        reBuild(messageArray);
    };

    websocket.onerror = function(res) {
        console.log("连接错误。");
    };

    $("#sendButton").on("click",function () {
        var message = $("#inputMessage").val();
        if(message!=''){
            $("#inputMessage").attr("placeholder","请输入信息");
            sendSocketMessage(message);
            $("#inputMessage").val("");
        }
    });

    function sendSocketMessage(message) {
        if(socketOpen){
            websocket.send(message);
        }
    }

    function reBuild(messageArray) {
        $("#topArea").empty();
        var htmlDom = "";
        for(var i in messageArray){
            if(messageArray[i].type == 'self'){
                htmlDom+='<div class="selfMessage"><div class="nameInfo">'
                    + messageArray[i].name+ " "
                    + messageArray[i].time
                    + '</div><div class="detailMessage">'
                    + messageArray[i].message
                    + '</div></div>';
            }
            else{
                htmlDom+='<div class="otherMessage"><div class="nameInfo">'
                    + messageArray[i].name+ " "
                    + messageArray[i].time
                    + '</div><div class="detailMessage">'
                    + messageArray[i].message
                    + '</div></div>';
            }
        }
        htmlDom+='<div class="clear"></div>'
        $("#topArea").append(htmlDom);
    }

})(Zepto);