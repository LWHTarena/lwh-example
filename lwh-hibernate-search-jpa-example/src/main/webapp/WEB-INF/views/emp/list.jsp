<%--
  Created by IntelliJ IDEA.
  User: liwh
  Date: 2018/9/6
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工记录</title>
</head>
<body>
<c:if test="${page == null || page.numberOfElements == 0 }">
    <h3>没有员工记录！</h3>
</c:if>

<c:if test="${page != null && page.numberOfElements > 0 }">

    <table border="1" cellspacing="0" cellpadding="10">

        <tr>
            <th>Id</th>
            <th>LastName</th>

            <th>Email</th>
            <th>Birth</th>

            <th>CreateTime</th>
            <th>DepartmentName</th>

            <th>Edit</th>
            <th>Delete</th>
        </tr>

        <c:forEach items="${page.content }" var="emp">

            <tr>
                <td>${emp.id }</td>
                <td>${emp.lastName }</td>

                <td>${emp.email }</td>
                <td>${emp.birth }</td>

                <td>${emp.createTime }</td>
                <td>${emp.department.departmentName }</td>

                <td>
                    <a href="${pageContext.request.contextPath }/emp/${emp.id}">Edit</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath }/emp/${emp.id}" class="delete">Delete</a>
                    <input type="hidden" value="${emp.lastName }">
                </td>
            </tr>

        </c:forEach>

        <tr>
            <td colspan="8">
                共 ${page.totalElements} 条记录  &nbsp;&nbsp;&nbsp;
                共 ${page.totalPages} 页  &nbsp;&nbsp;&nbsp;
                当前为 ${page.number + 1 } 页  &nbsp;&nbsp;&nbsp;

                <c:if test="${page.number + 1 > 1 }">
                    <a href="?pageNo=${page.number + 1 - 1 }">上一页</a>  &nbsp;&nbsp;&nbsp;
                </c:if>

                <c:if test="${page.number + 1 < page.totalPages}">
                    <a href="?pageNo=${page.number + 1 + 1 }">下一页</a>  &nbsp;&nbsp;&nbsp;
                </c:if>
            </td>
        </tr>

    </table>

</c:if>
</body>
</html>
