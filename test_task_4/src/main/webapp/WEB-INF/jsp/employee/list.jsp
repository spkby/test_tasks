<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../header.jsp" %>
<div class="jumbotron">
    <div class="container">

        <c:if test="${employees.size() != 0}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Department</th>
                    <th scope="col">Role</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${employees}" var="employee">
                    <tr>
                        <td>${employee.name}</td>
                        <td>${employee.department.name}</td>
                        <td>${employee.role.name}</td>
                        <td><a href="/employee/view/${employee.id}">Details</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${currAccount != null && currAccount.employee.role.id eq 1}">
            <a href="<c:url value="/employee/add"/>" class="btn btn-primary btn-block" role="button"
               aria-pressed="true">Add Employee</a>
        </c:if>
    </div>
</div>
<%@ include file="../footer.jsp" %>