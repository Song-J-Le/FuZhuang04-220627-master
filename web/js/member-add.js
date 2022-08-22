layui.use(['form', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate;
    //日期
    laydate.render({
        elem: '#birthday',
        //    强制显示
        trigger: 'click'
    });
    //from表单重新渲染
    form.render();
//    对按钮进行监听
    form.on('submit(add)', function (data) {
        data = data.field;
        let arr = [];
        $('input[type=checkbox][name=hobby]:checked').each(function () {
            //$(this).val()表示拿到里面的值
            //如果要拿到属性值用attr
            arr.push($(this).attr("title"));
            // $(this).attr("title","fasdfa");
        });
        //arr.toLocaleString()将数组转换为字符串
        data.hobby = arr.toLocaleString();
        console.log(data);
        let res = myAjax("/back/user/add", data, "post");
        if (res.count > 0) {
            layer.alert("增加成功", {
                    icon: 6
                },
                function () {
                    //关闭当前frame
                    xadmin.close();
                    // 可以对父窗口进行刷新
                    xadmin.father_reload();
                });
        } else {
            layer.alert("增加失败");
        }
        return false;
    })
})