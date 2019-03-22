/**
 * Created by AlexAnderIch on 2017/6/10.
 */
var exp = "${sessionScope.exp}";

function level(userexp) {
    var userlevel = pow(userexp, 0.5) / 10 + 1;//将经验值换算成等级
    switch (userlevel) {
        case 1:
            return "菜鸟";
        case 2:
            return "健将";
        case 3:
            return "老司机";
        default:
            return "传说";
//返回人的等级

    }
}

var mylevel = level(exp);
//用jquery显示出等级
