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

        <a href="/holiday/list" class="btn btn-secondary btn-block" role="button"
           aria-pressed="true">Back</a>

        <c:if test="${currAccount.employee.role.id < 3}">
            <button type="button" class="btn btn-success btn-block" data-toggle="modal"
                    data-target="#acceptedModal">Accepted
            </button>

            <button type="button" class="btn btn-outline-danger btn-block" data-toggle="modal"
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
                            <form action="/holiday/denied" method="post">
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
                            <form action="/holiday/accepted" method="post">
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
    </div>
</div>
<%@ include file="../footer.jsp" %>