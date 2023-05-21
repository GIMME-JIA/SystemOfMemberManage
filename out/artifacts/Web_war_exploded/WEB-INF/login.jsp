<%--
  Created by IntelliJ IDEA.
  User: 李嘉富
  Date: 2023/5/13
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <style>
        #cont div {
            text-align: center;
            padding: 15px;
        }

        #cont div:last-child input:first-child {
            margin-right: 30px;
        }

        #cont div:last-child input:first-child {
            margin-left: 30px;
        }

    </style>
</head>
<body>
<div style="margin-top: 150px">
    <jsp:include page="top.jsp"/>
    <div id="cont">
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {

        %>
        <h3 style="color: red;text-align: center">${message}</h3>
        <%
            }
        %>

        <form action="<%=request.getContextPath()%>/loginServlet?type=trueLogin" method="post">
            <div>
                <label for="userName">
                    账户：
                </label>
                <input type="text" name="userName" id="userName">
            </div>
            <div>
                <label for="password">
                    密码：
                </label>
                <input type="password" name="password" id="password">
            </div>
            <div>
                <input type="submit" value="登录">
                <input type="button" onclick="resetForm()" value="重置">
            </div>
        </form>
    </div>
    <jsp:include page="bottom.jsp"/>
</div>
<script>
    function resetForm() {
        document.forms[0].reset();
    }
</script>
</body>
</html>
