<%@ page import="com.beans.vo.MemberVO" %><%--
  Created by IntelliJ IDEA.
  User: 李嘉富
  Date: 2023/5/13
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新成员信息页面</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <style>
        #cont {
            text-align: center;
            margin-top: 150px;
        }

        #cont div {
            padding: 15px;
        }
    </style>
</head>
<body>
<div>
    <jsp:include page="../top.jsp"/>
    <div id="cont">
        <form action="<%=request.getContextPath()%>/memberServlet?type=update&pageNow=${requestScope.pageNow}"
              method="post">
            <%
                MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
            %>
            <div>
                <label>
                    编号：
                </label>
                <input type="text" readonly name="memberId" value="<%=memberVO.getId()%>">
            </div>
            <div>
                <label>
                    姓名：
                </label>
                <input type="text" name="memberName" value="<%=memberVO.getName()%>">
            </div>
            <div>
                <label>
                    学号：
                </label>
                <input type="text" name="no" value="<%=memberVO.getNo()%>">
            </div>
            <div>
                <label>
                    生日：
                </label>
                <input type="text" name="birthDay" placeholder="yyyy-MM-dd HH:mm:ss"
                       value="<%=memberVO.getBirthDay()%>">
            </div>
            <div>
                <input type="submit" value="更新">
            </div>
        </form>
    </div>
    <jsp:include page="../bottom.jsp"/>
</div>
</body>
</html>
