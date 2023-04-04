<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%@ page import="java.util.*,javax.servlet.*,javax.servlet.http.*" %>


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
                      <form action="/Cinema/authorize_payment" method="POST">
                        <div class="row">
                          <div class="col-6 text-center">
                            <label for="seats" class="form-label">Seats:</label>
                            <input type="text" id="seats" name="product" class="form-control" readonly="true" value=<c:out value="${seatsString}"/> />
                          </div>
                          <div class="col-6 text-center">
                            <label for="subtotal" class="form-label">Sub Total:</label>
                            <input type="text" id="subtotal" name="subtotal" class="form-control" readonly="true" value=<c:out value="${amount*0.8}"/> />
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-6 text-center">
                            <label for="shipping" class="form-label">Shipping:</label>
                            <input type="text" id="shipping" name="shipping" class="form-control" readonly="true" value=<c:out value="0"/> />
                          </div>
                          <div class="col-6 text-center">
                            <label for="tax" class="form-label">Tax:</label>
                            <input type="text" id="tax" name="tax" class="form-control" readonly="true" value=<c:out value="${amount*0.2}"/> />
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-12 text-center">
                            <label for="total" class="form-label">Total Amount:</label>
                            <input type="text" id="total" name="total" class="form-control" readonly="true" value=<c:out value="${amount}"/> />
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-12 text-center">
                            <input class="btn btn-outline-light mt-5 btn-lg px-5" type="submit" value="Checkout" />
                          </div>
                        </div>
                      </form>
                    </div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="footer.html"%>

</body>
</html>