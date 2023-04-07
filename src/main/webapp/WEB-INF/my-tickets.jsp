<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>GiGa-Plex</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>

        footer {
            border-top: 1px solid white;
        }
        a {
          color: white;
          text-decoration: none;
        }
    </style>

</head>
<body>
<div class="container-fluid pt-5 ">
    <%@ include file="header.html" %>
    <%@ include file="navbar.html"%>

    <div class="row justify-content-center bg-dark text-white">
        <div class="col-10">
            <h1 class="text-center">My Tickets</h1>
            <hr>
            <c:if test="${not empty bookings}">
                <table class="table table-striped text-white">
                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Date</th>
                            <th>Show ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="booking" items="${bookings}" varStatus="index">
                            <tr class="text-white">
                                <td class="text-white"><c:out value="${booking.bookingId}"/></td>
                                <td class="text-white"><c:out value="${booking}"/></td>
                                <td class="text-white"><c:out value="${booking.showId}"/></td>

                                <td>
                                    <table class="table text-white">
                                        <thead>
                                            <tr>
                                                <th>Row</th>
                                                <th>Number</th>
                                                <th>Seat Price</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="seat" items="${seats[index.index]}">
                                                <tr>
                                                    <td><c:out value="${seat.row}"/></td>
                                                    <td><c:out value="${seat.number}"/></td>
                                                    <td><c:out value="${seat.seatPrice}"/></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty bookings}">
                <p class="text-center">You have no bookings at the moment.</p>
            </c:if>
        </div>
    </div>

</div>
    <%@ include file="footer.html"%>
</body>
</html>