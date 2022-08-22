$(function () {
    let username = sessionStorage.getItem("username");
    $("#loginName").html(username);
})