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
            margin-bottom: 20px;
            position: relative;
            width: 100%;
            height: 300px;
            border: 1px solid white;
            border-radius: 10px;
            overflow: hidden;
        }

        .movie img {
            width: 100%;
            height: 100%;
            border-radius: 10px;
            object-fit: cover;
            transition: transform 0.5s ease;
        }

        .movie:hover img {
            transform: scale(1.2);
        }

        .movie-info {
            position: absolute;
            bottom: 0;
            left: 0;
            right: 0;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 10px;
            color: #fff;
            opacity: 0;
            transition: opacity 0.5s ease;
        }

        .movie:hover .movie-info {
            opacity: 1;
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

    <div class="row mt-5">
    <c:forEach var="movie" items="${movies}" varStatus="index">
        <div class="col-md-4">
            <div class="movie">
                <img src="<c:url value="${paths[movie.title]}"/>">
                <div class="movie-info">
                    <h2><c:out value="${movie.title}"/></h2>
                    <p><c:out value="${movie.description}"/></p>
                    <p><c:out value="${movie.duration}"/></p>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>

    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link" href="<c:url value='?page=${currentPage-1}'/>">&laquo;</a></li>
            </c:if>
            <c:forEach var="i" begin="1" end="${totalPages}">
                <c:choose>
                    <c:when test="${i eq currentPage}">
                        <li class="page-item active"><a class="page-link" href=""><c:out value="${i}"/></a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="<c:url value='?page=${i}'/>"><c:out value="${i}"/></a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage != totalPages}">
                <li class="page-item"><a class="page-link" href="<c:url value='?page=${currentPage+1}'/>">&raquo;</a></li>
            </c:if>
        </ul>
    </nav>
        <%@ include file="footer.html"%>
    </body>
    </html>