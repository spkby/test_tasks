<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<div class="jumbotron">
    <div class="container">
        <c:choose>
            <c:when test="${currAccount != null}">
                <h2>Hello ${currAccount.employee.name}</h2>
            </c:when>
            <c:otherwise>
                <h2>Hello Guest</h2>
                <a href="/login">Login</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@ include file="footer.jsp" %>