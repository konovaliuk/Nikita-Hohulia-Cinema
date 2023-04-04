<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>GiGa-Plex</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        .movie {
            display: flex;
            align-items: center;
            border-bottom: 1px solid white;

        }

        .movie img {
            width: 200px;
            height: 300px;
            object-fit: cover;
            object-position: center;
            margin-right: 20px;
            border: 1px solid white;
            border-radius: 10px;
            transition: all 0.3s ease-in-out;
        }

        .movie img:hover {
            transform: scale(1.1);
        }

        .movie-info {
            flex: 1;
            border: 1px solid white;
            border-radius: 10px;

        }


        .movie-info h2,
        .movie-info p {
            padding: 10px;
        }
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
<div class="container-fluid pt-5 bg-dark text-white">
    <%@ include file="header.html" %>
    <%@ include file="navbar.html"%>

    <div class="movie pt-5 pb-5">
            <img src="<c:url value="${path}"/>">
            <div class="movie-info">
                <h2><c:out value="${movie.title}"/></h2>
                <p><c:out value="${movie.description}"/></p>
                <p><c:out value="${movie.duration}"/></p>
            </div>
        </div>

<c:if test="${not empty scheduleToday}">
    <div class="container pt-5 bg-dark text-white">
        <h2>Today</h2>
        <div class="row mt-5">
            <c:forEach var="show" items="${scheduleToday}" varStatus="index">
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-body">
                            <h4 class="card-title text-dark"><c:out value="${show.startTime}"/> - <c:out value="${show.endTime}"/></h4>
                            <p class="card-text text-dark">Hall number: <c:out value="${show.hallId}"/></p>
                            <a href="<c:url value='/booking' ><c:param name='showId' value='${show.showId}' /></c:url>" class="btn btn-primary">Buy Ticket</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<c:if test='${not empty scheduleTomorrow}'>
    <div class="container pt-5 bg-dark text-white">
        <h2>Tomorrow</h2>
        <div class="row mt-5">
            <c:forEach var="show" items="${scheduleTomorrow}" varStatus="index">
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-body">
                            <h4 class="card-title text-dark"><c:out value="${show.startTime}"/> - <c:out value="${show.endTime}"/></h4>
                            <p class="card-text text-dark">Hall number: <c:out value="${show.hallId}"/></p>
                            <a href="<c:url value='/booking' ><c:param name='showId' value='${show.showId}' /></c:url>" class="btn btn-primary">Buy Ticket</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<c:if test='${not empty scheduleTheDayAfterTomorrow}'>
    <div class="container pt-5 bg-dark text-white">
        <h2>The Day After Tomorrow</h2>
        <div class="row mt-5">
            <c:forEach var="show" items="${scheduleTheDayAfterTomorrow}" varStatus="index">
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-body">
                            <h4 class="card-title text-dark"><c:out value="${show.startTime}"/> - <c:out value="${show.endTime}"/></h4>
                            <p class="card-text text-dark">Hall number: <c:out value="${show.hallId}"/></p>
                            <a href="<c:url value='/booking' ><c:param name='showId' value='${show.showId}' /></c:url>" class="btn btn-primary">Buy Ticket</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>
</div>


    <%@ include file="footer.html"%>
</body>
</html>
