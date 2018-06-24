<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<div class="jumbotron">
    <div class="container">
        <table>
            <tr>
                <td>id</td>
                <td>${employee.id}</td>
            </tr>
            <tr>
                <td>name</td>
                <td>${employee.name}</td>
            </tr>
            <tr>
                <td>department</td>
                <td>${employee.department.name}</td>
            </tr>
            <tr>
                <td>birthday</td>
                <td>${employee.birthday}</td>
            </tr>
            <tr>
                <td>role</td>
                <td>${employee.role.name}</td>
            </tr>
        </table>
    </div>
</div>
<%@ include file="footer.jsp" %>