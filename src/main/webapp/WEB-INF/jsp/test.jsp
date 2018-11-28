<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="x-dns-prefetch-control" content="on" />

<script type="text/javascript" src="/jquery-1.7.1.min.js"></script>
<label><input id="mobile" type="tel" placeholder="请输入手机号"></label>

<input id="getsms" type="button" value="获取验证码" >
<script>
    $("#getsms").click(function(){
        getCode();
        var get_code=$("#getsms");
        time(get_code);
    })


    // 获取验证码
    function getCode(){
        var mobile = $("#mobile").val();

        var get_code_url="/code";
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url: get_code_url,
            data: {"mobile":mobile},
            dataType: "json",
            // complete: function () { },
            success: function (result) {
                //console.debug(result);
                alert(result);

            },
            error: function (result, status) {

            }
        });
    }
    //验证码倒计时
    var wait=60;//时间
    function time(o,p) {//o为按钮的对象，p为可选，这里是60秒过后，提示文字的改变
        if (wait == 0) {
            o.removeAttr("disabled");
            o.val("获取验证码");//改变按钮中value的值
            p.html("如果您在1分钟内没有收到验证码，请检查您填写的手机号码是否正确或重新发送");
            wait = 60;
        } else {
            o.attr("disabled", true);//倒计时过程中禁止点击按钮
            o.val("倒数" + wait + "秒");//改变按钮中value的值
            wait--;
            setTimeout(function() {
                    time(o,p);//循环调用
                },
                1000)
        }
    }
</script>