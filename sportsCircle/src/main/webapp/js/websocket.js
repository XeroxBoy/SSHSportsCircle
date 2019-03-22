var ws = new WebSocket("ws://localhost:8888");


//打开Socket 
ws.onopen = function (event) {
// 监听消息
    ws.send("我是客户端" + new Date());
    ws.onmessage = function (msg) {
//	alert(msg.data);

        $("#news").html(msg.data);
        $(".sender").hide();
        //alert(msg.data);//输出一下服务器发送过来的消息
    };

// 监听Socket的关闭
    ws.onclose = function (msg) {
        console.log('socket已经关闭');
    };

    /*$(".sender").click(function(){
        ws.send("我是客户端       "  + new Date());
    
    });*/
};

