<%@ page import="com.beans.res.TableResult" %>
<%@ page import="com.dao.MemberDao" %>
<%@ page import="com.beans.entity.MemberDO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.beans.vo.MemberVO" %><%--
  Created by IntelliJ IDEA.
  User: 李嘉富
  Date: 2023/5/15
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成员管理</title>
    <style>
        table {
            border-collapse: collapse;
            border-spacing: 0;
            border: 1px solid black;
        }

        tr {
            line-height: 2;
        }

        th, td {
            border: 1px solid black;
            padding: 0 10px;
        }

        #cont {
            text-align: left;
            margin-left: 560px;
            margin-top: 50px;
            line-height: 1.5;
        }

        .nav, .search, .page {
            line-height: 2;
        }
    </style>
</head>
<body>
<div>
    <jsp:include page="../top.jsp"/>
    <div id="cont">
        <section class="nav">
            <%--            <a href="#">成员管理</a>--%>
            <%--            <a href="#">成绩管理</a>--%>
        </section>
        <section class="search">
            <%
                TableResult<MemberVO> tableResult = (TableResult) request.getAttribute("tableResult");
            %>
            <a href="<%=request.getContextPath()%>/memberServlet?type=toAdd">新增成员</a>
            <form method="post" action="<%=request.getContextPath()%>/memberServlet?type=toMemberManage">
                <input type="text" name="memberName" value="<%=tableResult.getMemberName()%>"
                       placeholder="输入成员姓名"/>
                <%--    value在没有被修改的时候就是1
                        由于不是ajax局部刷新
                --%>
                <input type="hidden" name="pageNow" id="pageNow" value="1">
                <input type="submit" value="查询">
            </form>
        </section>
        <section>
            <table>
                <thead>
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>学号</th>
                    <th>生日</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<MemberVO> data = tableResult.getData();
                    for (int i = 0; i < data.size(); i++) {
                        MemberVO memberVO = data.get(i);
                %>

                <tr>
                    <td><%=memberVO.getId()%>
                    </td>
                    <td><%=memberVO.getName()%>
                    </td>
                    <td><%=memberVO.getNo()%>
                    </td>
                    <td><%=memberVO.getBirthDay()%>
                    </td>
                    <td>
                        <a href="<%=request.getContextPath()%>/memberServlet?type=delete&id=<%=memberVO.getId()%>">删除</a>
                        <a href="<%=request.getContextPath()%>/memberServlet?type=toUpdate&id=<%=memberVO.getId()%>&pageNow=<%=tableResult.getPageNow()%>">更新</a>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
            <div class="page">
                <%
                    // 只要不是第一页就显示
                    if (tableResult.getPageNow() != 1) {
                %>
                <a href="#" onclick="goFirst()">首页</a>
                <a href="#" onclick="goPre()">上一页</a>
                <%
                    }
                %>

                <%
                    // 只要不是最后一页就显示
                    if (tableResult.getPageNow() != tableResult.getPageCount()) {
                %>
                <a href="#" onclick="goNext()">下一页</a>
                <%--                <a href="#" onclick="goLast()">尾页</a>--%>
                <%
                    }
                %>

                <%--                <span>共<%=tableResult.getPageCount()%>页</span>--%>
                <%--                <span>,共<%=tableResult.getTotalCount()%>条</span>--%>
                <span>,当前是<%=tableResult.getPageNow()%>页</span>
            </div>
        </section>
    </div>
    <jsp:include page="../bottom.jsp"/>
</div>
<script>
    // 到首页
    function goFirst() {
        document.forms[0].submit();
    }

    // 到上一页
    function goPre() {
        // 拿到当前页
        var currentPage = "<%=tableResult.getPageNow()%>";
        var prePage = parseInt(currentPage) - 1;
        // 修改搜索里提交的pageNow
        document.getElementById("pageNow").value = prePage;
        document.forms[0].submit();
    }

    // 到下一页
    function goNext() {
        // 拿到当前页
        var currentPage = "<%=tableResult.getPageNow()%>";
        var nextPage = parseInt(currentPage) + 1;
        // 修改搜索里提交的pageNow
        document.getElementById("pageNow").value = nextPage;
        document.forms[0].submit();
    }

    // 到尾页 todo 这里有空指针异常
    function goLast() {
        // 拿到尾页
        var pageCountStr = "<%=tableResult.getPageCount()%>";
        // 修改搜索里提交的pageNow
        document.getElementById("pageNow").value = parseInt(pageCountStr);
        document.forms[0].submit();
    }
</script>
</body>
</html>
