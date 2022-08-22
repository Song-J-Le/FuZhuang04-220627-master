//公共的方法
function myAjax(url, data, type) {
    let result = null;
    $.ajax({
        url: url,
        data: data,
        type: type,
        async: false,//关闭异步
        dataType: 'json',
        success: function (res) {
            result = res;
        }
    })
    return result;
}