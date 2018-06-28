<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../header.jsp" %>
<div class="jumbotron">
    <div class="container">

        <form>
            <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" value="${holiday.employee.name}" disabled>
            </div>
            <div class="form-group">
                <label>Date From</label>
                <input type="text" class="form-control" value="${holiday.dateFrom}" disabled>
            </div>
            <div class="form-group">
                <label>Date To</label>
                <input type="text" class="form-control" value="${holiday.dateTo}" disabled>
            </div>
            <div class="form-group">
                <label>Status</label>
                <input type="text" class="form-control" value="${holiday.status.name}" disabled>
            </div>
        </form>

        <c:if test="${currAccount.employee.role.id < 3}">
            <button type="button" class="btn btn-success btn-block" data-toggle="modal"
                    data-target="#acceptedModal">Accepted
            </button>

            <button type="button" class="btn btn-danger btn-block" data-toggle="modal"
                    data-target="#deniedModal">Denied
            </button>

            <!-- Modal Denied -->
            <div class="modal fade" id="deniedModal" tabindex="-1" role="dialog" aria-labelledby="deniedModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deniedModalLabel">Denied Holiday</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Name ${holiday.employee.name}<br>
                            Department ${holiday.employee.department.name}<br>
                            From ${holiday.dateFrom}<br>
                            To ${holiday.dateTo}
                        </div>
                        <div class="modal-footer">
                            <form action="<c:url value="/holiday/denied"/>" method="post">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <input hidden name="holiday_id" value="${holiday.id}">
                                <button type="submit" class="btn btn-danger">Denied</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal Denied -->
            <!-- Modal Accepted -->
            <div class="modal fade" id="acceptedModal" tabindex="-1" role="dialog" aria-labelledby="acceptedModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="acceptedModalLabel">Accepted Holiday</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Name ${holiday.employee.name}<br>
                            Department ${holiday.employee.department.name}<br>
                            From ${holiday.dateFrom}<br>
                            To ${holiday.dateTo}
                        </div>
                        <div class="modal-footer">
                            <form action="<c:url value="/holiday/accepted"/>" method="post">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <input hidden name="holiday_id" value="${holiday.id}">
                                <button type="submit" class="btn btn-danger">Accepted</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal Denied -->
        </c:if>
        <br>
        <c:if test="${currAccount.employee.id == holiday.employee.id}">
            <a href="<c:url value="/holiday/edit/${holiday.id}"/>" class="btn btn-dark btn-block" role="button">Edit</a>
            <button type="button" class="btn btn-outline-danger btn-block" data-toggle="modal"
                    data-target="#deleteModal">
                Delete
            </button>
            <!-- Modal -->
            <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
                 aria-labelledby="deleteModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalLabel">Delete Employee</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Name ${holiday.employee.name}<br>
                            Department ${holiday.employee.department.name}<br>
                            From ${holiday.dateFrom}<br>
                            To ${holiday.dateTo}
                        </div>
                        <div class="modal-footer">
                            <form action="<c:url value="/holiday/delete"/>" method="post">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <input hidden name="holiday_id" value="${holiday.id}">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal -->
        </c:if>
    </div>
</div>
<%@ include file="../footer.jsp" %>