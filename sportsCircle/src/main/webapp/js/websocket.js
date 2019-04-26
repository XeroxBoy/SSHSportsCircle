SendMsg = function () {
    console.log("开始从B端发送信息");
    var chatter = document.getElementById("chatter");
    var show = document.getElementById("show");
    var message = document.getElementById("msg");
    // var messageTo = document.getElementById("otherUser");
    // var chatter = document.getElementById("show");
    console.log(othername + " " + username + " " + message.value);
    ws.send(othername + " " + message.value);
    // var oneMessage0 = document.createElement("li");
    // oneMessage0.innerHTML = username + ": " + message.value;
    // chatter.appendChild(oneMessage0);
    // $("#chatter").appendChild(oneMessage);
    message.value = "";
};

if (typeof (WebSocket) == "undefined") {
    console.log("您的浏览器不支持WebSocket");
} else {
    console.log("您的浏览器支持WebSocket");
    var ws = new WebSocket("ws://localhost:8888/websocket/" + username);

//打开Socket
    ws.onopen = function () {
        console.log("websocket已打开");
        // document.getElementById("sender").onclick = SendMsg();
        $("#sender").click(SendMsg);

    // 监听消息
    ws.onmessage = function (msg) {
        var chatter = document.getElementById("chatter");
        var show = document.getElementById("show");
        // console.log(msg.data);
        var oneMessage = document.createElement("li");
        // console.log(oneMessage0);
        oneMessage.innerHTML = msg.data;
        // console.log(oneMessage0);
        chatter.appendChild(oneMessage);
        // ws.send("已收到");
    };
    // 监听Socket的关闭
    ws.onclose = function (msg) {
        document.getElementById("sender").onclick = null;
        console.log('连接中断' + msg);
    };


    }
}

