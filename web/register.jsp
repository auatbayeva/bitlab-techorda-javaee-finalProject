<%--
  Created by IntelliJ IDEA.
  User: aibanu_uatbayeva
  Date: 22.01.2025
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%@include file="common/header.jsp"%>

<body class="bg-gradient-primary">

<div class="container">
  <div class="row justify-content-center">
  <div class="card o-hidden border-0 shadow-lg my-5">
    <div class="card-body p-0">
      <!-- Nested Row within Card Body -->

          <div class="p-5">
            <div class="text-center">
              <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
            </div>
            <form class="user" action="/register" method="post">
              <% String emailError = request.getParameter("emailerror");
                if(emailError!=null){ %>
                  <div class="alert alert-danger" role="alert">
                    User with this email already exists!
                  </div>
              <%}
                String passwordError = request.getParameter("passworderror");
                if(passwordError!=null){ %>
                  <div class="alert alert-danger" role="alert">
                    Passwords do not match!
                  </div>
               <%}%>


              <div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                  <input type="text" class="form-control form-control-user" id="exampleFirstName" name="firstName"
                         placeholder="First Name" required>
                </div>
                <div class="col-sm-6">
                  <input type="text" class="form-control form-control-user" id="exampleLastName" name="lastName"
                         placeholder="Last Name">
                </div>
              </div>
              <div class="form-group">
                <input type="email" class="form-control form-control-user" id="exampleInputEmail" name="email"
                       placeholder="Email Address" required>
              </div>
              <div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                  <input type="password" class="form-control form-control-user" name="password"
                         id="exampleInputPassword" placeholder="Password" required>
                </div>
                <div class="col-sm-6">
                  <input type="password" class="form-control form-control-user" name="confirmPassword"
                         id="exampleRepeatPassword" placeholder="Repeat Password" required>
                </div>
              </div>
              <div class="form-group">
                  <label for="role" class="form-label">Role</label>
                  <select class="form-control"   id="role" name="role" required>
                    <option value="2">User</option>
                    <option value="1" >Admin</option>
                  </select>

              </div>

              <button type="submit" class="btn btn-primary btn-user btn-block">
                Register Account
              </button>
            </form>
            <hr>

            <div class="text-center">
              <a class="small" href="/login">Already have an account? Login!</a>
            </div>
          </div>
        </div>
      </div>

  </div>

</div>

<%@include file="common/footer.jsp"%>

</body>

</html>