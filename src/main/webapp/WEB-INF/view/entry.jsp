<%--
  Desc:
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="base/head.jsp" %>
</head>
<body>
<br>
<%@ include file="base/title.jsp" %>

<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <form id="frm_login" method="post" class="am-form" >
            <label for="name">账号:</label>
            <input type="text" name="name" id="name" value="admin">
            <br>
            <label for="password">密码:</label>
            <input type="password" name="password" id="password" value="">
            <br>
            <div class="am-cf">
                <input type="submit" id="btn_login" value="登 录"
                       class="am-btn am-btn-primary am-btn-sm am-fl">
                <input type="submit" name="" value="忘记密码 ^_^? "
                       class="am-btn am-btn-default am-btn-sm am-fr">
            </div>
        </form>
    </div>
</div>

<%@ include file="base/copyright.jsp" %>


<script src="${base}/component/jquery/2.1.3/jquery.min.js"></script>
<script>
    var me = '<%=session.getAttribute("me") %>';
    var base = '${base}';
    $(function() {
        $("#btn_login").click(function() {
            $.ajax({
                url : base + "/login.io",
                type: "POST",
                data:$('#frm_login').serialize(),
                dataType:"json",
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
//                    console.log("log - " + JSON.stringify(data));
                    if (data.ok == true) {
//                        alert("登陆成功");
                        document.location = "${base}/main.io";
                    } else {
                        alert(data.msg);
                    }
                }
            });
            return false;
        });
    });
</script>

</body>
</html>
