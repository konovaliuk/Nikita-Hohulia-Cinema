<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>GiGa-Plex</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .card-body {
          border: 1px solid white;
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

        <section class="vh-100 gradient-custom">
          <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
              <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                  <div class="card-body p-5 text-center">

                    <div class="mb-md-5 mt-md-4 pb-5">
                      <form method="POST" action="/Cinema/signup">
                        <h2 class="fw-bold mb-2 text-uppercase">Sing Up</h2>
                        <p class="text-white-50 mb-5">Please fill the form!</p>

                        <div class="form-outline form-white mb-4">
                          <input type="email" id="typeEmailX" class="form-control form-control-lg" name="email" />
                          <label class="form-label" for="typeEmailX">Email</label>
                        </div>

                        <div class="form-outline form-white mb-4">
                          <input type="password" id="typePasswordX" class="form-control form-control-lg" name="password" />
                          <label class="form-label" for="typePasswordX">Password</label>
                        </div>

                        <c:if test="${error != null}">
                            <div class="alert alert-danger" role="alert">
                                    <c:out value="${error}"/>
                            </div>
                        </c:if>

                        <button class="btn btn-outline-light btn-lg px-5" type="submit">Sing Up</button>

                      </form>
                    </div>

                    <div>
                      <p class="mb-0">Do have an account? <a href="<c:url value='/login'/>" class="text-white-50 fw-bold">Login</a></p>
                    </div>

                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>


</div>
    <%@ include file="footer.html"%>
</body>
</html>