$(function () {
    layui.use(['form', 'laydate', 'table'], function () {
        let form = layui.form;
        let laydate = layui.laydate;
        let table = layui.table;
        laydate.render({
            elem: '#start',
            trigger: 'click'
        });
        laydate.render({
            elem: '#end',
            trigger: 'click'
        });
        table.render({
            elem: '#test'
            , url: '/back/news/findAll'
            , where: {}
            , page: true //开启分页
            ,parseData: function(res){ //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
            , cols: [[ //表头
                {field: 'id', title: 'ID', sort: true, fixed: 'left'}
                , {field: 'name', title: '新闻名称'}
                , {field: 'content', title: '新闻内容'}
                , {field: 'enable', title: '状态',templet:'#enable'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
            ]]
        });
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                //弹出确定窗口
                layer.confirm('真的删除行么', function (index) {
                    //调用后台删除：ajax
                    let res = myAjax("/back/news/delete", {id: data.id}, 'post');
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
                sessionStorage.setItem("newsId", data.id);
                xadmin.open('编辑产品', '/html/news/news-edit.html', 600, 400);
            }
        });
        form.on('switch(enable)', function (obj) {
            let res = myAjax("/back/news/enable", {id: this.value}, 'post');
            if (res.count == 1) {
                layer.tips("修改状态成功", obj.othis);
            } else {
            }
        })
    })
})
function gotoAdd() {
    xadmin.open('添加产品', '/html/news/news-add.html', 600, 400);
    return false;
}