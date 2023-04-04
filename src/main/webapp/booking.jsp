<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>GiGa-Plex</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        a {
          color: white;
          text-decoration: none;
        }
        footer {
        border-top:solid 1px white;
        }
    </style>
</head>
<body>
    <%@ include file="header.html" %>
    <%@ include file="navbar.html"%>

    <div class="container-fluid pt-5 bg-dark text-white">
        <h1>Choose your seats:</h1>
        <form method="post" action="<c:url value='/checkout'><c:param name='showId' value='${showId}' /></c:url>" class="text-center">

            <c:forEach items="${seats}" var="row" varStatus="rowI">
                <div class="row">
                    <c:forEach items="${row}" var="seat"  varStatus="numberI">
                        <div class="col-md-1">
                            <label class="btn btn-outline-secondary">
                                <input type="checkbox" name="selectedSeats" value=<c:out value="${rowI.index+1}-${numberI.index+1}-${seat[1]}"/> <c:if test="${seat[0] == 1}">disabled</c:if>> <c:out value="${rowI.index+1}-${numberI.index+1}    ${seat[1]}"/>
                            </label>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>

            <br>
            <button type="submit" class="btn btn-primary">Buy Tickets</button>
        </form>
        <%@ include file="footer.html"%>
    </div>

</body>
</html>