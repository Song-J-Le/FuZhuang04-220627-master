$(function () {
    layui.use(['layedit', 'form', 'upload', 'laydate'], function () {
        let form = layui.form;
        let laydate = layui.laydate;
        let upload = layui.upload;
        layedit = layui.layedit;
        //常规使用 - 普通图片上传
        var uploadInst = upload.render({
            elem: '#test1',
            url: '/upload' //此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
            ,
            before: function (obj) {
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
        //页面加载完成，需要将所有菜系赋值给下拉菜单
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
            //    当下拉蔡丹赋值完成，需要根据产品id查询当前产品
            findById();
        }

        function findById() {
            let id = sessionStorage.getItem("news");
            let res = myAjax("/back/news/findById", {id: id}, "get");
            console.log(res);
            if (res != null) {
                setNewsData(res.data);
            }
        }
        function setNewsData(data) {
            $("#fenlei").val(data.fenleiId);
            $("#name").val(data.name)

            $("input[type=radio][name=isShow][value=" + data.isShow + "]").attr("checked", "checked")
            $("input[type=radio][name=isRecommend][value=" + data.isRecommend + "]").attr("checked", "checked")
            $("#demo1").attr("src", data.imgHref);
            layedit.setContent(index, data.content);
            form.render();
        }

        form.on("submit(edit)", function (data) {
            data = data.field;
            data.imgHref = imgHref;//图片地址
            data.content = layedit.getContent(index);
            data.id = sessionStorage.getItem("news");
            console.log(data);
            let res=myAjax("/back/news/update",data,'post');
            if (res.count > 0) {
                layer.alert("编辑成功", {
                        icon: 6
                    },
                    function () {
                        //关闭当前frame
                        xadmin.close();

                        // 可以对父窗口进行刷新
                        xadmin.father_reload();
                    });
            } else {
                layer.alert("编辑失败");
            }
            return false;
        })

    })

})