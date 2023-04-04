<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>



<%@ page import="java.util.*,javax.servlet.*,javax.servlet.http.*" %>
<%@ page import="com.stripe.Stripe, com.stripe.model.Charge" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <%@ include file="header.html" %>
    <%@ include file="navbar.html"%>
	<div class="container-fluid mt-5 pt-5 bg-dark text-white">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header bg-dark text-white">
						<h4>Payment Form</h4>
					</div>
					<div class="card-body bg-dark text-white">
						<form method="POST" action="/Cinema/pay" >
                            <div class="form-group">
                                <label for="amount">Amount:</label>
                                <input id="amount" class="form-control" type="number" min="0.01" step="0.01" value=<c:out value="${amount}"/> readonly="true"/>
                                <input type="hidden" name="amount" value=<c:out value="${amount}"/>/>
                            </div>
                            <div class="form-group">
                                <label for="cardNumber">Card Number:</label>
                                <input type="text" id="cardNumber" class="form-control" required="true" placeholder="Enter card number"/>
                            </div>
                            <div class="form-group">
                                <label for="cardExpiry">Expiration Date:</label>
                                <div class="input-group">
                                    <input type="text" id="cardExpiry" class="form-control" required="true" placeholder="MM / YY"/>
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="cardCVC">CVC:</label>
                                <input type="text" id="cardCVV" class="form-control" required="true" placeholder="Enter CVC"/>
                            </div>

                            <c:if test="${error != null}">
                                 <div class="alert alert-danger" role="alert">
                                     <c:out value="${error}"/>
                                 </div>
                            </c:if>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Submit Payment</button>
                            </div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="footer.html"%>

</body>
</html>