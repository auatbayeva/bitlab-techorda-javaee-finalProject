<%--
  Created by IntelliJ IDEA.
  User: aibanu_uatbayeva
  Date: 22.01.2025
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%@include file="common/header.jsp"%>

<body class="bg-gradient-primary">

<div class="container">

  <!-- Outer Row -->
  <div class="row justify-content-center">

    <div class="col-xl-6 ">

      <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
          <!-- Nested Row within Card Body -->

              <div class="p-5">
                <div class="text-center">
                  <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                </div>
                <form class="user" action = "login" method="post">
                    <% String emailError = request.getParameter("error");
                if(emailError!=null){ %>
                  <div class="alert alert-danger" role="alert">
                    Email or password error!
                  </div>
                    <%}%>
                  <div class="form-group">
                    <input type="email" class="form-control form-control-user"
                           id="exampleInputEmail" aria-describedby="emailHelp"
                           placeholder="Enter Email Address..." name="email">
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control form-control-user"
                           id="exampleInputPassword" placeholder="Password" name="password">
                  </div>

                  <button class="btn btn-primary btn-user btn-block">
                    Login
                  </button>

                </form>
                <hr>

                <div class="text-center">
                  <a class="small" href="/register">Create an Account!</a>
                </div>
              </div>

        </div>
      </div>

    </div>

  </div>

</div>

<%@include file="common/footer.jsp"%>

</body>

</html>