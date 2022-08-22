$(function () {
    let username = sessionStorage.getItem("username");
    $("#loginName").html(username);
    //js的定时器
    // 一种调用方法，则用引号括起来，一种直接写方法名
    setInterval(setNowTime, 1000);
    // setInterval(function () {// 匿名函数

    // }, 1000);

})

function setNowTime() {
    $("#nowTime").html(new Date().Format("yyyy-MM-dd hh:mm:ss"));
}