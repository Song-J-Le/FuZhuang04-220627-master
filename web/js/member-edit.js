$(function () {
    layui.use(['form', 'laydate', 'layedit'], function () {
        var form = lay.form,
            layer = lay.layer,
            laydate = lay.laydate,
            layedit = lay.layedit;
        laydate.render({
            elem: '#birthday',
            trigger: 'click'
        });
        function findById() {
            let id = sessionStorage.getItem("userId");
            let res = myAjax("/back/user/findById", {id: id}, "get");
            console.log(res);
            if (res != null) {
                setUserData(res.data);
            }
        }
        function setUserData(data) {
            form.val('userEdit', {
                "phone": data.phone,
                "sex": data.sex,
                "birthday": data.birthday
            });
            $("#username").val(data.name);
            $('input[type=radio][name=enable][value=' + data.enable + ']').attr("checked", "checked");
            let arr = data.hobby.split(",");
            $('input[type=checkbox][name=hobby]').each(function () {
                for (let i = 0; i < arr.length; i++) {
                    if (arr[i] == $(this).attr("title")) {
                        $(this).attr("checked", "checked");
                    }
                }
            });
            form.render();
        }
        form.on('submit(edit)', function (data) {
            data = data.field;
            let arr = [];
            $('input[type=checkbox][name=hobby]:checked').each(function () {
                arr.push($(this).attr("title"));
            });
            data.hobby = arr.toLocaleString();
            data.id = sessionStorage.getItem("userId");
            console.log(data);
            let res = myAjax("/back/user/update", data, "post");
            if (res.count > 0) {
                layer.alert("编辑成功", {
                        icon: 6
                    },
                    function () {
                        xadmin.close();
                        xadmin.father_reload();
                    });
            } else {
                layer.alert("编辑失败");
            }
            return false;
        })
    })
})