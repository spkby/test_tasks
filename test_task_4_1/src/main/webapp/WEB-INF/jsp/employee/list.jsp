<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../header.jsp" %>
<div class="jumbotron">
    <div class="container">
        <ul>
            <c:forEach items="${pageContext.request.getAttribute('employees')}" var="employee">
                <li>
                    <a href="/employee/view/${employee.id}">${employee.name} </a> ${employee.department.name}
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<%@ include file="../footer.jsp" %>