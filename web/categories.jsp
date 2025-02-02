<%@ page import="kz.bitlab.javaee.models.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.javaee.models.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<%@include file="common/header.jsp"%>

<body id="page-top">

<% User user = (User) request.getAttribute("user"); %>
<!-- Page Wrapper -->
<div id="wrapper">
     <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3"> Admin page </div>
        </a>
        <!-- Divider -->
        <hr class="sidebar-divider my-0">
        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="/home">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>News</span></a>
        </li>
        <!-- Nav Item - Tables -->
        <li class="nav-item">
            <a class="nav-link" href="/categories">
                <i class="fas fa-fw fa-table"></i>
                <span>Categories</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/users">
                <i class="fas fa-fw fa-table"></i>
                <span>Users</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/comments">
                <i class="fas fa-fw fa-table"></i>
                <span>Comments</span></a>
        </li>
    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=user.getFirstName()%> <%=user.getLastName()%></span>
                            <img class="img-profile rounded-circle"
                                 src="img/undraw_profile.svg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                Settings
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container">
                <!-- Content Row -->
                <div class="row ">

                    <div class="card shadow mb-4 col-lg-8">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Categories</h6>
                        </div>


                        <!-- Modal -->

                        <div class="card-body">
                            <button type="button" class="btn btn-primary mb-4" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                + New category
                            </button>
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" >

                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Details</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
                                     for(Category category : categories ){ %>
                                        <tr>
                                            <td><%=category.getId()%></td>
                                            <td><%=category.getName()%></td>
                                            <td>
                                                <a href="/categoryDetail?id=<%=category.getId()%>" class="btn btn-primary">Details</a>
                                            </td>
                                        </tr>
                                    <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>


                </div>


            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->


    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title fs-5" id="staticBackdropLabel">New category</h4>
            </div>
            <form action="/addCategory" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control form-control-user" id="name" name="name"
                               required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="/logout">Logout</a>
            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp"%>
</body>

</html>