<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<div class="jumbotron">
    <div class="container">
        <c:if test="${param.error}">
            <div class="alert alert-danger" role="alert">
                    ${param.error}
            </div>
        </c:if>
    </div>
</div>
<%@ include file="footer.jsp" %>
