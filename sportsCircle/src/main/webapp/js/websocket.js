if (typeof (WebSocket) == "undefined") {
    console.log("您的浏览器不支持WebSocket");
} else {
    console.log("您的浏览器支持WebSocket");
    var ws = new WebSocket("ws://localhost:8888/websocket/" + username);
    var chatter = document.getElementById("chatter");

SendMsg = function () {
    var message = document.getElementById("msg");
    // var messageTo = document.getElementById("otherUser");
    var chatter = document.getElementById("chatter");
    ws.send(othername + " " + username + " " + message.value);
    var oneMessage = document.createElement("li");
    oneMessage.innerHTML = username + ": " + message.value;
    chatter.appendChild(oneMessage);
    // $("#chatter").appendChild(oneMessage);
    message.value = "";
};
//打开Socket 
ws.onopen = function (event) {
// 监听消息
    ws.onmessage = function (msg) {
        var oneMessage = document.createElement("li");
        oneMessage.innerHTML = msg.data;
        chatter.appendChild(oneMessage);
    };
    document.getElementById("sender").onclick = SendMsg();
// 监听Socket的关闭
    ws.onclose = function (msg) {
        document.getElementById("sender").onclick = null;
        console.log('连接中断');
    };

};
}

