var ws = new WebSocket("ws://localhost:8888");

SendMsg = function () {
    var message = document.getElementById("msg");
    var messageTo = document.getElementById("otherUser");
    ws.send(messageTo.value + " " + ${sessionScope.name } +" " + massage.value);
    var oneMessage = document.createElement("li");
    oneMessage.innerHTML = ${sessionScope.name}+": " + message.value;
    $("#chatter").appendChild(oneMessage);
    message.value = "";
};
//打开Socket 
ws.onopen = function (event) {
// 监听消息
    ws.onmessage = function (msg) {
        var oneMessage = document.createElement("li");
        oneMessage.innerHTML = msg.data;
        $("#chatter").appendChild(oneMessage);
    };
    document.getElementById("sender").onclick = SendMsg();
// 监听Socket的关闭
    ws.onclose = function (msg) {
        document.getElementById("sender").onclick = null;
        console.log('连接中断');
    };

};

