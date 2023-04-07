<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Receipt</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<style type="text/css">
    table { border: 0; }
    table td { padding: 5px; }
</style>
</head>
<body>
    <%@ include file="header.html" %>
    <%@ include file="navbar.html"%>
<div align="center" class="container-fluid pt-5 mt-5 bg-dark text-white">
    <h1>Payment Done. Thank you for purchasing our products</h1>
    <br/>
    <h2>Receipt Details:</h2>
    <table>
        <tr>
            <td><b>Merchant:</b></td>
            <td>Company ABC Ltd.</td>
        </tr>
        <tr>
            <td><b>Payer:</b></td>
            <td><c:out value="${payer.firstName} ${payer.lastName}"/></td>
        </tr>
        <tr>
            <td><b>Description:</b></td>
            <td><c:out value="${transaction.description}"/></td>
        </tr>
        <tr>
            <td><b>Subtotal:</b></td>
            <td><c:out value="${transaction.amount.details.subtotal}"/> USD</td>
        </tr>
        <tr>
            <td><b>Shipping:</b></td>
            <td><c:out value="${transaction.amount.details.shipping}"/> USD</td>
        </tr>
        <tr>
            <td><b>Tax:</b></td>
            <td><c:out value="${transaction.amount.details.tax}"/> USD</td>
        </tr>
        <tr>
            <td><b>Total:</b></td>
            <td><c:out value="${transaction.amount.total}"/> USD</td>
        </tr>
    </table>
</div>
    <%@ include file="footer.html"%>
</body>
</html>