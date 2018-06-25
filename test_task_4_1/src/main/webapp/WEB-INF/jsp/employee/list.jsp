<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../header.jsp" %>
<div class="jumbotron">
    <div class="container">
        <div class="list-group">
            <c:forEach items="${pageContext.request.getAttribute('employees')}" var="employee">
            <a href="/employee/view/${employee.id}"
               class="list-group-item list-group-item-action">${employee.name}
                <!--<c:if test="${account!=null}">
                    <c:if test="${account.employee.role.id == 1}">
                        [ Department: ${employee.department.name},
                        Role: ${employee.role.name} ]
                    </c:if>
                </c:if>--></a>
                </c:forEach>
        </div>
        <c:if test="${account!=null}">
            <c:if test="${account.employee.role.id == 1}">
                <a href="/employee/add" class="btn btn-primary btn-block" role="button"
                   aria-pressed="true">Add Employee</a>
            </c:if>
        </c:if>
    </div>
</div>
<%@ include file="../footer.jsp" %>