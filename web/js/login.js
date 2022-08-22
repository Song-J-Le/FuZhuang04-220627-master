//  页面加载完成执行里面的代码
$(function () {
    //加载layui模板
    layui.use('form', function () {
        // 得到当前的模板： Object obj=new Object（）；
        // 定义数据：js定义数据老版本用var
        //新版本 let 和const
        // let  定义变量   const定义常量
        let form = layui.form;
        //如果用var ，我们可以在外层拿到里层的数据
        //var 不严谨，以后不要用了
        // var a=0;
        // let aa=0;
        // for (int i = 0; i < 5; i++) {
        //     var b=0;
        //     let bb=0;
        // }
        // console.log(bb);
        // console.log(b);//System.out.println("b");
        //js 有哪些数据类型呢？字符，数字，布尔，对象，数组
        // 字面量：值   标识符：名称
        // let str = "aaaa";
        // let num = 1;
        // let boo = true;
        // let obj = {name: "aaaa", "age": 18};
        // let obja = {"name": "aaaa", 'age': "18"};
        // obj.age=11;let age=obj.age;
        //对象 以{}的形式，里面有无数个属性：key：value
        // json和对象有什么区别？json对象一般情况下 key需要加引号
        //js里面不区分单双引号
        let arr = ["aaa", "18", "true"];//List<String>
        // let arra={arr};
        // console.log(typeof str);
        // console.log(typeof num);
        // console.log(typeof boo);
        // console.log(typeof obj);
        // console.log(typeof arr);
        // for (let i = 0; i < arr.length; i++) {
        // console.log(arr[i]);
        // }
        // arr.forEach(function (index,value){
        //     console.log(arr[value],index);
        // });
        // console.log(arr);
        // console.log(arr.toString());
        // console.log(arr.toLocaleString());
        // 字符串相关用法？
        // indexOf  charAt subString replace
        // split toString equals
        // startWith endWith content
        //js 比较相等用==  如果要比较值和地址 用===
        // 数组的常用方法？length,toString,size,pop push shift unshift
        // 熟练使用数组的增加和遍历
        // 前端删除 一般用  remove
        // -----------------------------------------------------
        // js 的方法定义
        // String name="aaa";
        //  void name(String a){this.name=a;}
        // let name = "aaa";
        // function定义方法  let  const 定义属性
        // function getName(a) {
        //     this.name = a;
        //    return "aa";
        // }
        // console.log(new Date().Format("yyyy"));
        // 如果你们用的框架，则可以在框架的帮助文档，或在百度里面搜索，
        // 如果看不懂，则表示属于程序员自己定义的方法
        // 规范：格式文档
        // layer.msg('玩命卖萌中', function () {
        //     //关闭后的操作
        //     console.log(11)
        // });
        // js里面一般情况下看到有on表示想对其添加一个监听事件
        // let aa = document.getElementById("test");
        // aa.addEventListener("click",function (){
        //     console.log(11)
        // })
        // js不要和jquery进行混合编程
        // $('#item').on('click',function() {
        //     $(this).html("修改html");
        // })
        //监听提交,监听点击事件
        //自定义验证规则
        form.verify({
            username: [/^[a-zA-Z0-9_-]{4,16}$/, "4到16位（字母，数字，下划线，减号）"]
            // phone: [/^[1](([3][0-9])|([4][014-9])|([5][0-35-9])|([6][2567])|([7][0-8])|([8][0-9])|([9][0-3,5-9]))[0-9]{8}$/,"手机号不正确"],
            // ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            // , code: [/^[a-z0-9]{5}$/, "验证码只能是5个字符"]

        });
        form.on('submit(login)', function (data) {
            data = data.field;//重新赋值
            // alert(888)//js 默认弹出警告框
            // console.log(data);//得到当前的form表单所有数据
            console.log(data);//只得到form表单里面有name属性的字段，类型为对象
            // console.log(JSON.stringify(data.field));//将对象转换为json对象的字符串
            //以后，我们前后端数据传值全部用json对象，而不是字符串
            // layer.msg(JSON.stringify(data.field), function () {
            // location.href = 'index.html';//跳转页面
            // });
            //拿到用户输入的数据，后应该怎么办？
            // 登录逻辑:
            // 不让按钮刷新页面
            //ajax发送到后台
            $.ajax({
                url: '/login',//对应后台webServlet的注解地址
                data: data,//传递到后台的数据，以后全部用对象
                type: 'post',//对应后台方法
                dataType: 'json',//固定用json对象
                success: function (res) {//后台传递给前端的数据
                    //最容易出错的地方：dateType，datetype，‘html，text’
                    //content:'json/application;charset=UTF-8',
                    console.log(res);

                    if (res.msg == 'error-code') {
                        layer.alert("验证码错误");
                    } else if (res.msg == 'error') {
                        layer.alert("用户名或者密码错误");
                    } else {
                        sessionStorage.setItem("username", data.username);
                        location.href = '/html/index.html';//跳转页面
                    }

                }
            });
            return false;
        });

    });
})



function checkCode(obj) {
    $("#code").focus();
    $(obj).attr("src", "/code?a=" + Math.random());
}