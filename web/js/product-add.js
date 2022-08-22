$(function () {
    layui.use(['layedit', 'form', 'upload', 'laydate', 'table'], function () {
        let form = layui.form;
        let laydate = layui.laydate;
        let upload = layui.upload;
        let layedit = layui.layedit;
        let imgHref = '';

        //    页面加载完成首先需要加载菜系
        //    先将菜系内容查询出来，然后赋值即可
        let res = myAjax("/back/fenLei/findAll", {isShow: 1}, "get");
        if (res != null) {
            setFenLeiData(res.data);
        }

        function setFenLeiData(data) {
            let html = '<option value=""></option>';
            for (let i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
            }
            $("#fenlei").html(html);
            form.render();
        }

        //常规使用 - 普通图片上传
        var uploadInst = upload.render({
            elem: '#test1',
            url: '/upload' //此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            },
            done: function (res) {
                console.log(res);
                //将图片上传的地址得到后赋值给全局变量
                imgHref = res.data.src;
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
            }
        });

        layedit.set({
            uploadImage: {
                url: '/upload' //接口url
                , type: '' //默认post
            }
        });
        let index = layedit.build('content'); //建立编辑器
        form.on('submit(add)', function (data) {
            data = data.field;
            data.imgHref = imgHref;
            data.content = layedit.getContent(index)
            console.log(data);
            let res=myAjax("/back/product/add",data,'post');
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
})