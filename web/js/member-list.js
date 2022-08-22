layui.use(['table', 'laydate'], function () {
    var table = layui.table;
    let form = layui.form;
    let laydate = layui.laydate;
    //执行一个laydate实例
    laydate.render({
        elem: '#start' //指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#end' //指定元素
    });
    //第一个实例
    tableRender({});
    //监听性别操作
    form.on('switch(enable)', function (obj) {
        //需要在数据库里面修改当前数据，将enable进行修改
        //这里需要调用后台ajax
        // $.ajax({
        //     url: '/back/user/enable',
        //     data: {id: this.value},
        //     type: 'post',
        //     dataType: 'json',
        //     success: function (res) {
        //         console.log(typeof res.count, 1, res.count == 1);
        //         if (res.count == 1) {
        //             console.log(11)
        //             layer.tips("修改状态成功", obj.othis);
        //         } else {
        //             layer.tips("修改状态失败", obj.othis);
        //         }
        //
        //     }
        // })
        // layer.tips(this.value + ' ' + this.name + '：' + obj.elem.checked, obj.othis);
        let res = myAjax("/back/user/enable", {id: this.value}, 'post');
        if (res.count == 1) {
            // console.log(11)
            layer.tips("修改状态成功", obj.othis);
            // layer.msg("修改状态成功");
        } else {
            // layer.tips("修改状态失败", obj.othis);
        }
    });
    //监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        console.log(data)
        if (obj.event === 'del') {
            //弹出确定窗口
            layer.confirm('真的删除行么', function (index) {
                //调用后台删除：ajax
                let res = myAjax("/back/user/delete", {id: data.id}, 'post');
                if (res.count == 1) {
                    // layer.tips("删除成功", obj.othis);
                    layer.msg("删除成功", function () {
                        obj.del();
                        layer.close(index);
                    })
                } else {
                    layer.tips("删除失败", obj.othis);
                }
            });
        } else if (obj.event === 'edit') {
            //    弹出编辑页面，将id传递到编辑页面去
            sessionStorage.setItem("userId", data.id);
            xadmin.open('编辑用户', '/html/user/member-edit.html', 600, 400);
        }
    });
    form.on('submit(sreach)', function (data) {
        data = data.field;
        console.log(data);
        tableRender(data);
        //需要查询所有，
        return false;
    })

    //如果第一次加载数据，直接传空对象即可
    function tableRender(data) {
        table.render({
            elem: '#test'
            , url: '/back/user/findAll' //数据接口
            , where: data
            , page: true //开启分页
            , parseData: function (res) { //res 即为原始返回的数据
                console.log(res);
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
            , cols: [[ //表头
                {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'}
                , {field: 'name', title: '用户名', width: 80}
                , {
                    field: 'sex', title: '性别', width: 80, sort: true, templet: function (d) {
                        //性别 有可能为null或null字符串
                        // 三元运算符和逻辑运算符
                        return d.sex == null || d.sex == 'null' ? "保密" : d.sex;
                    }
                }
                , {field: 'email', title: '邮箱',}
                , {field: 'phone', title: '电话'}
                // , {field: 'enable', title: '状态', width: 177, templet: '<div>{{d.enable==1?"启用":"停用"}}</div>'}
                , {field: 'enable', title: '状态', width: 160, templet: '#enable'}
                , {field: 'birthday', title: '生日', sort: true}
                , {field: 'hobby', title: '爱好', sort: true}
                , {
                    fixed: 'right',
                    title: '操作',
                    toolbar: '#barDemo',
                    width: 150
                }
            ]]
        });
    }
});

function gotoAdd() {
    xadmin.open('添加用户', '/html/user/member-add.html', 600, 400);
    return false;
}