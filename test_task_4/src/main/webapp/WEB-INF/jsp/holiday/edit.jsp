<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../header.jsp" %>
<div class="jumbotron">
    <div class="container">
        <c:if test="${param.error != null}">
            <div class="alert alert-danger" role="alert">
                Error: ${param.error}
            </div>
        </c:if>

        <form action="<c:url value="/holiday/edit"/>" method="post" class="needs-validation" novalidate>
            <input hidden value="${holiday.id}" name="holiday_id">
            <div class="form-group">
                <label>From</label>
                <input type="text" class="form-control" name="dateFrom" value="${holiday.dateFrom}"
                       placeholder="From YYYY-MM-DD" required>
                <div class="invalid-feedback">
                    Please enter from.
                </div>
            </div>
            <div class="form-group">
                <label>To</label>
                <input type="text" class="form-control" name="dateTo" value="${holiday.dateTo}"
                       placeholder="To YYYY-MM-DD" required>
                <div class="invalid-feedback">
                    Please enter to.
                </div>
            </div>

            <a href="<c:url value="/holiday/view/${holiday.id}"/>" class="btn btn-secondary btn-block" role="button"
               aria-pressed="true">Back</a>
            <button type="submit" class="btn btn-dark btn-block">Update</button>
        </form>

    </div>
</div>
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
<%@ include file="../footer.jsp" %>