Sendmsg = function () {
    console.log("开始发送打卡信息");
    var dkMessage = document.getElementById("faxie");
    $('canvas').barrager([{"msg": dkMessage.value}]);
    dkMessage.value = "";
    $('#dakaer').submit();
};
$('#dakaer').click(Sendmsg);
