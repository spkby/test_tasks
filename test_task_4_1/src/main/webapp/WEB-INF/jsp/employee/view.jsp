<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../header.jsp" %>
<div class="jumbotron">
    <div class="container">
        <form>
            <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" value="${employee.name}" disabled>
            </div>
            <div class="form-group">
                <label>Birthday</label>
                <input type="text" class="form-control" value="${employee.birthday}" disabled>
            </div>
            <div class="form-group">
                <label>Department</label>
                <input type="text" class="form-control" value="${employee.department.name}" disabled>
            </div>
            <div class="form-group">
                <label>Role</label>
                <input type="text" class="form-control" value="${employee.role.name}" disabled>
            </div>
            <div class="form-group">
                <label>Salary</label>
                <input type="text" class="form-control" value="${employee.salary.quantity}" disabled>
            </div>
            <div class="form-group">
                <label>Login</label>
                <input type="text" class="form-control" value="${loginEmployee}" disabled>
            </div>
        </form>

        <a href="/holiday/employee/${employee.id}" class="btn btn-success btn-block" role="button"
           aria-pressed="true">Holidays</a>

        <a href="/employee/edit/${employee.id}" class="btn btn-secondary btn-block" role="button">Edit</a>
        <c:if test="${currLogin!=loginEmployee}">
            <button type="button" class="btn btn-outline-danger btn-block" data-toggle="modal"
                    data-target="#exampleModal">
                Delete
            </button>
        </c:if>
        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Delete Employee</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Name ${employee.name}<br>
                        Birthday ${employee.birthday}<br>
                        Department ${employee.department.name}<br>
                        Role ${employee.role.name}
                    </div>
                    <div class="modal-footer">
                        <form action="/employee/delete" method="post">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <input hidden name="employee_id" value="${employee.id}">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<%@ include file="../footer.jsp" %>