SendMsg = function () {
    console.log("开始发送打卡信息");
    var message = document.getElementById("faxie");
    console.log(message.value);
    ws.send("danmu " + message.value);
    message.value = "";
};
//TODO 输出弹幕
PrintDanmu = function () {

};

if (typeof (WebSocket) == "undefined") {
    console.log("您的浏览器不支持WebSocket");
} else {
    console.log("您的浏览器支持WebSocket");
    var ws = new WebSocket("ws://localhost:8888/websocket/");

//打开Socket
    ws.onopen = function () {
        console.log("websocket已打开");
        $("#dakaer").click(SendMsg);

        // 监听消息
        ws.onmessage = function (msg) {
            //TODO 输出弹幕
        };
        // 监听Socket的关闭
        ws.onclose = function (msg) {
            document.getElementById("sender").onclick = null;
            console.log('连接中断' + msg);
        };


    }
}

