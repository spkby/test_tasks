<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<div class="jumbotron">
    <div class="container">
        <ul>
            <c:forEach items="${pageContext.request.getAttribute('departments')}" var="department">
                <li>
                    <a href="/department/view/${department.id}">${department.name}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<%@ include file="footer.jsp" %>