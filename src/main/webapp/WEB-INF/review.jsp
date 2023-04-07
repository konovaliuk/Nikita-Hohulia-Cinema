<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Review</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<style type="text/css">
    table {
        border-collapse: separate;
        border-spacing: 0;
        border-radius: 10px;
        border: 1px solid white;
    }
    table td { padding: 10px; }
    a {
       color: white;
       text-decoration: none;
    }

</style>
</head>
<body>
    <%@ include file="header.html" %>
    <%@ include file="navbar.html"%>
<div align="center" class="container-fluid pt-5 mt-5 bg-dark text-white">
    <h1>Please Review Before Paying</h1>
    <form action="execute_payment" method="post">
    <table class="border-white">
        <tr>
            <td colspan="2"><b>Transaction Details:</b></td>
            <td>
                <input type="hidden" name="paymentId" value=<c:out value="${param.paymentId}"/> />
                <input type="hidden" name="PayerID" value=<c:out value="${param.PayerID}"/> />
            </td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><c:out value="${transaction.description}"/></td>
        </tr>
        <tr>
            <td>Subtotal:</td>
            <td><c:out value="${transaction.amount.details.subtotal}"/> USD</td>
        </tr>
        <tr>
            <td>Shipping:</td>
            <td><c:out value="${transaction.amount.details.shipping}"/> USD</td>
        </tr>
        <tr>
            <td>Tax:</td>
            <td><c:out value="${transaction.amount.details.tax}"/> USD</td>
        </tr>
        <tr>
            <td>Total:</td>
            <td><c:out value="${transaction.amount.total}"/> USD</td>
        </tr>
        <tr><td><br/></td></tr>
        <tr>
            <td colspan="2"><b>Payer Information:</b></td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td><c:out value="${payer.firstName}"/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><c:out value="${payer.lastName}"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><c:out value="${payer.email}"/></td>
        </tr>
        <tr><td><br/></td></tr>
        <tr>
            <td colspan="2"><b>Shipping Address:</b></td>
        </tr>
        <tr>
            <td>Recipient Name:</td>
            <td><c:out value="${shippingAddress.recipientName}"/></td>
        </tr>
        <tr>
            <td>Line 1:</td>
            <td><c:out value="${shippingAddress.line1}"/></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><c:out value="${shippingAddress.city}"/></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><c:out value="${shippingAddress.state}"/></td>
        </tr>
        <tr>
            <td>Country Code:</td>
            <td><c:out value="${shippingAddress.countryCode}"/></td>
        </tr>
        <tr>
            <td>Postal Code:</td>
            <td><c:out value="${shippingAddress.postalCode}"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Pay Now" />
            </td>
        </tr>
    </table>
    </form>
</div>
    <%@ include file="footer.html"%>
</body>
</html>