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
            position: relative;
            margin-bottom: 20px;
            width: 100%;
            height: 600px;
            border: 1px solid white;
            border-radius: 10px;
            overflow: hidden;
        }

        .movie img {
            width: 100%;
            height: 100%;
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

    <div class="container pt-5 bg-dark text-white">
        <h2>Today</h2>
        <div class="row mt-5">
            <div id="carousel-today" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <c:forEach var="movie" items="${moviesToday}" varStatus="index">
                        <div class="carousel-item <c:if test="${index.first}">active</c:if>">
                            <a href="<c:url value='/show' ><c:param name='movieId' value='${movie.movieId}' /></c:url>" ontouchstart="">
                                <div class="movie">
                                    <img src="<c:url value="${paths[movie.title]}"/>">
                                    <div class="movie-info">
                                        <h2><c:out value="${movie.title}"/></h2>
                                        <p><c:out value="${movie.description}"/></p>
                                        <p><c:out value="${movie.duration}"/></p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carousel-today" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carousel-today" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    </div>

    <div class="container pt-5 bg-dark text-white">
            <h2>Tomorrow</h2>
            <div class="row mt-5">
                <div id="carousel-tomorrow" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <c:forEach var="movie" items="${moviesTomorrow}" varStatus="index">
                            <div class="carousel-item <c:if test="${index.first}">active</c:if>">
                                <a href="<c:url value='/show' ><c:param name='movieId' value='${movie.movieId}' /></c:url>" ontouchstart="">
                                    <div class="movie">
                                        <img src="<c:url value="${paths[movie.title]}"/>">
                                        <div class="movie-info">
                                            <h2><c:out value="${movie.title}"/></h2>
                                            <p><c:out value="${movie.description}"/></p>
                                            <p><c:out value="${movie.duration}"/></p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carousel-tomorrow" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carousel-tomorrow" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>


 <div class="container pt-5 bg-dark text-white">
         <h2>The Day After Tomorrow</h2>
         <div class="row mt-5">
             <div id="carousel-TheDayAfterTomorrow" class="carousel slide" data-bs-ride="carousel">
                 <div class="carousel-inner">
                     <c:forEach var="movie" items="${moviesTheDayAfterTomorrow}" varStatus="index">
                         <div class="carousel-item <c:if test="${index.first}">active</c:if>">
                             <a href="<c:url value='/show' ><c:param name='movieId' value='${movie.movieId}' /></c:url>" ontouchstart="">
                                 <div class="movie">
                                     <img src="<c:url value="${paths[movie.title]}"/>">
                                     <div class="movie-info">
                                         <h2><c:out value="${movie.title}"/></h2>
                                         <p><c:out value="${movie.description}"/></p>
                                         <p><c:out value="${movie.duration}"/></p>
                                     </div>
                                 </div>
                             </a>
                         </div>
                     </c:forEach>
                 </div>
                 <button class="carousel-control-prev" type="button" data-bs-target="#carousel-TheDayAfterTomorrow" data-bs-slide="prev">
                     <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                     <span class="visually-hidden">Previous</span>
                 </button>
                 <button class="carousel-control-next" type="button" data-bs-target="#carousel-TheDayAfterTomorrow" data-bs-slide="next">
                     <span class="carousel-control-next-icon" aria-hidden="true"></span>
                     <span class="visually-hidden">Next</span>
                 </button>
             </div>
         </div>
     </div>


    </div>

    <%@ include file="footer.html"%>
    </body>
    </html>